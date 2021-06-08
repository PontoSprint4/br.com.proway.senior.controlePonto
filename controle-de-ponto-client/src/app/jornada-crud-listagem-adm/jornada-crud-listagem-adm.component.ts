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

  public jornadas = [{data: Date.now(), idPessoa: 11, turno: { idTurno: 11, horaInicio: "08:00:00.207", horaFim: "18:00:00.207", nomeTurno: "Turno daora", minutosTrabalhados: 600, pessoasNoTurno: [11,12,13]}}];
  
  public pontos = [{momentoPonto: Date.now(), idPessoa: 12},{momentoPonto: Date.now(), idPessoa: 12}]
}
