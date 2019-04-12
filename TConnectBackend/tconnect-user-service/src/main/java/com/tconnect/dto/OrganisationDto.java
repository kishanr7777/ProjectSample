package com.tconnect.dto;

import java.util.Arrays;

import com.tconnect.entity.Project;

public class OrganisationDto {
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
	private String accessCode;
	
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
	
	public String[] getSector() {
		return sector;
	}
	public void setSector(String[] sector) {
		this.sector = sector;
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
		return "OrganisationDto [name=" + name + ", type=" + type + ", size=" + size + ", sector="
				+ Arrays.toString(sector) + ", location=" + location + ", website=" + website + ", bio=" + bio
				+ ", mission=" + mission + ", project=" + Arrays.toString(project) + ", orgType=" + orgType
				+ ", workingHere=" + workingHere + ", accessCode=" + accessCode + "]";
	}
	
}
