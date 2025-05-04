import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ContainerComponent } from '../../componentes/container/container.component';
import { HeaderComponent } from '../../componentes/header/header.component';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-home',
  imports: [
    CommonModule,
    RouterOutlet,
    ContainerComponent,
    HeaderComponent,
    LoginComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
