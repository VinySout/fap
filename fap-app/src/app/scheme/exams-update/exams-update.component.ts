import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamsCrudService } from 'src/app/shared/service/exams-crud.service';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { ptBrLocale } from 'ngx-bootstrap/locale';

defineLocale('pt-br', ptBrLocale); 

@Component({
  selector: 'app-exam-update',
  templateUrl: './exams-update.component.html',
  styleUrls: ['./exams-update.component.css']
})
export class ExamsUpdateComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;

  patientId = this.actRoute.snapshot.params['id1'];
  examsId = this.actRoute.snapshot.params['id2'];
  exams: any = {};

  constructor(public restApi: ExamsCrudService, public actRoute: ActivatedRoute,
              public router: Router, private localeService: BsLocaleService) {
              this.localeService.use('pt-br');
              this.datePickerConfig = Object.assign({},{
                containerClass:'theme-dark-blue',
                showWeekNumbers: false,
                adaptivePosition: true
              });
  }

  ngOnInit() {
    this.restApi.getExam(this.patientId, this.examsId).subscribe((data: {}) => {
      this.exams = data;
      
      this.exams.dtHemoglobina = new Date(this.exams.dtHemoglobina);
      this.exams.dtGlicemia = new Date(this.exams.dtGlicemia);
      this.exams.dtUrinaEas = new Date(this.exams.dtUrinaEas);

      if(this.exams.dtAboRh){this.exams.dtAboRh = new Date(this.exams.dtAboRh);}
      if(this.exams.dtVdrlHiv){this.exams.dtVdrlHiv = new Date(this.exams.dtVdrlHiv);}
      if(this.exams.dtCitOncotica){this.exams.dtCitOncotica = new Date(this.exams.dtCitOncotica);}
      if(this.exams.dtSifilis){this.exams.dtSifilis = new Date(this.exams.dtSifilis);}
      if(this.exams.dtHepatite){this.exams.dtHepatite = new Date(this.exams.dtHepatite);}
      if(this.exams.dtToxopla){this.exams.dtToxopla = new Date(this.exams.dtToxopla);}
      if(this.exams.dtCitomega){this.exams.dtCitomega = new Date(this.exams.dtCitomega);}
    })
  }
  updateExams(){
    if(window.confirm('Você tem certeza que deseja alterar os dados desses Exames?')){
      this.restApi.updateExams(this.patientId, this.examsId, this.exams).subscribe(data => {
        this.router.navigate(['/paciente-detalhes/' + this.patientId])
      })
    }
  }

}
