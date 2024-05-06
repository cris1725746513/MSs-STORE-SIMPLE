import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../api/producto';
import { FormaPago } from '../api/formasPago';
import { Observable } from 'rxjs';
import { Categories } from '../api/categories';

@Injectable({providedIn:'root'})
export class CatalogoService {

    baseUrlReader :string = "http://localhost:8080/v1/inventario/catalogo"
    constructor(private http: HttpClient) { }


    getFormasPAgo() {
        return this.http.get<any>(this.baseUrlReader+'/formaPago')
            .toPromise()
            .then(res => res as FormaPago[])
            .then(data => data);
    }




}
