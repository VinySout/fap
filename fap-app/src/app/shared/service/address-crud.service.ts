import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Endereco } from '../model/endereco';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddressCrudService {
  // API
  apiURL = "http://localhost:9898";
  constructor(private http: HttpClient) { }

  // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;'
    })
  }
  createAddress(idPaciente, address): Observable<Endereco> {
    return this.http.post<Endereco>(this.apiURL + '/api/pacientes/' + idPaciente + '/endereco', JSON.stringify(address), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getAddresses(idPaciente): Observable<Endereco>{
    return this.http.get<Endereco>(this.apiURL + '/api/pacientes/' + idPaciente + '/endereco')
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  getAddress(idPaciente, idAddress): Observable<Endereco>{
    return this.http.get<Endereco>(this.apiURL + '/api/pacientes/' + idPaciente + '/endereco/' + idAddress)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  deleteAddress(idPacient, idAddress): Observable<Endereco>{
    return this.http.delete<Endereco>(this.apiURL + '/api/pacientes/' + idPacient + '/endereco/' + idAddress, this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }
  updateAddress(idPacient, idAddress, address): Observable<Endereco> {
    return this.http.put<Endereco>(this.apiURL + '/api/pacientes/' + idPacient + '/endereco/' + idAddress, JSON.stringify(address), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }

  handleError(error: HttpErrorResponse){
    window.alert(error);
    return throwError(error);
  }
}
