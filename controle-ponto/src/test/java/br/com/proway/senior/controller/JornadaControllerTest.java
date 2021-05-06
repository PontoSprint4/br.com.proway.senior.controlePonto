package br.com.proway.senior.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import br.com.proway.senior.model.Pessoa;

public class JornadaControllerTest {

	@Test
	public void testPontosBatidos() {
		JornadaController jornadaController = new JornadaController();
		Pessoa pessoa = new Pessoa();
		
		assertTrue(jornadaController.verificaJornadaAberta(pessoa));
	}
	
	@Test
	public void testPontosBatidosFalso() {
		JornadaController jornadaController = new JornadaController();
		Pessoa pessoa = new Pessoa();
		pessoa.setId(114);
		pessoa.setIdTurno(3);
		
		assertFalse(jornadaController.verificaJornadaAberta(pessoa));
	}

	@Ignore
	public void testPadraoBatidaPonto() {
		fail("Not yet implemented");
	}

}
