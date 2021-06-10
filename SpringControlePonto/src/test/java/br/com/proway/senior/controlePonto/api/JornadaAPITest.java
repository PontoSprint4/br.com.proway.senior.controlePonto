package br.com.proway.senior.controlePonto.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.controller.JornadaController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.IntervaloTempo;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.services.JornadaService;
import br.com.proway.senior.controlePonto.services.TurnoService;

class JornadaAPITest {
	JornadaAPI api = new JornadaAPI(new JornadaService());
	JornadaController jornadaController = new JornadaController(DBConnection.getSession());
	TurnoService turnoService = new TurnoService();
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}
	
	@Test
	void testMarcarPonto() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDateTime momentoPonto = LocalDateTime.now().plusHours(2);
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		
		api.marcarPonto(idPessoa);
		ArrayList<Jornada> jornadasDoDia = (ArrayList<Jornada>) 
				jornadaController.obterJornadasDoDia(idPessoa, momentoPonto.toLocalDate());
		
		assertEquals(1, jornadasDoDia.size());
	}

	@Test
	void testCriarJornada() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		assertNotNull(api.criarJornada(jornada));
	}

	@Test
	void testGetJornada() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		Integer idJornada = api.criarJornada(jornada);
		assertNotNull(api.getJornada(idJornada));
	}

	@Test
	void testAtualizarJornada() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		Integer idJornada = api.criarJornada(jornada);
		jornada.setIdPessoa(666);
		api.atualizarJornada(jornada, idJornada);
		
		JornadaDTO retornada = api.getJornada(idJornada);
		assertEquals(jornada.getIdPessoa(), retornada.getIdPessoa());
	}

	@Test
	void testDeleteJornada() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		Integer idJornada = api.criarJornada(jornada);
		
		assertTrue(api.deleteJornada(idJornada));
	}

	@Test
	void testTodasJornadas() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		api.criarJornada(jornada);
		
		assertEquals(1, api.todasJornadas(idPessoa).size());
	}

	@Test
	void testJornadaDoDia() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		api.criarJornada(jornada);
		
		assertNotNull(api.jornadaDoDia(idPessoa));
	}

	@Test
	void testJornadasPorPeriodo() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		api.criarJornada(jornada);
		
		IntervaloTempo intervalo = new IntervaloTempo();
		intervalo.inicio = data.minusDays(1);
		intervalo.fim = data.plusDays(1);
		
		assertEquals(1, api.jornadasPorPeriodo(idPessoa, intervalo).size());
		
	}

	@Test
	void testMinutosTrabalhadosNoPeriodo() throws Exception {
		Integer idPessoa = 44;
		
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		turno.adicionaPessoaNoTurno(idPessoa);
		turnoService.salvar(turno);
		
		LocalDate data = LocalDate.now();
		
		Jornada jornada = new Jornada(data, idPessoa, turno);
		api.criarJornada(jornada);
		
		IntervaloTempo intervalo = new IntervaloTempo();
		intervalo.inicio = data.minusDays(1);
		intervalo.fim = data.plusDays(1);
		
		LocalDateTime momentoPonto = LocalDateTime.now().plusHours(2);
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		
		LocalDateTime momentoPonto2 = LocalDateTime.now().plusHours(4);
		Ponto ponto2 = new Ponto(idPessoa, momentoPonto2);
		
		api.marcarPonto(idPessoa, ponto);
		api.marcarPonto(idPessoa, ponto2);
		
		assertEquals(2*60, api.minutosTrabalhadosNoPeriodo(idPessoa, intervalo), 1);
	}

}
