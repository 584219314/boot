package boot.enums;

/**
 * xcwlkj.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */


/**
 * 是否枚举
 * @author danfeng.zhou
 * @version $Id: YesNoEnum.java, v 0.1 2018年4月10日 下午4:29:41 danfeng.zhou Exp $
 */
public enum YesNoEnum  {
    /** 是 */
    Y("Y", "是"),
    /** 否 */
    N("N", "否"),;
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
    private YesNoEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 
     * @param value
     * @return {@link YesNoEnum} 实例
     */
    public static YesNoEnum find(String value) {
        if (null == value) {
            return null;
        }
        for (YesNoEnum yesNoEnum : YesNoEnum.values()) {
            if (yesNoEnum.getValue().equals(value)) {
                return yesNoEnum;
            }
        }
        return null;
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
