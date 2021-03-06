package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

class GenericDAOTest {
	static PontoDAO daop;
	static Ponto ponto;
	static int idPessoa;
	
	static TurnoDAO daot;
	static Turno turno;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		daop = PontoDAO.getInstance(DBConnection.getSession());
		idPessoa = 155;
		
		daot = TurnoDAO.getInstance(DBConnection.getSession());
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(3), "Turno Noturno");
	
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		daop.deleteAll();
		daot.deleteAll();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		daop.deleteAll();
		daot.deleteAll();
	}

	@Test
	void testCreate() {
		Integer newId = daop.create(new Ponto(idPessoa, LocalDateTime.now()));
		assertNotNull(newId);
	}

	@Test
	void testGet() {
		Integer newId = daop.create(new Ponto(idPessoa, LocalDateTime.now()));
		assertNotNull(daop.get(newId));
	}

	@Test
	void testUpdate() {
		Integer newId = daop.create(new Ponto(idPessoa, LocalDateTime.now()));
		Ponto instancia = daop.get(newId);
		LocalDateTime novaHora = LocalDateTime.now().plusHours(2);
		instancia.setMomentoPonto(novaHora);
		assertTrue(daop.update(instancia));
		assertEquals(novaHora, daop.get(newId).getMomentoPonto());
	}

	@Test
	void testDelete() {
		int id = daop.create(new Ponto(idPessoa, LocalDateTime.now()));
		assertTrue(daop.delete(id));
		assertNull(daop.get(id));
	}

	@Test
	void testGetAll() {
		int tamanhoAntes = daop.getAll().size();
		daop.create(new Ponto(idPessoa,LocalDateTime.now()));
		assertEquals(tamanhoAntes + 1 , daop.getAll().size());
	}

	@Test
	void testDeleteAll() {
		daop.create(new Ponto(idPessoa, LocalDateTime.now()));
		assertTrue(daop.deleteAll());
		assertFalse(daop.deleteAll());
		assertEquals(0, daop.getAll().size());
	}

	@Test
	void testListarPorValorDeColunaComStringIncompleta() {
		int novaId = daot.create(turno);
		List<Turno> turnos = daot.listarPorValorDeColunaComStringIncompleta(Turno.class, "nomeTurno", "Turn");
		assertTrue(turnos.contains(daot.get(novaId)));
	}

	@Test
	void testListarPorValorDeColunaExatoClassOfTStringInteger() {
		int novaId = daot.create(turno);
		List<Turno> turnos = daot.listarPorValorDeColunaExato(Turno.class, "id", novaId);
		assertTrue(turnos.contains(daot.get(novaId)));
	}

	@Test
	void testListarPorValorDeColunaExatoClassOfTStringString() {
		int novaId = daot.create(turno);
		List<Turno> turnos = daot.listarPorValorDeColunaExato(Turno.class, "nomeTurno", "Turno Noturno");
		assertTrue(turnos.contains(daot.get(novaId)));
	}


}
