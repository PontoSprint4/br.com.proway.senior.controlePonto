import { Component, OnInit } from '@angular/core';
import { Turno } from '../turno';
import { TurnoService } from '../turno.service';

@Component({
  selector: 'app-turno-index',
  templateUrl: './turno-index.component.html',
  styleUrls: ['./turno-index.component.css']
})
export class TurnoIndexComponent implements OnInit {

  constructor(private turnoService: TurnoService) {
  }

  public turnos : Turno[] = [];

  ngOnInit(): void {
    this.getAll();
  }

  getAll(){
    this.turnoService.getAll()
      .subscribe(
        (turno)=>{this.turnos = turno}
      );
  }

  delete(id : number){
    this.turnoService.deleteTurno(id)
      .subscribe(
        (res)=>{
          console.log(res);
          this.getAll()
        }
      )
  }

}
