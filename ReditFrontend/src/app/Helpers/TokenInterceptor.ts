import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { catchError } from "rxjs/internal/operators/catchError";
import { filter, switchMap, take } from "rxjs/operators";
import { LoginResponse } from "../Model/LoginResponse";
import { AuthServiceService } from "../Services/auth-service.service";
@Injectable({
    providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor{
    private isRefreshing = false;
    private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  
    constructor(private authenticationService: AuthServiceService) {
    }
  
    intercept(httpRequest: HttpRequest<any>, httpHandler: HttpHandler): Observable<HttpEvent<any>> {
      if (httpRequest.url.includes(`${this.authenticationService.apiUrl}/auth/login`)) {
        return httpHandler.handle(httpRequest);
      }
      if (httpRequest.url.includes(`${this.authenticationService.apiUrl}/auth/signup`)) {
        return httpHandler.handle(httpRequest);
      }

      if (httpRequest.url.includes(`${this.authenticationService.apiUrl}/post/all/**`)&&(httpRequest.method.toUpperCase()==='GET')) {
        return httpHandler.handle(httpRequest);
      }
      
      if (httpRequest.url.includes(`${this.authenticationService.apiUrl}/suberedit`)) {
        return httpHandler.handle(httpRequest);
      }


      this.authenticationService.refreshToken();
      let token = this.authenticationService.getJwtToken();
      let request = this.addToken(httpRequest, token);
      if (httpRequest.url.includes(`${this.authenticationService.apiUrl}/refresh/token`)) {
        token = this.authenticationService.getRefreshToken();
        request = this.addToken(httpRequest, token);
        return httpHandler.handle(request);
      }
      // @ts-ignore
      return httpHandler.handle(request).pipe(catchError(error => {
        if (error instanceof HttpErrorResponse && !httpRequest.url.includes('/login') && error.status === 401) {
          return this.handle401Error(request, httpHandler);
        }
        return throwError(error);
      }));
    }
  
    private addToken = (request: HttpRequest<any>, token: string) => request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    })
  
    // tslint:disable-next-line:typedef
    private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
      if (!this.isRefreshing) {
        this.isRefreshing = true;
        this.refreshTokenSubject.next(null);
  
        return this.authenticationService.refreshToken().pipe(
          switchMap((token: any) => {
            this.isRefreshing = false;
            this.refreshTokenSubject.next(token.access_token);
            return next.handle(this.addToken(request, token.access_token));
          }));
  
      } else {
        return this.refreshTokenSubject.pipe(
          filter(token => token != null),
          take(1),
          switchMap(jwt => {
            return next.handle(this.addToken(request, jwt));
          }));
      }
    }

   
 
}