package br.com.proway.senior.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

class JornadaControllerTratamentosTest {
	static JornadaController controller = new JornadaController(DBConnection.getSession());

	static Jornada jornada;
	static Ponto ponto;
	static Turno turno;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(16), "Turno 1");
		jornada = new Jornada(LocalDate.now(), 1, turno);
		jornada.setListaPonto(new Ponto(0, null, LocalDateTime.now().plusHours(0)));
		jornada.setListaPonto(new Ponto(1, null, LocalDateTime.now().plusHours(4)));
		jornada.setListaPonto(new Ponto(2, null, LocalDateTime.now().plusHours(6)));
		jornada.setListaPonto(new Ponto(3, null, LocalDateTime.now().plusHours(10)));
		jornada.setListaPonto(new Ponto(4, null, LocalDateTime.now().plusHours(12)));
		jornada.setListaPonto(new Ponto(5, null, LocalDateTime.now().plusHours(16)));
	}
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}

	@Test
	void testCalcularHorasTrabalhadas() throws Exception {
		assertEquals(12*60, controller.calcularHorasTrabalhadas(jornada), 5);
	}
	
	@Test
	void testCalcularHorasTrabalhadasJornadaNula() throws Exception {
		Jornada jorn = null;
		assertThrows(Exception.class, () -> controller.calcularHorasTrabalhadas(jorn));
	}
	
	@Test
	void testCalcularHorasTrabalhadasJornadaPontosImpar() throws Exception {
		Jornada jornad = new Jornada(LocalDate.now(), 1, turno);
		jornad.setListaPonto(new Ponto(0, null, LocalDateTime.now().plusHours(0)));
		assertEquals(0, controller.calcularHorasTrabalhadas(jornad));
	}
	
	
	@Test
	void testVerificaSePontoEstaNaJornada() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,21, 18,0,0));
		
		assertTrue(controller.pontoDentroDoTurno(ponto, caso1));
	}
	
	@Test
	void testVerificaSePontoEstaForaJornada() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,21, 14,0,0));
		
		assertFalse(controller.pontoDentroDoTurno(ponto, caso1));
	}
	
	@Test
	void testVerificaSePontoEstaNaJornadaComTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,21, 14,50,1));
		
		int tolerancia = 10; //minutos
		assertTrue(controller.pontoDentroDoTurno(ponto, caso1, tolerancia));
	}
	
	@Test
	void testVerificaSePontoEstaNaJornadaDaMadruga() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno da madruga");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,29, 22,1,0));
		Ponto ponto2 = new Ponto(1, null, LocalDateTime.of(2021,5,29, 3,59,0));
		
		assertTrue(controller.pontoDentroDoTurno(ponto, caso2));
		assertTrue(controller.pontoDentroDoTurno(ponto2, caso2));
	}
	
	@Test
	void testVerificaSePontoNaoEstaNaJornadaDaMadruga() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno noturno");
		
		Ponto ponto1 = new Ponto(1, null, LocalDateTime.of(2021,5,21, 4,1,0));
		Ponto ponto2 = new Ponto(1, null, LocalDateTime.of(2021,5,21, 21,59,0));
		
		assertFalse(controller.pontoDentroDoTurno(ponto1, caso2));
		assertFalse(controller.pontoDentroDoTurno(ponto2, caso2));
	}
	
	
	
	@Test
	void testVerificaSePontoEstaNaJornadaDaMadrugaComTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		int tolerancia = 10; //minutos
		Turno caso2 = new Turno(inicio, fim, "Turno com tolerancia");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,29, 21,50,1));
		Ponto ponto2 = new Ponto(1, null, LocalDateTime.of(2021,5,29, 4,9,59));
		
		
		assertTrue(controller.pontoDentroDoTurno(ponto, caso2, tolerancia));
		assertTrue(controller.pontoDentroDoTurno(ponto2, caso2, tolerancia));
	}

}
