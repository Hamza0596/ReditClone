import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SubredditService {
  apiUrl = environment.apiUrl;
  constructor(private http: HttpClient) { }


  getAllSubreddits(): Observable<Array<any>> {
    return this.http.get<Array<any>>(`${this.apiUrl}/suberedit`);
  }
}
