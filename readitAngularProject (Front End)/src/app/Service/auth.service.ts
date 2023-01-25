import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { param } from 'jquery';
import { UserDTO } from '../Models/UserDTO';

const LOGIN_API = 'http://localhost:8080/api/login';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  authenticate(username, password): Observable<any> {
    return this.http.post(LOGIN_API, {
      username: username,
      password: password
    }, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  logout() {
    window.sessionStorage.clear();
  }
}