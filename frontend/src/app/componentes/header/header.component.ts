import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  isLoggedIn = false;  // Estado de login, altere conforme necessário

  constructor(private router: Router) {}

  goToLogin() {
    // Lógica de redirecionamento para a página de login
    this.router.navigate(['/login']);
  }

  logout() {
    // Simula o logout e redireciona para a página de login
    localStorage.removeItem('isLoggedIn');
    this.router.navigate(['/login']);
  }
}
