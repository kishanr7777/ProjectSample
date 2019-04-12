import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';

@Component({
	selector: 'app-explore',
	templateUrl: './explore.component.html',
	styleUrls: ['./explore.component.css']
})
export class ExploreComponent implements OnInit {

	companyList;
	constructor(private company: CompanyService) { }

	ngOnInit() {
		this.company.getAllCompanyList().subscribe(data => {
			this.companyList = data
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
