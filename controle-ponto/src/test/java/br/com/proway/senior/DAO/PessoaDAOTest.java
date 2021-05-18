package br.com.proway.senior.DAO;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.inOrder;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.PessoaDoPonto;

class PessoaDAOTest {
	
	static Session session;
	static PessoaDAO pdao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		pdao = PessoaDAO.getInstance(session);
	}

	@Test
	void testInsert() {
		ArrayList<PessoaDoPonto> listaPessoas = (ArrayList<PessoaDoPonto>) pdao.getAll();
		PessoaDoPonto pessoa = new PessoaDoPonto();
		PessoaDoPonto pessoa2 = new PessoaDoPonto();
		PessoaDoPonto pessoa3 = new PessoaDoPonto();
		pdao.insert(pessoa);
		pdao.insert(pessoa2);
		pdao.insert(pessoa3);
		assertEquals((listaPessoas.size() + 3), pdao.getAll().size());		
	}

	@Test
	void testUpdate() {
		PessoaDoPonto pessoaASerAtualizada = pdao.getAll().get(1);
		assertTrue(pdao.update(pessoaASerAtualizada));
	}

	@Test
	void testDelete() {
		ArrayList<PessoaDoPonto> listaPessoas = (ArrayList<PessoaDoPonto>) pdao.getAll();
		PessoaDoPonto pessoaASerAtualizada = pdao.getAll().get(1);
		pdao.delete(pessoaASerAtualizada);
		assertEquals((listaPessoas.size() - 1), pdao.getAll().size());
	}

	@Test
	void testGet() {
		int idCapturado = pdao.getAll().get(3).getId();
		assertNotNull(pdao.get(idCapturado));
	}

	@Test
	void testGetAll() {
		assertNotNull(pdao.getAll());
	}

}
