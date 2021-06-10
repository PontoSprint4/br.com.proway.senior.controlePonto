import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JornadaListagemComponent } from './jornada-listagem/jornada-listagem.component';
import { PontoMarcarComponent } from './ponto-marcar/ponto-marcar.component';
import { TurnoDetailsComponent } from './turno-details/turno-details.component';
import { TurnoIndexComponent } from './turno-index/turno-index.component';

const routes: Routes = [
  { path: 'turno-index', component: TurnoIndexComponent },
  { path: 'turno-details', component: TurnoDetailsComponent },
  { path: 'ponto-marcar', component: PontoMarcarComponent },
  { path: 'jornada-listagem', component: JornadaListagemComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }