package boot.model.db;

import boot.constants.PublicConstants;

public class JdbcModel {
	public static String url = PublicConstants.DB_CONFIG.get("spring.datasource.url");
	
	public static String userName = PublicConstants.DB_CONFIG.get("spring.datasource.username");
	
	public static String password = PublicConstants.DB_CONFIG.get("spring.datasource.password");

}
