import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

import { ContainerComponent } from './componentes/container/container.component';
import { HeaderComponent } from './componentes/header/header.component';
import { FormularioLoginComponent } from './pages/formulario-login/formulario-login.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    ContainerComponent,
    HeaderComponent,
    FormularioLoginComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'TitanRig';
}
