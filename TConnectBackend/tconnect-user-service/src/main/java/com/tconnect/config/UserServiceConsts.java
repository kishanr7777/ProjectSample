package com.tconnect.config;

import java.util.Arrays;
import java.util.List;

public interface UserServiceConsts {
	
	public final String MESSAGE_ERROR_PASSWORD_NOT_VALID = "{\"status\":false, \"message\":\"Password should have to follow criteria.\"}";
	public final String MESSAGE_ERROR_EMAIL_NOT_VALID = "{\"status\":false, \"message\":\"Please enter valid email.\"}";
	public final String MESSAGE_ERROR_EMAIL_ALREADY_REGISTER = "{\"status\":false, \"message\":\"Email is already registered.\"}";
	public final String MESSAGE_ERROR_FIRST_NAME_NOT_VALID = "{\"status\":false, \"message\":\"Please enter your first name.\"}";
	public final String MESSAGE_ERROR_LAST_NAME_NOT_VALID = "{\"status\":false, \"message\":\"Please enter your last name.\"}";
	public final String MESSAGE_ERROR_UNIVERSITY_NOT_VALID = "{\"status\":false, \"message\":\"Please enter your university.\"}";
	public final String MESSAGE_ERROR_COMPANY_NOT_VALID = "{\"status\":false, \"message\":\"Please enter company name.\"}";
	public final String MESSAGE_ERROR_COMPANY_ALREADY_REGISTERED = "{\"status\":false, \"message\":\"Company is already registered.\"}";
	public final String MESSAGE_ERROR_LOGIN_TYPE_NOT_VALID = "{\"status\":false, \"message\":\"Login type is not valid.\"}";
	public final String MESSAGE_REGISTRATION_SUCCESSFUL = "{\"status\":true, \"message\":\"Registration successfull.\"}";
	public final String MESSAGE_ACCESS_CODE_NOT_VALID =  "{\"status\":false, \"message\":\"Invalid Passcode.\"}";
	
	public final String PATTERN_PASSWORD = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.!@#$%^&]).{8,}";
	public final String PATTERN_EMAIL = "^[\\d\\w]+(\\.[\\d\\w]+)*@[\\d\\w]+(\\.[\\d\\w]+)*\\.[\\d\\w]{2,}$";
	
	public static final String ALPHANUMERIC = "abcdefghijklmnopqrstuvwxyzABCDFEGHJIKLMNOPQRSTUVWXYZ1234567890";
	public static final int MAX_IMAGE_NAME_LENGTH = 50;
	
	public static final String SAVED_FOLDEER_NAME = "/images/";
	
	public static final List<String> ALLOWED_IMAGE_EXTENSIONS = Arrays.asList(".png", ".jpeg", ".jpg", ".bmp");
	
	public static final String MESSAGE_ERROR_COMPANY_CAN_NOT_BE_UNFOLLOWED = "Company can not be unfollowed";
	public static final String MESSAGE_ERROR_COMPANY_CAN_NOT_BE_FOLLOWED = "Company can not be followed";
	public static final String MESSAGE_COMPANY_FOLLOWED = "Company followed";
	public static final String MESSAGE_COMPANY_UNFOLLOWED = "Company unfollowed";
}
