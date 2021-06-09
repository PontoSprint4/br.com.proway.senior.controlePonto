import { Component } from '@angular/core';
import { PontoService } from './ponto.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor (public pontoService: PontoService ){}

  getAll(){
    this.pontoService.getAll();
  }
}
