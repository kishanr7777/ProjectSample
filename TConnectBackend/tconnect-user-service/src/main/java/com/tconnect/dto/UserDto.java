package com.tconnect.dto;

import java.util.regex.Pattern;

import com.tatva.tconnectGeneralConfigs.TconnectConsts;
import com.tconnect.config.UserServiceConsts;

public class UserDto {
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String university;
	private String company;
	private String accessCode;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	@Override
	public String toString() {
		return "UserDto [email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", university=" + university + ", company=" + company + ", accessCode=" + accessCode + "]";
	}
	public String validateUserDto(String type) {
		if (!Pattern.matches(UserServiceConsts.PATTERN_EMAIL, this.email))
			return UserServiceConsts.MESSAGE_ERROR_EMAIL_NOT_VALID;
		if (!Pattern.matches(UserServiceConsts.PATTERN_PASSWORD, this.password))
			return UserServiceConsts.MESSAGE_ERROR_PASSWORD_NOT_VALID;
		if (this.firstName == null || this.firstName.equals(""))
			return UserServiceConsts.MESSAGE_ERROR_FIRST_NAME_NOT_VALID;
		if (this.lastName == null || this.lastName.equals(""))
			return UserServiceConsts.MESSAGE_ERROR_LAST_NAME_NOT_VALID;
		
		if(type.equals(TconnectConsts.ROLE_STUDENT)) {
			if (this.university == null || this.university.equals(""))
				return UserServiceConsts.MESSAGE_ERROR_UNIVERSITY_NOT_VALID;
		} else if(type.equals(TconnectConsts.ROLE_AGENT)){
			if (this.company == null || this.company.equals(""))
				return UserServiceConsts.MESSAGE_ERROR_COMPANY_NOT_VALID;
		} else {
			return UserServiceConsts.MESSAGE_ERROR_LOGIN_TYPE_NOT_VALID;
		}
		return null;
	}
}
