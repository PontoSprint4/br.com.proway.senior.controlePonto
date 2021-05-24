package br.com.proway.senior.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;

class JornadaControllerPreparaParaDTOTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@AfterEach
	void cleanDB() {
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
