package com.tconnect.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value=JsonInclude.Include.NON_NULL)
public class Experience {
	private String jobPosition;
	private String company;
	private String jobType;
	private String location;
	private Date startDate;
	private Date finishDate;
	
	public String getJobPosition() {
		return jobPosition;
	}
	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
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
		return "Experience [jobPosition=" + jobPosition + ", company=" + company + ", jobType=" + jobType
				+ ", location=" + location + ", startDate=" + startDate + ", finishDate=" + finishDate + "]";
	}
	

}
