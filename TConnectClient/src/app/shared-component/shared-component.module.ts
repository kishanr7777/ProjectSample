import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostsComponent } from './posts/posts.component';
import { RouterModule } from '@angular/router';
import { AvatarComponent } from './avatar/avatar.component';
import { OpportunityComponent } from './opportunity/opportunity.component';
import { CommentListComponent } from './comment-list/comment-list.component';
import { FormsModule } from '@angular/forms';
import { ReplyListComponent } from './reply-list/reply-list.component';
import { MomentModule } from 'ngx-moment';


@NgModule({
  declarations: [PostsComponent, AvatarComponent, OpportunityComponent, CommentListComponent, ReplyListComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    MomentModule
  ],
  exports: [
    PostsComponent,
    AvatarComponent,
    OpportunityComponent
  ]
})
export class SharedComponentModule { }
