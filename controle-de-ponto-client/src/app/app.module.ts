import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';
import { JornadaListagemComponent } from './jornada-listagem/jornada-listagem.component';

@NgModule({
  declarations: [
    AppComponent,
    PontoCrudListagemComponent,
    JornadaListagemComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
