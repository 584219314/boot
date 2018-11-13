package boot.shedule.model.req;

import java.io.Serializable;
import java.util.List;

import boot.shedule.model.CrawlerData;



public class CrawlerSetDataReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7449089125905390898L;
	
	private String code;
	private String price;
	private List<CrawlerData> list;
	
	public List<CrawlerData> getList() {
		return list;
	}

	public void setList(List<CrawlerData> list) {
		this.list = list;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
