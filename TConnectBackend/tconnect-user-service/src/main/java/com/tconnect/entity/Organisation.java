package com.tconnect.entity;

import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "userprofiles")
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class Organisation {
	@Id
	@JsonIgnore
	private ObjectId _id;
	private String name;
	private String type;
	private String size;
	private String[] sector;
	private String location;
	private String website;
	private String bio;
	private String mission;
	private Project[] project;
	private String orgType;
	private String workingHere;
	private String profileImageURL;
	private String accessCode;
	private int totalOpportunities;
	
	@Transient
	private boolean followed;
	
	
	public int getTotalOpportunities() {
		return totalOpportunities;
	}
	public void setTotalOpportunities(int totalOpportunities) {
		this.totalOpportunities = totalOpportunities;
	}
	public boolean isFollowed() {
		return followed;
	}
	public void setFollowed(boolean followed) {
		this.followed = followed;
	}
	public String getProfileImageURL() {
		return profileImageURL;
	}
	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}
		
	public String getWorkingHere() {
		return workingHere;
	}
	public void setWorkingHere(String workingHere) {
		this.workingHere = workingHere;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getMission() {
		return mission;
	}
	public void setMission(String mission) {
		this.mission = mission;
	}	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String[] getSector() {
		return sector;
	}
	public void setSector(String[] sector) {
		this.sector = sector;
	}
	public Project[] getProject() {
		return project;
	}
	public void setProject(Project[] project) {
		this.project = project;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	@Override
	public String toString() {
		return "Organisation [_id=" + _id + ", name=" + name + ", type=" + type + ", size=" + size + ", sector="
				+ Arrays.toString(sector) + ", location=" + location + ", website=" + website + ", bio=" + bio
				+ ", mission=" + mission + ", project=" + Arrays.toString(project) + ", orgType=" + orgType
				+ ", workingHere=" + workingHere + ", profileImageURL=" + profileImageURL + ", accessCode=" + accessCode
				+ ", totalOpportunities=" + totalOpportunities + ", followed=" + followed + "]";
	}
	
}
