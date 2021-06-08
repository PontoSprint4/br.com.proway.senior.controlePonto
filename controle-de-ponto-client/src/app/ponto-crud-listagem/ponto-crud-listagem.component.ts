import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ponto-crud-listagem',
  templateUrl: './ponto-crud-listagem.component.html',
  styleUrls: ['./ponto-crud-listagem.component.css']
})
export class PontoCrudListagemComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public pontos = [{momentoPonto: Date.now(), idPessoa: 12},{momentoPonto: Date.now(), idPessoa: 12}]

}
