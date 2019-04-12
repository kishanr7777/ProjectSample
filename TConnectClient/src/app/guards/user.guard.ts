import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../services/user.service';
import { Constants } from '../constants/Constants';

@Injectable({
	providedIn: 'root'
})
export class UserGuard implements CanActivate {

	constructor(private user: UserService) {
	}

	canActivate(
		next: ActivatedRouteSnapshot,
		state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
		let getData = async () => {
			await this.user.userProfile(localStorage.getItem(Constants.EMAIL))
			return true;
		}
		return getData();
	}
}
