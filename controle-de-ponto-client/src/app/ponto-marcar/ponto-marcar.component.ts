import { Component } from '@angular/core';

@Component({
  selector: 'app-ponto-marcar',
  templateUrl: './ponto-marcar.component.html',
  styleUrls: ['./ponto-marcar.component.css']
})
export class PontoMarcarComponent {  
  constructor(){
    setInterval(() => {
      this.horaAtual = Date.now();
    }, 100);
  }
  public horaAtual: number | undefined;


}
