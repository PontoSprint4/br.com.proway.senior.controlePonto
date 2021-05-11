package br.com.proway.senior.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.proway.senior.model.Ponto;

public class PontoController {
	public static void main(String[] args) {
		Ponto ponto = new Ponto(1, LocalDateTime.now());
		
		LocalDateTime registro = ponto.getMomentoPonto();
		
		//System.out.println(ponto.getMomentoPonto());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd HH:mm");
		
		System.out.println(registro.format(formatter));
		
		
	
	}
}
