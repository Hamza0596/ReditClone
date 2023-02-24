import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable, throwError } from "rxjs";
import { catchError } from "rxjs/internal/operators/catchError";
import { switchMap } from "rxjs/operators";
import { LoginResponse } from "../Model/LoginResponse";
import { AuthServiceService } from "../Services/auth-service.service";
@Injectable({
    providedIn: 'root'
})
export class TokenInterceptor implements HttpInterceptor{
    isTokenRefreshing = false;
    refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject(null);
      constructor(private authService:AuthServiceService){}
      
      intercept(req: HttpRequest<any>,
        next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.authService.getJwtToken()) {
            this.addToken(req, this.authService.getJwtToken());
        }

        return next.handle(req).pipe(catchError(error => {
            if (error instanceof HttpErrorResponse
                && error.status === 403) {
                return this.handleAuthErrors(req, next)  as Observable<HttpEvent<any>>;;
            } else {
                return throwError(error)  as Observable<HttpEvent<any>>;;
            }
         }));
    }




    private handleAuthErrors(req: HttpRequest<any>, next: HttpHandler) {
        if (!this.isTokenRefreshing) {
            this.isTokenRefreshing = true;
            this.refreshTokenSubject.next(null);

            return this.authService.getRefreshToken().pipe(
                switchMap((refreshTokenResponse: LoginResponse) => {
                    this.isTokenRefreshing = false;
                    this.refreshTokenSubject.next(refreshTokenResponse.authentificationToken);
                    return next.handle(this.addToken(req, refreshTokenResponse.authentificationToken));
                })
            )
        }
    }


    private addToken(req: HttpRequest<any>, jwtToken: string) {
        console.log("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        console.log(jwtToken);
        
        return req.clone({
            headers: req.headers.set('Authorization',
                'Bearer ' + jwtToken)
        });
    }

}