package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;

class JornadaDAOTestV2 {
	
	static Session session;
	static JornadaDAO jornadaDao;
	static PessoaDAO pessoaDao;
	static TurnoDAO turnoDao;
	
	@BeforeAll
	public static void setUpBeforeClass() throws Exception{
		session = DBConnection.getSession();
		jornadaDao = JornadaDAO.getInstance(session);
		pessoaDao = PessoaDAO.getInstance(session);
		turnoDao = TurnoDAO.getInstance(session);
	}
	
	@Test
	public void testCreate() {
//		PessoaDoPonto pessoa = new PessoaDoPonto();
//		pessoa = pessoaDao.getAll().get(0);
//		Turno turno = turnoDao.find(109);
//		Jornada jornada = new Jornada();
//		jornada.setData(LocalDate.of(2021,05,14));
//		jornada.setPessoa(pessoaDao.getAll().get(0));
//		jornada.setTurno(turnoDao.find(109));
//		jornadaDao.create(jornada);
		
	}

	@Test
	public void testGetAll() {
		System.out.println(jornadaDao.readAll());
		
		assertEquals(0, jornadaDao.readAll().size());
	}
	
//	@Test
//	public void testUpdate() {
//		session = DBConnection.getSession();
//		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
//		
//		PessoaDoPonto pessoa = new PessoaDoPonto(1);
//		Turno turno = new Turno();
//		turno.setId(49);
//		Jornada jornada = new Jornada(2003, LocalDate.now(), pessoa, turno);
//		
//		jornadaDao.update(jornada);
//		
//	}
//	
//	@Test
//	void testClearDb() {
//		
//		session = DBConnection.getSession();
//		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
//		session.createQuery("TRUNCATE jornada RESTART IDENTITY CASCADE").executeUpdate();
//		
//		assertEquals(0, jornadaDao.readAll().size());
//	}
//	
//	
//	

}
