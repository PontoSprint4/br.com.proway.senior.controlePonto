package br.com.proway.senior.controller;

import java.time.LocalDateTime;
import java.util.List;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.utils.FormatacaoDeTempo;

public class JornadaAPI {
	
	public static long calcularHorasTrabalhadas(Jornada jornada) throws Exception {
		long minutosTrabalhados = 0;
		int pointer = 0;
		
		List<Ponto> pontosDaJornada = jornada.getListaPonto();
		while (pointer < pontosDaJornada.size() - 1) {
			LocalDateTime pontoAtual = pontosDaJornada.get(pointer).getMomentoPonto();
			LocalDateTime proximo = pontosDaJornada.get(pointer+1).getMomentoPonto();
			
			minutosTrabalhados += FormatacaoDeTempo.tempoEntreRegistros(pontoAtual, proximo);
			pointer+=2; // Temos que considerar os intervalos entre pares de pontos!
		}
		return minutosTrabalhados;
	}

}
