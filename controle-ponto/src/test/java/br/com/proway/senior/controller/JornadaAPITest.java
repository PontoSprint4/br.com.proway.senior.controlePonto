package br.com.proway.senior.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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
	
	@Test
	void testCalcularHorasTrabalhadasJornadaNula() throws Exception {
		Jornada jorn = null;
		assertThrows(Exception.class, () -> JornadaAPI.calcularHorasTrabalhadas(jorn));
	}
	
	@Test
	void testCalcularHorasTrabalhadasJornadaPontosImpar() throws Exception {
		Jornada jornad = new Jornada(LocalDate.now(), 1, turno);
		jornad.setListaPonto(new Ponto(0, LocalDateTime.now().plusHours(0)));
		assertThrows(Exception.class, () -> JornadaAPI.calcularHorasTrabalhadas(jornad));
	}
	
	
	@Test
	void testVerificaSePontoEstaNaJornada() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, LocalDateTime.of(2021,5,21, 18,0,0));
		
		assertTrue(JornadaAPI.pontoDentroDoTurno(ponto, caso1));
	}
	
	@Test
	void testVerificaSePontoEstaForaJornada() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, LocalDateTime.of(2021,5,21, 14,0,0));
		
		assertFalse(JornadaAPI.pontoDentroDoTurno(ponto, caso1));
	}
	
	@Test
	void testVerificaSePontoEstaNaJornadaDaMadruga() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, LocalDateTime.of(2021,5,29, 3,0,0));
		
		assertTrue(JornadaAPI.pontoDentroDoTurno(ponto, caso2));
	}
	
	@Test
	void testVerificaSePontoNaoEstaNaJornadaDaMadruga() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto1 = new Ponto(1, LocalDateTime.of(2021,5,21, 4,20,0));
		Ponto ponto2 = new Ponto(1, LocalDateTime.of(2021,5,21, 21,20,0));
		
		assertFalse(JornadaAPI.pontoDentroDoTurno(ponto1, caso2));
		assertFalse(JornadaAPI.pontoDentroDoTurno(ponto2, caso2));
	}
	
	@Test
	void testVerificaSePontoEstaNaJornadaComTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, LocalDateTime.of(2021,5,21, 14,50,1));
		
		int tolerancia = 10; //minutos
		assertTrue(JornadaAPI.pontoDentroDoTurno(ponto, caso1, tolerancia));
	}
	
	@Test
	void testVerificaSePontoEstaNaJornadaDaMadrugaComTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, LocalDateTime.of(2021,5,29, 4,9,59));
		
		int tolerancia = 10; //minutos
		assertTrue(JornadaAPI.pontoDentroDoTurno(ponto, caso2, tolerancia));
	}

}
