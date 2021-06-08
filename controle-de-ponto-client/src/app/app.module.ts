import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { PontoCrudListagemComponent } from './ponto-crud-listagem/ponto-crud-listagem.component';
import { TurnoIndexComponent } from './turno-index/turno-index.component';
import { TurnoDetailsComponent } from './turno-details/turno-details.component';

@NgModule({
  declarations: [
    AppComponent,
    PontoCrudListagemComponent,
    TurnoIndexComponent,
    TurnoDetailsComponent,
    TurnoDetailsComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
