package br.com.proway.senior.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FormatacaoDeTempoTest {

	@Test
	void instancia() {
		assertNotNull(new FormatacaoDeTempo());
	}
	@Test
	void testTempoEntreRegistros() throws Exception {
		LocalDateTime hoje = LocalDateTime.of(2021, 5, 20, 11, 3, 0);
		LocalDateTime hojeMaisCedo = hoje.minusHours(3);
		assertEquals(180, FormatacaoDeTempo.tempoEntreRegistros(hojeMaisCedo, hoje));
	}
	
	@Test
	void testTempoEntreRegistrosInvalido(){
		LocalDateTime hoje = LocalDateTime.of(2021, 5, 20, 11, 3, 0);
		LocalDateTime hojeMaisCedo = hoje.minusHours(3);
		Assertions.assertThrows(Exception.class, () -> FormatacaoDeTempo.tempoEntreRegistros(hoje, hojeMaisCedo));
	}

	@Test
	void testMinutosParaHoras() {
		Long minutos = 1000L;
		assertEquals("16:40:00", FormatacaoDeTempo.minutosParaHoras(minutos, TimeUnit.MINUTES));
	}
	
	@Test
	void testMinutosParaHorasErrado() {
		Long minutos = 1000L;
		assertNotEquals("16:41:00", FormatacaoDeTempo.minutosParaHoras(minutos, TimeUnit.MINUTES));
	}
	
	@Test
	void testMinutosParaMinutos() {
		Long minutos = 59L;
		assertEquals("59:00", FormatacaoDeTempo.minutosParaHoras(minutos, TimeUnit.MINUTES));
	}
	
	@Test
	void testSegundosParaMinutos() {
		Long segundos = 61L;
		assertEquals("1:01", FormatacaoDeTempo.minutosParaHoras(segundos, TimeUnit.SECONDS));
	}
	
	@Test
	void testSegundosParaSegundos() {
		Long segundos = 50L;
		assertEquals("50", FormatacaoDeTempo.minutosParaHoras(segundos, TimeUnit.SECONDS));
	}
	
}
