import { Component } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';


@Component({
  selector: 'app-ponto-marcar',
  templateUrl: './ponto-marcar.component.html',
  styleUrls: ['./ponto-marcar.component.css']
})
export class PontoMarcarComponent {  
  constructor(
    private http: HttpClient
    ){
    setInterval(() => {
      this.horaAtual = Date.now();
    }, 100);
  }

  public horaAtual: number | undefined;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  baterPonto(id : number): void{
    this.http.post<string>(
        "http://localhost:8080/api/jornada/marcar_ponto/"+id, 
        null, 
        this.httpOptions
      )
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
      .subscribe((res)=>console.log(res))
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
