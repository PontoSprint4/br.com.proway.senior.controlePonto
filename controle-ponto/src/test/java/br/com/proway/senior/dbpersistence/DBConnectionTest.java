package br.com.proway.senior.dbpersistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.PontoDAO;

class DBConnectionTest {

	@Test
	void testGetSessionFactory() {
		DBConnection db = new DBConnection();
		SessionFactory session = db.getSessionFactory();
		assertNotNull(session);
	}

	@AfterAll
	public void testShutdown() throws Exception {
		DBConnection db = new DBConnection();
		Session session = db.getSession();
		db.shutdown();

		PontoDAO pdao = PontoDAO.getInstance(session);
		assertThrows(Exception.class, () -> pdao.get(149));
	}

	@Test
	void testGetSession() {
		DBConnection db = new DBConnection();
		Session session = db.getSession();

		assertNotNull(session);
	}

}
