import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { WelcomeModule } from './welcome/welcome.module';

const routes: Routes = [
  {
    path:'',
    canLoad:[AuthGuard],
    loadChildren : () => WelcomeModule
  },
  {
    path:'**',
    redirectTo:''
  }
  // {
  //   path:'student',
  //   loadChildren:'./student/student.module#StudentModule'
  // },
  // {
  //   path:'business',
  //   loadChildren:'./business/business.module#BusinessModule'
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
}
