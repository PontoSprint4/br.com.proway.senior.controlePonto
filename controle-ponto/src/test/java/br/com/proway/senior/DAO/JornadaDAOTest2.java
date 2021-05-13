package br.com.proway.senior.DAO;

import java.time.LocalDate;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;

class JornadaDAOTest2 {

	@Test
	public void testCreate() {
		Session session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		PessoaDoPonto pessoa = new PessoaDoPonto(1);
		Turno turno = new Turno();
		
		Jornada jornada = new Jornada();
		jornada.setData(LocalDate.now());
		jornadaDao.create(jornada);
		
	}
	
	@Test
	public void testGetAll() {
		Session session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		
		System.out.println(jornadaDao.readAll());
	}
	
	@Test
	public void testUpdate() {
		Session session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		
		PessoaDoPonto pessoa = new PessoaDoPonto(1);
		Turno turno = new Turno();
		turno.setId(49);
		Jornada jornada = new Jornada(2003, LocalDate.now(), pessoa, turno);
		
		jornadaDao.update(jornada);
		
	}

}
