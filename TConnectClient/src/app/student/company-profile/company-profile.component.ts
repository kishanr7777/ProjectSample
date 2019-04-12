import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { ActivatedRoute } from '@angular/router';

@Component({
	selector: 'app-company-profile',
	templateUrl: './company-profile.component.html',
	styleUrls: ['./company-profile.component.css']
})
export class CompanyProfileComponent implements OnInit {

	companyObject;
	companyPosts;
	companyOpportunity;
	constructor(private company: CompanyService, private route: ActivatedRoute) { }

	ngOnInit() {
		this.company.companyProfileForStudent(this.route.snapshot.params.name).subscribe(data => {
			this.companyObject = data;
			
			this.company.getPostByCompanyName(this.companyObject.name).subscribe(data => {
				this.companyPosts = data;
			}, error => {
				console.log(error);
			});

			this.company.getOppByCompanyName(this.companyObject.name).subscribe(data => {
				this.companyOpportunity = data;
			}, error => {
				console.log(error);
			});
		}, error => {
			console.log(error);
		});

	}

	followCompany(company, followed) {
		this.company.followCompany(company.name, followed).subscribe(data => {
			company.followed = followed;
		}, error => {
			console.log(error);
		});
	}

}
