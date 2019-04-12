import { Component, OnInit } from '@angular/core';
import { Constants } from '../constants/Constants';
import { Router, ActivatedRoute } from '@angular/router';
import { StudentModule } from '../student/student.module';
import { BusinessModule } from '../business/business.module';
import { AuthService } from '../services/auth.service';
import { WelcomeModule } from './welcome.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  constructor() {


  }
  ngOnInit() {
 
  }

}
