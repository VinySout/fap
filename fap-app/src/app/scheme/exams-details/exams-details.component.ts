import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamsCrudService } from 'src/app/shared/service/exams-crud.service';

@Component({
  selector: 'app-exam-details',
  templateUrl: './exams-details.component.html',
  styleUrls: ['./exams-details.component.css']
})
export class ExamsDetailsComponent implements OnInit {
  patientId = this.actRoute.snapshot.params['id1'];
  examsId = this.actRoute.snapshot.params['id2'];
  exams: any = {};

  constructor(
    public examsCrud: ExamsCrudService,
    public actRoute: ActivatedRoute,
    public router: Router
  ) { }

  ngOnInit() {
    this.examsCrud.getExam(this.patientId, this.examsId).subscribe((data: {}) => {
      this.exams = data;
    })
  }
  routeEditExams(){
    this.router.navigate(['/'+ this.patientId +'/exames-editar/' + this.examsId])
  }
  deleteExams(examsId){
    if(window.confirm("Você tem certeza que deseja deletar esses resultados de Exames!?")){
      this.examsCrud.deleteExams(this.patientId, examsId).subscribe(data => {
        this.router.navigate(['/paciente-detalhes/' + this.patientId])
      })
    }
  }

}
