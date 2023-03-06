import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient : HttpClient) { }

  getAllCommentsForPost(postId: number): Observable<any> {
    return this.httpClient.get(`http://localhost:8080/api/comment/${postId}`);
  }

  postComment(commentPayload: any): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/comments/', commentPayload);
  }
}
