import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';  // Importando o CookieService
import { CsrfInterceptor } from './app/csrf.interceptor';  // Importando o Interceptor CSRF

// Atualizando a configuração do app
bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(HttpClientModule), // Adicionando o HttpClientModule
    CookieService, // Registrando o CookieService para gerenciar cookies
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CsrfInterceptor, // Registrando o Interceptor CSRF
      multi: true
    }
  ]
})
  .catch((err) => console.error(err));
