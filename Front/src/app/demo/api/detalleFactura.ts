
import {Producto} from './producto'
import {Factura} from './factura'

export interface detalleFactura {
    id?: number;
    producto?: Producto;
    cantidad?: number;
    precioUnitario?: number;
    precioTotal?:number;
    factura? : Factura;
}
