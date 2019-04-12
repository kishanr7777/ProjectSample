import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WelcomeComponent } from './welcome.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { NewPasswordComponent } from './new-password/new-password.component';
import { AgentSignUpComponent } from './agent-sign-up/agent-sign-up.component';

const routes: Routes = [
  {
    path:'',
    component:WelcomeComponent,
    children:[
        {
            path:'',
            component:HomeComponent
        },
        {
            path:'login',
            component:LoginComponent
        },
        {
            path:'forgotPassword',
            component:ForgotPasswordComponent
        },
        {
            path:'newPassword',
            component:NewPasswordComponent
        },
        {
            path:'agentsignup/:name',
            component:AgentSignUpComponent
        }
    ],
}
        
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WelcomeRoutingModule { }
