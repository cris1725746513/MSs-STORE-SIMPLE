import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Producto } from '../api/producto';
import { Product } from '../api/product';
import { Observable } from 'rxjs';
import { Categories } from '../api/categories';

@Injectable({providedIn:'root'})
export class ProductService {

    baseURL: string = "http://localhost:8080/v1/inventario/productos";
    baseUrlReader :string = "http://localhost:8080/v1/inventario/reader/productos"
    constructor(private http: HttpClient) { }

    getProductsSmall() {
        return this.http.get<any>('assets/demo/data/products-small.json')
            .toPromise()
            .then(res => res.data as Producto[])
            .then(data => data);
    }

    getProducts() {
        return this.http.get<any>(this.baseUrlReader)
            .toPromise()
            .then(res => res as Producto[])
            .then(data => data);
    }

    getCategories() {
        return this.http.get<any>(this.baseUrlReader+'/categorias')
            .toPromise()
            .then(res => res as Categories[])
            .then(data => data);
    }

     createProducto(model:Producto):Observable<Producto>{
        const headers = { 'content-type': 'application/json'}
        return this.http.post<Producto>(this.baseURL,
        model,{'headers':headers});

    }

    updateProducto(model:Producto){
        return this.http.put(this.baseURL+
            "/"+model.id, model).toPromise();
    }

    getProductoByCodigo(codigo:any) {
        return this.http.get<any>(this.baseUrlReader + '/' + codigo)
            .toPromise()
            .then(res => res as Producto)
            .then(data => data);
    }
    deleteProducto(id:any){
        return this.http.delete(this.baseURL+'/' + id).toPromise();
    }


    getProductsMixed() {
        return this.http.get<any>('assets/demo/data/products-mixed.json')
            .toPromise()
            .then(res => res.data as Product[])
            .then(data => data);
    }

    getProductsWithOrdersSmall() {
        return this.http.get<any>('assets/demo/data/products-orders-small.json')
            .toPromise()
            .then(res => res.data as Product[])
            .then(data => data);
    }
}
