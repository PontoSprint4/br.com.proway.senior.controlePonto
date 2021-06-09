import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';
import { JornadaCrudListagemAdmComponent } from './jornada-crud-listagem-adm/jornada-crud-listagem-adm.component';
import { PontoCriarComponent } from './ponto-criar/ponto-criar.component';
import { PontoEditarComponent } from './ponto-editar/ponto-editar.component';
import { JornadaCriarComponent } from './jornada-criar/jornada-criar.component';
import { JornadaEditarComponent } from './jornada-editar/jornada-editar.component';


@NgModule({
  declarations: [
    AppComponent,
    PontoCrudListagemComponent,
    JornadaCrudListagemAdmComponent,
    PontoCriarComponent,
    PontoEditarComponent,
    JornadaCriarComponent,
    JornadaEditarComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
