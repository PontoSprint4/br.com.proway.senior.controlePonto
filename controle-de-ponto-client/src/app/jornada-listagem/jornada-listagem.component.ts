import { Component, OnInit } from '@angular/core';
import { Jornada } from '../jornada';
import { JornadaService } from '../jornada.service';

@Component({
  selector: 'app-jornada-listagem',
  templateUrl: './jornada-listagem.component.html',
  styleUrls: ['./jornada-listagem.component.css'],
})
export class JornadaListagemComponent implements OnInit {
  public isCollapsed: boolean[] = [];
  public jornadas: Jornada[] = [];

  constructor(private jornadaService: JornadaService) {}

  ngOnInit(): void {
    this.getAll();
  }
  
  getAll(){
    this.jornadaService.getAll()
      .subscribe(
        (jornada)=>{this.jornadas = jornada}
      );
  }

}
