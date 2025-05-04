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
      nome: new FormControl('Marcus'),
      telefone: new FormControl('11 1111-1111'),
      email: new FormControl('marcus@email.com'),
      aniversario: new FormControl(''),
      senha: new FormControl('123'),
      confirmaSenha: new FormControl('123')
    })
  }
  
  salvarLogin(){
    console.log(this.loginForm.value);
  }
  
  cancelar(){
    console.log('Submissão cancelada');
  }
}

