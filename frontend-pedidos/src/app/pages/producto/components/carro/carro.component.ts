import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Producto } from '../../../../model/producto';
import { CarroCompraService } from '../../../../services/carroCompra.service';
import { MatIcon } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDivider } from '@angular/material/divider';
import { CommonModule } from '@angular/common';
import { CarroItemComponent } from '../carroItem/carroItem.component';
import { OrderService } from '../../../../services/order.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClienteService } from '../../../../services/cliente.service';
import { Cliente } from '../../../../model/cliente';

@Component({
  selector: 'app-carro',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIcon,
    MatDivider,
    CarroItemComponent,
  ],
  templateUrl: './carro.component.html',
  styleUrl: './carro.component.css',
})
export class CarroCompraComponent implements OnInit {
  carrito: Producto[] = [];
  total: number = 0;

  constructor(
    private carroCompraService: CarroCompraService,
    private orderService: OrderService,
    private _snackBar: MatSnackBar,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    this.carroCompraService.getCarro().subscribe((carro) => {
      this.carrito = carro;
      this.total = this.carroCompraService.calcularTotal();
    });
  }

  comprar() {
    this.clienteService
      .getClienteSeleccionado()
      .subscribe((clienteSeleccionado) => {
        const cliente = clienteSeleccionado;

        if (!cliente || !cliente.id) {
          this._snackBar.open(
            'Debes seleccionar un cliente para realizar la compra',
            'Cerrar',
            {
              duration: 3000,
            }
          );
          return;
        }

        const clienteId = cliente.id;

        const productos = this.carrito.map((producto) => ({
          productoId: producto.id,
          cantidad: producto.cantidad,
        }));

        const order = { clienteId, productos };

        this.orderService.save(order).subscribe({
          next: (res) => {
            this.carroCompraService.vaciarCarrito();
            this._snackBar.open('Realizaste tu pedido exitosamente', 'Cerrar', {
              duration: 3000,
            });
          },
          error: (err) => {
            console.error('Error al realizar el pedido:', err);
          },
        });
      });
  }

  vaciarCarrito() {
    this.carroCompraService.vaciarCarrito();
  }
}
