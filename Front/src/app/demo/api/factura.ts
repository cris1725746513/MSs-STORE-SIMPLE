import { FormaPago } from './formasPago';
import { Customer } from "./customer";

export interface Factura {
    id?: number;
    numFactura?:string;
    fechaEmision?: Date;
    cliente?:Customer;
    total?:number;
    iva?:number;
    formaPago?:FormaPago[];
}
