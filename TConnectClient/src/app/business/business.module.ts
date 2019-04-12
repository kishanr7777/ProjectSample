import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule} from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule, MatCheckboxModule } from '@angular/material'
import { MatTabsModule } from '@angular/material/tabs';
import {  MatAutocompleteModule } from '@angular/material/autocomplete'
import { BusinessRoutingModule } from './business-routing.module';
import { BusinessComponent } from './business.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { SharedComponentModule } from '../shared-component/shared-component.module';
import { CompanyProfileComponent } from './company-profile/company-profile.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgentProfileComponent } from './agent-profile/agent-profile.component';




@NgModule({
  declarations: [BusinessComponent, HeaderComponent, HomeComponent, CompanyProfileComponent, AgentProfileComponent],
  imports: [
    CommonModule,
    BusinessRoutingModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatSelectModule,
    MatTabsModule,
    MatAutocompleteModule,
    SharedComponentModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class BusinessModule { }
