import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BsDatepickerModule } from 'ngx-bootstrap';
import { ModalModule } from 'ngx-bootstrap/modal';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { AppComponent } from './app.component';
import { PatientCreateComponent } from './patient-create/patient-create.component';
import { PatientDetailsComponent } from './patient-details/patient-details.component';
import { PatientUpdateComponent } from './patient-update/patient-update.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { MenuSidebarComponent } from './menu-sidebar/menu-sidebar.component';
import { AddressCreateComponent } from './scheme/address-create/address-create.component';
import { AddressUpdateComponent } from './scheme/address-update/address-update.component';
import { AppointmentCreateComponent } from './scheme/appointment-create/appointment-create.component';
import { AppointmetDetailsComponent } from './scheme/appointmet-details/appointmet-details.component';
import { AppointmetUpdateComponent } from './scheme/appointmet-update/appointmet-update.component';
import { PageNotFoundComponent } from './scheme/page-not-found/page-not-found.component';
import { ExamsCreateComponent } from './scheme/exams-create/exams-create.component';
import { ExamsDetailsComponent } from './scheme/exams-details/exams-details.component';
import { ExamsUpdateComponent } from './scheme/exams-update/exams-update.component';
import { SigninComponent } from './scheme/login/signin/signin.component';
import { SignupComponent } from './scheme/login/signup/signup.component';
import { JwtInterceptor } from './shared/auth/jwt.interceptor';
import { ErrorInterceptor } from './shared/auth/error.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    PatientCreateComponent,
    PatientDetailsComponent,
    PatientUpdateComponent,
    PatientListComponent,
    MenuSidebarComponent,
    AddressCreateComponent,
    AddressUpdateComponent,
    AppointmentCreateComponent,
    AppointmetDetailsComponent,
    AppointmetUpdateComponent,
    PageNotFoundComponent,
    ExamsCreateComponent,
    ExamsDetailsComponent,
    ExamsUpdateComponent,
    SigninComponent,
    SignupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    BsDatepickerModule.forRoot(),
    ModalModule.forRoot()
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
