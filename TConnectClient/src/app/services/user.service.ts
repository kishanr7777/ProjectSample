import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Constants } from '../constants/Constants';
import { BehaviorSubject } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class UserService {

	url = Constants.URL;
	agent: any = {};
	editAgent: any = {};
	user: any = {};
	private messageSource = new BehaviorSubject("");
	profileImage = this.messageSource.asObservable();

	constructor(private http: HttpClient) { }


	changeProfile(image) {
		this.messageSource.next(image);
	}

	fetchUserProfile(email) {
		let headers = new HttpHeaders().append('Accept', 'application/json');
		return this.http.get(this.url + "user/profile?email=" + email, { headers: headers });
	}

	updateUserProfile(userProfile) {
		let headers = new HttpHeaders().append('Accept', 'application/json');
		return this.http.patch(this.url + "user/profile/update", userProfile, { headers: headers });
	}

	userProfile(email) {
		return new Promise(resolve => {
			this.http.get(Constants.URL + "user/profile?email=" + email).subscribe((data: any) => {
				if (data.type === 'agent') {
					Object.assign(this.agent, data);
					Object.assign(this.editAgent, data);
				} else if (data.type == 'student') {
					this.messageSource.next(data.profileImageURL);
					Object.assign(this.user, data);
				}

				resolve();
			});
		}).catch(error => {
			console.log(error);
		})
	}
	userProfileUpdate() {
		this.http.patch(Constants.URL + "user/profile/update", this.editAgent).subscribe((data: any) => {
			if (data.type === 'agent') {
				this.clearObject();
				Object.assign(this.agent, data);
				Object.assign(this.editAgent, data);
			}
		});
	}

	clearObject() {
		this.agent = {};
		this.editAgent = {};
	}

	changeProfileImage(image: File) {
		let formdata: FormData = new FormData();
		formdata.append('file', image);
		formdata.append('email', localStorage.getItem(Constants.EMAIL));
		console.log(formdata);
		
		return this.http.post(this.url + "user/profile/image", formdata);
	}

	getPostsOfStudent(){
		return this.http.get(this.url+"feed/post/all",{params:{email:localStorage.getItem(Constants.EMAIL)}});
	}

	getOpportunityOfStudent(){
		return this.http.get(this.url+"feed/opportunity/all", {params:{email:localStorage.getItem(Constants.EMAIL)}});
	}

	doComment(commentText, creationTime){
		return this.http.post(this.url+"feed/post/comment/add",{email:localStorage.getItem(Constants.EMAIL),postId:creationTime,text:commentText})
	}

	doReply(replyText, postId, commentId){
		return this.http.post(this.url+"feed/post/comment/reply/add",{email:localStorage.getItem(Constants.EMAIL),postId:postId,text:replyText, commentId:commentId});
	}

	likePost(postId,liked){
		return this.http.post(this.url+"feed/post/like",{email:localStorage.getItem(Constants.EMAIL),postId:postId,like:liked})
	}

	likeComment(postId, commentId, liked){
		return this.http.post(this.url+"feed/post/comment/like",{email:localStorage.getItem(Constants.EMAIL),postId:postId,commentId:commentId,like:liked})
	}

	likeOpportunity(opportunityId, liked){
		return this.http.post(this.url+"feed/opportunity/like",{email:localStorage.getItem(Constants.EMAIL),opportunityId:opportunityId,like:liked});
	}

	commentOpportunity(opportunityId, text){
		return this.http.post(this.url+"feed/opportunity/comment/add",{email:localStorage.getItem(Constants.EMAIL),opportunityId: opportunityId,text:text});
	}

	likeCommentOpportunity(opportunityId, commentId, liked){
		return this.http.post(this.url+"feed/opportunity/comment/like",{email:localStorage.getItem(Constants.EMAIL),opportunityId:opportunityId,commentId:commentId,like:liked})
	}

	doReplyOpportunity(replyText, opportunityId, commentId){
		return this.http.post(this.url+"feed/opportunity/comment/reply/add",{email:localStorage.getItem(Constants.EMAIL),opportunityId:opportunityId,text:replyText, commentId:commentId});
	}

}