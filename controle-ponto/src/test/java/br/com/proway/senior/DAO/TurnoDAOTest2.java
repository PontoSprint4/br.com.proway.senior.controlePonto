package br.com.proway.senior.DAO;

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

}
