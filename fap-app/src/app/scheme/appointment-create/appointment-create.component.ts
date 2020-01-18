import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentCrudService } from '../../shared/service/appointment-crud.service';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { ptBrLocale } from 'ngx-bootstrap/locale';

defineLocale('pt-br', ptBrLocale); 

@Component({
  selector: 'app-appointment-create',
  templateUrl: './appointment-create.component.html',
  styleUrls: ['./appointment-create.component.css']
})
export class AppointmentCreateComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;

  idPaciente = this.actRoute.snapshot.params['id'];
  @Input() appointment = {
    dataConsulta: '',
    idadeGestacional: '',
    peso: '',
    presArterial: '',
    alturaUterina: '',
    bcf: '',
    edema: '',
    apresFetal: '',
    toque: '',
    obs: ''
  }
  apresFetais = ['Cefálica', 'Pélvica','Transversal'];

  constructor(public restApi: AppointmentCrudService, public actRoute: ActivatedRoute,
              public router: Router, private localeService: BsLocaleService) {
    this.localeService.use('pt-br');
    this.datePickerConfig = Object.assign({},{
        containerClass:'theme-dark-blue',
        showWeekNumbers: false,
        adaptivePosition: true
      });
  }

  ngOnInit() {}

  addAppointment(){
    this.restApi.createAppointment(this.idPaciente, this.appointment).subscribe((data: {}) => {
      this.router.navigate(['/paciente-detalhes/' + this.idPaciente])
    })
  }

}
