import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/api/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { CustomerService } from 'src/app/demo/service/customer.service';
import { Customer } from 'src/app/demo/api/customer';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  providers: [MessageService]
})
export class ClienteComponent implements OnInit{
    productDialog: boolean = false;

    deleteProductDialog: boolean = false;

    deleteProductsDialog: boolean = false;

    customers: Customer[] = [];

    customer: Customer = {};

    selectedCustomers: Customer[] = [];

    submitted: boolean = false;

    cols: any[] = [];

    statuses: any[] = [];

    rowsPerPageOptions = [5, 10, 20];

    constructor(private service: CustomerService, private messageService: MessageService) { }

    ngOnInit() {
        this.loadCustomers();
        this.cols = [
            { field: 'nombres', header: 'Nombres' },
            { field: 'identificacion', header: 'Identificacion' },
            { field: 'direccion', header: 'Direccion' }
        ];


    }

    openNew() {
        this.customer = {};
        this.submitted = false;
        this.productDialog = true;
    }
    loadCustomers(){
        this.service.getCustomersLarge().then(data =>
            this.customers = data).
            then(data => console.log(data));
    }

    deleteSelectedProducts() {
        this.deleteProductsDialog = true;
    }

    editProduct(customer: Customer) {
        this.customer = { ...customer };
        this.productDialog = true;
    }

    deleteProduct(customer: Customer) {
        this.deleteProductDialog = true;
        this.customer = { ...customer };
    }

    confirmDeleteSelected() {
        this.deleteProductsDialog = false;
       for (let custumer of this.selectedCustomers){
            this.deleteCustomer(custumer.id);
          }
       this.selectedCustomers = [];
    }


    confirmDelete() {
        this.deleteProductDialog = false;
        this.service.deleteCustomer(this.customer.id).then (() => {
            this.loadCustomers();
            this.messageService.add({ severity: 'success',
             summary: 'Successful', detail: 'Cliente eliminado', life: 3000 });
            this.customer = {};

        }).catch(()=>{});
    }

    deleteCustomer(id:any){
        this.service.deleteCustomer(id).then (() => {
            this.loadCustomers();
            this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Cliente eliminado', life: 2000 });
            this.customer = {};
        }).catch((error) =>
        {console.log(error)});
    }
    hideDialog() {
        this.productDialog = false;
        this.submitted = false;
    }

    saveProduct() {
        this.submitted = true;

        if (this.customer.nombres?.trim()) {
            if (this.customer.id) {

                    // Edit
                    this.customer.tipoIdentificacion =this.customer.identificacion.length==13 ? '2':'1';
                    this.service.updateCustomer(this.customer ).then(() =>
                    {
                        this.loadCustomers();
                        this.messageService.add({ severity: 'success', summary: 'Successful'
                        , detail: 'Cliente actualizado', life: 3000 });
                        this.productDialog = false;
                        this.customer = {};
                    }) .catch(HttpErrorResponse =>
                        {this.productDialog = false;
                            this.messageService.add({ severity: 'error', summary: 'Error'
                            , detail: HttpErrorResponse.error.Error, life: 3000 });
                        });


            } else {
                this.customer.tipoIdentificacion =this.customer.identificacion.length==13 ? '2':'1';
                this.service.createCustomer(this.customer).subscribe(data =>  {
                    this.messageService.add({ severity: 'success', summary: 'Successful'
                    , detail: 'Cliente creado', life: 3000 });
                    this.loadCustomers()

                });

            //this.products = [...this.products];
            this.productDialog = false;
            this.customer = {};
        }
         }
    }

    findIndexById(id: string): number {
            let index = -1;
       /* for (let i = 0; i < this.products.length; i++) {
            if (this.products[i].id === id) {
                index = i;
                break;
            }
        }*/

        return index;
    }

    createId(): string {
        let id = '';
        const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for (let i = 0; i < 5; i++) {
            id += chars.charAt(Math.floor(Math.random() * chars.length));
        }
        return id;
    }

    onGlobalFilter(table: Table, event: Event) {
        table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
    }
}

