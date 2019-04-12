import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Constants } from 'src/app/constants/Constants';

@Component({
	selector: 'app-posts',
	templateUrl: './posts.component.html',
	styleUrls: ['./posts.component.css']
})
export class PostsComponent implements OnInit {

	@Input()
	userType;

	@Input()
	post;

	@Output() reply = new EventEmitter<object>();

	showComments = false;
	commentText;
	images = [];
	videos = [];
	files = [];

	constructor(private user: UserService) {
	}

	ngOnInit() {
		if (this.post.attachments) {
			this.post.attachments.forEach(element => {
				if (this.isPic(element)) {
					this.images.push(element);
				} else if (this.isVideo(element)) {
					this.videos.push(element);
				}else{
					this.files.push(element);
				}
			});
		}
	}

	isPic(fileName) {
		let x = fileName.split('').reverse().join('').split('.')[0].split('').reverse().join('').toLowerCase();
		if (x === "png" || x === "jpg" || x === "jpeg" || x === "gif" || x === "ico" || x === "bmp") return true;
		return false
	}
	isVideo(fileName) {
		let x = fileName.toString().split('').reverse().join('').split('.')[0].split('').reverse().join('').toLowerCase();
		if (x === "mp4" || x === "ogg" || x === "ogv" || x === "3gp" || x === "flv" || x === "mkv") return true;
		return false;
	}

	doComment() {
		this.user.doComment(this.commentText, this.post.creationTime).subscribe(data => {
			this.commentText = "";
			this.showComments = true;
			console.log(this.post);
			if (this.post.commentList)
				this.post.commentList.push(data);
			else
				this.post.commentList = [data];
		}, error => {
			console.log(error);
		});
	}

	likePost(liked) {
		this.user.likePost(this.post.creationTime, !liked).subscribe(data => {
			this.post.likedByCurrentUser = !liked;
		}, error => {
			console.log(error);
		})
	}

	updateReply(event) {
		this.reply.emit(event);
	}

}
