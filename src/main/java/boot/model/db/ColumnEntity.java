package boot.model.db;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */

import java.io.Serializable;

/**
 * 列的属性
 * @author danfeng.zhou
 * @version $Id: ColumnEntity.java, v 0.1 2017年4月27日 下午6:15:39 danfeng.zhou Exp $
 */
public class ColumnEntity implements Serializable {
    /**  */
    private static final long serialVersionUID = -1079757960245376235L;
    /** 列名 */
    private String            columnName;
    /** 列名类型 */
    private String            dataType;
    /** jdbc数据库类型 */
    private String            jdbcType;
    /** 列名备注 */
    private String            comments;
    /** 属性名称(第一个字母大写)，如：user_name => UserName */
    private String            attrName;
    /** 属性名称(第一个字母小写)，如：user_name => userName */
    private String            attrname;
    /** 属性类型 */
    private String            attrType;
    /** auto_increment */
    private String            extra;

    /**
     * Getter method for property <tt>columnName</tt>.
     * 
     * @return property value of columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Setter method for property <tt>columnName</tt>.
     * 
     * @param columnName value to be assigned to property columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Getter method for property <tt>dataType</tt>.
     * 
     * @return property value of dataType
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Setter method for property <tt>dataType</tt>.
     * 
     * @param dataType value to be assigned to property dataType
     */
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    /**
     * Getter method for property <tt>jdbcType</tt>.
     * 
     * @return property value of jdbcType
     */
    public String getJdbcType() {
        return jdbcType;
    }

    /**
     * Setter method for property <tt>jdbcType</tt>.
     * 
     * @param jdbcType value to be assigned to property jdbcType
     */
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
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
     * Getter method for property <tt>attrName</tt>.
     * 
     * @return property value of attrName
     */
    public String getAttrName() {
        return attrName;
    }

    /**
     * Setter method for property <tt>attrName</tt>.
     * 
     * @param attrName value to be assigned to property attrName
     */
    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    /**
     * Getter method for property <tt>attrname</tt>.
     * 
     * @return property value of attrname
     */
    public String getAttrname() {
        return attrname;
    }

    /**
     * Setter method for property <tt>attrname</tt>.
     * 
     * @param attrname value to be assigned to property attrname
     */
    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    /**
     * Getter method for property <tt>attrType</tt>.
     * 
     * @return property value of attrType
     */
    public String getAttrType() {
        return attrType;
    }

    /**
     * Setter method for property <tt>attrType</tt>.
     * 
     * @param attrType value to be assigned to property attrType
     */
    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    /**
     * Getter method for property <tt>extra</tt>.
     * 
     * @return property value of extra
     */
    public String getExtra() {
        return extra;
    }

    /**
     * Setter method for property <tt>extra</tt>.
     * 
     * @param extra value to be assigned to property extra
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

}
