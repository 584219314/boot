package boot.enums;

public enum CodeEnum {
	utf8("utf-8","中文编码");
	private String value;
	
	private String msg;
	
	private CodeEnum(String value,String msg) {
		this.value = value;
		this.msg = msg;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
