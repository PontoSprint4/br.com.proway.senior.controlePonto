import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JornadaListagemComponent } from './jornada-listagem/jornada-listagem.component';
import { PontoMarcarComponent } from './ponto-marcar/ponto-marcar.component';
import { TurnoDetailsComponent } from './turno-details/turno-details.component';
import { TurnoIndexComponent } from './turno-index/turno-index.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';
import { PontoCriarComponent } from './ponto-criar/ponto-criar.component';
import { PontoEditarComponent } from './ponto-editar/ponto-editar.component';
import { JornadaCrudListagemAdmComponent } from './jornada-crud-listagem-adm/jornada-crud-listagem-adm.component';
import { JornadaCriarComponent } from './jornada-criar/jornada-criar.component';
import { JornadaEditarComponent } from './jornada-editar/jornada-editar.component';

const routes: Routes = [
  { path: '', component: PontoMarcarComponent },
  { path: 'turno-index', component: TurnoIndexComponent },
  { path: 'turno-details', component: TurnoDetailsComponent },
  { path: 'ponto-index', component: PontoCrudListagemComponent },
  { path: 'ponto-create', component: PontoCriarComponent },
  { path: 'ponto-edit/:id', component: PontoEditarComponent },
  { path: 'jornada-crud', component: JornadaCrudListagemAdmComponent },
  { path: 'jornada-create', component: JornadaCriarComponent },
  { path: 'jornada-edit/:id', component: JornadaEditarComponent },
  { path: 'ponto-marcar', component: PontoMarcarComponent },
  { path: 'jornada-listagem', component: JornadaListagemComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
