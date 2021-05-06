package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TurnoDAOTest {

	@BeforeClass
	public static void refreshDatabase() {

		String queryDrop = "DROP TABLE turnos";
		String queryCreate = "CREATE TABLE turnos (" 
							+ "id SERIAL PRIMARY KEY NOT NULL," 
							+ "nomeTurno VARCHAR NOT NULL,"
							+ "horaInicio TIME NOT NULL," 
							+ "horaFim TIME NOT NULL)";
		
		String query1 = "INSERT INTO turnos (nomeTurno, horaInicio, horaFim) VALUES (" + "'Primeiro Turno'" + ",'"
				+ LocalTime.of(8,0).toString() + "','" + LocalTime.of(18,0).toString() + "')";
		String query2 = "INSERT INTO turnos (nomeTurno, horaInicio, horaFim) VALUES (" + "'Segundo Turno'" + ",'"
				+ LocalTime.of(8,30).toString() + "','" + LocalTime.of(18,30).toString() + "')";
		String query3 = "INSERT INTO turnos (nomeTurno, horaInicio, horaFim) VALUES (" + "'Terceiro Turno'" + ",'"
				+ LocalTime.of(9,00,15).toString() + "','" + LocalTime.of(19,00,15).toString() + "')";
		String query4 = "INSERT INTO turnos (nomeTurno, horaInicio, horaFim) VALUES (" + "'Quarto Turno'" + ",'"
				+ LocalTime.of(10,15,45).toString() + "','" + LocalTime.of(5,15,45).toString() + "')";

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
		TurnoDAO.newInstance();
	}
	@Test
	public void testCreate() {
		TurnoDAO db = TurnoDAO.getInstance();			
		db.create("'Turno Comercial'", LocalTime.of(8, 0).toString(), LocalTime.of(18, 0).toString());
	}

	@Test
	public void testRead() {
		TurnoDAO db = TurnoDAO.getInstance();			
		System.out.println(db.read(1));
		assertEquals("[1, Primeiro Turno, 08:00:00, 18:00:00]", db.read(1).toString());
		}

	@Test
	public void testDelete() {
		TurnoDAO db = TurnoDAO.getInstance();			
		db.delete(2);
	}

	@Test
	public void testUpdate() {
		TurnoDAO db = TurnoDAO.getInstance();			
		db.update(3, "horafim", LocalTime.of(23, 0).toString());
	}
	
	@Test
	public void testUpdate2() {
		TurnoDAO db = TurnoDAO.getInstance();			
		db.update(3, "nometurno", "Turno Maluco");
	}

	@Test
	public void testReadAll() {
		TurnoDAO db = TurnoDAO.getInstance();			
		db.readAll();
		assertEquals("[[1, Primeiro Turno, 08:00:00, 18:00:00], " 
		+ "[2, Segundo Turno, 08:30:00, 18:30:00], " 
				+ "[4, Quarto Turno, 10:15:45, 05:15:45], " 
		+ "[3, Turno Maluco, 09:00:15, 19:00:15]" 
				+ "]", db.readAll().toString());		
	}
}
