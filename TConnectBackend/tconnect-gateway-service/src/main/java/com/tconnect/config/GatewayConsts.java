package com.tconnect.config;

public interface GatewayConsts {
	public final static String STATUS_LOGGED_IN = "logged in";
	public final static String STATUS_LOGGED_OUT = "logged out";
	public final static String STATUS_UNAUTHORIZED = "unauthorized";
	public final static String STATUS_FAILED = "failed";
	
	public static final String ANGULAR_ADDRESS = "192.168.0.114:4200";
	public static final String HOST_SMTP = "192.168.0.6"; 
	public static final int PORT_SMTP = 25;
	public static final String MAIL_ADDRESS = "nayan.patel@internal.mail";
	public static final String MAIL_PASSWORD = "tatva123";
	
	public static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDFEGHJIKLMNOPQRSTUVWXYZ1234567890";
	public static final int MAX_TOKEN_LENGTH = 50;
	
	public static final int MAX_RESET_PASSWORD_LINK_ACTIVE_TIME = 600000;
	
	public final static String PATTERN_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!@#$%^&]).{8,}";
	public final static String STATUS_PASSWORD_NOT_VALID = "Password should have to follow criteria.";
	public final static String STATUS_EMAIL_NOT_REGISTERED = "Email is not registered.";
	public final static String STATUS_LINK_EXPIRED = "Reset Password link expired.";
	public final static String STATUS_INVALID_TOKEN = STATUS_LINK_EXPIRED;
	public final static String STATUS_PASSWORD_RESET_SUCCESSFUL = "Password Changed Successfully.";
	public final static String STATUS_PASSWORD_RESET_EMAIL_SENT_SUCCESS = "Email sent successfully.";
	public final static String STATUS_PASSWORD_RESET_EMAIL_SENT_FAILURE = "Email can't be sent";
	
	public final static String[] ALLOWED_ORIGINS = {
			"*"
	};
	
	public static boolean checkForAllowedOrigin(String origin) {
		for(String o : ALLOWED_ORIGINS) {
			if(o.equals("*"))
				return true;
			
			if(o.equals(origin))
				return true;
		}
		
		return false;
	}
	
}
