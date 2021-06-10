
import { Component, OnInit } from '@angular/core';
import { Ponto } from '../ponto';
import { PontoService } from '../ponto.service';

@Component({
  selector: 'app-ponto-crud-listagem',
  templateUrl: './ponto-crud-listagem.component.html',
  styleUrls: ['./ponto-crud-listagem.component.css']
})
export class PontoCrudListagemComponent implements OnInit {

  constructor(private pontoService: PontoService) { }

  public pontos : Ponto[] = [
    {idPonto: 666, momentoPonto: this.pontoService.formatarData(Date.now()), idPessoa: 12},
    {idPonto: 666, momentoPonto: this.pontoService.formatarData(Date.now()), idPessoa: 12}]

  public pontoSelecionado : Ponto = {} as Ponto;
  
  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.pontoService.getAll()
      .subscribe(
        (ponto)=>{this.pontos = ponto}
      );
  }

  getOne(id : number){
    this.pontoService.getPonto(id)
    .subscribe(
      ponto => {console.log("callbacking");
      this.pontoSelecionado = ponto}
    );
  }

  create(ponto : Ponto){
    this.pontoService.createPonto(ponto)
      .subscribe((answer)=>{
        console.log(answer) ;
        this.getAll();
      });
  }

  update(id: number, novoPonto : Ponto){
    this.pontoService.updatePonto(id, novoPonto)
      .subscribe((answer)=>{
        console.log(answer);
        this.getAll();
      });
  }

  delete(id :number){
    this.pontoService.deletePonto(id)
      .subscribe(
        (answer)=>{
          console.log(answer);
          this.getAll();
        }
      );
  }

  
}
