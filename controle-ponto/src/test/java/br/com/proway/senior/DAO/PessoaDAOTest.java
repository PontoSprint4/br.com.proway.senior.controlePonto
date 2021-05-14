package br.com.proway.senior.DAO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

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
		ArrayList<PessoaDoPonto> pessoas = (ArrayList<PessoaDoPonto>) pessoaDao.getAll();
		assertEquals(6,pessoas.size());
	}

	@Test
	void testFind() {
		Session session = DBConnection.getSession();
		PessoaDAO pessoaDao = PessoaDAO.getInstance(session);

		PessoaDoPonto pessoa = pessoaDao.find(30);
		assertEquals(30, pessoa.getId());
	}
	
//	@Test 
//	void testUpdate(){
//		Session session = DBConnection.getSession();
//		PessoaDAO pessoaDao = PessoaDAO.getInstance(session);
//	}
	
	@Test
	void testClearDb() {
		Session session = DBConnection.getSession();
		PessoaDAO pessoaDao = PessoaDAO.getInstance(session);
		session.getTransaction();
		session.createQuery("TRUNCATE pessoadoponto RESTART IDENTITY CASCADE;").executeUpdate();
		
		assertEquals(0, pessoaDao.getAll().size());
	}

}
