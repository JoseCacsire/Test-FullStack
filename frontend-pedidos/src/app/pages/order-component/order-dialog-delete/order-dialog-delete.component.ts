import { Component, Inject } from '@angular/core';
import { OrderService } from '../../../services/order.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { switchMap } from 'rxjs';
import { MaterialModule } from '../../../material/material.module';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-dialog-delete',
  standalone: true,
  imports: [MaterialModule,CommonModule],
  templateUrl: './order-dialog-delete.component.html',
  styleUrl: './order-dialog-delete.component.css'
})
export class OrderDialogDeleteComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) private id: number,
    private _dialogRef: MatDialogRef<OrderDialogDeleteComponent>,
    private orderService: OrderService,
  ) { }

  ngOnInit(): void {
  }

  close() {
    this._dialogRef.close();
  }

  delete() {
    this.orderService.delete(this.id)
        .pipe(switchMap(() => this.orderService.getOrders()))
        .subscribe((data) => {
          this.orderService.setOrderChange(data);
          this.orderService.setMessageChange('Orden eliminada');
        });
        this.close();
  }

}
