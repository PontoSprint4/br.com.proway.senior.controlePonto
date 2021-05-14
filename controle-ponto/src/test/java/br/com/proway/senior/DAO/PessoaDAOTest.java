package br.com.proway.senior.DAO;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.PessoaDoPonto;

class PessoaDAOTest {

	@Test
	void testCreate() {
		Session session = DBConnection.getSession();
		PessoaDAO pessoaDao = PessoaDAO.getInstance(session);

		PessoaDoPonto pessoa = new PessoaDoPonto();

		pessoaDao.create(pessoa);

	}

	@Test
	void testFind() {
		Session session = DBConnection.getSession();
		PessoaDAO pessoaDao = PessoaDAO.getInstance(session);

		PessoaDoPonto pessoa = pessoaDao.find(6);
	}

}
