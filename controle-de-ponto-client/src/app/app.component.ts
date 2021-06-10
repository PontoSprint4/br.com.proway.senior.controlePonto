import { DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { Ponto } from './ponto';
import { PontoService } from './ponto.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor (public pontoService: PontoService, private datePipe: DatePipe ){}

  getAll(){
    this.pontoService.getAll();
  }

  getOne(id : number){
    this.pontoService.getPonto(id);
  }

  create(){
    let novoPonto : Ponto = 
      {
        momentoPonto: this.formatarData(Date.now()),
        idPessoa : 999
      }
    this.pontoService.createPonto(novoPonto);
  }

  update(){
    let novoPonto : Ponto = 
      {
        momentoPonto: this.formatarData(Date.now()),
        idPessoa : 456
      }
    this.pontoService.updatePonto(1565, novoPonto);
  }

  delete(){
    this.pontoService.deletePonto(1566)
  }

  formatarData(data : number) : string{
    return this.datePipe.transform(data,'yyyy-MM-ddTHH:mm:ss')!
  }
  
}
