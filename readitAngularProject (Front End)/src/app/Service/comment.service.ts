import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentDTO } from '../Models/CommentDTO';


const COMMENT_API = 'http://localhost:8080/api/comment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  findAllCommentsByPostId(postId): Observable<any>{
    return this.http.get<CommentDTO[]>(COMMENT_API + "/post/" + postId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  createComment(commentDTO): Observable<any> {
    return this.http.post(COMMENT_API, JSON.stringify(commentDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  deleteComment(commentId){
    return this.http.delete(COMMENT_API + "/delete/" + commentId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }
}
