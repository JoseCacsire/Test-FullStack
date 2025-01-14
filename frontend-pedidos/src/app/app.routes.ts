import { Routes } from '@angular/router';
import { ClienteComponent } from './pages/cliente/cliente.component';
import { ProductoComponent } from './pages/producto/producto.component';

export const routes: Routes = [
    {path:"",component:ClienteComponent},
    {path:"productos",component:ProductoComponent}
];
