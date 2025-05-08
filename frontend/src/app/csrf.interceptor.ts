import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';  // Para acessar o cookie CSRF

@Injectable()
export class CsrfInterceptor implements HttpInterceptor {

  constructor(private cookieService: CookieService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    // Captura o token CSRF do cookie
    const csrfToken = this.cookieService.get('XSRF-TOKEN');

    // Se o token CSRF existir, adicione ao cabeçalho
    const clonedRequest = csrfToken
      ? req.clone({
          setHeaders: {
            'X-XSRF-TOKEN': csrfToken  // Incluindo o token CSRF no cabeçalho
          }
        })
      : req;

    return next.handle(clonedRequest);
  }
}
