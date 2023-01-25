import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DownvoteDTO } from '../Models/DownvoteDTO';
import { UpvoteDTO } from '../Models/UpvoteDTO';

const UPVOTE_API = "http://localhost:8080/api/upvote";
const DOWNVOTE_API = "http://localhost:8080/api/downvote";

@Injectable({
  providedIn: 'root'
})
export class UpvoteDownvoteService {

  currentUserUpvotes: UpvoteDTO[] = [];
  currentUserDownvotes: DownvoteDTO[] = [];

  constructor(private http: HttpClient) { }

  createUpvote(upvoteDTO): Observable<any> {
    return this.http.post<UpvoteDTO>(UPVOTE_API, JSON.stringify(upvoteDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  deleteUpvote(upvoteId){
    return this.http.delete(UPVOTE_API + "/delete/" + upvoteId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  getUpvoteByUserId(userId): Observable<any> {
    return this.http.get<UpvoteDTO[]>(UPVOTE_API + "/" + userId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  createDownvote(downvoteDTO): Observable<any> {
    return this.http.post<DownvoteDTO>(DOWNVOTE_API, JSON.stringify(downvoteDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  deleteDownvote(downvoteId){
    return this.http.delete(DOWNVOTE_API + "/delete/" + downvoteId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  getDownvoteByUserId(userId): Observable<any> {
    return this.http.get<DownvoteDTO[]>(DOWNVOTE_API + "/" + userId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }


}
