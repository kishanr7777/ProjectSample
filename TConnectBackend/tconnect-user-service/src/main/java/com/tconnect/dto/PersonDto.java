package com.tconnect.dto;

import java.util.Arrays;

import com.tconnect.entity.Achievement;
import com.tconnect.entity.Education;
import com.tconnect.entity.Experience;

public class PersonDto {
	private String firstName;
	private String lastName;
	private String email;
	private String currentDegreeYear;
	private String currentDegreeName;
	private String careerGoal;
	private String[] skills;
	private Achievement[] achievement;
	private Education[] education;
	private Experience[] experience;
	private String jobTitle;
	private String company;
	private String bio;
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
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public PersonDto(String firstName, String lastName, String email, String currentDegreeYear,
			String currentDegreeName, String careerGoal, String[] skills, Achievement[] achievement,
			Education[] education, Experience[] experience, String jobTitle, String company, String bio,
			String accessCode) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.currentDegreeYear = currentDegreeYear;
		this.currentDegreeName = currentDegreeName;
		this.careerGoal = careerGoal;
		this.skills = skills;
		this.achievement = achievement;
		this.education = education;
		this.experience = experience;
		this.jobTitle = jobTitle;
		this.company = company;
		this.bio = bio;
		this.accessCode = accessCode;
	}
	public PersonDto() {
		super();
	}
	@Override
	public String toString() {
		return "PersonDto [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", currentDegreeYear=" + currentDegreeYear + ", currentDegreeName=" + currentDegreeName
				+ ", careerGoal=" + careerGoal + ", skills=" + Arrays.toString(skills) + ", achievement="
				+ Arrays.toString(achievement) + ", education=" + Arrays.toString(education) + ", experience="
				+ Arrays.toString(experience) + ", jobTitle=" + jobTitle + ", company=" + company + ", bio=" + bio
				+ ", accessCode=" + accessCode + "]";
	}
}
