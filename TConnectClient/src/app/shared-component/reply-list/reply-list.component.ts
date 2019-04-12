import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-reply-list',
	templateUrl: './reply-list.component.html',
	styleUrls: ['./reply-list.component.css']
})
export class ReplyListComponent implements OnInit {

	@Input()
	replies;

	@Input()
	postId;

	@Input()
	commentId;

	@Input()
	type;

	@Output() reply = new EventEmitter<object>();

	showInput = false;
	replyText;

	constructor(private user: UserService) { }

	ngOnInit() {
	}

	doReply() {
		console.log(this.type);
		
		if (this.type == 'post') {
			this.user.doReply(this.replyText, this.postId, this.commentId).subscribe(data => {
				let rep = {data: data, commentId: this.commentId };
				this.replyText = "";
				this.reply.emit(rep);
			}, error => {
				console.log(error);
			});
		}else if (this.type == 'opportunity'){
			this.user.doReplyOpportunity(this.replyText, this.postId, this.commentId).subscribe(data => {
				let rep = {data: data, commentId: this.commentId};
				this.replyText = "";
				this.reply.emit(rep);
			}, error=>{
				console.log(error);
			})
		}
	}

}
