
import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/demo/api/product';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { CustomerService } from 'src/app/demo/service/customer.service';
import { Customer } from 'src/app/demo/api/customer';
import { Producto } from 'src/app/demo/api/producto';
import { ProductService } from 'src/app/demo/service/product.service';
import { FormaPago } from 'src/app/demo/api/formasPago';
import { CatalogoService } from 'src/app/demo/service/catalogo.service';
import { Factura } from 'src/app/demo/api/factura';
import { detalleFactura } from 'src/app/demo/api/detalleFactura';


@Component({
  templateUrl: './facturacion.component.html',
  providers: [MessageService]
})
export class FacturacionComponent implements OnInit{

    facturaActual:Factura  = {};
    total:number = 0;


    productDialog: boolean = false;
    finalizarCompraDialog :boolean=false;
    dialogCliente : boolean = false;

    listaFormaPago : FormaPago 	[] = [];
    selectedFormaPago : FormaPago = {};
    valorPagoFormaPago : number;
    formasPagosSelecteds : FormaPago 	[] = [];

    product: Producto = {};
    products: Producto[] = [];
    deleteProductDialog: boolean = false;
    deleteProductsDialog: boolean = false;
    cantidadProducto : number = 1;
    codigo:string ;

    productosDetalle: detalleFactura[] = [];
    productoDetalle: detalleFactura ={};

    customers: Customer[] = [];
    customer: Customer = {};
    selectedCustomers: Customer[] = [];
    dni:string;

    submitted: boolean = false;
    cols: any[] = [];
    statuses: any[] = [];
    rowsPerPageOptions = [5, 10, 20];
    loading: boolean = true;



    constructor(private service: CustomerService, private messageService: MessageService
        ,private productService: ProductService, private catalogoService : CatalogoService) { }

    ngOnInit() {
        this.facturaActual.numFactura="002413";
        this.facturaActual.fechaEmision= new  Date();


        this.loadCustomers();
        this.getFormaPagos();
        this.cols = [
            { field: 'nombres', header: 'Nombres' },
            { field: 'identificacion', header: 'Identificacion' },
            { field: 'direccion', header: 'Direccion' }
        ];


    }
    loadCustomers(){
        this.service.getCustomersLarge().then(data =>
            this.customers = data);

    }

    getFormaPagos(){
        this.catalogoService.getFormasPAgo().then(data=>this.listaFormaPago=data)
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
    hideDialogFinalizarCompra() {
        this.finalizarCompraDialog = false;
    }
    aponeDialogFinalizarCompra(){
        this.CalculoIva();
        this.finalizarCompraDialog = true;

    }
    CalculoIva(){
        let subtotal = this.getTotal()/1.12;
        let iva = subtotal * 0.12 ;
        this.facturaActual.iva = parseFloat((iva).toFixed(2));
        this.facturaActual.subtotal = parseFloat((subtotal).toFixed(2));
        this.facturaActual.total = this.getTotal();
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

    onKeydownMain(event:any,estador:any):  void {
        if ((event.key === "Enter") && estador==1){
            this.searchProduct()
          }else  if ((event.key === "Enter") && estador==2){
            this.SeleccionarCliente();
          }
    }

    SeleccionarCliente(){
        this.customer = {};
        let validar : boolean = false;
        let err :any;
        if(this.dni){
            this.service.getCustomersByDni(this.dni)
            .subscribe({
                next: data => {
                    this.customer = data;     },
                error: error => {
                    validar = true;
                    err =error
                this.dialogCliente = false;
                }
            })

            if(validar){
                try {
                    this.messageService.add({ key: 'tst', severity: 'error', summary: 'Error Message'
                , detail: err.error.Error });
                } catch (error) {
                    console.log(error)
                }
            }


    }
    }

    mdf(msj :any){


        this.messageService.add({ key: 'tst',severity: 'success', summary: 'Successful', detail: 'Product Created', life: 3000 });

        this.messageService.add({ key: 'tst',severity: 'error', summary: 'Error'
        , detail: msj, life: 3000 });

    }

    getValoresdePago():number{
        if(this.formasPagosSelecteds.length >0){
            let  suma=0 ;
                for(let i = 0 ; i < this.formasPagosSelecteds.length ; i++ ){
                   suma = suma + this.formasPagosSelecteds[i].totalPago }
                   return this.getTotal()-suma;
        }else {
            return this.getTotal();
        }


    }

    addFormasPago(){
        this.selectedFormaPago.totalPago = this.valorPagoFormaPago;
        this.selectedFormaPago.factura = this.facturaActual;
        if(this.selectedFormaPago.id ||this.selectedFormaPago.totalPago >0){
            this.formasPagosSelecteds.push(this.selectedFormaPago);
            this.selectedFormaPago = {};
            this.valorPagoFormaPago = 0;
            this.getValoresdePago();
        }

    }

    deleteFormaPago(index:any ){
        this.formasPagosSelecteds = this.formasPagosSelecteds.slice(index)

        const filtered: FormaPago[] = [];
        for (let i = 0; i <  this.formasPagosSelecteds.length; i++) {
            if (i!=index) {
                filtered.push(this.formasPagosSelecteds[i]);
            }
        }

        this.formasPagosSelecteds = filtered;

    }

    guardarVenta(){
        this.facturaActual.cliente = this.customer;
        console.log( this.formasPagosSelecteds)
       console.log( this.productosDetalle)
       console.log( this.facturaActual)
    }
}
