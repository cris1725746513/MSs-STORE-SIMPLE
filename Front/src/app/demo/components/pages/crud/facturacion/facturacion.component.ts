
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/api/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { CustomerService } from 'src/app/demo/service/customer.service';
import { Customer } from 'src/app/demo/api/customer';
import { CountryService } from 'src/app/demo/service/country.service';
import { Producto } from 'src/app/demo/api/producto';
import { ProductService } from 'src/app/demo/service/product.service';
import { FormaPago } from 'src/app/demo/api/formasPago';
import { Factura } from 'src/app/demo/api/factura';
import { detalleFactura } from 'src/app/demo/api/detalleFactura';

@Component({
  selector: 'app-facturacion',
  templateUrl: './facturacion.component.html',
  styleUrl: './facturacion.component.scss',
  providers: [MessageService]
})
export class FacturacionComponent implements OnInit{

    facturaActual:Factura  = {};

    selectedCountryAdvanced: any[] = [];
    countries: any[] = [];
    filteredCountries: any[] = [];
    selectedMulti: any[] = [];
    cantidadProducto : number = 1;

    total:number = 0;
    codigo:string ;
    productosDetalle: detalleFactura[] = [];
    productoDetalle: detalleFactura ={};
    loading: boolean = true;

    productDialog: boolean = false;

    listaFormaPago : FormaPago 	[] = [];
    selectedFormaPago : FormaPago = {};

    formasPagosSelecteds : FormaPago 	[] = [];

    product: Producto = {};

    products: Producto[] = [];

    deleteProductDialog: boolean = false;

    deleteProductsDialog: boolean = false;

    customers: Customer[] = [];

    customer: Customer = {};

    selectedCustomers: Customer[] = [];

    submitted: boolean = false;

    cols: any[] = [];

    statuses: any[] = [];

    rowsPerPageOptions = [5, 10, 20];



    constructor(private service: CustomerService, private messageService: MessageService
        , private countryService: CountryService,private productService: ProductService) { }

    ngOnInit() {
        this.facturaActual.numFactura="002413";
        this.facturaActual.fechaEmision= new  Date();

        this.countryService.getCountries().then(countries => {
            this.countries = countries;
        });
        this.loadCustomers();
        this.cols = [
            { field: 'nombres', header: 'Nombres' },
            { field: 'identificacion', header: 'Identificacion' },
            { field: 'direccion', header: 'Direccion' }
        ];


    }
    loadCustomers(){
        this.service.getCustomersLarge().then(data =>
            this.customers = data).
            then(data => console.log(data));
    }

    filterCountry(event: any) {
        const filtered: any[] = [];
        const query = event.query;
        for (let i = 0; i < this.countries.length; i++) {
            const country = this.countries[i];
            if (country.name.toLowerCase().indexOf(query.toLowerCase()) == 0) {
                filtered.push(country);
            }
        }

        this.filteredCountries = filtered;
    }

    deleteProduct(index:any ){
        this.productosDetalle = this.productosDetalle.slice(index)

        const filtered: detalleFactura[] = [];
        for (let i = 0; i <  this.productosDetalle.length; i++) {
            if (i!=index) {
                filtered.push(this.productosDetalle[i]);
            }
        }

        this.productosDetalle = filtered;

    }

    hideDialog() {
        this.productDialog = false;
        this.submitted = false;
        this.product = {};
        this.cantidadProducto=1;
        this.codigo =  '';
    }

    openNew() {
        this.product = {};
        this.submitted = false;
        this.productDialog = true;
    }
    searchProduct(){
        if(this.codigo){
            this.productService.getProductoByCodigo(this.codigo).then(data =>
            {
               this.openNew();
               this.product = {...data};
            }) .catch(HttpErrorResponse =>
                {this.productDialog = false;
                    this.messageService.add({ severity: 'error', summary: 'Error'
                    , detail: HttpErrorResponse.error.Error, life: 3000 });
                });
        }
    }
    saveProduct(){
        if(this.product && this.cantidadProducto > 0){
            this.submitted =true;
            this.productoDetalle.cantidad=this.cantidadProducto;
            this.productoDetalle.precioUnitario=this.product.pvp;
            this.productoDetalle.producto = this.product;
            this.productoDetalle.factura = this.facturaActual;
            this.productoDetalle.precioTotal = this.cantidadProducto * this.product.pvp;
            let addprouct = this.productoDetalle;
            this.productosDetalle.push(addprouct);
            this.productoDetalle = {};
            this.product = {};
            this.hideDialog()
        }
    }

    getTotal(): number{
        let initialValue = 0;
        this.total = this.productosDetalle.reduce(
            (accumulator, currentValue) => accumulator + currentValue.precioTotal,
            initialValue,
          );

        return this.total;
    }

    onKeydownMain(event:any):  void {
        if ((event.key === "Enter")){
            this.searchProduct()
          }
    }
}
