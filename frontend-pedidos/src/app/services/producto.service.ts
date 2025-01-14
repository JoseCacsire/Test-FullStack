import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../model/producto';

const URL = `${environment.HOST}/producto`

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  constructor(private http:HttpClient) {

  }

  getProductos():Observable<Producto[]>{
    return this.http.get<Producto[]>(URL)
  }

}
