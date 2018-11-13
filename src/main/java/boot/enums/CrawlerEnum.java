package boot.enums;

public enum CrawlerEnum {
	DJ("dj", "京东"),
	GSNY("gsny", "工商能源"),
	TB("tb", "淘宝"), YMX("ymx", "亚马逊"), WB("wb", "微博"),JYM("jym", "交易猫"),
    ZH("zh", "知乎");  
    // 成员变量  
    private String code;  
    private String name;  
    // 构造方法  
    private CrawlerEnum(String code, String name) {  
        this.code = code;  
        this.name = name;  
    }  
    // 普通方法  
    public static String getName(String index) {  
        for (CrawlerEnum c : CrawlerEnum.values()) {  
            if (c.getCode().equals(index) ) {  
                return c.name;  
            }  
        }  
        return null;  
    }  
    // get set 方法  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}  
}
