import { Component, OnInit, Input, Sanitizer } from '@angular/core';

@Component({
	selector: 'app-avatar',
	templateUrl: './avatar.component.html',
	styleUrls: ['./avatar.component.css']
})
export class AvatarComponent implements OnInit {

	@Input()
	height;

	@Input()
	width;

	@Input()
	profileUrl;

	@Input()
	type;	

	constructor() { }

	ngOnInit() {
	}

}
