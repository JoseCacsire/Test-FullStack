import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment.development';
import { BehaviorSubject, Observable,tap } from 'rxjs';
import { Cliente } from '../model/cliente';
import { ClienteAgregado } from '../model/clienteAgregado';


const URL = `${environment.HOST}/clientes`

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  clientes: Cliente[]=[]

  private clienteSeleccionado:BehaviorSubject<Cliente> = new BehaviorSubject<Cliente>(null)

  getClienteSeleccionado():Observable<Cliente>{
    return this.clienteSeleccionado.asObservable()
  }

  setClienteSeleccionado(cliente:Cliente){
    this.clienteSeleccionado.next(cliente)
  }
  
  private clienteChange:BehaviorSubject<Cliente[]>=new BehaviorSubject<Cliente[]>(this.clientes)

  constructor(private http:HttpClient) { 
    this.getClientes().subscribe()
  }

  getClientes():Observable<Cliente[]>{
    return this.http.get<Cliente[]>(URL).pipe(
      tap(clientes=>{
        this.clientes=clientes
        this.setClienteChange(this.clientes)
      })
    )
  }


  agregarCliente(cliente:ClienteAgregado):Observable<Cliente>{
    return this.http.post<Cliente>(URL,cliente).pipe(
      tap(
        cliente=>{
          this.clientes.push(cliente)
          this.setClienteChange(this.clientes)
        }
      )
    )
  }

  getClienteChange(){
    return this.clienteChange.asObservable()
  }

  setClienteChange(clientes:Cliente[]){
    this.clienteChange.next(clientes)
  }

}
