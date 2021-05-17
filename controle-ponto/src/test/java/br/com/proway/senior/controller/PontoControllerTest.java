package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

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
		pdao = new PontoDAO(session);

	}

	@Test
	void testInsert() throws Exception {
		Ponto ponto = new Ponto(null, LocalDateTime.of(1997, 5, 13, 23, 59));
		pontoController.insert(ponto);
		ArrayList<Ponto> listaPontos = pontoController.getAll();
		for (int i = 0; i < listaPontos.size(); i++) {
			if (listaPontos.get(i).equals(ponto)) {
				assertEquals(ponto, pontoController.getAll().get(i));
			}
		}
	}
	
	@Test
	void testInsertNotEquals() throws Exception {
		Ponto ponto = new Ponto(null, LocalDateTime.of(1997, 5, 13, 23, 59));
		pontoController.insert(ponto);
		ArrayList<Ponto> listaPontos = pontoController.getAll();
		for (int i = 0; i < listaPontos.size(); i++) {
			if (!listaPontos.get(i).equals(ponto)) {
				assertNotEquals(ponto, pontoController.getAll().get(i));
			}
		}
	}
	
	@Test
	void testGet() throws Exception {
		assertNotNull(pontoController.get(19));
	}
	
	@Test
	void testGetFail() throws Exception {
		assertNotNull(pontoController.get(-22));
//		assertTh
	}

	@Test
	void testGetAll() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}

}
