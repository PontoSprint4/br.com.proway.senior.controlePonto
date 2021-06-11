import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Jornada } from '../jornada';
import { JornadaService } from '../jornada.service';
import { Turno } from '../turno';
import { TurnoService } from '../turno.service';

@Component({
  selector: 'app-jornada-editar',
  templateUrl: './jornada-editar.component.html',
  styleUrls: ['./jornada-editar.component.css']
})
export class JornadaEditarComponent implements OnInit {

  public jornada : Jornada = {} as Jornada;
  public turnos : Turno[] = [];

  constructor(private jornadaService : JornadaService, private turnoService : TurnoService, private route: ActivatedRoute){}

  nomeTurno!: Turno;

  public selecionado : Jornada = {} as Jornada;

  ngOnInit(): void {
    this.getTurno();
    const routeParams = this.route.snapshot.paramMap;
    this.jornadaService.getJornada(Number(routeParams.get('id')))
      .subscribe((res)=>{this.selecionado = res})
  }

  getTurno(){
    this.turnoService.getAll()
    .subscribe((turno)=> this.turnos = turno);
  }

  atualizarJornada(){
    this.jornadaService.updateJornada(this.selecionado.id, this.selecionado)
    .subscribe((res)=>{console.log(res)})

    history.back();
  }

}
