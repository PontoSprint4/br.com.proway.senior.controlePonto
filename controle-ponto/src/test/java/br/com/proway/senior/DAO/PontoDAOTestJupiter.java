package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Ponto;

class PontoDAOTestJupiter {
	static PontoDAO pdao;
    static Session session;
    static Ponto ponto;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
        pdao = PontoDAO.getInstance(session);
	}

	@Test
	void testInsert() {
		Ponto ponto1 = new Ponto(null, LocalDateTime.of(2021, 5, 13, 23, 59));
        Ponto ponto2 = new Ponto(null, LocalDateTime.of(2020, 6, 15, 9, 15));
        Ponto ponto3 = new Ponto(null, LocalDateTime.of(2019, 6, 15, 9, 15));
        pdao.insert(ponto1);
        pdao.insert(ponto2);
        pdao.insert(ponto3);
        ArrayList<Ponto> listaPontos = (ArrayList<Ponto>) pdao.getAll();
        for (int i = 0; i < listaPontos.size(); i++) {
			if (listaPontos.get(i).equals(ponto1)) {
				assertEquals(ponto1, pdao.getAll().get(i));
			}
		}
	}
	
	@Deprecated
	void testInsertInvalido() {
		Ponto ponto = new Ponto();
        pdao.insert(ponto);
        ArrayList<Ponto> listaPontos = (ArrayList<Ponto>) pdao.getAll();
        for (int i = 0; i < listaPontos.size(); i++) {
			if (listaPontos.get(i).equals(ponto)) {
				assertNull(pdao.getAll().get(i));
			}
		}
	}

	@Test
	void testUpdate() {
		session.clear();
		ArrayList<Ponto> listaPontos = (ArrayList<Ponto>) pdao.getAll();
		for (int i = 0; i < listaPontos.size(); i++) {
			if (i == 6) {
				ponto = listaPontos.get(i);
			}
		}
        ponto.setMomentoPonto(LocalDateTime.of(1515, 6, 6, 20, 59));
        assertTrue(pdao.update(ponto));
	}

	@Test
	void testDelete() {
		ArrayList<Ponto> listaPontos = (ArrayList<Ponto>) pdao.getAll();
		for (int i = 0; i < listaPontos.size(); i++) {
			if (i == 8) {
				ponto = listaPontos.get(i);
			}
		}
        assertTrue(pdao.delete(ponto));
	}

	@Test
	void testGet() throws Exception {
		int idCapturado = 0;
		ArrayList<Ponto> listaPontos = (ArrayList<Ponto>) pdao.getAll();
		for (int i = 0; i < listaPontos.size(); i++) {
			if ((i+1) == 10) {
				idCapturado = listaPontos.get(10).getIdPonto();
			}
		}
        assertNotNull(pdao.get(idCapturado));
	}

	@Test
	void testGetAll() {
        assertNotNull(pdao.getAll());
	}

}
