package com.tconnect.entity;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class Education {
	
	@JsonIgnore
	private ObjectId universityId;
	
	@Transient
	private String university;
	
	public ObjectId getUniversityId() {
		return universityId;
	}
	public void setUniversityId(ObjectId universityId) {
		this.universityId = universityId;
	}
	private String courseStudied;
	private String degreeType;
	private String degreeClassification;
	private Date startDate;
	private Date finishDate;

	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getCourseStudied() {
		return courseStudied;
	}
	public void setCourseStudied(String courseStudied) {
		this.courseStudied = courseStudied;
	}
	public String getDegreeType() {
		return degreeType;
	}
	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}
	public String getDegreeClassification() {
		return degreeClassification;
	}
	public void setDegreeClassification(String degreeClassification) {
		this.degreeClassification = degreeClassification;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	@Override
	public String toString() {
		return "Education [universityId=" + universityId + ", university=" + university + ", courseStudied="
				+ courseStudied + ", degreeType=" + degreeType + ", degreeClassification=" + degreeClassification
				+ ", startDate=" + startDate + ", finishDate=" + finishDate + "]";
	}
}
