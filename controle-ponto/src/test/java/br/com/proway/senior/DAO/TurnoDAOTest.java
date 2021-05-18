package br.com.proway.senior.DAO;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Turno;

class TurnoDAOTest {
	
	static Turno turno;
	static Session session;
	static TurnoDAO tdao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		tdao = TurnoDAO.getInstance(session);
	}

	@Test
	void testInsert() {
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) tdao.getAll();
		Turno turno1 = new Turno(0, LocalTime.now(), LocalTime.now().plusHours(7), "Primeiro Turno");
		Turno turno2 = new Turno(0, LocalTime.now().plusHours(2), LocalTime.now().plusHours(9), "Segundo Turno");
		Turno turno3 = new Turno(0, LocalTime.now().plusHours(4), LocalTime.now().plusHours(11), "Terceiro Turno");
		Turno turno4 = new Turno(0, LocalTime.now().plusHours(6), LocalTime.now().plusHours(13), "Turno Comercial");
		Turno turno5 = new Turno(0, LocalTime.now().plusHours(8), LocalTime.now().plusHours(15), "Turno Noturno");
		tdao.insert(turno1);
		tdao.insert(turno2);
		tdao.insert(turno3);
		tdao.insert(turno4);
		tdao.insert(turno5);
		assertEquals((listaTurnos.size() + 5), tdao.getAll().size());
	}

	@Test
	void testUpdate() {
		session.clear();
		Turno turnoASerAtualizado = tdao.getAll().get(1);
		turnoASerAtualizado.setHoraInicio(LocalTime.now());
		turnoASerAtualizado.setHoraFim(LocalTime.now().plusHours(8));
		assertTrue(tdao.update(turnoASerAtualizado));
	}

	@Test
	void testDelete() {
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) tdao.getAll();
		tdao.delete(tdao.getAll().get(3));
		assertEquals((listaTurnos.size() - 1), tdao.getAll().size());
	}

	@Test
	void testGet() {
		int idCapturado = tdao.getAll().get(1).getId();
		assertEquals(idCapturado, tdao.getAll().get(1).getId());
	}
	
	@Test
	void testGetAll() {
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) tdao.getAll();
		assertEquals(listaTurnos.size(), tdao.getAll().size());
	}

	@Test
	void testGetAllNotNull() {
		assertNotNull(tdao.getAll());
	}

}
