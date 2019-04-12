import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BusinessComponent } from './business.component';
import { HomeComponent } from './home/home.component';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { LoginComponent } from '../welcome/login/login.component';
import { BusinessGuard } from '../guards/business.guard';
import { AgentProfileComponent } from './agent-profile/agent-profile.component';


const routes: Routes = [
  {
    path:'',
    canActivate:[BusinessGuard],
    component:BusinessComponent,
    children:[
      {
        path:'',
        component:HomeComponent
      },
      {
        path:'companyProfile',
        component:CompanyProfileComponent
      },
      {
        path:'agentProfile',
        component:AgentProfileComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BusinessRoutingModule { }
