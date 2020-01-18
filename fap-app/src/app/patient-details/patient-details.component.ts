import { Component, OnInit } from '@angular/core';
import { RestApiService } from '../shared/service/rest-api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressCrudService } from '../shared/service/address-crud.service';
import { AppointmentCrudService } from '../shared/service/appointment-crud.service';
import { ExamsCrudService } from '../shared/service/exams-crud.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patientId = this.actRoute.snapshot.params['id'];
  patient: any = {};
  patientAddress: any = [];
  patientAptnt: any = [];
  patientExams: any = [];
  error = '';

  constructor(
    public restApi: RestApiService,
    public enderecoCrud: AddressCrudService,
    public appointmentCrud: AppointmentCrudService,
    public examsCrud: ExamsCrudService,
    public actRoute: ActivatedRoute,
    public router: Router) { }

  ngOnInit() {
    this.restApi.getPatient(this.patientId).subscribe((data: {}) => {
      this.patient = data;
    });
    this.appointmentCrud.getAppointments(this.patientId).subscribe((data: {}) => {
      this.patientAptnt = data;
    });
    this.loadAddress();
    this.loadExams();
  }
  loadAddress(){
    this.enderecoCrud.getAddresses(this.patientId).subscribe((data: {}) => {
      this.patientAddress = data;
    })
  }
  loadExams(){
    this.examsCrud.getExams(this.patientId).subscribe((data: {}) => {
      this.patientExams = data;
    })
  }
  deletePatient(patientId){
    if(window.confirm("Você tem certeza que deseja deletar este Paciente!?")){
      this.restApi.deletePatient(patientId).subscribe(data => {
        this.router.navigate(['/pacientes-lista'])
      })
    }
  }  
  deleteAddress(addressId){
    if(window.confirm("Você tem certeza que deseja deletar este Endereço!?")){
      this.enderecoCrud.deleteAddress(this.patientId, addressId).subscribe(data => {
        this.loadAddress()
      }, 
      error => {
        this.error = error;
        alert(error);
      })
    }
  }
}
