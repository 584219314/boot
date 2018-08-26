package boot.enums;

import boot.exception.BizException;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2017 All Rights Reserved.
 */

/**
 * 数据库类型枚举
 * @author danfeng.zhou
 * @version $Id: DbTypeEnum.java, v 0.1 2017年6月19日 下午4:01:52 danfeng.zhou Exp $
 */
public enum DbTypeEnum {
    /** MySQL数据库 */
    MYSQL("mysql", "MySQL数据库"),
    /** Oracle数据库 */
    ORACLE("oracle", "Oracle数据库"),
    /** db2数据库 */
    DB2("db2", "DB2数据库");

    /** 字典的值 */
    private String value;

    /** 字典的文本 */
    private String label;

    /**
     * 私有构造方法
     * 
     * @param value
     * @param label
     */
    private DbTypeEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 根据数据库实例代码查询数据库类型
     * @param value
     * @return {@link DbTypeEnum} 实例
     */
    public static DbTypeEnum find(String value) {
        for (DbTypeEnum dbTypeEnum : DbTypeEnum.values()) {
            if (dbTypeEnum.getValue().equals(value)) {
                return dbTypeEnum;
            }
        }
        throw new BizException(BizException.PARAM_ERROR.getCode(), "根据value=" + value + "获取数据库类型失败");
    }

    /** 
     * @see tf56.smartlogis.base.utils.dict.Dict#getValue()
     */
    public String getValue() {
        return value;
    }

    /** 
     * @see tf56.smartlogis.base.utils.dict.Dict#getLabel()
     */
    public String getLabel() {
        return label;
    }

}
