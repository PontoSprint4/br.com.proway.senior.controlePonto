package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Turno;

class TurnoControllerTest {
	
	static Session session;
	static TurnoController turnoController;
	static TurnoDAO tdao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		turnoController = new TurnoController(session);
		tdao = TurnoDAO.getInstance(session);
	}
	
	@AfterEach
	void before() {
		turnoController.deleteAll();
	}

	@Test
	void testCreate() {
		assertEquals(0, turnoController.getAll().size());
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Comercial");
		assertNotNull(turnoController.create(turno));
		assertEquals(1, turnoController.getAll().size());
	}
	
	@Test
	void testGet() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno vespertino");
		Integer idTurnoCadastrado = turnoController.create(turno);
		Turno turnoConsultado = turnoController.get(idTurnoCadastrado);
		assertEquals("Turno vespertino", turnoConsultado.getNomeTurno());
	}
	
	@Test
	void testGetIdInvalido() {
		assertThrows(Exception.class, () -> turnoController.get(0));
	}
	
	@Test
	void testGetAll() {
		assertEquals(0, turnoController.getAll().size());
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno1");
		turnoController.create(turno);
		Turno turno2 = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno2");
		turnoController.create(turno2);
		assertEquals(2, turnoController.getAll().size());
		
	}
	
	@Test
	void testUpdate() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Update");
		Integer idCadastrado = turnoController.create(turno);
		Turno turnoNovo = new Turno(idCadastrado, LocalTime.now(), LocalTime.now(), "Turno3");
		//Turno turnoNovo = turnoController.get(idCadastrado);
		//turnoNovo.setNomeTurno("Turno3");
		boolean sucesso = turnoController.update(idCadastrado, turnoNovo);
		assertTrue(sucesso);
		assertEquals("Turno3", turnoController.get(idCadastrado).getNomeTurno());
	}

	@Test
	void testUpdateObjetoInexistente() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Update 2");
		Integer idCadastrado = turnoController.create(turno);
		Turno turnoNovo = turnoController.get(idCadastrado);
		assertThrows(Exception.class, () -> turnoController.update(10, turnoNovo));
	}

	@Test
	void testUpdateInvalidoNulo() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno4");
		Integer idCadastrado = turnoController.create(turno);
		Turno turnoNovo = null;
		assertThrows(Exception.class, () -> turnoController.update(idCadastrado, turnoNovo));
	}
	
	@Test
	void testUpdateInvalidoIdInexistente() {
		Turno turnoNovo = new Turno();
		assertThrows(Exception.class, () -> turnoController.update(0, turnoNovo));
	}
	
	@Test
	void testDelete() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno5");
		Integer idCadastrado = turnoController.create(turno);
		assertEquals(1, turnoController.getAll().size());
		turnoController.delete(idCadastrado);
		assertEquals(0, turnoController.getAll().size());
	}
	
	@Test
	void testDeleteInvalidoIdInexistente() {
		assertEquals(0, turnoController.getAll().size());
		assertThrows(Exception.class, () -> turnoController.delete(100));
	}
	
	@Test
	void testDeleteInvalidoIdIgualAZero() {
		assertThrows(Exception.class, () -> turnoController.delete(0));
	}
	
	@Test
	void testDeleteAll() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno1");
		turnoController.create(turno);
		Turno turno2 = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno2");
		turnoController.create(turno2);
		assertEquals(2, turnoController.getAll().size());
		turnoController.deleteAll();
		assertEquals(0, turnoController.getAll().size());
	}
}
