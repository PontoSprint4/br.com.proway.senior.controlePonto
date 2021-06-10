import { Component, OnInit } from '@angular/core';
import { Ponto } from '../ponto';
import { PontoService } from '../ponto.service';

@Component({
  selector: 'app-ponto-criar',
  templateUrl: './ponto-criar.component.html',
  styleUrls: ['./ponto-criar.component.css']
})
export class PontoCriarComponent implements OnInit {

  constructor(private pontoService: PontoService) { }

  public ponto : Ponto = {} as Ponto;

  ngOnInit(): void {
  }

  enviaPonto(ponto : Ponto){
    this.pontoService.createPonto(ponto)
    .subscribe((res)=>{console.log("Criado o ponto: "+res)});
  }

}
