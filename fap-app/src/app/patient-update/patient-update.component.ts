import { Component, OnInit } from '@angular/core';
import { RestApiService } from '../shared/service/rest-api.service';
import { ActivatedRoute, Router } from '@angular/router';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { ptBrLocale } from 'ngx-bootstrap/locale';

defineLocale('pt-br', ptBrLocale); 


@Component({
  selector: 'app-patient-update',
  templateUrl: './patient-update.component.html',
  styleUrls: ['./patient-update.component.css']
})
export class PatientUpdateComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;

  eCivils = ['casada', 'divorciada', 'solteira','separada','viúva'];

  idPaciente = this.actRoute.snapshot.params['id'];
  patient: any = {};

  constructor(public restApi: RestApiService, public actRoute: ActivatedRoute, 
              public router: Router, private localeService: BsLocaleService) {
              this.localeService.use('pt-br');
              this.datePickerConfig = Object.assign({},{
                containerClass:'theme-dark-blue',
                showWeekNumbers: false,
                adaptivePosition: true
              });
  }

  ngOnInit() {
    this.restApi.getPatient(this.idPaciente).subscribe((data: {}) => {
      this.patient = data;
      this.patient.dataNasc = new Date(this.patient.dataNasc);
      this.patient.dum = new Date(this.patient.dum);
      this.patient.dpp = new Date(this.patient.dpp);
    })
  }

  updatePatient(){
    if(window.confirm('Você tem certeza que deseja alterar os dados desse Paciente?')){
      this.restApi.updatePatient(this.idPaciente, this.patient).subscribe(data => {
        this.router.navigate(['/pacientes-lista'])
      })
    }
  }

}
