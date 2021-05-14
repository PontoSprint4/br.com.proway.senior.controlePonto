package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
		Turno turno1 = new Turno(0, LocalTime.now(), LocalTime.now().plusHours(7), "Turno 1");
		turnoDao.create(turno1);
		
		Turno turno2 = new Turno(0, LocalTime.now().plusHours(2), LocalTime.now().plusHours(9), "Turno 2");
		turnoDao.create(turno2);
		
		Turno turno3 = new Turno(0, LocalTime.now().plusHours(4), LocalTime.now().plusHours(11), "Turno 3");
		turnoDao.create(turno3);
		
		Turno turno4 = new Turno(0, LocalTime.now().plusHours(6), LocalTime.now().plusHours(13), "Turno 4");
		turnoDao.create(turno4);
		
		Turno turno5 = new Turno(0, LocalTime.now().plusHours(8), LocalTime.now().plusHours(15), "Turno 5");
		turnoDao.create(turno5);
		
		
		// Teste Find
		Turno turnoFind = turnoDao.find(turno3.getId());
		assertEquals(turno3.getId(), turnoFind.getId());
		
		// Teste Update
		session.clear();
		Turno turnoASerAtualizado = turnoDao.find(turno4.getId());
		turnoASerAtualizado.setHoraInicio(LocalTime.now());
		turnoASerAtualizado.setHoraFim(LocalTime.now().plusHours(4));
		turnoDao.update(turnoASerAtualizado);
		
		assertEquals(turnoASerAtualizado, turno4);
		
		// Teste Delete / Get All
		turnoDao.delete(turno2);		
		assertEquals(4, turnoDao.readAll().size());
	}
	
//	@Test
//	void testFind() {
//		Session session = DBConnection.getSession();
//		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
//		
//		Turno turno = turnoDao.find(37);
//		assertEquals(37, turno.getId());
//	}
//	
//	@Test
//	void testUpdate() {
//		Session session = DBConnection.getSession();
//		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
//		
//		Turno turno = new Turno(37, LocalTime.now(), LocalTime.now().plusHours(7), "Atualizadasso");
//		
//		assertTrue(turnoDao.update(turno));
//	}
//	
//	@Test
//	void testDelete() {
//		Session session = DBConnection.getSession();
//		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
//		Turno turno = new Turno();
//		turno.setId(37);
//		
//		turnoDao.delete(turno);
//	}
//	
//	@Test
//	void testGetAll() {
//		Session session = DBConnection.getSession();
//		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
//		
//		ArrayList<Turno>  listaTurnos = (ArrayList)turnoDao.readAll();
//		for(Turno item : listaTurnos) {
//			System.out.println(item);
//		}
//	}
//	
//	@Test
//	void testClearDb() {
//		Session session = DBConnection.getSession();
//		TurnoDAO turnoDao = TurnoDAO.getInstance(session);
//		session.createQuery("TRUNCATE turno RESTART IDENTITY CASCADE").executeUpdate();
//		
//		assertEquals(0, turnoDao.readAll().size());
//	}

}
