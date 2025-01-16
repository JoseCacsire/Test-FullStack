import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Order } from '../model/order';
import { OrderEstado } from '../model/orderEstado';

const URL = `${environment.HOST}/pedido`

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:HttpClient) {
  }


  private sellingChange: Subject<Order[]> = new Subject<Order[]>();


  private messageChange: Subject<string> = new Subject<string>();


  
  setOrderChange(orders: Order[]) {
    this.sellingChange.next(orders);
  }

  getOrderChange() {
    return this.sellingChange.asObservable();
  }

  setMessageChange(message: string) {
    this.messageChange.next(message);
  }

  getMessageChange() {
    return this.messageChange.asObservable();
  }

  // Metodos para el CRUD

  getOrders():Observable<Order[]>{
    return this.http.get<Order[]>(URL)
  }

  patch(id: number, estado: OrderEstado){
    return this.http.patch(`${URL}/${id}/estado`,estado);
  }

  save(order: Order){
    return this.http.post(URL,order);
  }

  delete(id: number){
    return this.http.delete(`${URL}/${id}`);
  }

}
