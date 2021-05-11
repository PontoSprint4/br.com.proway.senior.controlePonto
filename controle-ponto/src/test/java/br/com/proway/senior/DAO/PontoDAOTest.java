package br.com.proway.senior.DAO;

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
				 	
//		String query1 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 111 + ",'" + LocalDateTime.now() + "')"; 
//		String query2 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 112 + ",'" + LocalDateTime.now() + "')";
		String query3 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 113 + ",'" + LocalDateTime.now() + "')";
		
		// pessoa id = 114
		String query4 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now() + "')";
		String query5 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().plusHours(4) + "')";
		String query6 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().plusHours(6) + "')";
		String query7 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
				+ 114 + ",'" + LocalDateTime.now().plusHours(10) + "')";
		
		try {
			PostgresConnector.executeUpdate(queryDrop);
			PostgresConnector.executeUpdate(queryCreate);
//			PostgresConnector.executeUpdate(query1);
//			PostgresConnector.executeUpdate(query2);
			PostgresConnector.executeUpdate(query3);
			PostgresConnector.executeUpdate(query4);
			PostgresConnector.executeUpdate(query5);
			PostgresConnector.executeUpdate(query6);
			PostgresConnector.executeUpdate(query7);
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
	public void testReadByIdJornada() {
		PontoDAO db = PontoDAO.getInstance();
		db.readByIdJornada(113);
		assertTrue(db.readAll().size() == 5);
	}
	
	@Test
	public void testCreate() {
		PontoDAO db = PontoDAO.getInstance();
		Jornada jornada = new Jornada(555, 2, LocalDate.now(), 3);
		
		//jornada.setId(555);
		db.create(jornada);
		
		assertTrue(db.readAll().size() == 8);
	}

	@Test
	public void testDelete() {
		PontoDAO db = PontoDAO.getInstance();
		db.delete(2);
		assertTrue(db.readAll().size() == 7);
	}
	
	
	/*@Test
	 * public void testReadByIdJornada() {
		PontoDAO db = PontoDAO.getInstance();
		JornadaDAO jornadaDB = JornadaDAO.getInstance();
		jornadaDB.;
		db.readByIdJornada(114);
	}*/

}
