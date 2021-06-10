import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

import { Ponto } from './ponto';

@Injectable({
  providedIn: 'root'
})
export class PontoService {
  public pontoSelecionado : Ponto = {} as Ponto;
  public listaDePontos : Ponto[] = [];

  //Variaveis
  private pontoUrl = 'http://localhost:8080/api/';

  //
  constructor(
    private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
    
  //Criação de Ponto
  createPonto(ponto: Ponto): void{
    this.http.post<Ponto>(
        this.pontoUrl+"ponto/", 
        JSON.stringify(ponto), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
      .subscribe((answer)=>{console.log(answer)})
  }

  //Busca um Ponto
  getPonto( id : number): void {
    this.http.get<Ponto>(this.pontoUrl + "ponto/" + id)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
    .subscribe(ponto => {console.log("callbacking");this.pontoSelecionado = ponto});
  }

  //Busca todos os Pontos
  getAll() : void{
    let lista : Ponto[] = [];
    this.http.get<Ponto>(this.pontoUrl+ "pontos/")
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
      .subscribe(pontos => lista.push(pontos));

    this.listaDePontos = lista;
  }

  //Atualiza um Ponto
  updatePonto(id : number, atualizado : Ponto){
    this.http.put<Ponto>
    (
        this.pontoUrl+ "ponto/"+id, 
        JSON.stringify(atualizado), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
      .subscribe((answer)=>{console.log(answer)})
  }

  //Deleta um Ponto
  deletePonto(id: number){
    this.http.delete<Ponto>
    (
      this.pontoUrl+ "ponto/"+id
    )
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
    .subscribe((answer)=>{console.log(answer)})
  }

  // Manipulação de erros
  handleError(error: HttpErrorResponse) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Erro ocorreu no lado do client
      errorMessage = error.error.message;
    } else {
      // Erro ocorreu no lado do servidor
      errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  };
}
