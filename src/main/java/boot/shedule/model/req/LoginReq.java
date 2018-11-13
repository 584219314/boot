package boot.shedule.model.req;

import java.io.Serializable;

public class LoginReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1153693289602274080L;
	private String code;
	private String loginName;
	private String password;
	private String url;
	private String verificationCode;
	private String loginNameHtml;
	private String passwordHtml;
	private String verificationCodeHtml;
	public String getLoginNameHtml() {
		return loginNameHtml;
	}
	public void setLoginNameHtml(String loginNameHtml) {
		this.loginNameHtml = loginNameHtml;
	}
	public String getPasswordHtml() {
		return passwordHtml;
	}
	public void setPasswordHtml(String passwordHtml) {
		this.passwordHtml = passwordHtml;
	}
	public String getVerificationCodeHtml() {
		return verificationCodeHtml;
	}
	public void setVerificationCodeHtml(String verificationCodeHtml) {
		this.verificationCodeHtml = verificationCodeHtml;
	}
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
