import { Component, OnInit } from '@angular/core';
import { Jornada } from '../jornada';
import { JornadaService } from '../jornada.service';

@Component({
  selector: 'app-jornada-crud-listagem-adm',
  templateUrl: './jornada-crud-listagem-adm.component.html',
  styleUrls: ['./jornada-crud-listagem-adm.component.css'],
})
export class JornadaCrudListagemAdmComponent implements OnInit {
  public isCollapsed: boolean[] = [];
  public jornadas: Jornada[] = [];

  constructor(private jornadaService: JornadaService) {}

  ngOnInit(): void {
    this.getAll();
  }
  
  create(){
    
  }

  getAll(){
    this.jornadaService.getAll()
      .subscribe(
        (jornada)=>{this.jornadas = jornada}
      );
  }
}
