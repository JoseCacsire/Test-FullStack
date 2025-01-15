import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { ClienteService } from '../../../services/cliente.service';
import { Observable } from 'rxjs';
import { Cliente } from '../../../model/cliente';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    RouterLink,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',

})
export class HeaderComponent implements OnInit{ 
  name:Observable<Cliente>

  constructor(private clienteService:ClienteService){

  }
  ngOnInit(): void {
    this.name= this.clienteService.getClienteSeleccionado()
  }

}
