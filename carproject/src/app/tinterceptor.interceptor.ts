import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class TinterceptorInterceptor implements HttpInterceptor {
  token:any =localStorage.getItem('jwttoken')

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if (this.token) {
      request=request.clone({
        setHeaders:{authorization:`Bearer ${this.token}`}
      })
      
    }
    return next.handle(request)
  }
}
