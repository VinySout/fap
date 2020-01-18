import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Paciente } from '../model/paciente';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {
  
  // API
  apiURL = "http://localhost:9898";
  constructor(private http: HttpClient) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;'
    })
  }

  getPatients(): Observable<Paciente>{
    return this.http.get<Paciente>(this.apiURL + '/api/pacientes')
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getPatient(idPaciente): Observable<Paciente>{
    return this.http.get<Paciente>(this.apiURL + '/api/pacientes/' + idPaciente)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  createPatient(patient): Observable<Paciente> {
    return this.http.post<Paciente>(this.apiURL + '/api/pacientes', JSON.stringify(patient), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  updatePatient(id, patient): Observable<Paciente> {
    return this.http.put<Paciente>(this.apiURL + '/api/pacientes/' + id, JSON.stringify(patient), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  deletePatient(idPaciente): Observable<Paciente> {
    return this.http.delete<Paciente>(this.apiURL + '/api/pacientes/' + idPaciente, this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }

  handleError(error: HttpErrorResponse){
    window.alert(error);
    return throwError(error);
  }
}
