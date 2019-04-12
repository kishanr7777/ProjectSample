import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanLoad, Route, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
import { AuthService } from '../services/auth.service';
import { Constants } from '../constants/Constants';
import { StudentModule } from '../student/student.module';
import { BusinessModule } from '../business/business.module';
import { WelcomeModule } from '../welcome/welcome.module';

@Injectable({
	providedIn: 'root'
})
export class AuthGuard implements CanLoad {

	constructor(private auth: AuthService, private router: Router) { }

	canLoad(route: Route): Observable<boolean> | Promise<boolean> | boolean {
		let checkAuthentication = async () => {

			await this.auth.checkAuthenticated();
			if (localStorage.getItem(Constants.TYPE) === Constants.STATUS_STUDENT) {
				route.loadChildren = () => StudentModule
				// return false;
			}
			else if (localStorage.getItem(Constants.TYPE) === Constants.STATUS_AGENT) {
				route.loadChildren = () => BusinessModule
				//return false;
			}
			else {
				route.loadChildren = () => WelcomeModule
			}

			return true;
		}
		return checkAuthentication();
	}
}
