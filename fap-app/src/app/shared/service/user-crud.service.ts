import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { User } from '../model/user';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserCrudService {

    // API
    apiURL = "http://localhost:9898";
    constructor(private http: HttpClient) { }

   // Http Options
   httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json;'
    })
  }


  createUser(user): Observable<User> {
    return this.http.post<User>(this.apiURL + '/api/auth/signup', JSON.stringify(user), this.httpOptions)
    .pipe(
      retry(1), catchError(this.handleError)
    )
  }

  handleError(error: HttpErrorResponse){
    return throwError(error);
  }
}
