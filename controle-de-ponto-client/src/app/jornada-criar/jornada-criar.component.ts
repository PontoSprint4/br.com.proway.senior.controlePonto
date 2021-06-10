import { Component, OnInit } from '@angular/core';
import { Jornada } from '../jornada';
import { JornadaService } from '../jornada.service';

@Component({
  selector: 'app-jornada-criar',
  templateUrl: './jornada-criar.component.html',
  styleUrls: ['./jornada-criar.component.css']
})
export class JornadaCriarComponent implements OnInit {

  public jornada : Jornada = {} as Jornada;
  public turnos : Jornada[] = [];

  /*importar turnoService*/
  constructor(private jornadaService : JornadaService){}

  ngOnInit(): void {
  }

  /*getTurno(){
    this.turnoService.getAll()
    .subscribe((turno)=> this.turnos = turno));
  }*/

  enviaJornada(jornada : Jornada){
    this.jornadaService.createJornada(jornada)
    .subscribe((res)=>{console.log("Criado o ponto: "+res)});

    this.jornada = {} as Jornada;
    history.back();
  }
}
