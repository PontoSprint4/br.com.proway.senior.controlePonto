package br.com.proway.senior.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

class JornadaAPITest {

	static Jornada jornada;
	static Ponto ponto;
	static Turno turno;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(16), "Turno 1");
		jornada = new Jornada(LocalDate.now(), 1, turno);
		jornada.setListaPonto(new Ponto(0, LocalDateTime.now().plusHours(0)));
		jornada.setListaPonto(new Ponto(1, LocalDateTime.now().plusHours(4)));
		jornada.setListaPonto(new Ponto(2, LocalDateTime.now().plusHours(6)));
		jornada.setListaPonto(new Ponto(3, LocalDateTime.now().plusHours(10)));
		jornada.setListaPonto(new Ponto(4, LocalDateTime.now().plusHours(12)));
		jornada.setListaPonto(new Ponto(5, LocalDateTime.now().plusHours(16)));
	}

	@Test
	void testCalcularHorasTrabalhadas() throws Exception {
		assertEquals(12*60, JornadaAPI.calcularHorasTrabalhadas(jornada), 5);
	}

}
