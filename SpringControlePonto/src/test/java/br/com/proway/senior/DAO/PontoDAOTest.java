package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Ponto;

class PontoDAOTest {

	static Session session = DBConnection.getSession();
	static PontoDAO pdao = PontoDAO.getInstance(session);
	
	int idPessoa = 42;

	@AfterAll
	static void after() {
		pdao.deleteAll();
	}
	
	@Test
	void testGetInstance() {
		assertNotNull(PontoDAO.getInstance(DBConnection.getSession()));
	}

	@Test
	void testCreatePonto() {
		Ponto ponto = new Ponto(idPessoa, LocalDateTime.now());
		Integer id = pdao.create(ponto);
		assertNotNull(id);
	}

	@Test
	void testUpdatePonto() {
		Ponto ponto = new Ponto(idPessoa,LocalDateTime.now());
		Integer id = pdao.create(ponto);
		ponto.setIdPonto(id);
		ponto.setMomentoPonto(LocalDateTime.now().plusHours(2));
		Boolean sucesso = pdao.update(ponto);
		assertTrue(sucesso);
	}

	@Test
	void testDeleteInt() {
		Ponto ponto = new Ponto(idPessoa,LocalDateTime.now());
		Integer id = pdao.create(ponto);
		pdao.delete(id);
		assertNull(pdao.get(id));
	}

	@Test
	void testGetInt() {
		Ponto ponto = new Ponto(idPessoa,LocalDateTime.now());
		Integer id = pdao.create(ponto);
		assertNotNull(pdao.get(id));
	}

	@Test
	void testGetAll() {
		int pontoAntes = pdao.getAll().size();
		Ponto ponto = new Ponto(idPessoa,LocalDateTime.now());
		Ponto ponto2 = new Ponto(idPessoa,LocalDateTime.now());
		pdao.create(ponto);
		pdao.create(ponto2);
		assertEquals(pontoAntes+2, pdao.getAll().size());
	}
	@Test
	void testdeleteAll() {
		Ponto ponto = new Ponto(idPessoa,LocalDateTime.now());
		Ponto ponto2 = new Ponto(idPessoa,LocalDateTime.now());
		pdao.create(ponto);
		pdao.create(ponto2);
		int pontoAntes = pdao.getAll().size();
		pdao.deleteAll();
		assertEquals(0, pdao.getAll().size());
	}

}
