<<<<<<< HEAD
//package br.com.proway.senior.DAO;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.sql.SQLException;
//import java.time.LocalDate;
//
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import br.com.proway.senior.dbpersistence.PostgresConnector;
//import br.com.proway.senior.model.PessoaDoPonto;
//
//public class JornadaDAOTest {
=======
package br.com.proway.senior.DAO;

import org.hibernate.Session;
import org.junit.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;

public class JornadaDAOTest {
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	@BeforeClass
//	public static void refreshDatabase() {
//
//		String queryDrop = "DROP TABLE jornadas";
//
//		String queryCreate = "CREATE TABLE jornadas (" + "id SERIAL PRIMARY KEY NOT NULL," + "idPessoa INT NOT NULL,"
//				+ "data DATE NOT NULL," + "idTurno INT NOT NULL)";
//
//		String query1 = "INSERT INTO jornadas (idPessoa, data, idTurno) VALUES (" + 111 + ",'"
//				+ LocalDate.now().toString() + "'," + 1 + ")";
//		String query2 = "INSERT INTO jornadas (idPessoa, data, idTurno) VALUES (" + 112 + ",'"
//				+ LocalDate.now().toString() + "'," + 1 + ")";
//		String query3 = "INSERT INTO jornadas (idPessoa, data, idTurno) VALUES (" + 113 + ",'"
//				+ LocalDate.now().toString() + "'," + 2 + ")";
//		String query4 = "INSERT INTO jornadas (idPessoa, data, idTurno) VALUES (" + 114 + ",'"
//				+ LocalDate.now().toString() + "'," + 3 + ")";
//		String query5 = "INSERT INTO jornadas (idPessoa, data, idTurno) VALUES (" + 117 + ",'"
//				+ LocalDate.now().toString() + "'," + 2 + ")";
//
//		try {
//			PostgresConnector.executeUpdate(queryDrop);
//			PostgresConnector.executeUpdate(queryCreate);
//			PostgresConnector.executeUpdate(query1);
//			PostgresConnector.executeUpdate(query2);
//			PostgresConnector.executeUpdate(query3);
//			PostgresConnector.executeUpdate(query4);
//			PostgresConnector.executeUpdate(query5);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
<<<<<<< HEAD
//	@Before
//	public void cleanDAO() {
//		JornadaDAO.newInstance();
//	}
//
//	@Test
=======
//	@Test
//	public void testCreate() {
//		DBConnection db = new DBConnection();
//		Session session = db.getSession();
//		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
//		PessoaDoPonto pessoa = new PessoaDoPonto(1);
//		Turno turno = new Turno(1, LocalTime.now(), LocalTime.now().plusHours(8), "Turno 1");
//		jornadaDao.create(pessoa, turno);
//		
//	}
//
//	@Ignore
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	public void testCreateData() {
//		JornadaDAO db = JornadaDAO.getInstance();
//		PessoaDoPonto pessoa = new PessoaDoPonto();
//
//		db.create(pessoa);
//	}
//
<<<<<<< HEAD
//	@Test
=======
//	@Ignore
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	public void testBuscarJornadaPorId() {
//		JornadaDAO db = JornadaDAO.getInstance();
//		System.out.println(db.read(1));
//		assertEquals(LocalDate.now().toString(), db.read(2).get(2).toString());
//	}
//
<<<<<<< HEAD
//	@Test
=======
//	@Ignore
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	public void testReadAll() {
//		JornadaDAO db = JornadaDAO.getInstance();
//
//		assertEquals(
//					 "[[1, 111, " + LocalDate.now().toString() + ", 1],"
//				   + " [2, 112, " + LocalDate.now().toString() + ", 1]," 
//				   + " [3, 113, " + LocalDate.now().toString() + ", 2],"
//				   + " [4, 114, " + LocalDate.now().toString() + ", 3],"
//				   + " [5, 117, " + LocalDate.now().toString() + ", 2],"
//				   + " [6, 118, " + LocalDate.now().toString() + ", 1]]",
//				db.readAll().toString());
//	}
//
<<<<<<< HEAD
//	@Test
=======
//	@Ignore
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	public void testUpdate() {
//		JornadaDAO db = JornadaDAO.getInstance();
//		db.update(4, "data", LocalDate.of(2021, 05, 03));
//
//		assertEquals("[4, 114, 2021-05-03, 3]", db.read(4).toString());
//	}
//
<<<<<<< HEAD
//	@Test
=======
//	@Ignore
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	public void testDelete() {
//		JornadaDAO db = JornadaDAO.getInstance();
//		db.delete(2);
//		assertTrue(db.readAll().size() == 5);
//	}
//
//    public void testTempoTrabalhado() {
//		//Cenário
//
//    }
<<<<<<< HEAD
//}
=======
	
	
	@Test
	public void testCreate() {
		Session session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		PessoaDoPonto pessoa = new PessoaDoPonto(1);
		Turno turno = new Turno();
		
		jornadaDao.create(pessoa, turno);
		
	}
}
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
