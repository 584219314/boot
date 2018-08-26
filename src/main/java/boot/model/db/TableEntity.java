package boot.model.db;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.io.Serializable;
import java.util.List;

/**
 * 表数据
 * @author danfeng.zhou
 * @version $Id: TableEntity.java, v 0.1 2017年4月27日 下午6:15:31 danfeng.zhou Exp $
 */
public class TableEntity implements Serializable {

    /**  */
    private static final long  serialVersionUID = 6775400809910904359L;

    /** 表的名称 */
    private String             tableName;
    /** 表的备注 */
    private String             comments;
    /** 表的主键 */
    private ColumnEntity       pk;
    /** 表的列名(不包含主键) */
    private List<ColumnEntity> columns;
    /** 类名(第一个字母大写)，如：sys_user => SysUser */
    private String             className;
    /** 类名(第一个字母小写)，如：sys_user => sysUser */
    private String             classname;

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
     * Getter method for property <tt>comments</tt>.
     * 
     * @return property value of comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Setter method for property <tt>comments</tt>.
     * 
     * @param comments value to be assigned to property comments
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Getter method for property <tt>pk</tt>.
     * 
     * @return property value of pk
     */
    public ColumnEntity getPk() {
        return pk;
    }

    /**
     * Setter method for property <tt>pk</tt>.
     * 
     * @param pk value to be assigned to property pk
     */
    public void setPk(ColumnEntity pk) {
        this.pk = pk;
    }

    /**
     * Getter method for property <tt>columns</tt>.
     * 
     * @return property value of columns
     */
    public List<ColumnEntity> getColumns() {
        return columns;
    }

    /**
     * Setter method for property <tt>columns</tt>.
     * 
     * @param columns value to be assigned to property columns
     */
    public void setColumns(List<ColumnEntity> columns) {
        this.columns = columns;
    }

    /**
     * Getter method for property <tt>className</tt>.
     * 
     * @return property value of className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Setter method for property <tt>className</tt>.
     * 
     * @param className value to be assigned to property className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Getter method for property <tt>classname</tt>.
     * 
     * @return property value of classname
     */
    public String getClassname() {
        return classname;
    }

    /**
     * Setter method for property <tt>classname</tt>.
     * 
     * @param classname value to be assigned to property classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

}
