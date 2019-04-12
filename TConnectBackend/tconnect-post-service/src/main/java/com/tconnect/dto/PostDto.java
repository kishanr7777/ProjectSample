package com.tconnect.dto;

import java.util.LinkedHashSet;

public class PostDto {
	private String content;
	private LinkedHashSet<String> universities;
	
	private String companyName;
	private String agentEmail;
	private boolean isPublic;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LinkedHashSet<String> getUniversities() {
		return universities;
	}
	public void setUniversities(LinkedHashSet<String> universities) {
		this.universities = universities;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
		return "PostDto [content=" + content + ", universities=" + universities
				+ ", companyName=" + companyName + ", agentEmail=" + agentEmail + ", isPublic=" + isPublic + "]";
	}
	
}
