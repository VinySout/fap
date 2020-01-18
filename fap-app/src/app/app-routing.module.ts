import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PatientCreateComponent } from './patient-create/patient-create.component';
import { PatientDetailsComponent } from './patient-details/patient-details.component';
import { PatientListComponent } from './patient-list/patient-list.component';
import { PatientUpdateComponent } from './patient-update/patient-update.component';
import { AddressCreateComponent } from './scheme/address-create/address-create.component';
import { AddressUpdateComponent } from './scheme/address-update/address-update.component';
import { AppointmentCreateComponent } from './scheme/appointment-create/appointment-create.component';
import { AppointmetDetailsComponent } from './scheme/appointmet-details/appointmet-details.component';
import { AppointmetUpdateComponent } from './scheme/appointmet-update/appointmet-update.component';
import { PageNotFoundComponent } from './scheme/page-not-found/page-not-found.component';
import { ExamsCreateComponent } from './scheme/exams-create/exams-create.component';
import { ExamsUpdateComponent } from './scheme/exams-update/exams-update.component';
import { ExamsDetailsComponent } from './scheme/exams-details/exams-details.component';
import { SignupComponent } from './scheme/login/signup/signup.component';
import { SigninComponent } from './scheme/login/signin/signin.component';
import { AuthGuard } from './shared/auth/auth.guard';


const routes: Routes = [
  { path: '', pathMatch: 'full' , redirectTo: 'pacientes-lista' },
  { path: 'signup', component: SignupComponent },
  { path: 'login', component: SigninComponent },
  { path: 'paciente-novo', component: PatientCreateComponent, canActivate: [AuthGuard] },
  { path: 'paciente-detalhes/:id', component: PatientDetailsComponent, canActivate: [AuthGuard] },
  { path: 'pacientes-lista', component: PatientListComponent, canActivate: [AuthGuard] } ,
  { path: 'paciente-editar/:id', component: PatientUpdateComponent, canActivate: [AuthGuard] },
  { path: ':id/endereco-novo', component:  AddressCreateComponent, canActivate: [AuthGuard]  },
  { path: ':id1/endereco-editar/:id2', component: AddressUpdateComponent, canActivate: [AuthGuard]  },
  { path: ':id/consulta-nova', component: AppointmentCreateComponent, canActivate: [AuthGuard]  },
  { path: ':id1/consulta-detalhes/:id2', component: AppointmetDetailsComponent, canActivate: [AuthGuard]  },
  { path: ':id1/consulta-editar/:id2', component: AppointmetUpdateComponent, canActivate: [AuthGuard]  },
  { path: ':id/exames-novo' , component: ExamsCreateComponent, canActivate: [AuthGuard]  },
  { path: ':id1/exames-editar/:id2', component: ExamsUpdateComponent, canActivate: [AuthGuard]  },
  { path: ':id1/exames-detalhes/:id2', component: ExamsDetailsComponent, canActivate: [AuthGuard]  },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
