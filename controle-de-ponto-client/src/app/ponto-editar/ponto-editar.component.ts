import { Component, OnInit } from '@angular/core';
import { Ponto } from '../ponto';
import { PontoService } from '../ponto.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-ponto-editar',
  templateUrl: './ponto-editar.component.html',
  styleUrls: ['./ponto-editar.component.css']
})
export class PontoEditarComponent implements OnInit {

  constructor(private route: ActivatedRoute, private pontoService: PontoService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    this.pontoService.getPonto(Number(routeParams.get('id')))
      .subscribe((res)=>{this.selecionado = res})
  }

  public selecionado : Ponto = {} as Ponto;

  atualizarPonto(){
    this.pontoService.updatePonto(this.selecionado.idPonto, this.selecionado)
    .subscribe((res)=>{console.log(res)})

    history.back();
  }
}
