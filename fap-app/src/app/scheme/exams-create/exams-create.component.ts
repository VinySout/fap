import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ExamsCrudService } from 'src/app/shared/service/exams-crud.service';
import { BsDatepickerConfig } from 'ngx-bootstrap/datepicker';
import { BsLocaleService } from 'ngx-bootstrap/datepicker';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { ptBrLocale } from 'ngx-bootstrap/locale';

defineLocale('pt-br', ptBrLocale); 

@Component({
  selector: 'app-exam-create',
  templateUrl: './exams-create.component.html',
  styleUrls: ['./exams-create.component.css']
})
export class ExamsCreateComponent implements OnInit {
  datePickerConfig: Partial<BsDatepickerConfig>;

  idPaciente = this.actRoute.snapshot.params['id'];
  @Input() exams = {
    aboRh: '',
    dtAboRh: '',
    hemoglobina: '',
    dtHemoglobina: '',
    vdrlHiv: '',
    dtVdrlHiv: '',
    urinaEas: '',
    dtUrinaEas: '',
    citOncotica: '',
    dtCitOncotica: '',    
    glicemia: '',
    dtGlicemia: '',
    sifilis: '',
    dtSifilis: '',    
    hepatite: '',
    dtHepatite: '',
    toxoplasmose: '',
    dtToxopla: '',
    citomegalovirus: '',
    dtCitomega: '',
    
  }
    

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
  }
  addExams(){
    this.restApi.createExam(this.idPaciente, this.exams).subscribe((data: {}) => {
      this.router.navigate(['/paciente-detalhes/' + this.idPaciente])
    })
  }

}
