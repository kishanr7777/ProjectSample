import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { FormControl, FormGroup, Validator, Validators } from '@angular/forms';
import { debounceTime, startWith } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { Constants } from 'src/app/constants/Constants';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	studentForm: FormGroup;
	agentForm: FormGroup;
	isRegistered = false;
	messageRegistered = "";
	failedMessage = "";
	filterUniversities: String[];
	tempuniversities = [];
	universities = [];

	constructor(private auth: AuthService, private router: Router, private user: UserService) { }

	ngOnInit() {
		this.newForm();
		this.auth.getUniversities().subscribe((data: any) => {
			for (let index = 0; index < data.length; index++) {
				this.tempuniversities.push(data[index].name);
			}
			/*distinct uni array*/
			this.universities = this.tempuniversities.filter((currentValue, index, arrayObject) => arrayObject.indexOf(currentValue) === index);
		});
		this.studentForm.controls.university.valueChanges.pipe(debounceTime(400)).subscribe(value => {
			if (value != null) {
				this.filterUniversities = this._filterUniversities(value);
			}
		})
	}

	private _filterUniversities(uni: string): String[] {
		if (uni.length >= 3) {
			let univalue = uni.toLowerCase();
			return this.universities.filter((option: any) => option.toLowerCase().includes(univalue));
		}
	}

	newForm() {
		this.studentForm = new FormGroup({
			firstName: new FormControl("", Validators.compose([
				Validators.required,
				Validators.minLength(3),
				Validators.maxLength(20)
			])),
			lastName: new FormControl("", Validators.compose([
				Validators.required,
				Validators.minLength(3),
				Validators.maxLength(20)
			])),
			email: new FormControl("", Validators.compose([
				Validators.required,
				Validators.pattern(Constants.EMAIL_PATTERN)

			])),
			password: new FormControl("", Validators.compose([
				Validators.required,
				Validators.minLength(8),
				Validators.maxLength(16),
				Validators.pattern(Constants.PASSWORD_PATTERN)
			])),
			university: new FormControl(""),
			confirmpassword: new FormControl("", Validators.required)

		});
		this.agentForm = new FormGroup({
			firstName: new FormControl("", Validators.compose([
				Validators.required,
				Validators.minLength(3),
				Validators.maxLength(20)
			])),
			lastName: new FormControl("", Validators.compose([
				Validators.required,
				Validators.minLength(3),
				Validators.maxLength(20)
			])),
			email: new FormControl("", Validators.compose([
				Validators.required,
				Validators.pattern(Constants.EMAIL_PATTERN)

			])),
			password: new FormControl("", Validators.compose([
				Validators.required,
				Validators.minLength(8),
				Validators.maxLength(16),
				Validators.pattern(Constants.PASSWORD_PATTERN)
			])),
			company: new FormControl("", Validators.required),
			confirmpassword: new FormControl("", Validators.required)
		});
	}

	registerStudent() {
		this.isRegistered = false;
		this.messageRegistered = "";
		this.failedMessage = "";
		if (this.studentForm.valid) {
			this.auth.registerStudent(this.studentForm.value).subscribe(data => {
				if (data[Constants.STATUS] == true) {
					this.isRegistered = true;
					this.messageRegistered = "Registration Successful!!!"
					this.studentForm.reset();
				} else {
					this.messageRegistered = "Registration failed!!!"
					this.failedMessage = data[Constants.MESSAGE]
				}
			});
		}
	}

	registerAgent() {
		this.isRegistered = false;
		this.messageRegistered = "";
		this.failedMessage = "";
		if (this.agentForm.valid)
			this.auth.registerAgent(this.agentForm.value).subscribe(data => {
				if (data[Constants.STATUS] == true) {
					this.isRegistered = true;
					this.messageRegistered = "Registration Successful!!!"
					this.agentForm.reset();
				} else {
					this.messageRegistered = "Registration failed!!! "
					this.failedMessage = data[Constants.MESSAGE];
				}
			});
	}
}
