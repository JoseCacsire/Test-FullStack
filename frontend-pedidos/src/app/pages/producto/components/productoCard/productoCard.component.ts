import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { Producto } from '../../../../model/producto';
import { CommonModule } from '@angular/common';
import { CarroCompraService } from '../../../../services/carroCompra.service';
import { MatIcon } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-producto-card',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    MatIcon,
    MatButtonModule
  ],
  templateUrl: './productoCard.component.html',
  styleUrl: './productoCard.component.css',
})
export class ProductoCardComponent {
  @Input()producto!:Producto

  constructor(private carroCompra:CarroCompraService){

  }

  agregarProducto(productoSeleccionado: Producto) {
    this.carroCompra.agregarProducto(productoSeleccionado);
  }

  getCantidad(id:number){
    return this.carroCompra.getCantidadProducto(id)
  }

  disminuirProducto(id:number){
    this.carroCompra.disminuirProducto(id)
  }

}
