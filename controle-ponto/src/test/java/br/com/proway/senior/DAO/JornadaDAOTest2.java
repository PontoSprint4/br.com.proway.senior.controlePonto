package br.com.proway.senior.DAO;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Pessoa;
import br.com.proway.senior.model.Ponto;

public class JornadaDAOTest2 {

	JornadaDAO_V1 db = JornadaDAO_V1.getInstance();

	@Test
	public void testCadastrar() {
		Jornada jornada = new Jornada();
		Pessoa pessoa = new Pessoa();
		Ponto ponto = new Ponto(651, null, null, pessoa.getIdPessoa(1));

		assertTrue(db.cadastrar(jornada));
	}

	@Test
	public void testBuscarUltimaJornadaAberta() {

	}

	@Test
	public void testAtualizar() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemover() {
		fail("Not yet implemented");
	}

	@Test
	public void testNewInstance() {
		fail("Not yet implemented");
	}

}
