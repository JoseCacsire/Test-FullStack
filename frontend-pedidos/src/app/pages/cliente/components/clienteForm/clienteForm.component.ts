import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { ClienteService } from '../../../../services/cliente.service';
import { ClienteAgregado } from '../../../../model/clienteAgregado';
import { CommonModule } from '@angular/common';
import { FormErrorComponent } from "../../../../common/components/form-error/form-error.component";
import { validadoEmail, validadorNombre } from '../../../../common/validators/validator';

@Component({
  selector: 'app-cliente-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatIcon,
    MatButtonModule,
    MatInputModule,
    CommonModule,
    FormErrorComponent
],
  templateUrl: './clienteForm.component.html',
  styleUrl: './clienteForm.component.css',
  
})
export class ClienteFormComponent { 

  clienteForm:FormGroup
  nombreControl:FormControl<string> = new FormControl<string>("",[Validators.required],[validadorNombre()])
  correoControl:FormControl<string> = new FormControl<string>("",[Validators.required,Validators.email],[validadoEmail()])

  constructor(private fb:FormBuilder,private clienteService:ClienteService){
    this.clienteForm = this.fb.group({
      nombre:this.nombreControl,
      correo:this.correoControl
    })
  }

  agregarCliente(){
    if (!this.clienteForm.valid) {
      return
    }
    
    this.clienteService.agregarCliente(this.clienteForm.value as ClienteAgregado).subscribe(val=>{

    })
  }


}
