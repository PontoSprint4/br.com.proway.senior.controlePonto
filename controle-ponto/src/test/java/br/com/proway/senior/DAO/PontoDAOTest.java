package br.com.proway.senior.DAO;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Ponto;

class PontoDAOTest {

	static Session session = DBConnection.getSession();
	static PontoDAO pdao = PontoDAO.getInstance(session);

	@Test
	void testGetInstance() {
		assertNotNull(PontoDAO.getInstance(DBConnection.getSession()));
	}

	@Test
	void testCreatePonto() {
		Ponto ponto = new Ponto(LocalDateTime.now());
		Integer id = pdao.create(ponto);
		assertNotNull(id);
	}

	@Test
	void testUpdatePonto() {
		Ponto ponto = new Ponto(LocalDateTime.now());
		Integer id = pdao.create(ponto);
		ponto.setIdPonto(id);
		ponto.setMomentoPonto(LocalDateTime.now().plusHours(2));
		Boolean sucesso = pdao.update(ponto);
		assertTrue(sucesso);
	}

	@Test
	void testDeleteInt() {
		Ponto ponto = new Ponto(LocalDateTime.now());
		Integer id = pdao.create(ponto);
		pdao.delete(id);
		assertNull(id);
	}

	@Test
	void testGetInt() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

}
