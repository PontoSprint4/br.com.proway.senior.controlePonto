import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';

import { AppComponent } from './app.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';
import { JornadaCrudListagemAdmComponent } from './jornada-crud-listagem-adm/jornada-crud-listagem-adm.component';
import { PontoCriarComponent } from './ponto-criar/ponto-criar.component';
import { PontoEditarComponent } from './ponto-editar/ponto-editar.component';
import { JornadaCriarComponent } from './jornada-criar/jornada-criar.component';
import { JornadaEditarComponent } from './jornada-editar/jornada-editar.component';
import { JornadaListagemComponent } from './jornada-listagem/jornada-listagem.component';
import { TurnoIndexComponent } from './turno-index/turno-index.component';
import { TurnoDetailsComponent } from './turno-details/turno-details.component';
import { PontoMarcarComponent } from './ponto-marcar/ponto-marcar.component';


@NgModule({
  declarations: [
    AppComponent,
    TurnoIndexComponent,
    TurnoDetailsComponent,
    JornadaCrudListagemAdmComponent,
    PontoCriarComponent,
    PontoEditarComponent,
    PontoCrudListagemComponent,
    JornadaCriarComponent,
    JornadaEditarComponent,
    JornadaListagemComponent,
    PontoMarcarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
