import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { CompanyService } from '../services/company.service';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { Constants } from '../constants/Constants';

@Injectable({
  providedIn: 'root'
})
export class BusinessGuard implements CanActivate {

  constructor(private company:CompanyService,private auth:AuthService,private user:UserService){}
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
      let getData = async () => {

        await this.user.userProfile(localStorage.getItem(Constants.EMAIL));
        await this.company.companyProfile(this.user.agent.company);
        return new Promise(function(resolve, reject) {
          setTimeout(() => resolve(1), 1000);
        }).then(function(result) { 
          return true;
        });
      }
      return getData(); 
  }
}
