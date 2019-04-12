import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { StudentModule } from 'src/app/student/student.module';
import { BusinessModule } from 'src/app/business/business.module';
import { Constants } from 'src/app/constants/Constants';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  disabled: boolean = true;
  loginError = "";
  passwordChangeMessage:string='';
  constructor(private auth: AuthService, private router: Router) { }

  ngOnInit() {
    if(localStorage.getItem(Constants.PASSWORD_CHANGED)!=null)
    {
      this.passwordChangeMessage=localStorage.getItem(Constants.PASSWORD_CHANGED);
    }
    this.loginForm = new FormGroup({
      email: new FormControl("", Validators.compose([
        Validators.required,
        Validators.pattern('^[\\d\\w]+(\\.[\\d\\w]+)*@[\\d\\w]+(\\.[\\d\\w]+)*\\.[\\d\\w]{2,}$')
      ])),
      password: new FormControl("", Validators.compose([Validators.required]))
    },
    );
  }
  ngAfterContentInit()
  {
    localStorage.removeItem(Constants.PASSWORD_CHANGED);
  }

  login() {
    this.loginError = "";
    if (this.loginForm.valid) {
      this.auth.verifyUser(this.loginForm.value.email, this.loginForm.value.password).subscribe(data => {
        if (data[Constants.TYPE] === Constants.STATUS_STUDENT) {
          localStorage.setItem(Constants.TYPE,Constants.STATUS_STUDENT);
          this.router.resetConfig([
            { path: '', loadChildren: () => StudentModule }
          ]);
        }
        else if (data[Constants.TYPE] === Constants.STATUS_AGENT) {
          localStorage.setItem(Constants.TYPE,Constants.STATUS_AGENT);
          this.router.resetConfig([
            { path: '', loadChildren: () => BusinessModule }
          ]);
          
        }
        if(data[Constants.TOKEN])
          localStorage.setItem(Constants.TOKEN, data[Constants.TOKEN]);
          localStorage.setItem(Constants.EMAIL,this.loginForm.controls.email.value);
		  console.log(localStorage.getItem(Constants.EMAIL));
        this.router.navigateByUrl('');
      }, error => {
        this.loginError = "Username or Password does not match";
      });
    }
  }
}
