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

@Document(collection="posts")
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class OpportunityEntity {

	@Id
	@JsonIgnore
	private ObjectId _id;
	private String type;
	private String headline;
	private String jobType;
	private String location;
	private String jobFunction;
	private String description;
	private String url;
	private Date deadline;
	private Date creationDate;
	private boolean isPublic;
	private LinkedHashSet<ObjectId> universities;
	private ArrayList<String> attachments;
	@JsonIgnore
	private ObjectId company;
	@JsonIgnore
	private ObjectId agent;
	
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
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public ArrayList<String> getAttachments() {
		return attachments;
	}
	public void setAttachments(ArrayList<String> attachments) {
		this.attachments = attachments;
	}
	public ObjectId getCompany() {
		return company;
	}
	public void setCompany(ObjectId company) {
		this.company = company;
	}
	public ObjectId getAgent() {
		return agent;
	}
	public void setAgent(ObjectId agent) {
		this.agent = agent;
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
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
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
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public LinkedHashSet<ObjectId> getUniversities() {
		return universities;
	}
	public void setUniversities(LinkedHashSet<ObjectId> universities) {
		this.universities = universities;
	}
	@Override
	public String toString() {
		return "OpportunitiesEntity [_id=" + _id + ", type=" + type + ", headline=" + headline + ", jobType=" + jobType
				+ ", location=" + location + ", jobFunction=" + jobFunction + ", description=" + description + ", url="
				+ url + ", deadline=" + deadline + ", creationDate=" + creationDate + ", isPublic=" + isPublic
				+ ", universities=" + universities + ", attachments=" + attachments + ", company=" + company
				+ ", agent=" + agent + ", companyName=" + companyName + ", agentEmail=" + agentEmail + "]";
	}
}
