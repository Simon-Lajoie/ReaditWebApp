import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PostDTO } from '../Models/PostDTO';



const POST_API = "http://localhost:8080/api/post";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  posts: PostDTO[] = [];
  constructor(private http: HttpClient) {
   }

  getPosts(): Observable<any> {
    return this.http.get<PostDTO[]>(POST_API, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  createPost(postDTO): Observable<any> {
    return this.http.post<PostDTO>(POST_API, JSON.stringify(postDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  deletePost(postId){
    return this.http.delete(POST_API + "/delete/" + postId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'text', observe: 'response' });
  }

  getPostsByUserId(userId): Observable<any> {
    return this.http.get<PostDTO[]>(POST_API + "/user/" + userId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

  getPostsById(postId): Observable<any> {
    return this.http.get<PostDTO>(POST_API + "/" + postId, { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  } 

  updatePost(postDTO, postId): Observable<any> {
    return this.http.put<PostDTO>(POST_API + "/update/" + postId, JSON.stringify(postDTO), { 'headers': new HttpHeaders({ 'Content-Type': 'application/json' }), 'responseType': 'json', observe: 'response' });
  }

}

