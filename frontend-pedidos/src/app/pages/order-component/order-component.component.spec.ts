import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderComponentComponent } from './order-component.component';

describe('OrderComponentComponent', () => {
  let component: OrderComponentComponent;
  let fixture: ComponentFixture<OrderComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrderComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
