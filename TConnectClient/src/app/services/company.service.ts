import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Constants } from '../constants/Constants';

@Injectable({
	providedIn: 'root'
})
export class CompanyService {

	companyObject: any = {};
	editCompanyObject: any = {};
	constructor(private http: HttpClient) { }

	companyProfile(name) {
		new Promise(resolve => {
			this.http.get(Constants.URL + "org/profile/company?name=" + name).subscribe(data => {
				Object.assign(this.companyObject, data);
				Object.assign(this.editCompanyObject, data);
				console.log(data);
				resolve();
			})
		}).catch(error => {
			console.log(error);
		})
	}

	companyProfileForStudent(name) {
		return this.http.get(Constants.URL + "user/view/company",{params:{name:name,email:localStorage.getItem(Constants.EMAIL)}});
	}

	companyProfileUpdate() {
		this.http.patch(Constants.URL + "org/profile/company/update", this.editCompanyObject).subscribe(data => {
			this.clearObjects();
			Object.assign(this.companyObject, data);
			Object.assign(this.editCompanyObject, data);
		});
	}
	clearObjects() {
		this.companyObject = {};
		this.editCompanyObject = {};
	}
	changeProfileImage(image: File) {
		let formdata: FormData = new FormData();
		formdata.append('file', image);
		formdata.append('name', this.companyObject.name);
		this.http.post(Constants.URL + "org/profile/company/image", formdata).subscribe(data => {
			this.clearObjects();
			Object.assign(this.companyObject, data);
			Object.assign(this.editCompanyObject, data);
		});
	}
	getAllCompanyList() {
		return this.http.get(Constants.URL + "user/explore",{params:{email:localStorage.getItem(Constants.EMAIL)}});
	}

	followCompany(name, followed){
		let data = {email:localStorage.getItem(Constants.EMAIL), companyName:name, follow:followed};
		return this.http.post(Constants.URL + "user/company/follow",data);
	}
	getCompanyProfileByUnauthorizeUser(name)
	{
		return this.http.get(Constants.URL +"org/company/profile?name="+name);
	}
	writePost(writePostFormData)
	{
		return this.http.post(Constants.URL+ "feed/post/add",writePostFormData );
	}
	getPostByCompanyName(companyName)
	{
		return this.http.get(Constants.URL+"feed/post/company",{params:{name:companyName, email:localStorage.getItem(Constants.EMAIL)}});
	}
	getOppByCompanyName(companyName)
	{
		return this.http.get(Constants.URL+"feed/opportunity/company",{params:{name:companyName, email:localStorage.getItem(Constants.EMAIL)}});
	}
	getPostByAgentEmail(email)
	{
		return this.http.get(Constants.URL+"feed/post/agent?email="+email);
	}
	getOppsByAgentEmail(email)
	{
		return this.http.get(Constants.URL+"feed/opportunity/agent?email="+email);
	}
	writeOpp(writePostFormData)
	{
		return this.http.post(Constants.URL + "feed/opportunity/add",writePostFormData);
	}
	
}
