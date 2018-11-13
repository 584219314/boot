package boot.shedule.model.res;

import java.io.Serializable;

public class FlipRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8068479139016852050L;
	private String url;
	private int page;
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
