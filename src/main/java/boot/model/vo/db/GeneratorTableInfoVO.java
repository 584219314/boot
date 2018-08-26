package boot.model.vo.db;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.io.Serializable;

/**
 * 代码生成表基本信息
 * @author danfeng.zhou
 * @version $Id: GeneratorTableInfoVO.java, v 0.1 2017年6月19日 下午5:39:29 danfeng.zhou Exp $
 */
public class GeneratorTableInfoVO implements Serializable {
    /**  */
    private static final long serialVersionUID = 4216476820117896091L;

    /** 表名 */
    private String            tableName;
    /** engine引擎 */
    private String            engine;
    /** 表的字符校验编码集 */
    private String            tableCollation;
    /** 备注 */
    private String            remark;
    /** 创建时间 */
    private String            createTime;
    /** 数据库名称 */
    private String            databaseName;

    /**
     * Getter method for property <tt>tableName</tt>.
     * 
     * @return property value of tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Setter method for property <tt>tableName</tt>.
     * 
     * @param tableName value to be assigned to property tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Getter method for property <tt>engine</tt>.
     * 
     * @return property value of engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * Setter method for property <tt>engine</tt>.
     * 
     * @param engine value to be assigned to property engine
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * Getter method for property <tt>tableCollation</tt>.
     * 
     * @return property value of tableCollation
     */
    public String getTableCollation() {
        return tableCollation;
    }

    /**
     * Setter method for property <tt>tableCollation</tt>.
     * 
     * @param tableCollation value to be assigned to property tableCollation
     */
    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    /**
     * Getter method for property <tt>remark</tt>.
     * 
     * @return property value of remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * Setter method for property <tt>remark</tt>.
     * 
     * @param remark value to be assigned to property remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * Getter method for property <tt>createTime</tt>.
     * 
     * @return property value of createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * Setter method for property <tt>createTime</tt>.
     * 
     * @param createTime value to be assigned to property createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * Getter method for property <tt>databaseName</tt>.
     * 
     * @return property value of databaseName
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * Setter method for property <tt>databaseName</tt>.
     * 
     * @param databaseName value to be assigned to property databaseName
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

}
