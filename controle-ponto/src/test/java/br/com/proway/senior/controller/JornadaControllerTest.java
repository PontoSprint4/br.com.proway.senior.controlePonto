package br.com.proway.senior.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Ponto;

public class JornadaControllerTest {
<<<<<<< HEAD
//
//	@Test
//	public void testPontosBatidos() {
//		JornadaController jornadaController = new JornadaController();
//		PessoaDoPonto pessoa = new PessoaDoPonto();
//
//		assertTrue(jornadaController.verificaJornadaAberta(pessoa));
//	}
//
//	@Test
//	public void testPontosBatidosFalso() {
//		JornadaController jornadaController = new JornadaController();
//		PessoaDoPonto pessoa = new PessoaDoPonto();
//		pessoa.setId(114);
//		pessoa.setIdTurno(3);
//
//		assertFalse(jornadaController.verificaJornadaAberta(pessoa));
//	}
//
//	@Ignore
//	public void testPadraoBatidaPonto() {
//		fail("Not yet implemented");
//	}
//
=======

	@Test
	public void testPontosBatidos() {
		JornadaController jornadaController = new JornadaController();
		PessoaDoPonto pessoa = new PessoaDoPonto();
		
		assertTrue(jornadaController.verificaJornadaAberta(pessoa));
	}
	
	@Test
	public void testPontosBatidosFalso() {
		JornadaController jornadaController = new JornadaController();
		PessoaDoPonto pessoa = new PessoaDoPonto();
		pessoa.setId(114);
		
		
		assertFalse(jornadaController.verificaJornadaAberta(pessoa));
	}

	@Ignore
	public void testPadraoBatidaPonto() {
		fail("Not yet implemented");
	}
	
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
//	@Test
//	public void testHorasFalta() {
//		ArrayList<String> pontos = new ArrayList<String>();
//		PontoDAO db = PontoDAO.getInstance();
//		JornadaController controller = new JornadaController();
//		PessoaDoPonto pessoa = new PessoaDoPonto();
//		pessoa.setId(114);
//		System.out.println(db.readAll().toString());
//		pontos.add(db.readAll().toString());
//		System.out.println(pontos);
//	}

}
