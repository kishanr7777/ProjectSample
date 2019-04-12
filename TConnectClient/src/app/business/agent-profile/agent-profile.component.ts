import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { FormGroup,FormControl, Validators} from '@angular/forms';

declare var $:any;
@Component({
  selector: 'app-agent-profile',
  templateUrl: './agent-profile.component.html',
  styleUrls: ['./agent-profile.component.css']
})
export class AgentProfileComponent implements OnInit {

  agent:FormGroup;

  constructor(private user:UserService) { }

  ngOnInit() {
      this.agentForm();
  }
  agentForm()
  {
            this.agent=new FormGroup({
              firstName: new FormControl(this.user.editAgent.firstName,Validators.required),
              lastName : new FormControl(this.user.editAgent.lastName,Validators.required),
              jobTitle : new FormControl(this.user.editAgent.jobTitle,Validators.required),
              company: new FormControl({value:this.user.editAgent.company,disabled:true}),
            });
  }
  compareAgentForm()
  {
    if(this.user.agent.firstName == this.agent.controls.firstName.value && this.user.agent.lastName == this.agent.controls.lastName.value && this.user.agent.jobTitle == this.agent.controls.jobTitle.value)
        return true;
    if(!this.agent.valid)
        return true;
  }
  editAgentForm()
  {
    Object.assign(this.user.editAgent,this.agent.value);
    this.user.userProfileUpdate();
    this.agentForm();
    this.closeModal();
  }
  resetEditAgentForm()
  {
   this.agentForm();
  }
  editAgent()
  {
    this.user.userProfileUpdate();
  }
  compareAgent()
  {
    return JSON.stringify(this.user.agent)==JSON.stringify(this.user.editAgent);
  }
  resetEditAgent()
  {
      this.user.editAgent={};
      Object.assign(this.user.editAgent,this.user.agent);
  }
  changeProfile() {
		$(document).ready(function () {
			$('#fileInput').trigger('click');
		});
	}

	profileChanged(event) {
		let profilePic: File = event.target.files.item(0);
		this.user.changeProfileImage(profilePic).subscribe(data => {
      this.user.agent=data;
      this.user.editAgent=data;
      console.log(this.user.agent);
			//this.user.changeProfile(this.user.editAgent.profileImageURL);
		}, error => {
			console.log(error);
		});
	}
  closeModal()
  {
    $(document).ready(function () {
      $('.modal').modal('hide');
     });
  }
}
