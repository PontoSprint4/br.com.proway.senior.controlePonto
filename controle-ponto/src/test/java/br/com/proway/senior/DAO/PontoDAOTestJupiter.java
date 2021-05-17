package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Ponto;

class PontoDAOTestJupiter {
	static PontoDAO instance;
    static Session session;
    static Ponto ponto;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
        instance = PontoDAO.getInstance(session);
	}

	@Test
	void testInsert() {
		Ponto ponto1 = new Ponto(null, LocalDateTime.of(2021, 5, 13, 23, 59));
        Ponto ponto2 = new Ponto(null, LocalDateTime.of(2020, 6, 15, 9, 15));
        instance.insert(ponto1);
        instance.insert(ponto2);
        assertNotNull(ponto1);
	}

	@Test
	void testUpdate() {
		session.clear();
        ponto = new Ponto(90, LocalDateTime.of(1515, 6, 6, 20, 59));
        assertTrue(instance.update(ponto));
	}

	@Test
	void testDelete() {
		ponto = new Ponto(3, LocalDateTime.of(2222, 5, 13, 20, 59));
        instance.insert(ponto);
        assertTrue(instance.delete(ponto));
	}

	@Test
	void testGet() throws Exception {
        assertEquals(90, (int) instance.get(90).getIdPonto());
	}
	
	@Test
	void testGetNotNull() throws Exception {
        assertNotNull(instance.get(90));
	}

	@Test
	void testGetAll() {
        assertNotNull(instance.getAll());
	}

}
