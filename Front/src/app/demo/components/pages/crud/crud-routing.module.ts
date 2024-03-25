import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CrudComponent } from './crud.component';
import {ClienteComponent} from './cliente/cliente.component'
import {FacturacionComponent} from './facturacion/facturacion.component'

@NgModule({
	imports: [RouterModule.forChild([
		{ path: 'inventario', component: CrudComponent },
        { path: 'clientes', component: ClienteComponent },
        { path: 'facturacion', component: FacturacionComponent }
	])],
	exports: [RouterModule]
})
export class CrudRoutingModule { }
