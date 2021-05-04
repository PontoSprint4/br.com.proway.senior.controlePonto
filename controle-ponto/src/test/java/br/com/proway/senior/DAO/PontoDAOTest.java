package br.com.proway.senior.DAO;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.proway.senior.model.Jornada;

public class PontoDAOTest {
	
	@BeforeClass
	public static void refreshDatabase() {
		String queryDrop = "DROP TABLE pontos";
		
		String queryCreate = "CREATE TABLE pontos ("
				+ "id SERIAL PRIMARY KEY NOT NULL,"
				+ "ceJornada INT NOT NULL,"
				+ "momentoPonto TIMESTAMP NOT NULL)";
				 	
		String query1 = "INSERT INTO pontos (ceJornada, momentoPonto) VALUES ("
				+ 111 + ",'" + LocalDateTime.now().toString()+")";
		String query2 = "INSERT INTO pontos (ceJornada, momentoPonto) VALUES ("
				+ 112 + ",'" + LocalDateTime.now().toString() + ")";
		String query3 = "INSERT INTO pontos (ceJornada, momentoPonto) VALUES ("
				+ 113 + ",'" + LocalDateTime.now().toString()+")";
		String query4 = "INSERT INTO jornadas (ceJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().toString()+")";
		
		try {
			PostgresConnector.executeUpdate(queryDrop);
			PostgresConnector.executeUpdate(queryCreate);
			PostgresConnector.executeUpdate(query1);
			PostgresConnector.executeUpdate(query2);
			PostgresConnector.executeUpdate(query3);
			PostgresConnector.executeUpdate(query4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	@Before
	public void cleanDAO() {
		PontoDAO.newInstance();
	}	
	
	@Test
	public void testCreate() {
		PontoDAO db = PontoDAO.getInstance();
		Jornada jornada = new Jornada();
		db.create(jornada);
		assertTrue(db.readAll().size() == 5);
	}

	@Test
	public void testDelete() {
		PontoDAO db = PontoDAO.getInstance();
		db.delete(2);
		assertTrue(db.readAll().size() == 3);
	}

}
