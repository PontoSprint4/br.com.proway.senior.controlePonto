import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Jornada } from './jornada';

@Injectable({
  providedIn: 'root'
})
export class JornadaService {
  public jornadaSelecionada : Jornada = {} as Jornada;
  public listaDeJornadas : Jornada[] = [];

  //Variaveis
  private url = 'http://localhost:8080/api/';

  //
  constructor(
    private http: HttpClient,
    private datePipe: DatePipe ) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
    
  //Criação de Jornada
  createJornada(jornada: Jornada) : Observable<Jornada> {
    return this.http.post<Jornada>(
      this.url+"jornada/", 
      JSON.stringify(jornada), 
      this.httpOptions
    )
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  //Busca um Jornada
  getJornada( id : number): Observable<Jornada> {
    return this.http.get<Jornada>(this.url + "jornada/" + id)
    .pipe(
      retry(2),
      catchError(this.handleError)
    )
  }

  //Busca todos os Jornadas
  getAll() : Observable<Jornada[]>{
    return this.http.get<Jornada[]>(this.url+ "jornadas/")
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Busca todos os Jornadas
  getAllJornadasDaPessoa(id : number) : Observable<Jornada[]>{
    return this.http.get<Jornada[]>(this.url+ "jornadas/todas/" + id)
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }
  
  //Atualiza um Jornada
  updateJornada(id : number, atualizado : Jornada) : Observable<Jornada>{
    return this.http.put<Jornada>
    (
        this.url+ "jornada/"+id, 
        JSON.stringify(atualizado), 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
  }

  //Deleta um Jornada
  deleteJornada(id: number) : Observable<Jornada>{
    return this.http.delete<Jornada>
    (
      this.url+ "jornada/"+id
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