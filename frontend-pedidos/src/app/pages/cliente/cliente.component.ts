import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../../services/cliente.service';
import { Cliente } from '../../model/cliente';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { ClienteFormComponent } from "./components/clienteForm/clienteForm.component";
import { MatDivider } from '@angular/material/divider';



@Component({
  selector: 'app-cliente',
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIcon,
    RouterLink,
    ClienteFormComponent,
    MatDivider
],
  templateUrl: './cliente.component.html',
  styleUrl: './cliente.component.css',
})
export class ClienteComponent implements OnInit{ 

  clientes:Cliente[] = []

  selected:Cliente 

  constructor(private clienteService:ClienteService){
    
  }
  ngOnInit(): void {
    this.clienteService.getClienteChange().subscribe(clientes=>{
      this.clientes= clientes
      
    })
  }

  seleccionar(clienteSeleccionado:Cliente){
    this.selected=clienteSeleccionado
    this.clienteService.setClienteSeleccionado(clienteSeleccionado)
  }
}
