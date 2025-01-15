import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Producto } from '../../../../model/producto';
import { CarroCompraService } from '../../../../services/carroCompra.service';
import { MatIcon } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDivider } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { CarroItemComponent } from "../carroItem/carroItem.component";
import { OrderService } from '../../../../services/order.service';
import { MatSnackBar } from '@angular/material/snack-bar';

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
    private orderService: OrderService,
    private _snackBar: MatSnackBar
  ){
    
  }

  
  ngOnInit(): void {
    this.carroCompraService.getCarro().subscribe(carro=> {
      this.carrito= carro
      this.total = this.carroCompraService.calcularTotal()  
  })
  }

  comprar() {
    const clienteId = 3; 
    const productos = this.carrito.map(producto => ({
      productoId: producto.id,
      cantidad: producto.cantidad, 
    }));
  
    const order = { clienteId, productos };
  
    this.orderService.save(order).subscribe({
      next: (res) => {
        this._snackBar.open('Realizaste tu pedido exitosamente', 'Cerrar', {
          duration: 3000,
        });
        this.carroCompraService.vaciarCarrito();
      },
      error: (err) => {
        console.error('Error al realizar el pedido:', err);
      },
    });
  }


  vaciarCarrito(){
    this.carroCompraService.vaciarCarrito()
  }
}
