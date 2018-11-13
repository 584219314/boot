package boot.shedule.model.res;

import java.io.Serializable;
import java.util.Map;

public class LoginRes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4290977408974382885L;
	private Map<String,String> cookies;
	public Map<String, String> getCookies() {
		return cookies;
	}
	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}
}
