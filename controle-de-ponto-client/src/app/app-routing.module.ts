import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TurnoDetailsComponent } from './turno-details/turno-details.component';
import { TurnoIndexComponent } from './turno-index/turno-index.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';

const routes: Routes = [
  { path: 'turno-index', component: TurnoIndexComponent },
  { path: 'turno-details', component: TurnoDetailsComponent },
  { path: 'ponto-index', component: PontoCrudListagemComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }