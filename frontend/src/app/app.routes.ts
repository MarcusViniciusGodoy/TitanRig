import { Routes } from '@angular/router';
import { ContainerComponent } from './componentes/container/container.component';
import { LoginComponent } from './componentes/login/login.component';

export const routes: Routes = [
  { path: '', component: ContainerComponent }, 
  { path: 'login', component: LoginComponent },
];
