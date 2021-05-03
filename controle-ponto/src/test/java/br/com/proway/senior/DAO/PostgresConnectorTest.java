package br.com.proway.senior.DAO;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

public class PostgresConnectorTest {

	@Test
	public void testExecuteQuery() {
		String insert1 = "INSERT INTO jornadas VALUES (353, '2021-05-03', 235)";
		String insert2 = "INSERT INTO jornadas VALUES (235, '2021-05-02', 256)";
		String select = "SELECT * FROM jornadas";
		String delete = "DELETE FROM jornadas";
		try {
			PostgresConnector.executeUpdate(insert1);
			PostgresConnector.executeUpdate(insert2);
			ResultSet rs = PostgresConnector.executeQuery(select);
			rs.next();
			assertEquals(1, rs.getInt(1));
			assertEquals(1, rs.getInt(1));
			PostgresConnector.executeUpdate(delete);
			ResultSet rs2 = PostgresConnector.executeQuery(select);
			assertFalse(rs2.next());
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	@Test
	public void testDbVersion() {
		String version = PostgresConnector.dbVersion();
		assertEquals("PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit", version);
	}

	@Test
	public void testExecuteUpdateDelete() {
		String delete = "DELETE FROM tabelateste";
		try {
			PostgresConnector.executeUpdate(delete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
