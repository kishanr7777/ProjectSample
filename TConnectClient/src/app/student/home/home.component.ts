import { Component, OnInit } from '@angular/core';
import { CompanyService } from 'src/app/services/company.service';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

	companyList;
	posts;
	opportunities;
	tab = 0;

	constructor(private company: CompanyService, private user: UserService) { }

	ngOnInit() {
		this.company.getAllCompanyList().subscribe(data => {
			this.companyList = data;
		}, error => {
			console.log(error);
		});
		this.loadPosts();		
		this.loadOpportunity();
	}

	loadPosts(){
		this.user.getPostsOfStudent().subscribe(data => {
			this.posts = data;
		}, error => {
			console.log(error);
		});
	}

	loadOpportunity(){
		this.user.getOpportunityOfStudent().subscribe(data=>{
			this.opportunities = data;
		},error=>{
			console.log(error);
			
		})
	}
}
