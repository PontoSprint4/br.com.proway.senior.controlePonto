export interface Turno {
    id: number;
    horaInicio: string;
    horaFim?: string;
    nomeTurno: string;
    pessoasNoTurno: [];
    minutosTrabalho: number    
}