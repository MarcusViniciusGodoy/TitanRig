import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';


@Component({
  selector: 'app-formulario-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './formulario-login.component.html',
  styleUrl: './formulario-login.component.css'
})
export class FormularioLoginComponent {

  loginForm!: FormGroup;

  constructor(
    private router: Router
  ) {
    this.loginForm = new FormGroup({
      nome: new FormControl('', Validators.required),
      telefone: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.email]),
      aniversario: new FormControl(''),
      senha: new FormControl('', Validators.required),
      confirmaSenha: new FormControl('', Validators.required)
    })
  }
  
  salvarLogin(){
    console.log(this.loginForm.value);
    this.loginForm.reset();
    this.router.navigateByUrl('/login')
  }
  
  cancelar(){
    this.loginForm.reset();
  }
}

