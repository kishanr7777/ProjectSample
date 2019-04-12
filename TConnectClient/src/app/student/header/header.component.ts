import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { WelcomeModule } from 'src/app/welcome/welcome.module';
import { Constants } from 'src/app/constants/Constants';
import { UserService } from 'src/app/services/user.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

	// image = this.userService.user.profileImageURL;

	image: string;
	name;

	constructor(private userService: UserService, private auth: AuthService, private router: Router) { }


	ngOnInit() {
		this.userService.profileImage.subscribe(image => this.image = image);
		if (this.userService.user.firstName)
			this.name = this.userService.user.firstName;
		else
			this.name = "student";
	}

	logout() {
		this.auth.logout().subscribe(data => {
			if (data[Constants.STATUS] === Constants.STATUS_LOGED_OUT) {
				this.router.resetConfig([
					{ path: '', loadChildren: () => WelcomeModule }
				])
				localStorage.removeItem(Constants.TYPE);
				localStorage.removeItem(Constants.TOKEN);
				this.router.navigateByUrl('/login');
			}
		});
	}

}
