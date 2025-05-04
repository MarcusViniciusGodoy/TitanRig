import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ContainerComponent } from '../../componentes/container/container.component';

@Component({
  selector: 'app-formulario-login',
  imports: [
    CommonModule,
    ContainerComponent,
    FormsModule
  ],
  templateUrl: './formulario-login.component.html',
  styleUrl: './formulario-login.component.css'
})
export class FormularioLoginComponent {

  senha: string = '';
  confirmarSenha: string = '';
  senhaVisivel: boolean = false;
  confirmarSenhaVisivel: boolean = false;
}
