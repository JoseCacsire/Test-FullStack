import { Injectable } from '@angular/core';
import { Producto } from '../model/producto';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarroCompraService {
  private carrito:  Producto[] = JSON.parse(localStorage.getItem("shoppingCart")) || []

  private carro:BehaviorSubject<Producto[]> = new BehaviorSubject(this.carrito)
  constructor() { }

  getCarro():Observable<Producto[]>{
    return this.carro.asObservable()
  }

  guardarCarro(carro:Producto[]){
    this.carro.next(carro)
    localStorage.setItem("shoppingCart",JSON.stringify(carro))
  }

  agregarProducto(producto:Producto){
    const indice = this.carrito.findIndex(product=> product.id===producto.id)
    if (indice!==-1) {
      this.carrito[indice] = {
        ...producto,cantidad: this.carrito[indice].cantidad+1
      }
    }else{
      this.carrito = [...this.carrito,{...producto,cantidad:1}]
    }

    this.guardarCarro(this.carrito)
    
  }

  disminuirProducto(id:number){
    const indice = this.carrito.findIndex(product=> product.id===id)
    if (this.carrito[indice].cantidad===1) {
      this.carrito = [
        ...this.carrito.slice(0, indice),  
        ...this.carrito.slice(indice + 1) 
      ];
    }else{
      this.carrito = this.carrito.map(product=> product.id===id? {...product,cantidad:product.cantidad-1}:product)
    }

    this.guardarCarro(this.carrito)
  }

  vaciarCarrito(){
    this.carrito=[]
    this.guardarCarro(this.carrito)
  }

  eliminarProducto(id:number){
    this.carrito = this.carrito.filter(product=> product.id!==id)
    this.guardarCarro(this.carrito)
  }

  getCantidadProducto(id:number):number{
    const indice = this.carrito.findIndex(product=> product.id===id)
    return indice>=0? this.carrito[indice].cantidad:0 
  }

  calcularTotal():number{
    let total = 0;
    this.carrito.forEach(producto=>{
      total += producto.cantidad*producto.precio
    })

    return total
  }

}
