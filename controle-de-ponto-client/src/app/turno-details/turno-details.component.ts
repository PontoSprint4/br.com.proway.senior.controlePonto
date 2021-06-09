import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-turno-details',
  templateUrl: './turno-details.component.html',
  styleUrls: ['./turno-details.component.css']
})
export class TurnoDetailsComponent { 

  @Input() public pessoas? : pessoa[];
  // TODO: Fazer isso funcionar (receber de outro componente)
}

export interface pessoa {id: number,nomePessoa: string}