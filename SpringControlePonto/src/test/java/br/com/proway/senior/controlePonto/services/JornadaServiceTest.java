package br.com.proway.senior.controlePonto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

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
import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

class JornadaServiceTest {
	static JornadaController controllerJornada;
	static PontoController controllerPonto;
	static TurnoController controllerTurno;
	//static JornadaDTOController controllerDTOJornada;
	static JornadaService jService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controllerJornada = new JornadaController(DBConnection.getSession());
		controllerPonto = new PontoController(DBConnection.getSession());
		controllerTurno = new TurnoController(DBConnection.getSession());
		//controllerDTOJornada = 	new JornadaDTOController();
		jService = new JornadaService();
	}
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}

	@Test
	void testCreateGetJornada() throws Exception {
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021,5,26),55,turno);
		int idCapturado = jService.createJornada(jornada); //retorna idCadastrado
		assertEquals(jornada.getId(), jService.getJornada(idCapturado).getId());
	}

	@Test
	void testUpdateJornada() throws Exception {
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021,5,26),55,turno);
		int idCapturado = jService.createJornada(jornada); //retorna idCadastrado
		Jornada jornadaAtualizada = new Jornada(LocalDate.of(2021,5,27),55,turno);
		assertTrue(jService.updateJornada(idCapturado, jornadaAtualizada));
	}

	@Test
	void testDeleteJornada() throws Exception {
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021,5,26),55,turno);
		int idCapturado = jService.createJornada(jornada); //retorna idCadastrado
		assertTrue(jService.deleteJornada(idCapturado));
	}

	@Test
	void testTodasJornadas() throws Exception {
		ArrayList<JornadaDTO> jornadasAntes = jService.todasJornadas(55);
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021,5,26),55,turno);
		jService.createJornada(jornada);
		ArrayList<JornadaDTO> jornadasDepois = jService.todasJornadas(55);
		assertEquals(jornadasAntes.size() + 1, jornadasDepois.size());
	}

	@Test
	void testJornadasPorPeriodo() throws Exception {
		LocalDate inicio = LocalDate.of(2021, 4, 6);
		LocalDate fim = LocalDate.of(2021, 6, 6);
		
		Turno turno = new Turno(LocalTime.of(8, 0), LocalTime.of(18, 0), "Turno comercial");
		controllerTurno.create(turno);
		
		Jornada jornadaInicio = new Jornada(inicio,51,turno);
		Jornada jornadaMeio = new Jornada(LocalDate.of(2021, 5, 26),51,turno);
		Jornada jornadaFim = new Jornada(fim,51,turno);
		Jornada jornadaFora = new Jornada(LocalDate.of(2021, 12, 25),51,turno);
		jService.createJornada(jornadaInicio);
		jService.createJornada(jornadaMeio);
		jService.createJornada(jornadaFim);
		jService.createJornada(jornadaFora);
		
		assertEquals(3, jService.jornadasPorPeriodo(51, inicio, fim).size());
	}

	@Test
	void testMinutosTrabalhadosNoPeriodo() throws Exception {
		LocalDate inicio = LocalDate.now();
		LocalDate fim = LocalDate.now().plusMonths(1);
		LocalDate hoje = LocalDate.now();
		LocalDate diaFora = LocalDate.now().plusMonths(2);
		
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		controllerTurno.create(turno);
		
		Jornada jornada1 = new Jornada(hoje,62,turno);
		jService.createJornada(jornada1);
		Jornada jornadaFora = new Jornada(diaFora,62,turno);
		jService.createJornada(jornadaFora);
		
		
		Ponto ponto1 = new Ponto(62, LocalDateTime.of(hoje, LocalTime.now()));
		controllerPonto.create(ponto1);
		jService.marcarPonto(62, ponto1);
		Ponto ponto2 = new Ponto(62, LocalDateTime.of(hoje, LocalTime.now().plusMinutes(60)));
		controllerPonto.create(ponto2);
		jService.marcarPonto(62, ponto2);
		Ponto ponto3 = new Ponto(62, LocalDateTime.of(diaFora, LocalTime.now()));
		controllerPonto.create(ponto3);
		jService.marcarPonto(62, ponto3);
		Ponto ponto4 = new Ponto(62, LocalDateTime.of(diaFora, LocalTime.now().plusMinutes(60)));
		controllerPonto.create(ponto4);
		jService.marcarPonto(62, ponto4);
		
		
		assertEquals(60, jService.minutosTrabalhadosNoPeriodo(62, inicio, fim));
	}

	@Test
	void testJornadaDoDia() throws Exception {
		LocalDate hoje = LocalDate.now();
		
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		controllerTurno.create(turno);
		
		Jornada jornada1 = new Jornada(hoje,62,turno);
		jService.createJornada(jornada1);
		
		assertEquals(LocalDate.now(), jService.jornadaDoDia(62).getData());
	}
	
	@Test
	void testJornadaDoDiaMaisDeUmaJornada() throws Exception {
		LocalDate hoje = LocalDate.now();
		
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		controllerTurno.create(turno);
		
		Jornada jornada1 = new Jornada(hoje,62,turno);
		jService.createJornada(jornada1);
		Jornada jornada2 = new Jornada(hoje,62,turno);
		jService.createJornada(jornada2);
		
		assertThrows(Exception.class, () -> jService.jornadaDoDia(62));
	}

	@Test
	void testMarcarPonto() {
		fail("Not yet implemented");
	}

}
