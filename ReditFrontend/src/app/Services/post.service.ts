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

  getAllPosts(page : number): Observable<any> {
    return this.httpClient.get(`${this.apiUrl}/post/all/${page}`);
  }


  addPost(post:any):Observable<any> {
return this.httpClient.post(`${this.apiUrl}/post`,post)
  }

  getPostById(id:number){
    return this.httpClient.get(`${this.apiUrl}/post/${id}`);

  }


  getAllPostsByUser(userName:String):Observable<any[]>{
    return this.httpClient.get<any[]>(`${this.apiUrl}/post/byUserName/${userName}`)
  }

  searchPosts(query :String  , page :number): Observable<any>{
    return this.httpClient.get(`${this.apiUrl}/post/search/${query}/${page}`);

  }

  deletePostById(id :number): Observable<any>{
    return this.httpClient.delete(`${this.apiUrl}/post/${id}`);

  }
}
