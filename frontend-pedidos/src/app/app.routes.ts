import { Routes } from '@angular/router';
import { ClienteComponent } from './pages/cliente/cliente.component';
import { ProductoComponent } from './pages/producto/producto.component';
import { OrderComponentComponent } from './pages/order-component/order-component.component';

export const routes: Routes = [
    {path:"",component:ClienteComponent},
    {path:"productos",component:ProductoComponent},
    {path:"pedidos",component:OrderComponentComponent}
];
