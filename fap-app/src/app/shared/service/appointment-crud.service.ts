import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Appointment } from '../model/appointment';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AppointmentCrudService {
  // API
  apiURL = "http://localhost:9898";
  constructor(private http: HttpClient) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;'
    })
  }

  createAppointment(idPaciente, appointment): Observable<Appointment> {
    return this.http.post<Appointment>(this.apiURL + '/api/pacientes/' + idPaciente + '/consultas', JSON.stringify(appointment), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getAppointments(idPaciente): Observable<Appointment>{
    return this.http.get<Appointment>(this.apiURL + '/api/pacientes/' + idPaciente + '/consultas')
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getAppointment(idPaciente, idAppointment): Observable<Appointment>{
    return this.http.get<Appointment>(this.apiURL + '/api/pacientes/' + idPaciente + '/consultas/' + idAppointment)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  deleteAppointment(idPacient, idAppointment): Observable<Appointment>{
    return this.http.delete<Appointment>(this.apiURL + '/api/pacientes/' + idPacient + '/consultas/' + idAppointment, this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  updateAppointment(idPacient, idAppointment, appointment): Observable<Appointment> {
    return this.http.put<Appointment>(this.apiURL + '/api/pacientes/' + idPacient + '/consultas/' + idAppointment, JSON.stringify(appointment), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }

  handleError(error: HttpErrorResponse){
    window.alert(error);
    return throwError(error);
  }
}
