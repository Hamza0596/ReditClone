import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Injectable ,Inject} from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Loginrequestpayload } from '../Model/Loginrequestpayload';
import { LoginResponse } from '../Model/LoginResponse';
import { SignUpRequestPayload } from '../Model/SignUpRequestPayload';
import {LOCAL_STORAGE, StorageService } from 'ngx-webstorage-service';
import { map, tap } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  apiUrl = environment.apiUrl;

  constructor(private httpClient : HttpClient, @Inject(LOCAL_STORAGE) private storage: StorageService) { }

  Signup(signUpRequestPayload:SignUpRequestPayload): Observable<any> {
    return this.httpClient.post<any>(`${this.apiUrl}/auth/signup`,signUpRequestPayload)
}


login(loginrequestpayload:Loginrequestpayload): Observable<any> {
  const TOKEN_KEY = 'my-app-token';
  const tokenValue = 'my-token-value';
    return this.httpClient.post<LoginResponse>(`${this.apiUrl}/auth/login`,loginrequestpayload).pipe(map(data=>{
       
        this.storage.set('authenticationToken', data.authentificationToken);
        this.storage.set('username', data.userName);
        this.storage.set('refreshToken', data.refreshToken);
        this.storage.set('expiresAt', data.expiresAt);
        return data;

    })
  )
}

refreshToken() {
  const refreshTokenPayload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  }
  return this.httpClient.post<LoginResponse>('http://localhost:8080/api/auth/refresh/token',
    refreshTokenPayload)
    .pipe(tap(response => {
      this.storage.set('authenticationToken', response.authentificationToken);
      this.storage.set('expiresAt', response.expiresAt);
    }));
}


getJwtToken(){
  return this.storage.get('authenticationToken');
}
getRefreshToken() {
  return this.storage.get('refreshToken');
}

getUserName() {
  return this.storage.get('username');
}

getExpirationTime() {
  return this.storage.get('expiresAt');
}




}


