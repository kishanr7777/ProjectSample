import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StudentRoutingModule } from './student-routing.module';
import { StudentComponent } from './student.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SharedComponentModule } from '../shared-component/shared-component.module';
import { StudentProfileComponent } from './student-profile/student-profile.component';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule} from '@angular/material/form-field';
import { RouterModule } from '@angular/router';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatTabsModule } from '@angular/material';
import { CKEditorModule } from 'ng2-ckeditor';
import { MatInputModule, MatAutocompleteModule, MatSelectModule } from '@angular/material';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { ExploreComponent } from './explore/explore.component';

@NgModule({
	declarations: [StudentComponent, HeaderComponent, HomeComponent, StudentProfileComponent, CompanyProfileComponent, ExploreComponent],
	imports: [
		CommonModule,
		StudentRoutingModule,
		SharedComponentModule,
		RouterModule,
		MatTabsModule,
		MatFormFieldModule,
		MatDatepickerModule,
    	MatNativeDateModule,
		FormsModule,
		CKEditorModule,
		MatInputModule,
		MatAutocompleteModule,
		MatSelectModule
	],
	exports:[StudentProfileComponent]
})
export class StudentModule { }
