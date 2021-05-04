package br.com.proway.senior.DAO;

<<<<<<< HEAD
import static org.junit.Assert.*;

import org.junit.Test;

public class PostgresConnectorTest {
	
=======
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.proway.senior.model.Pessoa;

public class PostgresConnectorTest {

>>>>>>> testesJornadaDAO
	@Test
	public void testDbVersion() {
		String version = PostgresConnector.dbVersion();
		assertEquals("PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit", version);
	}

	@Test
	public void testExecuteUpdateDelete() {
		String delete = "DELETE FROM jornadas";
		try {
			PostgresConnector.executeUpdate(delete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
