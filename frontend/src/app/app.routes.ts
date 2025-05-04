import { Routes } from '@angular/router';

import { FormularioLoginComponent } from './pages/formulario-login/formulario-login.component';
import { HomeComponent } from './pages/home/home.component';

export const routes: Routes = [
  { 
    path:'formulario', 
    component: FormularioLoginComponent
  },
  { 
    path:'home', 
    component: HomeComponent
  },
  { 
    path:'', 
    component: HomeComponent,
    pathMatch: 'full'
  }
];
