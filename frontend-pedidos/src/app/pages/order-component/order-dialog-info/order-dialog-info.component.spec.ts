import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderDialogInfoComponent } from './order-dialog-info.component';

describe('OrderDialogInfoComponent', () => {
  let component: OrderDialogInfoComponent;
  let fixture: ComponentFixture<OrderDialogInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderDialogInfoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderDialogInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
