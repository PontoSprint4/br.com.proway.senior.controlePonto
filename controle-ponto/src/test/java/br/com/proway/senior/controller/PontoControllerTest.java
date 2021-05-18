package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Ponto;

class PontoControllerTest {

	static Session session;
	static PontoController pontoController;
	static PontoDAO pdao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		pontoController = new PontoController(session);
		pdao = PontoDAO.getInstance(session);

	}

	@Test
	void testInsert() {
		Ponto ponto = new Ponto(null, LocalDateTime.of(1997, 5, 13, 23, 59));
		Ponto ponto1 = new Ponto(null, LocalDateTime.of(2005, 5, 13, 23, 59));
		ArrayList<Ponto> listaPontos = pontoController.getAll();
		pontoController.insert(ponto);
		pontoController.insert(ponto1);
		assertEquals((listaPontos.size() + 2), pontoController.getAll().size());
	}

	@Test
	void testInsertNotEquals() {
		Ponto ponto = new Ponto(null, LocalDateTime.of(2009, 5, 13, 23, 59));
		Ponto ponto1 = new Ponto(null, LocalDateTime.of(2020, 5, 13, 23, 59));
		ArrayList<Ponto> listaPontos = pontoController.getAll();
		pontoController.insert(ponto);
		pontoController.insert(ponto1);
		assertNotEquals(listaPontos.size(), pontoController.getAll());
	}

	@Test
	void testGet() throws Exception {
		int idCapturado = 0;
		ArrayList<Ponto> listaPontos = (ArrayList<Ponto>) pdao.getAll();
		for (int i = 0; i < listaPontos.size(); i++) {
			if ((i+1) == 1) {
				idCapturado = listaPontos.get(1).getIdPonto();
			}
		}
		assertNotNull(pontoController.get(idCapturado));
	}

	@Test
	void testGetFail() {
		assertThrows(Exception.class, () -> pontoController.get(999));
	}
	
	@Test
	void testGetFailNegativo() {
		assertThrows(Exception.class, () -> pontoController.get(-22));
	}

	@Test
	void testGetAllPorTamanhoDaLista() {
		ArrayList<Ponto> listaPontos = pontoController.getAll();
		assertEquals(listaPontos.size(), pontoController.getAll().size());
	}

	@Test
	void testGetAllListaNaoPodeSerNull() {
		assertNotNull(pontoController.getAll());

	}
	
	@Test
	void testUpdate() {
		Ponto ponto = pontoController.getAll().get(1);
		ponto.setMomentoPonto(LocalDateTime.now().plusHours(4));
		assertTrue(pdao.update(ponto));
	}

	@Test
	void testDelete() {
		Ponto ponto = pontoController.getAll().get(1);
		ArrayList<Ponto> listaPontos = pontoController.getAll();
		pontoController.delete(ponto);
		assertEquals((listaPontos.size() - 1), pontoController.getAll().size());
	}

}
