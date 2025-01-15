import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderDialogDeleteComponent } from './order-dialog-delete.component';

describe('OrderDialogDeleteComponent', () => {
  let component: OrderDialogDeleteComponent;
  let fixture: ComponentFixture<OrderDialogDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderDialogDeleteComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderDialogDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
