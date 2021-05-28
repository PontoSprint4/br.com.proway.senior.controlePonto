package br.com.proway.senior.controlePonto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
	static JornadaService jService;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		controllerJornada = new JornadaController(DBConnection.getSession());
		controllerPonto = new PontoController(DBConnection.getSession());
		controllerTurno = new TurnoController(DBConnection.getSession());
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
		Jornada jornada = new Jornada(LocalDate.of(2021, 5, 26), 55, turno);
		int idCapturado = jService.createJornada(jornada); // retorna idCadastrado
		assertEquals(jornada.getId(), jService.getJornada(idCapturado).getId());
	}

	@Test
	void testUpdateJornada() throws Exception {
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021, 5, 26), 55, turno);
		int idCapturado = jService.createJornada(jornada); // retorna idCadastrado
		Jornada jornadaAtualizada = new Jornada(LocalDate.of(2021, 5, 27), 55, turno);
		assertTrue(jService.updateJornada(idCapturado, jornadaAtualizada));
	}

	@Test
	void testDeleteJornada() throws Exception {
		Integer tamanhoAntes = jService.getAll().size();
		
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021, 5, 26), 55, turno);
		int idCapturado = jService.createJornada(jornada); // retorna idCadastrado
		
		assertTrue(jService.deleteJornada(idCapturado));
		assertEquals(tamanhoAntes, jService.getAll().size());
	}

	@Test
	void testTodasJornadas() throws Exception {
		ArrayList<JornadaDTO> jornadasAntes = jService.todasJornadas(55);
		Turno turno = new Turno(LocalTime.of(22, 0), LocalTime.of(4, 0), "Turno da madrugada");
		controllerTurno.create(turno);
		Jornada jornada = new Jornada(LocalDate.of(2021, 5, 26), 55, turno);
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

		Jornada jornadaInicio = new Jornada(inicio, 51, turno);
		Jornada jornadaMeio = new Jornada(LocalDate.of(2021, 5, 26), 51, turno);
		Jornada jornadaFim = new Jornada(fim, 51, turno);
		Jornada jornadaFora = new Jornada(LocalDate.of(2021, 12, 25), 51, turno);
		jService.createJornada(jornadaInicio);
		jService.createJornada(jornadaMeio);
		jService.createJornada(jornadaFim);
		jService.createJornada(jornadaFora);

		assertEquals(3, jService.jornadasPorPeriodo(51, inicio, fim).size());
	}

	@Test
	void testMinutosTrabalhadosNoPeriodo() throws Exception {
		Integer idPessoa = 62;
		LocalDate inicio = LocalDate.now();
		LocalDate fim = LocalDate.now().plusMonths(1);
		LocalDate hoje = LocalDate.now();
		LocalDate diaFora = LocalDate.now().plusMonths(2);

		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		turno.adicionaPessoaNoTurno(idPessoa);
		controllerTurno.create(turno);

		Jornada jornada1 = new Jornada(hoje, idPessoa, turno);
		jService.createJornada(jornada1);
		Jornada jornadaFora = new Jornada(diaFora, idPessoa, turno);
		jService.createJornada(jornadaFora);

		Ponto ponto1 = new Ponto(idPessoa, LocalDateTime.of(hoje, LocalTime.now()));
		controllerPonto.create(ponto1);
		jService.marcarPonto(idPessoa, ponto1);
		Ponto ponto2 = new Ponto(idPessoa, LocalDateTime.of(hoje, LocalTime.now().plusMinutes(60)));
		controllerPonto.create(ponto2);
		jService.marcarPonto(idPessoa, ponto2);
		Ponto ponto3 = new Ponto(idPessoa, LocalDateTime.of(diaFora, LocalTime.now()));
		controllerPonto.create(ponto3);
		jService.marcarPonto(idPessoa, ponto3);
		Ponto ponto4 = new Ponto(idPessoa, LocalDateTime.of(diaFora, LocalTime.now().plusMinutes(60)));
		controllerPonto.create(ponto4);
		jService.marcarPonto(idPessoa, ponto4);

		assertEquals(60, jService.minutosTrabalhadosNoPeriodo(idPessoa, inicio, fim));
	}

	@Test
	void testJornadaDoDia() throws Exception {
		LocalDate hoje = LocalDate.now();

		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		controllerTurno.create(turno);

		Jornada jornada1 = new Jornada(hoje, 62, turno);
		jService.createJornada(jornada1);
		
		Jornada jornada2 = new Jornada(hoje, 9, turno);
		jService.createJornada(jornada2);

		assertEquals(LocalDate.now(), jService.jornadaDoDia(62).getData());
	}

	@Test
	void testJornadaDoDiaMaisDeUmaJornada() throws Exception {
		LocalDate hoje = LocalDate.now();

		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		controllerTurno.create(turno);

		Jornada jornada1 = new Jornada(hoje, 62, turno);
		jService.createJornada(jornada1);
		Jornada jornada2 = new Jornada(hoje, 62, turno);
		jService.createJornada(jornada2);

		assertThrows(Exception.class, () -> jService.jornadaDoDia(62));
	}

	@Test
	void testMarcarPontoCase0() throws Exception {
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		turno.adicionaPessoaNoTurno(70);
		
		controllerTurno.create(turno);
		LocalDate hoje = LocalDate.now();
		Ponto ponto = new Ponto(70, LocalDateTime.of(hoje, LocalTime.now()));
		Integer idPonto = controllerPonto.create(ponto);
		
		jService.marcarPonto(70, controllerPonto.get(idPonto));
		ArrayList<Jornada> jornadasDoDia = (ArrayList<Jornada>) controllerJornada.obterJornadasDoDia(70, hoje);
		assertEquals(1, jornadasDoDia.size());
	}

	@Test
	void testMarcarPontoCase1() throws Exception {
		Integer idPessoa = 70;
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		turno.adicionaPessoaNoTurno(idPessoa);
		controllerTurno.create(turno);
		LocalDate hoje = LocalDate.now();
		Ponto ponto = new Ponto(idPessoa, LocalDateTime.of(hoje, LocalTime.now()));
		Jornada jornada1 = new Jornada(hoje, idPessoa, turno);
		jService.createJornada(jornada1);
		jService.marcarPonto(idPessoa, ponto);
		int idJornada = jService.jornadaDoDia(idPessoa).getId();
		List<Ponto> listaJornada = jService.getJornada(idJornada).getListaPonto();
		assertEquals(1, listaJornada.size());
	}

	@Test
	void testMarcarPontoCaseDefault() throws Exception {
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		controllerTurno.create(turno);
		LocalDate hoje = LocalDate.now();
		
		Ponto ponto = new Ponto(70, LocalDateTime.of(hoje, LocalTime.now()));
		Integer idPonto = controllerPonto.create(ponto);
		
		Jornada jornada1 = new Jornada(hoje, 70, turno);
		jService.createJornada(jornada1);
		Jornada jornada2 = new Jornada(hoje, 70, turno);
		jService.createJornada(jornada2);
		
		assertThrows(Exception.class, 
				() ->jService.marcarPonto(70, 
					controllerPonto.get(idPonto)));
	}
	
	@Test
	void testMarcarPontoExcessaoPontoForaDoTurno() throws Exception {
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno teste");
		turno.adicionaPessoaNoTurno(70);
		controllerTurno.create(turno);
		LocalDate hoje = LocalDate.now();
		
		Ponto ponto = new Ponto(70, LocalDateTime.of(hoje, turno.getHoraFim().plusHours(3)));
		Integer idPonto = controllerPonto.create(ponto);
		
		Jornada jornada1 = new Jornada(hoje, 70, turno);
		jService.createJornada(jornada1);
		
		assertThrows(Exception.class, 
				() ->jService.marcarPonto(70, 
					controllerPonto.get(idPonto)));
	}
	
	@Test
	void testMarcarPontoDaMadruga() throws Exception {
		Turno turno = new Turno(LocalTime.of(23,0), LocalTime.of(4,00), "Turno teste");
		turno.adicionaPessoaNoTurno(70);
		controllerTurno.create(turno);
		
		LocalDate hoje = LocalDate.now();
		
		Ponto ponto = new Ponto(70, LocalDateTime.of(hoje, LocalTime.of(3,59)));
		Integer idPonto = controllerPonto.create(ponto);
		
		Jornada jornada1 = new Jornada(hoje.minusDays(1), 70, turno);
		Integer idJornada = jService.createJornada(jornada1);
		
		jService.marcarPonto(70, controllerPonto.get(idPonto));
		
		List<Ponto> listaJornada = jService.getJornada(idJornada).getListaPonto();
		assertEquals(1, listaJornada.size());
	}
	
	@Test
	void testMarcarPontoMultiplasJornadasNoDia() throws Exception {
		Turno turno = new Turno(LocalTime.of(23,0), LocalTime.of(4,00), "Turno teste");
		turno.adicionaPessoaNoTurno(70);
		controllerTurno.create(turno);
		
		LocalDate hoje = LocalDate.now();
		
		Ponto ponto = new Ponto(70, LocalDateTime.of(hoje, LocalTime.of(3,59)));
		Integer idPonto = controllerPonto.create(ponto);
		
		Jornada jornada1 = new Jornada(hoje.minusDays(1), 70, turno);
		Jornada jornada2 = new Jornada(hoje.minusDays(1), 70, turno);
		jService.createJornada(jornada1);
		jService.createJornada(jornada2);
		
		assertThrows(Exception.class, 
				() ->jService.marcarPonto(70, 
					controllerPonto.get(idPonto)));
	}

}
