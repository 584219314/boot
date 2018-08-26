package boot.util;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import boot.enums.DbTypeEnum;
import boot.enums.GeneratorEnum;
import boot.enums.YesNoEnum;
import boot.exception.BizException;
import boot.model.db.ColumnEntity;
import boot.model.db.JdbcModel;
import boot.model.db.TableEntity;
import boot.model.vo.db.GeneratorTableInfoVO;


/**
 * 代码生成工具类
 * @author danfeng.zhou
 * @version $Id: GenUtil.java, v 0.1 2017年4月27日 下午6:08:27 danfeng.zhou Exp $
 */
public class GenUtil {
    //数据库类型枚举
    private static final DbTypeEnum DB_TYPE_ENUM = DbTypeEnum.MYSQL;

    /**
     * 模版列表
     * @return
     */
    private static List<String> getTemplates(String createPage) {
        List<String> templates = new ArrayList<String>();
        //数据库-表 映射实体对象
        templates.add("Entity.java.vm");
        //DAO
        templates.add("Mapper.java.vm");
        //Mapper
        templates.add("Mapper.xml.vm");
        //Service服务
        templates.add("Service.java.vm");
        //控制层，页面
        if (StringUtil.equals(YesNoEnum.Y.getValue(), createPage)) {
            templates.add("template/Controller.java.vm");
        }
        return templates;
    }

    /**
     * 生成代码
     * @param generatorTableInfoVO
     * @param columns
     * @param zip
     * @param createPage  Y:生成controller和页面  N：只生成到service层
     */
    private static void generator(GeneratorTableInfoVO generatorTableInfoVO, List<Map<String, Object>> columns, ZipOutputStream zip, String systemCode, String tablePrefix, String createPage) {
        //配置信息
        Configuration config = getConfig();
        //表信息
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(generatorTableInfoVO.getTableName());
        tableEntity.setComments(generatorTableInfoVO.getRemark());
        //表名转换成Java类名
        String className = tableToJava(tableEntity.getTableName(), tablePrefix);
        tableEntity.setClassName(className);
        tableEntity.setClassname(StringUtils.uncapitalize(className));

        boolean hasBigDecimal = false;
        boolean hasDate = false;

        //列信息
        List<ColumnEntity> columsList = new ArrayList<ColumnEntity>();
        for (Map<String, Object> column : columns) {
            ColumnEntity columnEntity = new ColumnEntity();
            columnEntity.setColumnName(ObjectUtil.toString(column.get("columnName")));
            columnEntity.setDataType(ObjectUtil.toString(column.get("dataType")));
            columnEntity.setComments(ObjectUtil.toString(column.get("columnComment")));
            columnEntity.setExtra(ObjectUtil.toString(column.get("extra")));
            //列名转换成Java属性名
            String attrName = columnToJava(columnEntity.getColumnName());
            columnEntity.setAttrName(attrName);
            columnEntity.setAttrname(StringUtils.uncapitalize(attrName));

            //列的数据类型，转换成Java类型
            String attrType = config.getString(columnEntity.getDataType(), "unknowType-unknowType");
            String[] attrTypes = attrType.split("-");
            columnEntity.setAttrType(attrTypes[0]);
            if (attrTypes.length > 1) {
                columnEntity.setJdbcType(attrTypes[1]);
            }
            if (StringUtil.equalsIgnoreCase(columnEntity.getAttrType(), "BigDecimal")) {
                hasBigDecimal = true;
            }
            if (StringUtil.equalsIgnoreCase(columnEntity.getAttrType(), "Date")) {
                hasDate = true;
            }
            //是否主键
            if ("PRI".equalsIgnoreCase(ObjectUtil.toString(column.get("columnKey"))) && tableEntity.getPk() != null) {
                tableEntity.setPk(columnEntity);
            }
            columsList.add(columnEntity);
        }
        tableEntity.setColumns(columsList);

        //没主键，则第一个字段为主键
        if (tableEntity.getPk() == null) {
            tableEntity.setPk(tableEntity.getColumns().get(0));
        }

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        //封装模板数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", tableEntity.getTableName());
        map.put("comments", tableEntity.getComments());
        map.put("pk", tableEntity.getPk());
        map.put("className", tableEntity.getClassName());
        map.put("classname", tableEntity.getClassname());
        map.put("pathName", tableEntity.getClassname().toLowerCase());
        map.put("columns", tableEntity.getColumns());
        map.put("package", config.getString("package"));
        map.put("author", config.getString("author"));
        map.put("datetime", DateUtil.format(new Date(), DateUtil.CHINESE_DATE_TIME));
        map.put("year", DateUtil.getCurrentDateTime().substring(0, 4));
        map.put("systemCode", systemCode);
        map.put("hasBigDecimal", hasBigDecimal);
        map.put("hasDate", hasDate);
        String prefix = tablePrefix.replaceAll("xc_", "");
        if (StringUtil.isNotBlank(prefix)) {
            prefix = prefix.substring(0, prefix.length() - 1);
        }
        map.put("prefix", prefix);
        map.put("seqName", tableEntity.getTableName().substring(3).toUpperCase() + "_SEQ");
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates(createPage);
        for (String template : templates) {
            try {
                //渲染模板
                StringWriter sw = new StringWriter();
                Template tpl = Velocity.getTemplate(template, "UTF-8");
                tpl.merge(context, sw);
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, tableEntity.getClassName(), tableEntity.getClassname(), config.getString("package"), systemCode, prefix)));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new BizException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            } catch (Exception e) {
                throw new BizException("渲染模板失败，表名：" + tableEntity.getTableName(), e);
            }
        }
    }

    /**
     * 列名转换成Java属性名
     * @param columnName 列名
     * @return
     */
    private static String columnToJava(String columnName) {
        if (columnName.contains("_")) {
            return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
        }
        return columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
    }

    /**
     * 表名转换成Java类名
     * @param tableName 表名
     * @param tablePrefix 表前缀
     * @return
     */
    private static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     * @return
     */
    private static Configuration getConfig() {
        try {
            return new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new BizException("获取自动生成代码generator.properties配置文件失败", e);
        }
    }

    /**
     * 获取文件名
     * @param template
     * @param className
     * @param classname
     * @param packageName
     * @param systemCode
     * @param prefix
     * @return
     */
    private static String getFileName(String template, String className, String classname, String packageName, String systemCode, String prefix) {
        String packagePath = "";
        if (StringUtils.isNotBlank(packageName)) {
            packagePath = packageName.replace(".", File.separator) + File.separator;
        }
        //数据库-表 映射实体对象
        if (template.contains(GeneratorEnum.MODEL.getValue())) {
            return packagePath + systemCode + File.separator + "model" + File.separator + className + ".java";
        }
        //Mapper 数据库操作层
        if (template.contains(GeneratorEnum.MAPPER.getValue())) {
            return packagePath + systemCode + File.separator + "mapper" + File.separator + className + "Mapper.java";
        }
        //Mapper xml
        if (template.contains(GeneratorEnum.MODELXML.getValue())) {
            return "mybatis" + File.separator + "mapper" + File.separator + className + "Mapper.xml";
        }
        //Service服务
        if (template.contains(GeneratorEnum.SERVICE.getValue())) {
            return packagePath + systemCode + File.separator + "service" + File.separator + className + "Service.java";
        }
        //控制层
        if (template.contains(GeneratorEnum.CONTROLLER.getValue())) {
            return packagePath + systemCode + File.separator + "web" + File.separator + "controller" + File.separator + className + "Controller.java";
        }
        return null;
    }

    /**
     * 查询表信息
     * @param databaseName
     * @param dbTypeEnum
     * @param tableName
     * @return
     */
    private static GeneratorTableInfoVO queryTable(DbTypeEnum dbTypeEnum, String tableName) {
        Connection conn = ConnectDbUtil.getConnection(dbTypeEnum);
        String databaseName = getDatabaseName(dbTypeEnum);
        QueryRunner qr = new QueryRunner();
        String qrySql = "";
        if (dbTypeEnum == DbTypeEnum.MYSQL) {
            qrySql = "select table_name tableName, engine, table_comment remark, create_time createTime,table_collation tableCollation from information_schema.tables where table_schema='"
                     + databaseName + "' and table_name='" + tableName + "'";
        } else {
            throw new BizException(BizException.PARAM_ERROR.getCode(), "暂时不支持此数据库类型");
        }
        GeneratorTableInfoVO generatorTableInfoVO = null;
        try {
            generatorTableInfoVO = qr.query(conn, qrySql, new BeanHandler<GeneratorTableInfoVO>(GeneratorTableInfoVO.class));
        } catch (SQLException e) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), "执行sql语句:" + qrySql + "出现异常", e);
        }
        DbUtils.closeQuietly(conn);
        return generatorTableInfoVO;
    }

    /**
     * 根据url地址获取数据库实例
     * @param dbTypeEnum
     * @param databaseName
     * @return
     */
    private static String getDatabaseName(DbTypeEnum dbTypeEnum) {
        String url = JdbcModel.url;
        if (dbTypeEnum == DbTypeEnum.MYSQL) {
            String dbUrl = url.substring(0, url.indexOf("?"));
            String dbName = url.substring(dbUrl.lastIndexOf("/") + 1, dbUrl.length());
            return dbName;
        } else {
            throw new BizException(BizException.SYSTEM_ERROR.getCode(), "暂时不支持非mysql数据库的代码生成");
        }
    }

    /**
     * 查询当前表的列信息
     * @param dbTypeEnum
     * @param tableName
     * @return
     */
    private static List<Map<String, Object>> queryColumns(DbTypeEnum dbTypeEnum, String tableName) {
        Connection conn = ConnectDbUtil.getConnection(dbTypeEnum);
        String databaseName = getDatabaseName(dbTypeEnum);
        QueryRunner qr = new QueryRunner();
        String qrySql = "";
        if (dbTypeEnum == DbTypeEnum.MYSQL) {
            qrySql = "select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_schema='" + databaseName
                     + "' and table_name='" + tableName + "'";
        } else {
            throw new BizException(BizException.PARAM_ERROR.getCode(), "暂时不支持此数据库类型");
        }
        List<Map<String, Object>> result = null;
        try {
            result = qr.query(conn, qrySql, new MapListHandler());
        } catch (SQLException e) {
            throw new BizException(BizException.PARAM_ERROR.getCode(), "执行sql语句:" + qrySql + "出现异常", e);
        }
        DbUtils.closeQuietly(conn);
        return result;
    }

    public static byte[] generatorCode(DbTypeEnum dbTypeEnum, String[] tableNames, String systemCode, String tablePrefix, String isCreatePage) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            //查询表信息
            GeneratorTableInfoVO generatorTableInfoVO = queryTable(dbTypeEnum, tableName);
            //查询列信息
            List<Map<String, Object>> columns = queryColumns(dbTypeEnum, tableName);
            //生成代码
            generator(generatorTableInfoVO, columns, zip, systemCode, tablePrefix, isCreatePage);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /** 
     * 根据byte数组，生成文件 
     */
    public static void createFile(byte[] bfile, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {//判断文件目录是否存在  
                dir.mkdirs();
            }
            file = new File(filePath + "\\" + fileName + ".zip");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        //表前缀
        String tablePrefix = "";
        String[] tableNames = new String[] { "crawler_data" };
        //系统代码
        String systemCode = "shedule";
        //生成文件路径
        String filePath = "D:\\temp";
        //是否生成控制器
        String isCreatePage = YesNoEnum.N.getValue();

        //        String isCreatePage = YesNoEnum.Y.getValue();
        //生成文件
        byte[] bfile = GenUtil.generatorCode(DB_TYPE_ENUM, tableNames, systemCode, tablePrefix, isCreatePage);
        createFile(bfile, filePath, systemCode);
    }

}
