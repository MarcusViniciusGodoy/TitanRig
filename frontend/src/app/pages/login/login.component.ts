import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-login',
  imports: [
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  email: string = '';
  senha: string = '';
  lembrarDeMim: boolean = false;

  constructor(private http: HttpClient, private router: Router) {}

  login() {
    const body = new URLSearchParams();
    body.set('username', this.email);
    body.set('password', this.senha);
    if (this.lembrarDeMim) {
      body.set('remember-me', 'on');
    }

    const csrfToken = this.cookieService.get('XSRF-TOKEN');

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'X-XSRF-TOKEN': csrfToken || '' 
    });

    this.http.post('/login', body.toString(), {
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
      }),
      withCredentials: true
    }).subscribe({
      next: () => {
        this.router.navigate(['/home']);
      },
      error: (err) => {
        alert('Erro no login: ' + err.message);
      }
    });
  }

  irParaCadastro() {
    this.router.navigate(['/formulario']);
  }
}