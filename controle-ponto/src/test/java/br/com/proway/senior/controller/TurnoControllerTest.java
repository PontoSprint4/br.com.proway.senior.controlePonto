package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import org.hibernate.Session;
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
	void testInsert() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Comercial");
		Turno turno2 = new Turno(null, LocalTime.now().plusHours(8), LocalTime.now().plusHours(16), "Segundo Turno");
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) turnoController.getAll();
		turnoController.insert(turno);
		turnoController.insert(turno2);
		assertEquals((listaTurnos.size() + 2), turnoController.getAll().size());
	}
	
	@Test
	void testInsertNotEquals() {
		Turno turno = new Turno(null, LocalTime.now(), LocalTime.now().plusHours(8), "Turno Comercial");
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) turnoController.getAll();
		turnoController.insert(turno);
		assertNotEquals(listaTurnos.size(), turnoController.getAll().size());
	}

	@Test
	void testGet() throws Exception {
		int idCapturado = 0;
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) turnoController.getAll();
		for (int i = 0; i < listaTurnos.size(); i++) {
			if ((i+1) == 1) {
				idCapturado = listaTurnos.get(1).getId();
			}
		}
		assertNotNull(turnoController.get(idCapturado));
	}

	@Test
	void testGetAll() {
		assertNotNull(turnoController.getAll());
	}
	
	@Test
	void testGetAllPorTamanhoDaLista() {
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) turnoController.getAll();
		assertEquals(listaTurnos.size(), turnoController.getAll().size());
	}

	@Test
	void testDelete() {
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) turnoController.getAll();
		Turno turno = turnoController.getAll().get(1);
		turnoController.delete(turno);
		assertEquals((listaTurnos.size() - 1), turnoController.getAll().size());
	}

}
