import { Ponto } from "./ponto";

export interface Jornada {
    id : number;
    data : string;
    turno : any;
    listaPonto : Ponto[];
    idPessoa : number;
    dia ?: number;
    mes ?: number;
    ano ?: number;
    minutosTrabalhados : number;
    estado : string;  
}
