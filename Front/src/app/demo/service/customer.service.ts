import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../api/customer';

@Injectable()
export class CustomerService {

    constructor(private http: HttpClient) { }

    getCustomersSmall() {
        return this.http.get<any>('http://localhost:8080/v1/sysreserva/clientes')
            .toPromise()
            .then(res => res as Customer[])
            .then(data => data);
    }

    getCustomersMedium() {
        return this.http.get<any>('http://localhost:8080/v1/sysreserva/clientes')
            .toPromise()
            .then(res => res as Customer[])
            .then(data => data);
    }

    getCustomersLarge() {
        return this.http.get<any>('http://localhost:8080/v1/sysreserva/clientes')
            .toPromise()
            .then(res => res as Customer[])
            .then(data => data);
    }
}
