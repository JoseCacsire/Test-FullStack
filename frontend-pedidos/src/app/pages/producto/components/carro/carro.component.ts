import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Producto } from '../../../../model/producto';
import { CarroCompraService } from '../../../../services/carroCompra.service';
import { MatIcon } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDivider } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { CarroItemComponent } from "../carroItem/carroItem.component";

@Component({
  selector: 'app-carro',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIcon,
    MatDivider,
    CarroItemComponent
],
  templateUrl: './carro.component.html',
  styleUrl: './carro.component.css',
})
export class CarroCompraComponent implements OnInit{
  carrito:Producto[] = []
  total:number = 0

  constructor(
    private carroCompraService:CarroCompraService,
   
  ){
    
  }

  ngOnInit(): void {
    this.carroCompraService.getCarro().subscribe(carro=> {
      this.carrito= carro
      this.total = this.carroCompraService.calcularTotal()  
  })
  }

  vaciarCarrito(){
    this.carroCompraService.vaciarCarrito()
  }
}
