package br.com.proway.senior.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.controller.JornadaController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

class JornadaControllerTratamentosTest {
	static JornadaController controller = new JornadaController(DBConnection.getSession());

	static Jornada jornada;
	static Ponto ponto;
	static Turno turno;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(16), "Turno 1");
		jornada = new Jornada(LocalDate.now(), 1, turno);
		jornada.adicionaPontoNaLista(new Ponto(0, null, LocalDateTime.now().plusHours(0)));
		jornada.adicionaPontoNaLista(new Ponto(1, null, LocalDateTime.now().plusHours(4)));
		jornada.adicionaPontoNaLista(new Ponto(2, null, LocalDateTime.now().plusHours(6)));
		jornada.adicionaPontoNaLista(new Ponto(3, null, LocalDateTime.now().plusHours(10)));
		jornada.adicionaPontoNaLista(new Ponto(4, null, LocalDateTime.now().plusHours(12)));
		jornada.adicionaPontoNaLista(new Ponto(5, null, LocalDateTime.now().plusHours(16)));
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
		Jornada jornada = new Jornada(LocalDate.now(), 1, turno);
		jornada.adicionaPontoNaLista(new Ponto(0, null, LocalDateTime.now().plusHours(0)));
		assertEquals(0, controller.calcularHorasTrabalhadas(jornada));
	}
	
	@Test
	void testCalculaHorasTrabalhadasListaDeJornada() throws Exception{
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		
		Jornada jornada1 = new Jornada(LocalDate.now(), 1, turno);
		jornada1.adicionaPontoNaLista(new Ponto(0, null, LocalDateTime.now().plusHours(0)));
		jornada1.adicionaPontoNaLista(new Ponto(1, null, LocalDateTime.now().plusHours(4)));
		jornada1.adicionaPontoNaLista(new Ponto(2, null, LocalDateTime.now().plusHours(6)));
		jornada1.adicionaPontoNaLista(new Ponto(3, null, LocalDateTime.now().plusHours(10)));
		
		Jornada jornada2 = new Jornada(LocalDate.now(), 1, turno);
		jornada2.adicionaPontoNaLista(new Ponto(0, null, LocalDateTime.now().plusHours(0)));
		jornada2.adicionaPontoNaLista(new Ponto(1, null, LocalDateTime.now().plusHours(4)));
		jornada2.adicionaPontoNaLista(new Ponto(2, null, LocalDateTime.now().plusHours(6)));
		jornada2.adicionaPontoNaLista(new Ponto(3, null, LocalDateTime.now().plusHours(10)));
		
		jornadas.add(jornada1);
		jornadas.add(jornada2);
		
		assertEquals(16*60, controller.calcularHorasTrabalhadas(jornadas));
	}
	
	@Test
	void testCalcularHorasTrabalhadasListaNulaDeJornadas() throws Exception {
		ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
		assertThrows(Exception.class, () -> controller.calcularHorasTrabalhadas(jornadas));
	}
	
	
	@Test
	void testVerificaSePontoEstaNaTurno() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,21, 18,0,0));
		
		assertTrue(controller.pontoDentroDoTurno(ponto, caso1));
	}
	
	@Test
	void testVerificaSePontoEstaForaTurno() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,21, 14,0,0));
		
		assertFalse(controller.pontoDentroDoTurno(ponto, caso1));
	}
	
	@Test
	void testVerificaSePontoEstaNoTurnoComTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(15, 0);
		LocalTime fim = LocalTime.of(22, 0);
		Turno caso1 = new Turno(inicio, fim, "Turno diario");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,21, 14,50,1));
		
		int tolerancia = 10; //minutos
		assertTrue(controller.pontoDentroDoTurno(ponto, caso1, tolerancia));
	}
	
	@Test
	void testVerificaSePontoEstaNoTurnoDaMadruga() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno da madruga");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,29, 22,1,0));
		Ponto ponto2 = new Ponto(1, null, LocalDateTime.of(2021,5,29, 3,59,0));
		
		assertTrue(controller.pontoDentroDoTurno(ponto, caso2));
		assertTrue(controller.pontoDentroDoTurno(ponto2, caso2));
	}
	
	@Test
	void testVerificaSePontoNaoEstaNoTurnoDaMadruga() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno caso2 = new Turno(inicio, fim, "Turno noturno");
		
		Ponto ponto1 = new Ponto(1, null, LocalDateTime.of(2021,5,21, 4,1,0));
		Ponto ponto2 = new Ponto(1, null, LocalDateTime.of(2021,5,21, 21,59,0));
		
		assertFalse(controller.pontoDentroDoTurno(ponto1, caso2));
		assertFalse(controller.pontoDentroDoTurno(ponto2, caso2));
	}
	
	
	
	@Test
	void testVerificaSePontoEstaNoTurnoDaMadrugaComTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		int tolerancia = 10; //minutos
		Turno caso2 = new Turno(inicio, fim, "Turno com tolerancia");
		
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,29, 21,50,1));
		Ponto ponto2 = new Ponto(1, null, LocalDateTime.of(2021,5,29, 4,9,59));
		
		
		assertTrue(controller.pontoDentroDoTurno(ponto, caso2, tolerancia));
		assertTrue(controller.pontoDentroDoTurno(ponto2, caso2, tolerancia));
	}
	
	@Test
	void testVerificaSePontoEstaNoTurnoDaMadrugaExcessaoTurno() throws Exception{
		int tolerancia = 10; //minutos
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,29, 21,50,1));
		
		assertThrows(Exception.class, () -> controller.pontoDentroDoTurno(ponto, null, tolerancia));
	}
	
	@Test
	void testVerificaSePontoEstaNoTurnoDaMadrugaExcessaoPonto() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		int tolerancia = 10; //minutos
		Turno caso2 = new Turno(inicio, fim, "Turno com tolerancia");
	
		assertThrows(Exception.class, () -> controller.pontoDentroDoTurno(null, caso2, tolerancia));
	}
	
	@Test
	void testVerificaSePontoEstaNoTurnoDaMadrugaExcessaoTolerancia() throws Exception{
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Ponto ponto = new Ponto(1, null, LocalDateTime.of(2021,5,29, 21,50,1));
		int tolerancia = -666; //minutos
		Turno caso2 = new Turno(inicio, fim, "Turno com tolerancia");
	
		assertThrows(Exception.class, () -> controller.pontoDentroDoTurno(ponto, caso2, tolerancia));
	}
	

}
