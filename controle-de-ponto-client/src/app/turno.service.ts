import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { DatePipe } from '@angular/common';

import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

import { Turno } from './turno';

@Injectable({
  providedIn: 'root'
})
export class TurnoService {
  public turnoSelecionado : Turno = {} as Turno;
  public turnos : Turno[] = [];

  //Variaveis
  private turnoUrl = 'http://localhost:8080/api/';

  //
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe ) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
    
  //Criação de Turno
  createTurno(turno: Turno): Observable<Turno>{
    return this.http.post<Turno>(
        this.turnoUrl+"turno/", 
        JSON.stringify(turno), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Busca um Turno
  getTurno( id : number): Observable<Turno> {
    return this.http.get<Turno>(this.turnoUrl + "turno/" + id)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  //Busca todos os Turnos
  getAll() : Observable<Turno[]>{
    return this.http.get<Turno[]>(this.turnoUrl+ "turnos/")
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Atualiza um Turno
  updateTurno(id : number, atualizado : Turno) : Observable<Turno>{
    return this.http.put<Turno>(
        this.turnoUrl+ "turno/"+id, 
        JSON.stringify(atualizado), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Deleta um Turno
  deleteTurno(id: number) : Observable<Turno>{
    return this.http.delete<Turno>
    (
      this.turnoUrl+ "turno/"+id
    )
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
    
  }

  //Buscar todas as pessoas do Turno
  getPessoas( id: number): Observable<Turno> {
    return this.http.get<Turno>(this.turnoUrl + "turno/pessoas/" + id)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  //Buscar Turno da Pessoa
  getTurnoPessoa( id : number): Observable<Turno> {
    return this.http.get<Turno>(this.turnoUrl + "turno/pessoa/" + id)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  //Adiconar Pessoas no Turno
  addPessoasTurno(listaPessoas: string, id: number): Observable<Turno>{
    return this.http.put<Turno>(
        this.turnoUrl+"turno/pessoas_add/"+id, 
        JSON.stringify(listaPessoas), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }
  //Deleta uma Pessoa do Turno
  deletePessoasTurno(listaPessoas: string, id: number): Observable<Turno>{
    return this.http.put<Turno>(
        this.turnoUrl+"turno/pessoas_remove/"+id, 
        JSON.stringify(listaPessoas), 
        this.httpOptions
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
