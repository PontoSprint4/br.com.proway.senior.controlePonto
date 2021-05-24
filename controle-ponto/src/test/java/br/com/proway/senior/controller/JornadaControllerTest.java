package br.com.proway.senior.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Turno;

class JornadaControllerTest {
    static Session session;
    static JornadaController jornadaController;
    static JornadaDAO jdao;
    static TurnoDAO tdao;
    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		jornadaController = new JornadaController(session);
		tdao = TurnoDAO.getInstance(session);
	}

	@AfterEach
	void before() {
		jornadaController.deleteAll();
		tdao.deleteAll();
	}
	
    @Test
    void testCreate() {
    	int tamanho = jornadaController.getAll().size();
    	Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno1");
    	tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.now(), 1, turno);
    	jornadaController.create(jornada);
    	assertEquals(tamanho + 1, jornadaController.getAll().size());
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
    void testGetAll() {
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
	void testUpdateInvalidoIdInexistente() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno7");
		tdao.create(turno);
    	Jornada jornada = new Jornada(LocalDate.of(2021, 5, 20), 15, turno);
		assertThrows(Exception.class, () -> jornadaController.update(100, jornada));
	}
	
	@Test
	void testUpdateInvalidoNulo() {
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
	void testDeleteInvalidoIdInexistente() {
		assertThrows(Exception.class, () -> jornadaController.delete(100));
	}
	
	@Test
	void testDeleteInvalidoIdIgualAZero() {
		assertThrows(Exception.class, () -> jornadaController.delete(0));
	}
}
