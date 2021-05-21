package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

class JornadaDAOTest {
	
	static JornadaDAO dao = JornadaDAO.getInstance(DBConnection.getSession());
	
	LocalDate data = LocalDate.now();
	int idPessoa = 12;
	Turno turnoPadrao = new Turno(LocalTime.now(), LocalTime.now().plusMinutes(42), "Turno Teste");
	Ponto ponto1 = new Ponto(LocalDateTime.now());
	Ponto ponto2 = new Ponto(LocalDateTime.now().plusMinutes(42));
	
	@AfterAll
	static void after() {
		dao.deleteAll();
	}
	
	@Test
	void testGetInstance() {
		assertNotNull(JornadaDAO.getInstance(DBConnection.getSession()));
	}

	@Test
	void testCreateJornada() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		Integer idCriado = dao.create(instancia);
		assertNotNull(idCriado);
	}

	@Test
	void testGetInt() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		int idCriado = dao.create(instancia);
		assertNotNull(dao.get(idCriado));
	}

	@Test
	void testUpdateJornada() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		int idCriado = dao.create(instancia);
		
		Jornada jornadaSalva = dao.get(idCriado);
		LocalDate novaData = LocalDate.now().plusDays(12);
		jornadaSalva.setData(novaData);
		
		assertTrue(dao.update(jornadaSalva));
		assertEquals(novaData, dao.get(idCriado).getData());
	}

	@Test
	void testDeleteInt() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		int idCriado = dao.create(instancia);
		
		assertTrue(dao.delete(idCriado));
		assertNull(dao.get(idCriado));
	}

	@Test
	void testGetAll() {
		int tamanhoAntes = dao.getAll().size();
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		dao.create(instancia);
		assertEquals(tamanhoAntes+1, dao.getAll().size());
	}

	@Test
	void testDeleteAll() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		dao.create(instancia);
		assertTrue(dao.deleteAll());
		assertEquals(0, dao.getAll().size());
		assertFalse(dao.deleteAll()); // Nada a deletar, FALSE!
	}

	@Test
	void testReadByIdPessoa() {
		int idCriado = dao.create(new Jornada(data, idPessoa, turnoPadrao));
		Jornada retornado = dao.get(idCriado);
		
		assertTrue(dao.readByIdPessoa(idPessoa).contains(retornado));
		
	}
}
