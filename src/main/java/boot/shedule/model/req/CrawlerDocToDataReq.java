package boot.shedule.model.req;

import java.io.Serializable;

public class CrawlerDocToDataReq implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1671270567995793499L;
	private String code;
	
	private String html;
	
	public String getHtml() {
		return html;
	}
	public void setHtml(String html) {
		this.html = html;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
