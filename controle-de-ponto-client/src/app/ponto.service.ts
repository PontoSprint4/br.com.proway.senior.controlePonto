import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { DatePipe } from '@angular/common';

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
    private http: HttpClient,
    private datePipe: DatePipe ) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
    
  //Criação de Ponto
  createPonto(ponto: Ponto): Observable<Ponto>{
    return this.http.post<Ponto>(
        this.pontoUrl+"ponto/", 
        JSON.stringify(ponto), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Busca um Ponto
  getPonto( id : number): Observable<Ponto> {
    return this.http.get<Ponto>(this.pontoUrl + "ponto/" + id)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  //Busca todos os Pontos
  getAll() : Observable<Ponto[]>{
    return this.http.get<Ponto[]>(this.pontoUrl+ "pontos/")
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Atualiza um Ponto
  updatePonto(id : number, atualizado : Ponto) : Observable<Ponto>{
    return this.http.put<Ponto>
    (
        this.pontoUrl+ "ponto/"+id, 
        JSON.stringify(atualizado), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Deleta um Ponto
  deletePonto(id: number) : Observable<Ponto>{
    return this.http.delete<Ponto>
    (
      this.pontoUrl+ "ponto/"+id
    )
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
    
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

  formatarData(data : number) : string{
    return this.datePipe.transform(data,'yyyy-MM-ddTHH:mm:ss')!
  }
}
