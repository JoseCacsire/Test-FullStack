import { Component } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MaterialModule } from '../../material/material.module';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Order } from '../../model/order';
import { OrderService } from '../../services/order.service';
import { MatDialog } from '@angular/material/dialog';
import { OrderDialogInfoComponent } from './order-dialog-info/order-dialog-info.component';

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

  displayedColumns = [
    { def: 'id', hide: true },
    { def: 'username', hide: false },
    { def: 'creation_date', hide: false },
    { def: 'order_state', hide: false },
    { def: 'estado', hide: false },
    { def: 'actions', hide: false },
  ];

  constructor(
    private orderService: OrderService, 
    private _dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource(this.orders);
    this.orderService.getOrders().subscribe((orders) => {
      this.orders = orders;
      this.createTable(this.orders);
    });



  }

  openDialog(order?: Order){
    this._dialog.open(OrderDialogInfoComponent, {
      width: '750px',
      data: order
    });
  }

  deleteOrder(order: Order){
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


  changeState(order: Order){}

}
