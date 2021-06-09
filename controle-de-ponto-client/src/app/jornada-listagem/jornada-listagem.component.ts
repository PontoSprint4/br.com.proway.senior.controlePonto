import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-jornada-listagem',
  templateUrl: './jornada-listagem.component.html',
  styleUrls: ['./jornada-listagem.component.css'],
})
export class JornadaListagemComponent implements OnInit {
  public isCollapsed: boolean[] = [];

  constructor() {}

  ngOnInit(): void {}

  public turno = {
    id: 0,
    nomeTurno: 'turno 1',
    horaInicio: new Date(2021, 6, 8, 8, 0),
    horaFim: new Date(2021, 6, 8, 18, 0),
    minutosTrabalho: 600,
    pessoasNoTurno: [0, 1, 2],
  };
  public pontos = [
    { id: 0, momentoPonto: Date.now(), idPessoa: 12 },
    { id: 1, momentoPonto: Date.now(), idPessoa: 12 },
  ];
  public jornadas = [
    {
      id: 0,
      idPessoa: this.pontos[0].idPessoa,
      data: new Date(2021, 6, 8),
      minutosTrabalhados: 836,
      estado: 'FECHADO',
      turno: this.turno,
      listaPonto: this.pontos,
      dia: 9,
      mes: 6,
      ano: 2021,
    },
    {
      id: 1,
      idPessoa: this.pontos[0].idPessoa,
      data: new Date(2021, 6, 8),
      minutosTrabalhados: 836,
      estado: 'ABERTO',
      turno: this.turno,
      listaPonto: this.pontos,
      dia: 9,
      mes: 6,
      ano: 2021,
    },
  ];
}
