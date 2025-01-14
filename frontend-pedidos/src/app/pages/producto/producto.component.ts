import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../services/producto.service';
import { Producto } from '../../model/producto';
import { ProductoCardComponent } from "./components/productoCard/productoCard.component";
import { CommonModule } from '@angular/common';
import { CarroCompraComponent } from "./components/carro/carro.component";
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-producto',
  standalone: true,
  imports: [
    ProductoCardComponent,
    CommonModule,
    CarroCompraComponent,
    MatButtonModule,
    MatIconModule
],
  templateUrl: './producto.component.html',
  styleUrl: './producto.component.css',
})
export class ProductoComponent implements OnInit{
  productos:Producto[]=[] 
  constructor(private productoService:ProductoService){

  }
  ngOnInit(): void {
    this.productoService.getProductos().subscribe(productos=>{
      this.productos=productos
    })
  }

  verCarro(){
    
  }

}
