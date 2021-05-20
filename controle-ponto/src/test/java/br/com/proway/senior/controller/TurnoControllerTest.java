package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.junit.Ignore;
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

	@Test
	void testCreate() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Comercial");
		Turno turno2 = new Turno(null, LocalTime.now().plusHours(8), LocalTime.now().plusHours(16), "Segundo Turno");
		List<Turno> listaTurnos = turnoController.getAll();
		turnoController.create(turno);
		turnoController.create(turno2);
		assertEquals((listaTurnos.size() + 2), turnoController.getAll().size());
	}
	
	@Test
	void testGet() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno vespertino");
		turnoController.create(turno);
		Turno turnoConsultado = turnoController.get(turno.getId());
		assertEquals(turno.getNomeTurno(), turnoConsultado.getNomeTurno());
	}
	
	@Test
	void testGetIdInvalido() {
		assertThrows(Exception.class, () -> turnoController.get(0));
	}
	
	@Test
	void testGetAll() {
		assertNotNull(turnoController.getAll());
	}
	
	@Test
	void testUpdate() throws Exception {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno2");
		int id =turnoController.create(turno);
		
		Turno turnoParaAlterar = turnoController.get(id);
		Turno turnoNovo = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Teste");
		
		turnoController.update(turnoParaAlterar.getId(), turnoNovo);
		Turno turnoAlterado = turnoController.get(turnoParaAlterar.getId());
		
		assertEquals("Turno Teste",turnoAlterado.getNomeTurno());
	}
	
	@Test
	void testDelete() throws Exception {
		List<Turno> listaTurnos = turnoController.getAll();
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno");
		turnoController.create(turno);
		turnoController.delete(turno.getId());
		List<Turno> listaTurnosPosDeletar = turnoController.getAll();
		assertEquals((listaTurnos.size()), listaTurnosPosDeletar.size());
	}

}
