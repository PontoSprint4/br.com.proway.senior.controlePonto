package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
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
				+ "idJornada INT NOT NULL,"
				+ "momentoPonto TIMESTAMP NOT NULL)";
				 	
		String query1 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 111 + ",'" + LocalDateTime.now().plusHours(3) + "')"; 
		String query2 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 112 + ",'" + LocalDateTime.now().plusMinutes(51) + "')";
		String query3 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 113 + ",'" + LocalDateTime.now().plusMinutes(17) + "')";
		String query4 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 111 + ",'" + LocalDateTime.now().plusHours(9) + "')"; 
		// pessoa id = 114
		String query5 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now() + "')";
		String query6 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().plusHours(4) + "')";
		String query7 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().plusHours(6) + "')";
		String query8 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().plusHours(10) + "')";
		
		try {
			PostgresConnector.executeUpdate(queryDrop);
			PostgresConnector.executeUpdate(queryCreate);
			PostgresConnector.executeUpdate(query1);
			PostgresConnector.executeUpdate(query2);
			PostgresConnector.executeUpdate(query3);
			PostgresConnector.executeUpdate(query4);
			PostgresConnector.executeUpdate(query5);
			PostgresConnector.executeUpdate(query6);
			PostgresConnector.executeUpdate(query7);
			PostgresConnector.executeUpdate(query8);
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
	public void testRead() {
		PontoDAO db = PontoDAO.getInstance();
		db.read(2);
	}
	
	@Test
	public void testReadByIdJornada() {
		PontoDAO db = PontoDAO.getInstance();
		db.readByIdJornada(111);
		assertFalse(db.readAll().size() == 5);
	}
	
	@Test
	public void testCreate() {
		PontoDAO db = PontoDAO.getInstance();
		Jornada jornada = new Jornada(555, 2, LocalDate.now(), 3);
		
		
		db.create(jornada); // 9 itens no db		
		assertTrue(db.readAll().size() == 9);
	}

	@Test
	public void testDelete() {
		PontoDAO db = PontoDAO.getInstance();
		db.delete(2);
		assertEquals(db.readAll().size(), 8);
	}
	
}
