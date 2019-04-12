import { Component, OnInit, ElementRef } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';

declare var $: any;
@Component({
	selector: 'app-student-profile',
	templateUrl: './student-profile.component.html',
	styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit {

	years = [{ id: 1, name: "1st Year" }, { id: 2, name: "2nd Year" }, { id: 3, name: "3rd Year" }, { id: 4, name: "4th Year" }, { id: 5, name: "5th Year" }]
	degrees = [{ id: 1, name: "Masters" }, { id: 2, name: "Undergraduates" }, { id: 3, name: "Phd" }]
	isNew = true;
	studentProfile: any;
	index: any;
	achievement: any;
	education: any;
	experience: any;
	studentInfo: any;
	skill: any;
	careerGoal;
	isLoaded = false;
	filterUniversities: String[];
	tempuniversities = [];
	universities = [];
	image ;

	constructor(private userService: UserService, private auth: AuthService) {
	}


	ngOnInit() {
		this.studentProfile = this.userService.user;
		this.image = this.studentProfile.profileImageURL;
		this.isLoaded = true;
		this.newFields();
		this.auth.getUniversities().subscribe((data: any) => {
			for (let index = 0; index < data.length; index++) {
				this.tempuniversities.push(data[index].name);
			}
			/*distinct uni array*/
			this.universities = this.tempuniversities.filter((currentValue, index, arrayObject) => arrayObject.indexOf(currentValue) === index);
		});
	}

	_filterUniversities() {
		let uni = this.education.university;
		if (uni.length >= 3) {
			let univalue = uni.toLowerCase();
			this.filterUniversities = this.universities.filter((option: any) => option.toLowerCase().includes(univalue));
		}
	}

	changeProfile() {
		$(document).ready(function () {
			$('#fileInput').trigger('click');
		});
	}

	profileChanged(event) {
		let profilePic: File = event.target.files.item(0);
		console.log(profilePic);
		this.userService.changeProfileImage(profilePic).subscribe(data => {
			this.studentProfile = data;
			this.image = this.studentProfile.profileImageURL;
			this.userService.changeProfile(this.image);
		}, error => {
			console.log(error);
		});
	}

	closeModal() {
		$(document).ready(function () {
			$('.modal').modal('hide');
		});
	}

	newFields() {
		$(document).ready(function () {
			$('form').trigger('reset');
		});
		this.achievement = { title: "", description: "" };
		this.education = {
			university: "",
			courseStudied: "",
			degreeType: "",
			degreeClassification: "",
			startDate: "",
			finishDate: ""
		};
		this.experience = {
			jobPosition: "",
			company: "",
			jobType: "",
			location: "",
			startDate: "",
			finishDate: ""
		};
		this.studentInfo = {
			firstName: "",
			lastName: "",
			currentDegreeName: "",
			currentDegreeYear: "",
		};
		this.careerGoal = "";
		this.skill = "";
		this.isNew = true;
		this.index = -1;
	}

	setIsNew() {
		this.isNew = false
	}

	editProfile() {
		this.studentInfo.firstName = this.studentProfile.firstName;
		this.studentInfo.lastName = this.studentProfile.lastName;
		this.studentInfo.currentDegreeName = this.studentProfile.currentDegreeName;
		this.studentInfo.currentDegreeYear = this.studentProfile.currentDegreeYear;
	}

	saveProfile() {
		this.studentProfile.firstName = this.studentInfo.firstName;
		this.studentProfile.lastName = this.studentInfo.lastName;
		this.studentProfile.currentDegreeName = this.studentInfo.currentDegreeName;
		this.studentProfile.currentDegreeYear = this.studentInfo.currentDegreeYear;
		this.updateUserProfile();
		this.closeModal();

	}

	editCareerGoal() {
		this.careerGoal = this.studentProfile.careerGoal;
	}

	saveCareerGoal() {
		this.studentProfile.careerGoal = this.careerGoal;
		this.updateUserProfile();
		this.closeModal();
	}

	updateUserProfile() {
		this.userService.updateUserProfile(this.studentProfile).subscribe(data => {
			if (data) {
				this.studentProfile = data;
				this.userService.user = data;
			}
		}, error => {
			console.log(error);
		})
		this.newFields();
	}

	deleteSkill(item) {
		let index = this.studentProfile.skills.indexOf(item)
		this.studentProfile.skills.splice(index, 1);
		this.updateUserProfile();
		event.preventDefault();
	}

	addNewSkill() {
		if (this.studentProfile.skills && !this.studentProfile.skills.includes(this.skill)) {
			this.studentProfile.skills.push(this.skill);
		}
		else {
			this.studentProfile.skills = [this.skill];
		}
		this.closeModal();
		this.updateUserProfile();
	}

	addAchievement() {

		if (this.isNew) {
			if (this.studentProfile.achievement)
				this.studentProfile.achievement.push(this.achievement);
			else
				this.studentProfile.achievement = [this.achievement];
		}
		else {
			this.studentProfile.achievement[this.index] = this.achievement;
		}
		this.closeModal();
		this.updateUserProfile();
	}

	deleteAchievement(achievement) {
		this.studentProfile.achievement.splice(this.studentProfile.achievement.indexOf(achievement), 1);
		this.updateUserProfile();
		event.preventDefault();
	}

	editAchievement(achievement) {
		this.isNew = false;
		this.achievement = Object.assign({}, achievement);
		this.index = this.studentProfile.achievement.indexOf(achievement);
	}

	addEducation() {
		if (this.isNew)
			if (this.studentProfile.education)
				this.studentProfile.education.push(this.education);
			else
				this.studentProfile.education = [this.education];
		else {
			this.studentProfile.education[this.index] = this.education;
		}
		this.closeModal();
		this.updateUserProfile();
	}

	deleteEducation(education) {
		this.studentProfile.education.splice(this.studentProfile.education.indexOf(education), 1);
		this.updateUserProfile();
		event.preventDefault();
	}

	editEducation(education) {
		this.isNew = false;
		this.education = Object.assign({}, education);
		this.index = this.studentProfile.education.indexOf(education);
		this.education.startDate = new Date(this.education.startDate);
		this.education.finishDate = new Date(this.education.finishDate);
	}

	addExperience() {
		if (this.isNew)
			if (this.studentProfile.experience)
				this.studentProfile.experience.push(this.experience);
			else
				this.studentProfile.experience = [this.experience];
		else {
			this.studentProfile.experience[this.index] = this.experience;
		}
		this.closeModal();
		this.updateUserProfile();
	}


	deleteExperience(experience) {
		this.studentProfile.experience.splice(this.studentProfile.experience.indexOf(experience), 1);
		this.updateUserProfile();
		event.preventDefault();
	}

	editExperience(experience) {
		this.experience = Object.assign({}, experience)
		this.isNew = false;
		this.index = this.studentProfile.experience.indexOf(experience);
		this.experience.startDate = new Date(experience.startDate)
		this.experience.finishDate = new Date(experience.finishDate)
	}
}
