import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { WelcomeComponent } from './welcome.component';
import { WelcomeRoutingModule} from './welcome-routing.module';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { MatInputModule, MatAutocompleteModule, MatSelectModule } from '@angular/material';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { AgentSignUpComponent } from './agent-sign-up/agent-sign-up.component';
import { SharedComponentModule } from '../shared-component/shared-component.module';

@NgModule({
  declarations: [WelcomeComponent, HomeComponent, LoginComponent, ForgotPasswordComponent, NewPasswordComponent, AgentSignUpComponent],
  imports: [
    CommonModule,
    WelcomeRoutingModule,
    SharedComponentModule,
    ReactiveFormsModule,
    FormsModule,
    MatInputModule,
    MatAutocompleteModule,
    MatSelectModule
  ],
  exports:[]
  
})
export class WelcomeModule { }
