import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private router: Router) {}

  irParaCadastro() {
    this.router.navigate(['/formulario']);
  }

  login() {
    localStorage.setItem('isLoggedIn', 'true'); 
    this.router.navigate(['/home']); 
  }
}
