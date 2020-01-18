import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { RestApiService } from '../shared/service/rest-api.service';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { ptBrLocale } from 'ngx-bootstrap/locale';

defineLocale('pt-br', ptBrLocale); 

@Component({
  selector: 'app-patient-create',
  templateUrl: './patient-create.component.html',
  styleUrls: ['./patient-create.component.css']
})
export class PatientCreateComponent implements OnInit {

  datePickerConfig: Partial<BsDatepickerConfig>;

  @Input() patient = {
    cpf: '',
    identidade: '',
    idade: '',
    telefone: '',
    nomePaciente: '',
    nomeMae: '',
    estadoCivil: '',
    dataNasc: '',
    dum: '',
    dpp: '',
    altura: '',
    peso: '',
    imc: ''
  }
  eCivils = ['casada', 'divorciada', 'solteira','separada','viúva'];

  constructor(public restApi: RestApiService, public router: Router,
              private localeService: BsLocaleService) {
              this.localeService.use('pt-br');
              this.datePickerConfig = Object.assign({},{
                  containerClass:'theme-dark-blue',
                  showWeekNumbers: false,
                  adaptivePosition: true
                });
  }

  ngOnInit() {
  }
  addPatient(){
    this.restApi.createPatient(this.patient).subscribe((data: {}) => {
      this.router.navigate(['/pacientes-lista'])
    })
  }

  

}
