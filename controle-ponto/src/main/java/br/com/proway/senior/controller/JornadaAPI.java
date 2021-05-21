package br.com.proway.senior.controller;

import java.time.LocalDateTime;
import java.util.List;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
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

}
