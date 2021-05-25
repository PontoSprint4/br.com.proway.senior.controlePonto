package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.DAO.TurnoDAO;
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
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}

	@Test
	void testCreate() {
		assertEquals(0, pontoController.getAll().size());
		Ponto ponto = new Ponto(null, null, LocalDateTime.now());
		pontoController.create(ponto);
		assertNotNull(pontoController.getAll());
		assertEquals(1, pontoController.getAll().size());
		
	}

	@Test
	void testGet() throws Exception {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 05, 21, 9, 40));
		Integer idCadastrado = pontoController.create(ponto);
		Ponto pontoConsultado = pontoController.get(idCadastrado);
		assertEquals(LocalDateTime.of(2021, 05, 21, 9, 40), pontoConsultado.getMomentoPonto());
	}
	
	@Test
	void testGetIdInvalido() {
		assertThrows(Exception.class, () -> pontoController.get(0));
	}

	@Test
	void testUpdate() throws Exception {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 05, 21, 9, 40));
		Integer idCadastrado = pontoController.create(ponto);
		Ponto pontoNovo = pontoController.get(idCadastrado);
		pontoNovo.setMomentoPonto(LocalDateTime.of(2021, 05, 21, 10, 4));
		boolean sucesso = pontoController.update(pontoNovo, idCadastrado);
		assertTrue(sucesso);
		assertEquals(LocalDateTime.of(2021, 05, 21, 10, 4), pontoController.get(idCadastrado).getMomentoPonto());
	}
	
	@Test
	void testUpdateObjetoInexistente() throws Exception {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 5, 21, 10, 9));
		Integer idCadastrado = pontoController.create(ponto);
		Ponto pontoNovo = pontoController.get(idCadastrado);
		assertThrows(Exception.class, () -> pontoController.update(pontoNovo, 10));
	}
	
	@Test
	void testUpdateInvalidoNulo() {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 5, 21, 10, 14));
		Integer idCadastrado = pontoController.create(ponto);
		Ponto pontoNovo = null;
		assertThrows(Exception.class, () -> pontoController.update(pontoNovo, idCadastrado));		
	}
	
	@Test
	void testUpdateInvalidoIdInexistente() {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 5, 21, 10, 14));
		assertThrows(Exception.class, () -> pontoController.update(ponto, 0));
	}

	@Test
	void testDelete() throws Exception {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 5, 21, 10, 26));
		Integer idCadastrado = pontoController.create(ponto);
		assertEquals(1, pontoController.getAll().size());
		pontoController.delete(idCadastrado);
		assertEquals(0, pontoController.getAll().size());
	}
	
	@Test
	void testDeleteInvalidoIdInexistente() {
		assertEquals(0,  pontoController.getAll().size());
		assertThrows(Exception.class, () -> pontoController.delete(100));
	}
	
	@Test
	void testDeleteInvalidoIgualAZero() {
		assertThrows(Exception.class, () -> pontoController.delete(0));
	}
	
	@Test
	void testDeleteAll() {
		Ponto ponto = new Ponto(null, null, LocalDateTime.of(2021, 5, 21, 10, 48));
		pontoController.create(ponto);
		Ponto ponto2 = new Ponto(null, null, LocalDateTime.of(2021, 5, 21, 10, 49));
		pontoController.create(ponto2);
		assertEquals(2, pontoController.getAll().size());
		pontoController.deleteAll();
		assertEquals(0, pontoController.getAll().size());
	}
}
