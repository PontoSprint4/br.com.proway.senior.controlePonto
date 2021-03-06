package br.com.proway.senior.controlePonto.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Ponto;

class PontoServiceTest {

	static PontoController pController;
	static PontoService pService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pController = new PontoController(DBConnection.getSession());
		pService = new PontoService();
	}
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}

	@Test
	void testCreateGetPonto() throws Exception {
		Ponto ponto = new Ponto(70, LocalDateTime.now());
		int idCapturado = pService.createPonto(ponto);
		assertEquals(idCapturado, pService.getPonto(idCapturado).getIdPonto());
		
	}

	@Test
	void testUpdatePonto() throws Exception {
		Ponto ponto = new Ponto(70, LocalDateTime.now());
		Integer idPonto = pService.createPonto(ponto);
		
		Ponto pontoAtualizado = new Ponto(70, LocalDateTime.now().plusMinutes(30));
		
		assertTrue(pService.updatePonto(idPonto, pontoAtualizado));
	}

	@Test
	void testDeletePonto() throws Exception {
		Integer tamanhoAntes = pService.getAll().size();
		
		Ponto ponto = new Ponto(70, LocalDateTime.now());
		Integer idPonto = pService.createPonto(ponto);
		
		pService.deletePonto(idPonto);
		assertEquals(tamanhoAntes, pService.getAll().size());
	}
	
	@Test
	void testGetPonto() throws Exception {
		Ponto ponto = new Ponto(70, LocalDateTime.now());
		Integer idPonto = pService.createPonto(ponto);
		assertNotNull(pService.getPonto(idPonto));
	}
	
	@Test
	void testGetAllPonto() throws Exception {
		Integer tamanhoAntes = pService.getAll().size();
		Ponto ponto = new Ponto(70, LocalDateTime.now());
		pService.createPonto(ponto);
		pService.createPonto(ponto);
		pService.createPonto(ponto);
		assertEquals(tamanhoAntes+3, pService.getAll().size());
	}

}
