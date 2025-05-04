import { Routes } from '@angular/router';

import { FormularioLoginComponent } from './pages/formulario-login/formulario-login.component';
import { ContainerComponent } from './componentes/container/container.component';

export const routes: Routes = [
  { 
    path:'formulario', 
    component: FormularioLoginComponent
  },
  { 
    path:'home', 
    component: ContainerComponent
  },
  { 
    path:'', 
    component: ContainerComponent,
    pathMatch: 'full'
  }
];
