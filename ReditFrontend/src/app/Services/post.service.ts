import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  apiUrl = environment.apiUrl;

  constructor(private httpClient : HttpClient) { }

  getAllPosts(): Observable<any> {
    return this.httpClient.get(`${this.apiUrl}/post`);
  }


  addPost(post:any):Observable<any> {
return this.httpClient.post(`${this.apiUrl}/post`,post)
  }

  getPostById(id:number){
    return this.httpClient.get(`${this.apiUrl}/post/${id}`);

  }
}
