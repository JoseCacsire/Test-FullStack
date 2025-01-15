import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Order } from '../model/order';

const URL = `${environment.HOST}/pedido`

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http:HttpClient) {

  }

  getOrders():Observable<Order[]>{
    return this.http.get<Order[]>(URL)
  }

  patch(id: number, order: Order){
    return this.http.patch(`${URL}/${id}/estado`,order);
  }

  save(order: Order){
    return this.http.post(URL,order);
  }

}
