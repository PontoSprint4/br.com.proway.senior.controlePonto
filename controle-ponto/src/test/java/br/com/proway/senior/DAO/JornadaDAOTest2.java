package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;

class JornadaDAOTest2 {
	
	static Session session;
	static JornadaDAO jornadaDao;
	static PessoaDAO pessoaDao;
	static TurnoDAO turnoDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
		session = DBConnection.getSession();
		jornadaDao = JornadaDAO.getInstance(session);
		pessoaDao = PessoaDAO.getInstance(session);
		turnoDao = TurnoDAO.getInstance(session);
	}
	
	@Test
	public void testCreate() {
		PessoaDoPonto pessoa = pessoaDao.find(114);
		Turno turno = turnoDao.find(109);
		Jornada jornada = new Jornada();
		jornada.setData(LocalDate.now());
		jornadaDao.create(jornada);
		
	}
	
	@Test
	public void testGetAll() {
		session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		
		System.out.println(jornadaDao.readAll());
	}
	
	@Test
	public void testUpdate() {
		session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		
		PessoaDoPonto pessoa = new PessoaDoPonto(1);
		Turno turno = new Turno();
		turno.setId(49);
		Jornada jornada = new Jornada(2003, LocalDate.now(), pessoa, turno);
		
		jornadaDao.update(jornada);
		
	}
	
	@Test
	void testClearDb() {
		
		session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		session.createQuery("TRUNCATE jornada RESTART IDENTITY CASCADE").executeUpdate();
		
		assertEquals(0, jornadaDao.readAll().size());
	}
	
	
	

}
