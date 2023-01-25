import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDTO } from '../Models/UserDTO';

const USER_KEY = 'auth-user';
const USER_API = 'http://localhost:8080/api/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  currentUser: UserDTO = this.getCurrentUser();
  
  constructor(private http: HttpClient) {
  }

  getCurrentUser(){
    this.currentUser = JSON.parse(sessionStorage.getItem(USER_KEY));
    return this.currentUser;
  }

  setCurrentUser(userDTO){
    sessionStorage.setItem(USER_KEY, userDTO);
  }

  createAccount(registerDTO): Observable<any> {
    return this.http.post(USER_API, JSON.stringify(registerDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  updateAccount(userDTO, id): Observable<any>{
    return this.http.put<UserDTO>(USER_API + "/update/" + id, JSON.stringify(userDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  deleteUser(id){
    return this.http.delete(USER_API + "/delete/" + id, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  findByUsername(username): Observable<any>{
    return this.http.get<UserDTO>(USER_API + "/byUsername/" + username, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  findAllUsers(): Observable<any> {
    return this.http.get<UserDTO[]>(USER_API, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  findById(userId){
    return this.http.get(USER_API + "/" + userId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  findAllOnlineUsers(): Observable<any> {
    return this.http.get<UserDTO[]>(USER_API + "/online", { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }
}
