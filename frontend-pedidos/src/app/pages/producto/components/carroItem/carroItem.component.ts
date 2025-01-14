import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { Producto } from '../../../../model/producto';
import { CommonModule } from '@angular/common';
import { CarroCompraService } from '../../../../services/carroCompra.service';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-carro-item',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule
  ],
  templateUrl: './carroItem.component.html',
  styleUrl: './carroItem.component.css',
  
})
export class CarroItemComponent { 

  @Input() producto!:Producto

  constructor(private carroCompraService:CarroCompraService){

  }

  eliminarProducto(id:number){
    this.carroCompraService.eliminarProducto(id)
  }
}
