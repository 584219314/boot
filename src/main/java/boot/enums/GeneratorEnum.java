package boot.enums;

public enum GeneratorEnum {
    /** MAPPER */
    MAPPER("MAPPER", "Mapper.java.vm"),
    /** SERVICE */
    SERVICE("SERVICE", "Service.java.vm"),
    /** MODEL */
    MODEL("MODEL", "Entity.java.vm"),
    /** MODELXML */
    MODELXML("MODELXML", "Mapper.xml.vm"),
    /** CONTROLLER */
    CONTROLLER("CONTROLLER", "Controller.java.vm"),;
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
    private GeneratorEnum(String value, String label) {
        this.value = value;
        this.label = label;
    }

    /**
     * 
     * @param value
     * @return {@link YesNoEnum} 实例
     */
    public static GeneratorEnum find(String value) {
        if (null == value) {
            return null;
        }
        for (GeneratorEnum generatorEnum : GeneratorEnum.values()) {
            if (generatorEnum.getValue().equals(value)) {
                return generatorEnum;
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
