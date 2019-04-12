package com.tconnect.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "posts")
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class PostEntity {
	@Id
	@JsonIgnore
	private ObjectId postId;
	private String content;
	private LinkedHashSet<ObjectId> universities;
	private ArrayList<String> attachments;
	private Date creationDate;
	private String type;
	private boolean isPublic;
	
	@JsonIgnore
	private ObjectId companyId;
	@JsonIgnore
	private ObjectId agentId;
	
	@Transient
	private String companyName;
	@Transient
	private String agentEmail;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public ObjectId getPostId() {
		return postId;
	}
	public void setPostId(ObjectId postId) {
		this.postId = postId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LinkedHashSet<ObjectId> getUniversities() {
		return universities;
	}
	public void setUniversities(LinkedHashSet<ObjectId> universities) {
		this.universities = universities;
	}
	public ArrayList<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(ArrayList<String> attachments) {
		this.attachments = attachments;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public ObjectId getCompanyId() {
		return companyId;
	}
	public void setCompanyId(ObjectId companyId) {
		this.companyId = companyId;
	}
	public ObjectId getAgentId() {
		return agentId;
	}
	public void setAgentId(ObjectId agentId) {
		this.agentId = agentId;
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
		return "PostEntity [postId=" + postId + ", content=" + content + ", universities=" + universities
				+ ", attachments=" + attachments + ", creationDate=" + creationDate + ", type=" + type + ", isPublic="
				+ isPublic + ", companyId=" + companyId + ", agentId=" + agentId + ", companyName=" + companyName
				+ ", agentEmail=" + agentEmail + "]";
	}
}
