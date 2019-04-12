import { Component, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Constants } from 'src/app/constants/Constants';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {

  resetPasswordForm: FormGroup;
  token: string = '';
  changePasswordMessage='';

  constructor(private authService: AuthService, private activeRoute: ActivatedRoute,private router:Router) { }

  ngOnInit() {
    this.activeRoute.queryParams.subscribe(params => {
      this.token = params[Constants.TOKEN];
    })
    this.resetPasswordForm = new FormGroup({
      password: new FormControl("", Validators.compose([Validators.required, Validators.pattern(Constants.PASSWORD_PATTERN), Validators.minLength(8), Validators.maxLength(16)])),
      repassword: new FormControl("", Validators.compose([Validators.required]))
    });
  }

  changePassword() {
    this.authService.changePassword(this.resetPasswordForm.controls.password.value, this.token).subscribe(data => {
      if(data[Constants.STATUS]==="Password Changed Successfully.")
      {
        localStorage.setItem(Constants.PASSWORD_CHANGED,"Password Changed Successfully");
        this.router.navigateByUrl("login");
      }
      else
      {
        this.changePasswordMessage=data[Constants.STATUS];
      }
    })
  }
}
