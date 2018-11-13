package boot.shedule.model.req;

import java.io.Serializable;

public class MainReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7876790346628638159L;
	
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
