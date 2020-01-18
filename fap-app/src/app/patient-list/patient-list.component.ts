import { Component, OnInit } from '@angular/core';
import { RestApiService } from '../shared/service/rest-api.service';

@Component({
  selector: 'app-patient-list',
  templateUrl: './patient-list.component.html',
  styleUrls: ['./patient-list.component.css']
})
export class PatientListComponent implements OnInit {

  Patient: any = [];

  constructor(
    public restApi: RestApiService
  ) { }

  ngOnInit() {
    this.loadPatients()
  }
  loadPatients(){
    return this.restApi.getPatients().subscribe((data: {}) => {
      this.Patient = data;
    })
  }

}
