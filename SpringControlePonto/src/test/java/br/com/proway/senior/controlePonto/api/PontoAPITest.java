package br.com.proway.senior.controlePonto.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.services.PontoService;

class PontoAPITest {
	PontoAPI api = new PontoAPI(new PontoService());
	
	PontoController controllerP = new PontoController(DBConnection.getSession());
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}
	
	@Test
	void testCriarPonto() throws Exception {
		Integer idPessoa = 41;
		LocalDateTime momentoPonto = LocalDateTime.now();
		
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		Integer idGerado = api.criarPonto(ponto);
		assertNotNull(idGerado);
	}

	@Test
	void testGetPonto() throws Exception {
		Integer idPessoa = 41;
		LocalDateTime momentoPonto = LocalDateTime.now();
		
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		Integer idGerado = api.criarPonto(ponto);
		assertNotNull(api.getPonto(idGerado));
	}

	@Test
	void testGetAllPontos() throws Exception {
		Integer idPessoa = 41;
		LocalDateTime momentoPonto = LocalDateTime.now();
		
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		api.criarPonto(ponto);
		assertEquals(1, api.getAllPontos().size());
	}

	@Test
	void testAtualizarPonto() throws Exception {
		Integer idPessoa = 41;
		LocalDateTime momentoPonto = LocalDateTime.now();
		
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		Integer idGerado = api.criarPonto(ponto);
		ponto.setIdPessoa(1);
		api.atualizarPonto(idGerado, ponto);
		assertEquals(ponto.getIdPessoa(), api.getPonto(idGerado).getIdPessoa());
	}

	@Test
	void testDeletePonto() throws Exception {
		Integer idPessoa = 41;
		LocalDateTime momentoPonto = LocalDateTime.now();
		
		Ponto ponto = new Ponto(idPessoa, momentoPonto);
		Integer id = api.criarPonto(ponto);
		api.deletePonto(id);
		assertEquals(0, api.getAllPontos().size());
	}

}
