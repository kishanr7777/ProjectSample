import { Component, OnInit } from '@angular/core';
import { WelcomeModule } from 'src/app/welcome/welcome.module';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { Constants } from 'src/app/constants/Constants';
import { UserService } from 'src/app/services/user.service';
import { useAnimation } from '@angular/animations';
import { CompanyService } from 'src/app/services/company.service';

@Component({
	selector: 'app-header',
	templateUrl: './header.component.html',
	styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {


	constructor(private auth: AuthService, private router: Router, private user: UserService,private company:CompanyService) {

	}

	ngOnInit() {

	}

	logout() {
		this.auth.logout().subscribe(data => {
			if (data[Constants.STATUS] === Constants.STATUS_LOGED_OUT) {
				this.router.resetConfig([
					{ path: '', loadChildren: () => WelcomeModule }
				])
				localStorage.removeItem(Constants.TYPE);
				localStorage.removeItem(Constants.TOKEN);
				localStorage.removeItem(Constants.EMAIL);
				this.router.navigateByUrl('login');
			}
		}, error => {
			console.log(error);
		});
	}

}
