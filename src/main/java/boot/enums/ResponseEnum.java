package boot.enums;

public enum ResponseEnum {
	ok("200","成功"),
	rate_limit("401","次数受限"),
	error("500","系统错误"),;
	
	private String code;
	private String msg;
	private ResponseEnum(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	
}
