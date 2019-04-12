import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Constants } from 'src/app/constants/Constants';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  email : FormControl;
  emailMessage='';
  constructor(private auth:AuthService, private router: Router) { }

  ngOnInit() {
    
    this.email = new FormControl("",Validators.compose([
      Validators.required,
      Validators.pattern(Constants.EMAIL_PATTERN)
    ]));
  }
  forgotPassword()
  {
    this.auth.forgotPassword(this.email.value).subscribe(data=>{
      // remove below line when email server is ready.
      if (data[Constants.STATUS] == "Email sent successfully."){
        this.router.navigateByUrl("newPassword?token=" + data[Constants.TOKEN]);
      }
      else{
        this.emailMessage = data[Constants.STATUS];
        }
    });
  }
}
