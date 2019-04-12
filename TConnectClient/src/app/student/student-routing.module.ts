import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentComponent } from './student.component';
import { HomeComponent } from './home/home.component';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { UserGuard } from '../guards/user.guard';
import { ExploreComponent } from './explore/explore.component';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { BusinessGuard } from '../guards/business.guard';

const routes: Routes = [
	{
		path: '',
		canActivate:[UserGuard],
		component: StudentComponent,
		children: [
			{
				path: '',
				component: HomeComponent
				// component:ExploreComponent
			},
			{
				path: 'studentProfile',
				component: StudentProfileComponent,
			},
			{
				path:'explore',
				component:ExploreComponent
			},
			{
				path:'companyProfile/:name',
				component:CompanyProfileComponent
			}

		],

	}
];

@NgModule({
	imports: [RouterModule.forChild(routes)],
	exports: [RouterModule]
})
export class StudentRoutingModule { }
