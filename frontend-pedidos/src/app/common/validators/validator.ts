import { HttpClient } from "@angular/common/http";
import { inject } from "@angular/core";
import { AbstractControl, AsyncValidatorFn } from "@angular/forms";

import { delay, map } from "rxjs";
import { environment } from "../../environments/environment.development";

const USER_FILTER_USERNAME= `${environment.HOST}/clientes`

export const validadorNombre = ():AsyncValidatorFn =>{
    const http = inject(HttpClient)

    return (control:AbstractControl)=>{
        if(!control.value) {
            return null
        }
        const url = `${USER_FILTER_USERNAME}/nombre/${control.value}`
        return http.get(url)
            .pipe(
                map(existe => existe? {clienteExiste:true}:null),
                delay(500)
            )

    }
}

export const validadoEmail = ():AsyncValidatorFn =>{
    const http = inject(HttpClient)

    return (control:AbstractControl)=>{
        if(!control.value) {
            return null
        }
        const url = `${USER_FILTER_USERNAME}/correo/${control.value}`
        return http.get(url)
            .pipe(
                map(existe => existe? {emailExiste:true}:null),
                delay(500)
            )

    }
}
