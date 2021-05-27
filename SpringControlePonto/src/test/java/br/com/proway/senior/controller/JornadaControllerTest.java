package br.com.proway.senior.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.controller.JornadaController;
import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

class JornadaControllerTest {
    static Session session;
    static JornadaController jornadaController;
    static JornadaDAO jdao;
    static TurnoDAO tdao;
    
    static PontoController pcontroller;
    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		jornadaController = new JornadaController(session);
		tdao = TurnoDAO.getInstance(session);
	}

	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}
	
    @Test
    void testCreate() throws Exception {
    	int tamanho = jornadaController.getAll().size();
    	Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno1");
    	tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.now(), 1, turno);
    	jornadaController.create(jornada);
    	assertEquals(tamanho + 1, jornadaController.getAll().size());
    }
    
    @Test
    void testCreateExceptionTurnoNulo() throws Exception {
    	Jornada jornada = new Jornada(LocalDate.now(), 1, null);
    	assertThrows(Exception.class, ()-> jornadaController.create(jornada));
    }
    
    @Test
    void testCreateExceptionJornadaSemData() throws Exception {
    	Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno1");
    	tdao.create(turno);
    	Jornada jornada = new Jornada(null, 1, turno);
    	assertThrows(Exception.class, ()-> jornadaController.create(jornada));

    }

    @Test
    void testGet() throws Exception {
    	Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno2");
    	tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.now(), 2, turno);
    	Integer idJornadaCadastrada = jornadaController.create(jornada);
    	Jornada jornadaConsultada = jornadaController.get(idJornadaCadastrada);
    	assertEquals("Turno2", jornadaConsultada.getTurno().getNomeTurno());
    	assertEquals(2, jornadaConsultada.getIdPessoa());
    }
    
	@Test
	void testGetIdInvalido() {
		assertThrows(Exception.class, () -> jornadaController.get(0));
	}

    @Test
    void testGetAll() throws Exception {
    	int tamanho = jornadaController.getAll().size();
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno3");
		tdao.create(turno);
		Turno turno2 = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno4");
		tdao.create(turno2);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 3, turno);
		jornadaController.create(jornada);
    	Jornada jornada2 = new Jornada(LocalDate.of(2021, 5, 20), 4, turno2);
		jornadaController.create(jornada2);
		assertEquals(tamanho +2, jornadaController.getAll().size());
    }

	@Test
	void testUpdate() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno5");
		tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 5, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		Jornada jornadaNova = new Jornada(LocalDate.of(2021, 4, 15), 5, turno);
		jornadaController.update(idCadastrado, jornadaNova);
		assertEquals("Turno5", jornadaController.get(idCadastrado).getTurno().getNomeTurno());
		assertEquals(LocalDate.of(2021, 4, 15), jornadaController.get(idCadastrado).getData());
	}
	
	@Test
	void adicionarPontoNaJornadaTest() throws Exception{
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno Top");
		tdao.create(turno);
		
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 666, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		
		Ponto ponto = new Ponto(12 ,666, LocalDateTime.now().plusMinutes(44));
		Integer idPonto = PontoDAO.getInstance(DBConnection.getSession()).create(ponto);
		Ponto pontoRetornado = PontoDAO.getInstance(DBConnection.getSession()).get(idPonto);
		
		assertTrue(jornadaController.adicionarPontoNaJornada(idCadastrado, pontoRetornado));
		assertTrue(jornadaController.get(idCadastrado).getListaPonto().contains(pontoRetornado));
	}
	
	@Test
	void adicionarPontoNaJornadaInexistenteTest() throws Exception{
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno Top");
		tdao.create(turno);
		
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 666, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		jornadaController.delete(idCadastrado);
		
		Ponto ponto = new Ponto(12 ,666, LocalDateTime.now().plusMinutes(44));
		Integer idPonto = PontoDAO.getInstance(DBConnection.getSession()).create(ponto);
		Ponto pontoRetornado = PontoDAO.getInstance(DBConnection.getSession()).get(idPonto);
		
		assertThrows(Exception.class, () -> jornadaController.adicionarPontoNaJornada(idCadastrado, pontoRetornado));
	}
	
	@Test
	void adicionarPontoInexistenteNaJornadaTest() throws Exception{
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(8), "Turno Top");
		tdao.create(turno);
		
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 666, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		
		assertThrows(Exception.class, () -> jornadaController.adicionarPontoNaJornada(idCadastrado, null));
	}
	
	@Test
	void testUpdateInvalidoIdInexistente() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno7");
		tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 15, turno);
		assertThrows(Exception.class, () -> jornadaController.update(100, jornada));
	}
	
	@Test
	void testUpdateInvalidoNulo() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno6");
		tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 15, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		Jornada jornadaNova = null;
		assertThrows(Exception.class, () -> jornadaController.update(idCadastrado, jornadaNova));
	}
	
	@Test
	void testDelete() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno8");
		tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 8, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		int tamanho = jornadaController.getAll().size();
		jornadaController.delete(idCadastrado);
		assertEquals(tamanho -1, jornadaController.getAll().size());
	}
	
	@Test
	void testDeleteAll() throws Exception {
		int tamanhoInicial = jornadaController.getAll().size();
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno8");
		tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 8, turno);
		Integer idCadastrado = jornadaController.create(jornada);
		
		
		jornadaController.deleteAll();
		
		assertEquals(tamanhoInicial, jornadaController.getAll().size());
	}
	
	
	@Test
	void testDeleteInvalidoIdInexistente() {
		assertThrows(Exception.class, () -> jornadaController.delete(100));
	}
	
	@Test
	void testDeleteInvalidoIdIgualAZero() {
		assertThrows(Exception.class, () -> jornadaController.delete(0));
	}
}
