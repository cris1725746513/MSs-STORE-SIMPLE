import { HttpClient , HttpHeaders} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from '../api/customer';
import { Observable } from 'rxjs';

@Injectable({providedIn:'root'})
export class CustomerService{

    baseURL: string = "http://localhost:8081/v1/sysreserva/clientes";
    constructor(private http: HttpClient) { }



    getCustomersSmall() {
        return this.http.get<any>(this.baseURL)
            .toPromise()
            .then(res => res as Customer[])
            .then(data => data);
    }

    getCustomersMedium() {
        return this.http.get<any>(this.baseURL)
            .toPromise()
            .then(res => res as Customer[])
            .then(data => data);
    }

    getCustomersLarge() {
        return this.http.get<any>(this.baseURL)
            .toPromise()
            .then(res => res as Customer[])
            .then(data => data);
    }

    createCustomer(customer:Customer):Observable<Customer>{
        const headers = { 'content-type': 'application/json'}
        return this.http.post<Customer>(this.baseURL,
        customer,{'headers':headers});

    }

    updateCustomer(customer:Customer){
        return this.http.put(this.baseURL+
            "/"+customer.id, customer).toPromise();
    }

    getCustomersByDni(dni:any){
        return this.http.get<Customer>(this.baseURL + '/' + dni)
    }
    deleteCustomer(id:any){
        return this.http.delete(this.baseURL+'/' + id).toPromise();
    }
}
