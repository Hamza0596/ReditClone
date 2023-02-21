import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { SignUpRequestPayload } from '../Model/SignUpRequestPayload';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  apiUrl = environment.apiUrl;

  constructor(private httpClient : HttpClient) { }

  Signup(signUpRequestPayload:SignUpRequestPayload){
    return this.httpClient.post(`${this.apiUrl}/auth/signup`,signUpRequestPayload)
}}
