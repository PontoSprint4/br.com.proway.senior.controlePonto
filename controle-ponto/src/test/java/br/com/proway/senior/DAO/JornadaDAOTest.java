package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import br.com.proway.senior.modelos.Jornada;
import br.com.proway.senior.modelos.Ponto;

public class JornadaDAOTest {

	@Test
	public void testCadastrar() {
		Jornada jornada = new Jornada();
		
		JornadaDAO db = JornadaDAO.getInstance();
		db.cadastrar(jornada);
		
		//Que a sorte esteja com você
		assertTrue(db.cadastrar(jornada));
	}
	
	@Test
	public void testBuscarUltimaRetornaNull() {
		Jornada jornadaFechada = new Jornada();
		ArrayList<Ponto> pontosJornadaFechada = new ArrayList<Ponto>();
		Ponto ponto1 = new Ponto();
		
		pontosJornadaFechada.add(ponto1);
		pontosJornadaFechada.add(ponto1);
		pontosJornadaFechada.add(ponto1);
		pontosJornadaFechada.add(ponto1);
		
		jornadaFechada.setIdPessoa(456);
		jornadaFechada.setAberta(false);
		jornadaFechada.setPontos(pontosJornadaFechada);
		
		JornadaDAO db = JornadaDAO.getInstance();
		db.cadastrar(jornadaFechada);

		assertEquals(null, db.buscarUltima(456));
	}
	
	@Test
	public void testBuscarUltimaRetornaJornada() {
		Jornada jornadaAberta = new Jornada();
		ArrayList<Ponto> pontosJornadaAberta = new ArrayList<Ponto>();
		Ponto ponto2 = new Ponto();
		
		pontosJornadaAberta.add(ponto2);
		pontosJornadaAberta.add(ponto2);
		pontosJornadaAberta.add(ponto2);
		
		jornadaAberta.setIdPessoa(498);
		jornadaAberta.setPontos(pontosJornadaAberta);
		
		JornadaDAO db = JornadaDAO.getInstance();
		db.cadastrar(jornadaAberta);

		assertTrue(jornadaAberta.equals(db.buscarUltima(498)));
	}
}
