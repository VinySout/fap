import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Exams } from '../model/exams';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ExamsCrudService {

  apiURL = "http://localhost:9898";
  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;'
    })
  }
  createExam(idPaciente, exams): Observable<Exams> {
    return this.http.post<Exams>(this.apiURL + '/api/pacientes/' + idPaciente + '/exames', JSON.stringify(exams), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getExams(idPaciente): Observable<Exams>{
    return this.http.get<Exams>(this.apiURL + '/api/pacientes/' + idPaciente + '/exames')
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getExam(idPaciente, idExams): Observable<Exams>{
    return this.http.get<Exams>(this.apiURL + '/api/pacientes/' + idPaciente + '/exames/' + idExams)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  deleteExams(idPacient, idExams): Observable<Exams>{
    return this.http.delete<Exams>(this.apiURL + '/api/pacientes/' + idPacient + '/exames/' + idExams, this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  updateExams(idPacient, idExams, exams): Observable<Exams> {
    return this.http.put<Exams>(this.apiURL + '/api/pacientes/' + idPacient + '/exames/' + idExams, JSON.stringify(exams), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }

  handleError(error: HttpErrorResponse){
    window.alert(error);
    return throwError(error);
  }
}
