import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-jornada-listagem',
  templateUrl: './jornada-listagem.component.html',
  styleUrls: ['./jornada-listagem.component.css']
})
export class JornadaListagemComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public turno = {nomeTurno: "turno 1", inicioTurno: new Date(2021, 6, 8, 8,0), fimTurno: new Date(2021, 6, 8, 18,0)}
  public pontos = [{momentoPonto: Date.now(), idPessoa: 12},{momentoPonto: Date.now(), idPessoa: 12}]
  public jornadas = [{dataJornada: new Date(2021, 6, 8), horasTrabalhadas: 836, status: "FECHADO", turno: this.turno, ponto1: this.pontos[0], ponto2: this.pontos[1]}];
}
