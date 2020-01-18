import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentCrudService } from 'src/app/shared/service/appointment-crud.service';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { ptBrLocale } from 'ngx-bootstrap/locale';

defineLocale('pt-br', ptBrLocale); 

@Component({
  selector: 'app-appointmet-update',
  templateUrl: './appointmet-update.component.html',
  styleUrls: ['./appointmet-update.component.css']
})
export class AppointmetUpdateComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;

  patientId = this.actRoute.snapshot.params['id1'];
  aptnId = this.actRoute.snapshot.params['id2'];
  appointment: any = {};
  
  apresFetais = ['Cefálica', 'Pélvica','Transversal'];

  constructor(
    public restApi: AppointmentCrudService,
    public actRoute: ActivatedRoute,
    public router: Router, private localeService: BsLocaleService) {
      this.localeService.use('pt-br');
      this.datePickerConfig = Object.assign({},{
        containerClass:'theme-dark-blue',
        showWeekNumbers: false,
        adaptivePosition: true
      });
    }

  ngOnInit() {
    this.restApi.getAppointment(this.patientId, this.aptnId).subscribe((data: {}) => {
      this.appointment = data;
      this.appointment.dataConsulta = new Date(this.appointment.dataConsulta);
    })
  }
  updateAptn(){
    if(window.confirm('Você tem certeza que deseja alterar esta Consulta?')){
      this.restApi.updateAppointment(this.patientId, this.aptnId, this.appointment).subscribe(data => {
        this.router.navigate(['/paciente-detalhes/' + this.patientId])
      })
    }
  }

}
