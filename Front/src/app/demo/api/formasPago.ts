import { Factura } from 'src/app/demo/api/factura';
export interface FormaPago {
    id?: number;
    descripcion?: string;
    valor?:number;
    totalPago?:number;
    factura?:Factura;
}
