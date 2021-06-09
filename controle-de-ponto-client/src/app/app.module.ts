import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';
import { JornadaCrudListagemAdmComponent } from './jornada-crud-listagem-adm/jornada-crud-listagem-adm.component';
import { JornadaListagemComponent } from './jornada-listagem/jornada-listagem.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  declarations: [
    AppComponent,
    PontoCrudListagemComponent,
    JornadaCrudListagemAdmComponent,
    JornadaListagemComponent,
  ],
  imports: [
    BrowserModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
