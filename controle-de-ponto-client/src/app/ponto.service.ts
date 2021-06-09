import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Ponto } from './ponto';

@Injectable({
  providedIn: 'root'
})
export class PontoService {

  private pontoUrl = 'http://localhost:8080/api/pontos/';

  constructor(
    private http: HttpClient) { }

  createPonto(ponto: Ponto){

  }

  getPonto(): void {
  
  }

  getAll() : void{
    let lista : Ponto[] = [];
    this.http.get<Ponto>(this.pontoUrl).subscribe(pontos => lista.push(pontos));
    console.log(lista)
  }

  updatePonto(ponto: Ponto){

  }

  deletePonto(id: number){

  }
}
