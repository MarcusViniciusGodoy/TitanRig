import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { ContainerComponent } from '../../componentes/container/container.component';

@Component({
  selector: 'app-formulario-login',
  imports: [
    CommonModule,
    ContainerComponent,
    ReactiveFormsModule
  ],
  templateUrl: './formulario-login.component.html',
  styleUrl: './formulario-login.component.css'
})
export class FormularioLoginComponent {

  loginForm!: FormGroup;

  constructor() {
    this.loginForm = new FormGroup({
      nome: new FormControl(''),
      telefone: new FormControl(''),
      email: new FormControl(''),
      aniversario: new FormControl(''),
      senha: new FormControl(''),
      confirmaSenha: new FormControl('')
    })
  }
}

