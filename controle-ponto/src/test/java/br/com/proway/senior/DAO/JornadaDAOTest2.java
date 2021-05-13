package br.com.proway.senior.DAO;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;

class JornadaDAOTest2 {

	@Test
	public void testCreate() {
		Session session = DBConnection.getSession();
		JornadaDAO jornadaDao = JornadaDAO.getInstance(session);
		PessoaDoPonto pessoa = new PessoaDoPonto(1);
		Turno turno = new Turno();
		
		jornadaDao.create(pessoa, turno);
		
	}

}
