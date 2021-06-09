import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-jornada-crud-listagem-adm',
  templateUrl: './jornada-crud-listagem-adm.component.html',
  styleUrls: ['./jornada-crud-listagem-adm.component.css']
})
export class JornadaCrudListagemAdmComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public turno = {nomeTurno: "turno 1", inicioTurno: new Date(2021, 6, 8, 8,0), fimTurno: new Date(2021, 6, 8, 18,0)}
  public pontos = [{idPonto: 1, momentoPonto: Date.now(), idPessoa: 12},{idPonto: 11, momentoPonto: Date.now(), idPessoa: 12}]
  public jornadas = [{dataJornada: new Date(2021, 6, 8), horasTrabalhadas: 836, status: "FECHADO", turno: this.turno, ponto: [this.pontos[0], this.pontos[1]]}];
}
