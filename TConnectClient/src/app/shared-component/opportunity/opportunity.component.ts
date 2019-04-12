import { Component, OnInit, Input, Inject } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Constants } from 'src/app/constants/Constants';
import { Router } from '@angular/router';
import { DOCUMENT } from '@angular/common';

@Component({
	selector: 'app-opportunity',
	templateUrl: './opportunity.component.html',
	styleUrls: ['./opportunity.component.css']
})
export class OpportunityComponent implements OnInit {

	@Input()
	userType;

	@Input()
	opportunity;

	showComments = false;
	commentText;

	constructor(private user: UserService, @Inject(DOCUMENT) private document: any) { }

	ngOnInit() {
	}

	navigate(url) {
		if (url.search('http') == -1) {
			window.open("http://" + url, "_blank");
		}
		else {
			window.open(url, "_blank");
		}
	}

	doLike(liked) {
		this.user.likeOpportunity(this.opportunity.creationTime, !liked).subscribe(data => {
			this.opportunity.likedByCurrentUser = !liked;
		}, error => {
			console.log(error);

		})
	}

	doComment() {
		this.user.commentOpportunity(this.opportunity.creationTime, this.commentText).subscribe(data => {
			this.commentText = "";
			if (this.opportunity.commentList)
				this.opportunity.commentList.push(data);
			else
				this.opportunity.commentList = [data];
		}, error => {
			console.log(error);

		})
	}

}
