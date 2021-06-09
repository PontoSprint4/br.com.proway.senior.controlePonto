import { Component } from '@angular/core';

@Component({
  selector: 'app-turno-index',
  templateUrl: './turno-index.component.html',
  styleUrls: ['./turno-index.component.css']
})
export class TurnoIndexComponent { 
  
  public Turnos = [
    {
    id: 0,
    nomeTurno: 'turno 0',
    horaInicio: new Date(2021, 6, 8, 8, 0),
    horaFim: new Date(2021, 6, 8, 18, 0),
    minutosTrabalho: 600,
    pessoasNoTurno: [0, 1, 2],
  },  
  {
    id: 1,
    nomeTurno: 'turno 1',
    horaInicio: new Date(2021, 6, 8, 8, 0),
    horaFim: new Date(2021, 6, 8, 18, 0),
    minutosTrabalho: 600,
    pessoasNoTurno: [0, 1, 2],
  },
  {
    id: 2,
    nomeTurno: 'turno 2',
    horaInicio: new Date(2021, 6, 8, 8, 0),
    horaFim: new Date(2021, 6, 8, 18, 0),
    minutosTrabalho: 600,
    pessoasNoTurno: [0, 1, 2],
  }
  ]

}
