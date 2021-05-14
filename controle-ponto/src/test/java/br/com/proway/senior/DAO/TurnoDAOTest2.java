package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Turno;

class TurnoDAOTest2 {

	@Test
	void testCreate() {
		Session session = DBConnection.getSession();
		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
		
		Turno turno = new Turno(0, LocalTime.now(), LocalTime.now().plusHours(7), "Turno 2");
		
		turnoDao.create(turno);
	}
	
	@Test
	void testFind() {
		Session session = DBConnection.getSession();
		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
		
		Turno turno = turnoDao.find(37);
		assertEquals(37, turno.getId());
	}
	
	@Test
	void testUpdate() {
		Session session = DBConnection.getSession();
		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
	}
	
	@Test
	void testClearDb() {
		Session session = DBConnection.getSession();
		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
		session.createQuery("TRUNCATE TABLE RESTART IDENTITY CASCADE").executeUpdate();
		
		assertEquals(0, turnoDao.readAll().size());
	}

}
