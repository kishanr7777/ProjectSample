import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-comment-list',
	templateUrl: './comment-list.component.html',
	styleUrls: ['./comment-list.component.css']
})
export class CommentListComponent implements OnInit {

	@Input()
	comments;

	@Input()
	postId;

	@Input()
	type;

	showReply = -1;

	constructor(private user: UserService) { }

	ngOnInit() {
	}

	showReplyFunction(i) {
		if (this.showReply == i)
			this.showReply = -1;
		else
			this.showReply = i;
	}

	likeComment(comment) {
		if (this.type == "post") {
			this.user.likeComment(this.postId, comment.statement.creationTime, !comment.likedByCurrentUser).subscribe(data => {
				if (comment.likedByCurrentUser) {
					comment.likedByCurrentUser = !comment.likedByCurrentUser;
				}
				else {
					comment.likedByCurrentUser = !comment.likedByCurrentUser;
				}
			})
		} else if (this.type == "opportunity") {
			this.user.likeCommentOpportunity(this.postId, comment.statement.creationTime, !comment.likedByCurrentUser).subscribe(data => {
				if (comment.likedByCurrentUser) {
					comment.likedByCurrentUser = !comment.likedByCurrentUser;
				}
				else {
					comment.likedByCurrentUser = !comment.likedByCurrentUser;
				}
			})
		}
	}

	updateReplies(event) {
			for (var i = 0; i < this.comments.length; i++) {
				if (this.comments[i].statement.creationTime == event.commentId) {
					if (this.comments[i].replies)
						this.comments[i].replies.push(event.data)
					else
						this.comments[i].replies = [event.data];
				}
			}
	}
}
