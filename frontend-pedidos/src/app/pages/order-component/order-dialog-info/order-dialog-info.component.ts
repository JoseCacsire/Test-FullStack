import { Component, Inject } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Order } from '../../../model/order';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-order-dialog-info',
  standalone: true,
  imports: [MaterialModule,CommonModule],
  templateUrl: './order-dialog-info.component.html',
  styleUrl: './order-dialog-info.component.css'
})
export class OrderDialogInfoComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public order: any,

  ) { }

  ngOnInit(): void {
  }

}
