import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MaterialModule } from '../../material/material.module';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Order } from '../../model/order';
import { OrderService } from '../../services/order.service';
import { MatDialog } from '@angular/material/dialog';
import { OrderDialogInfoComponent } from './order-dialog-info/order-dialog-info.component';
import { OrderDialogDeleteComponent } from './order-dialog-delete/order-dialog-delete.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';
import { OrderEstado } from '../../model/orderEstado';

@Component({
  selector: 'app-order-component',
  standalone: true,
  imports: [MaterialModule,FormsModule,CommonModule],
  templateUrl: './order-component.component.html',
  styleUrl: './order-component.component.css'
})
export class OrderComponentComponent {

  dataSource: MatTableDataSource<Order>;

  orders: Order[];

  orderEstado : OrderEstado;

  displayedColumns = [
    { def: 'id', hide: true },
    { def: 'username', hide: false },
    { def: 'creation_date', hide: false },
    { def: 'order_state', hide: false },
    { def: 'estado', hide: false },
    { def: 'actions', hide: false },
  ];

  constructor(
    private _snackBar: MatSnackBar,
    private orderService: OrderService, 
    private _dialog: MatDialog,
  ) {}

  ngOnInit(): void {

    this.orderService.getOrders().subscribe((data) => {
      this.orders = data;
      this.createTable(this.orders);
    });

    this.orderService.getOrderChange().subscribe((data) => {
      this.createTable(data);
    });

    this.orderService.getMessageChange().subscribe((data) => {
      this._snackBar.open(data, 'Info', {
        duration: 2000,
      });
    });

  }

  openDialog(order?: Order){
    this._dialog.open(OrderDialogInfoComponent, {
      width: '750px',
      data: order
    });
  }

  deleteOrder(id: Number){
    this._dialog.open(OrderDialogDeleteComponent, {
      width: '300px',
      data: id
    });
  }

  createTable(data:Order[]) {
    this.dataSource = new MatTableDataSource(data);
  }
  
  getDisplayedColumns() {
    return this.displayedColumns.filter((cd) => !cd.hide).map((cd) => cd.def);
  }

  applyFilter(e: any) {
    this.dataSource.filter = e.target.value.trim().toLowerCase();
  }


  
  changeState(order: Order) {

    const stateMap = {
      'pendiente': {
        newState: 'cancelado',
        message: 'ESTADO DEL PEDIDO : CANCELADO',
      },
      'cancelado': {
        newState: 'entregado',
        message: 'ESTADO DEL PEDIDO : ENTREGADO',
      },
    };

    const stateInfo = stateMap[order.estado];

    if (stateInfo) {

      const OrderEstado = {
        estado: stateInfo.newState,
      };
      
      this.orderService
        .patch(order.id, OrderEstado)
        .pipe(switchMap(() => this.orderService.getOrders()))
        .subscribe((data) => {
          this.orderService.setOrderChange(data);
          this.orderService.setMessageChange(stateInfo.message);
        });
    } 
    else {
      this.orderService.setMessageChange(
        'PEDIDO YA ENTREGADO'
      );
    }
  }

   

}
