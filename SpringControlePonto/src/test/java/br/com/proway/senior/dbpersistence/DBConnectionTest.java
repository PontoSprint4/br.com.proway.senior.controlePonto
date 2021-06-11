package br.com.proway.senior.dbpersistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;

class DBConnectionTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@Test
	void initialize() {
		assertNotNull(new DBConnection());
	}
	@Test
	void testGetSessionFactory() {
		assertNotNull(DBConnection.getSessionFactory());
	}
	
	@Test
	void testGetSessionFactorySenhaErrada() throws Exception {
		DBConnection.clearDBConnection();
		DBConnection.setPassword("errado");
		assertThrows( ExceptionInInitializerError.class, () -> DBConnection.getSessionFactory());
		DBConnection.setPassword("admin");
	}

	@Test
	void testShutdown() throws Exception {
		Session session = DBConnection.getSession();
		SessionFactory sessionFac = DBConnection.getSessionFactory();
		DBConnection.shutdown();
		assertFalse(session.isConnected());
		assertTrue(sessionFac.isClosed());
		DBConnection.getSession();
		
	}

	@Test
	void testGetSession() {
		assertNotNull(DBConnection.getSession());
	}

}
