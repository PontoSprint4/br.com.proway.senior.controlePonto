package br.com.proway.senior.dbpersistence;

import static org.junit.Assert.assertNotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;

import br.com.proway.senior.DAO.PontoDAO;

public class DBConnectionTest {

	@Test
	public void testGetSessionFactory() {
		DBConnection db = new DBConnection();
		SessionFactory session = db.getSessionFactory();
		assertNotNull(session);
	}

	@Test
	public void testShutdown() {
		DBConnection db = new DBConnection();
		Session session = db.getSession();
		db.shutdown();
		
		PontoDAO pontodao = PontoDAO.getInstance(session);
		pontodao.get(3);
	
	}

	@Test
	public void testGetSession() {
		DBConnection db = new DBConnection();
		Session session = db.getSession();
		
		assertNotNull(session);
	}

}
