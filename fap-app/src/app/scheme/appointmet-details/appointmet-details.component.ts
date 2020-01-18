import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentCrudService } from 'src/app/shared/service/appointment-crud.service';

@Component({
  selector: 'app-appointmet-details',
  templateUrl: './appointmet-details.component.html',
  styleUrls: ['./appointmet-details.component.css']
})
export class AppointmetDetailsComponent implements OnInit {
  patientId = this.actRoute.snapshot.params['id1'];
  appointmentId = this.actRoute.snapshot.params['id2'];
  appointment: any = {};

  constructor(
    public appointmentCrud: AppointmentCrudService,
    public actRoute: ActivatedRoute,
    public router: Router) { }

  ngOnInit() {
    this.appointmentCrud.getAppointment(this.patientId, this.appointmentId).subscribe((data: {}) => {
      this.appointment = data;
    })
  }
  routeEditAptn(){
    this.router.navigate(['/'+ this.patientId +'/consulta-editar/' + this.appointmentId])
  }
  deleteAptn(aptnId){
    if(window.confirm("Você tem certeza que deseja deletar esta Consulta!?")){
      this.appointmentCrud.deleteAppointment(this.patientId, aptnId).subscribe(data => {
        this.router.navigate(['/paciente-detalhes/' + this.patientId])
      })
    }
  }

}
