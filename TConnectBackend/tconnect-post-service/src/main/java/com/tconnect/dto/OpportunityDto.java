package com.tconnect.dto;

import java.util.Date;
import java.util.LinkedHashSet;

import com.tconnect.configs.PostServiceConsts;
import com.tconnect.entity.ResponseStatus;

public class OpportunityDto {
	private String headline;
	private String jobType;
	private String location;
	private String jobFunction;
	private String description;
	private String url;
	private Date deadline;
	private boolean isPublic;
	private LinkedHashSet<String> universities;
	private String companyName;
	private String agentEmail;

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getHeadline() {
		return headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobFunction() {
		return jobFunction;
	}

	public void setJobFunction(String jobFunction) {
		this.jobFunction = jobFunction;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public LinkedHashSet<String> getUniversities() {
		return universities;
	}

	public void setUniversities(LinkedHashSet<String> universities) {
		this.universities = universities;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAgentEmail() {
		return agentEmail;
	}

	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	@Override
	public String toString() {
		return "OpportunityDto [headline=" + headline + ", jobType=" + jobType + ", location=" + location
				+ ", jobFunction=" + jobFunction + ", description=" + description + ", url=" + url + ", deadline="
				+ deadline + ", isPublic=" + isPublic + ", universities=" + universities + ", companyName="
				+ companyName + ", agentEmail=" + agentEmail + "]";
	}
	
	public ResponseStatus Validate() {
		
		if (this.headline == null || this.headline.equals("") || this.jobType == null || this.jobType.equals("")
				|| this.location == null || this.location.equals("") || this.jobFunction == null
				|| this.jobFunction.equals("") || this.description == null || this.description.equals("")
				|| this.url == null || this.url.equals("") || this.companyName == null || this.companyName.equals("")
				|| this.agentEmail == null || this.agentEmail.equals(""))
			return new ResponseStatus(false, PostServiceConsts.MESSAGE_ERROR_NULL_FIELD);
		
		if (this.deadline.compareTo(new Date(System.currentTimeMillis())) < 0)
			return new ResponseStatus(false, PostServiceConsts.MESSAGE_ERROR_NULL_FIELD);
		return null;
	}

}
