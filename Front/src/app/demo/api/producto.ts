import {Categories} from './categories'
export interface Producto {
    id?: string;
    codigo?: string;
    nombre?: string;
    detalle?: string;
    pvp?: number;
    existente?: number;
    categoria?: Categories;
    imagen?: string;
    costoProveedor?: number;
    iva ?: boolean ;
    proveedor ?:string;
}
