package com.tconnect.entity;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Document(collection = "userprofiles")
@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class UserProfileEntity {
	@Id
	@JsonIgnore
	private ObjectId _id;
	private String firstName;
	private String lastName;
	private String email;
	private String type;
	private String currentDegreeYear;
	private String currentDegreeName;
	private String careerGoal;
	private String[] skills;
	private Achievement[] achievement;
	private Education[] education;
	private Experience[] experience;
	private String jobTitle;
	
	@JsonIgnore
	private ObjectId orgId;
	
	@Transient
	private String company;
	
	private String bio;
	private String profileImageURL;
	private String accessCode;
	
	@JsonIgnore
	private LinkedHashSet<ObjectId> followedCompanyIds;
	
//	//TODO remove if not used
//	@Transient
//	private LinkedHashSet<Organisation> followedCompanies;
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCurrentDegreeYear() {
		return currentDegreeYear;
	}
	public void setCurrentDegreeYear(String currentDegreeYear) {
		this.currentDegreeYear = currentDegreeYear;
	}
	public String getCurrentDegreeName() {
		return currentDegreeName;
	}
	public void setCurrentDegreeName(String currentDegreeName) {
		this.currentDegreeName = currentDegreeName;
	}
	public String getCareerGoal() {
		return careerGoal;
	}
	public void setCareerGoal(String careerGoal) {
		this.careerGoal = careerGoal;
	}
	public String[] getSkills() {
		return skills;
	}
	public void setSkills(String[] skills) {
		this.skills = skills;
	}
	public Achievement[] getAchievement() {
		return achievement;
	}
	public void setAchievement(Achievement[] achievement) {
		this.achievement = achievement;
	}
	public Education[] getEducation() {
		return education;
	}
	public void setEducation(Education[] education) {
		this.education = education;
	}
	public Experience[] getExperience() {
		return experience;
	}
	public void setExperience(Experience[] experience) {
		this.experience = experience;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public ObjectId getOrgId() {
		return orgId;
	}
	public void setOrgId(ObjectId orgId) {
		this.orgId = orgId;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	public LinkedHashSet<ObjectId> getFollowedCompanyIds() {
		return followedCompanyIds;
	}
	public void setFollowedCompanyIds(LinkedHashSet<ObjectId> followedCompanyIds) {
		this.followedCompanyIds = followedCompanyIds;
	}
	public String getProfileImageURL() {
		return profileImageURL;
	}
	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}
	@Override
	public String toString() {
		return "UserProfileEntity [_id=" + _id + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", type=" + type + ", currentDegreeYear=" + currentDegreeYear + ", currentDegreeName="
				+ currentDegreeName + ", careerGoal=" + careerGoal + ", skills=" + Arrays.toString(skills)
				+ ", achievement=" + Arrays.toString(achievement) + ", education=" + Arrays.toString(education)
				+ ", experience=" + Arrays.toString(experience) + ", jobTitle=" + jobTitle + ", orgId=" + orgId
				+ ", company=" + company + ", bio=" + bio + ", profileImageURL=" + profileImageURL + ", accessCode="
				+ accessCode + ", followedCompanyIds=" + followedCompanyIds + "]";
	}
}
