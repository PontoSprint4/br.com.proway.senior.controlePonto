package br.com.proway.senior.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;
import br.com.proway.senior.utils.FormatacaoDeTempo;
import br.com.proway.senior.utils.Validadores;

public class JornadaAPI {
	/**
	 * Calcula o tempo trabalhado durante uma {@link Jornada} FECHADA (numero par de pontos)
	 * e retorna esse valor em MINUTOS.
	 * 
	 * @param Jornada : jornada
	 * @return long : Minutos trabalhados na Jornada.
	 * 
	 * @see FormatacaoDeTempo
	 * @throws Exception
	 */
	public static long calcularHorasTrabalhadas(Jornada jornada) throws Exception {
		if(Validadores.ehObjetoNulo(jornada)) {
			throw(new Exception("Jornada esta nula!"));
		}
		
		List<Ponto> pontosDaJornada = jornada.getListaPonto();
		if (pontosDaJornada.size() % 2 != 0 ) {
			throw(new Exception("A Jornada tem um numero impar de pontos e esta incompleta!"));
		}
		
		long minutosTrabalhados = 0;
		int pointer = 0;
				
		while (pointer < pontosDaJornada.size() - 1) {
			LocalDateTime pontoAtual = pontosDaJornada.get(pointer).getMomentoPonto();
			LocalDateTime proximo = pontosDaJornada.get(pointer+1).getMomentoPonto();
			
			minutosTrabalhados += FormatacaoDeTempo.tempoEntreRegistros(pontoAtual, proximo);
			pointer+=2; // Temos que considerar os intervalos entre pares de pontos!
		}
		return minutosTrabalhados;
	}
	
	/**
     * <h1>Funcao que informa se o {@link Ponto} desejado se enquadra dentro do periodo
     * estabelecido pelo {@link Turno}.</h1>
     * 
     * <br>
     * Existem dois casos:
     * <br>
     * Caso 1 : O turno nao cruza a meia noite.
     * <br>
     * Caso 2 : O turno cruza a meia noite.
     * <br>
     * 
     * @param ponto
     * @param turno
     * @return boolean : ponto pertence ao turno
     */
    public static boolean pontoDentroDoTurno(Ponto ponto, Turno turno) {
    	return pontoDentroDoTurno(ponto, turno, 0);
    }
    
    /**
     * <h1>Funcao que informa se o {@link Ponto} desejado se enquadra dentro do periodo
     * estabelecido pelo {@link Turno} com uma tolerancia estabecida em MINUTOS.</h1>
     * 
     * <br>
     * Existem dois casos:
     * <br>
     * Caso 1 : O turno nao cruza a meia noite.
     * <br>
     * Caso 2 : O turno cruza a meia noite.
     * <br>
     * 
     * @param Ponto : ponto
     * @param Turno : turno
     * @param int: Tolerancia em minutos
     * @return boolean : ponto pertence ao turno
     */
    public static boolean pontoDentroDoTurno(Ponto ponto, Turno turno, int toleranciaMinutos) {
    	// Manter apenas horario comercial
    	LocalDateTime horaPonto = ponto.getMomentoPonto();
    	
    	LocalTime horaPontoLocal = LocalTime.of(horaPonto.getHour(), horaPonto.getMinute(), horaPonto.getSecond());
    	LocalTime inicioTurno = turno.getHoraInicio();
    	LocalTime fimTurno = turno.getHoraFim();
    	
    	if (inicioTurno.isBefore(fimTurno)) {
    		// CASO1 - inicio e fim de Turno no mesmo dia
    		if (horaPontoLocal.isBefore(fimTurno.plusMinutes(toleranciaMinutos)) && 
        			horaPontoLocal.isAfter(inicioTurno.minusMinutes(toleranciaMinutos))) {
        		return true;
        	}
    		return false;
    	}
    	else {
    		// CASO 2 - inicio e fim de turno em dias diferentes (madrugada)
    		if (horaPontoLocal.isBefore(inicioTurno.minusMinutes(toleranciaMinutos)) && 
        			horaPontoLocal.isBefore(fimTurno.plusMinutes(toleranciaMinutos))) {
        		return true;
        	}
    		return false;	
    	}	
    }

}
