import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CompanyService } from 'src/app/services/company.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Constants } from 'src/app/constants/Constants';

@Component({
  selector: 'app-agent-sign-up',
  templateUrl: './agent-sign-up.component.html',
  styleUrls: ['./agent-sign-up.component.css']
})
export class AgentSignUpComponent implements OnInit {

  profileImageURL;
  companyName;
  agentForm:FormGroup;
  messageRegistered;
	failedMessage;
  isRegistered = false;

  constructor(private route:ActivatedRoute,private company:CompanyService,private auth:AuthService,private router:Router) { }

  ngOnInit() {
    this.companyName=this.route.snapshot.params.name;
    this.company.getCompanyProfileByUnauthorizeUser(this.companyName).subscribe((data:any)=>{
      this.profileImageURL=data.profileImageURL;
    });

    this.agentForm=new FormGroup({
      firstName:new FormControl("",Validators.compose([
				Validators.required,
				Validators.minLength(3),
				Validators.maxLength(20)
			])),
      lastName:new FormControl("",Validators.compose([
				Validators.required,
				Validators.minLength(3),
				Validators.maxLength(20)
			])),
      email:new FormControl("",Validators.compose([
				Validators.required,
				Validators.pattern(Constants.EMAIL_PATTERN)
			])),
      password:new FormControl("",Validators.compose([
				Validators.required,
				Validators.minLength(8),
				Validators.maxLength(16),
				Validators.pattern(Constants.PASSWORD_PATTERN)
			])),
      confirmPassword:new FormControl("",Validators.required),
      accessCode:new FormControl("",Validators.required),
      company:new FormControl(this.companyName),
    });
  }
  registerAgent()
  {
    this.messageRegistered = "";
		this.failedMessage = "";
    this.auth.manageAgent(this.agentForm.value).subscribe((data)=>{
      if (data[Constants.STATUS] == true) {
        this.isRegistered = true;
        this.messageRegistered = "Registration Successful!!!"
        this.agentForm.reset();
      } else {
        this.messageRegistered = "Registration failed!!!"
        this.failedMessage = data[Constants.MESSAGE]
      }
    },
    error=>{
      console.log(error);
    })
  }

}
