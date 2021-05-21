package br.com.proway.senior.dbpersistence;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
		DBConnection.shutdown();
		assertFalse(DBConnection.getSession().isConnected());
		assertTrue(DBConnection.getSessionFactory().isClosed());
		
	}

	@Test
	void testGetSession() {
		assertNotNull(DBConnection.getSession());
	}

}
