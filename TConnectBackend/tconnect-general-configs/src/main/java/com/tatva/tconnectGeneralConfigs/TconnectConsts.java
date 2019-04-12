package com.tatva.tconnectGeneralConfigs;

public interface TconnectConsts {
	public static String DB_NAME = "tconnectdb";
	public static String DB_HOST = "192.168.0.22";
	public static String DB_PORT = "27017";
	public static String PROPERTY_FILE_PATH = "src\\main\\resources\\application.properties";
	public final static String[] ALLOWED_HOSTS = { 
		"192.168.0.140",
		"192.168.0.22",
		"127.0.0.1" 
	};
	public final static String ERROR_UNAUTHORIZED_HOST_RESUEST = "{\"status\":\"unauthorized\"}";
	
	public static String ROLE_STUDENT = "student";
	public static String ROLE_AGENT = "agent";
	public static String ROLE_COMPANY = "company";
	public static String ROLE_UNIVERSITY = "university";
	
	public static String TCONNECT_HTTP_PROTOCOL = "http://";
	public static String TCONNECT_GATEWAY_ADDRESS = "192.168.0.22:10001";
	
	
}
