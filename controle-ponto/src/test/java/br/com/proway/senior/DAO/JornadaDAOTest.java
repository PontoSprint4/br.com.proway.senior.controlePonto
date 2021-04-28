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
		ArrayList<Ponto> pontosJornada = new ArrayList<Ponto>();
		Ponto ponto = new Ponto(651, null, null, 732);
		JornadaDAO db = JornadaDAO.getInstance();
		
		pontosJornada.add(ponto);
		
		jornada.setIdPessoa(ponto.getIdPessoa());
		jornada.setId(222);
		jornada.setPontos(pontosJornada);
	
		assertTrue(db.cadastrar(jornada));
	}
	
	@Test
	public void testBuscarUltimaRetornaJornadaAberta() {
		JornadaDAO db = JornadaDAO.getInstance();
		
		Integer idEsperado = 222;

		assertTrue(db.buscarUltimaJornadaAberta(732).getId().equals(idEsperado));
	}
	
	@Test
	public void testAtualizarJornada() {
		JornadaDAO db = JornadaDAO.getInstance();
		Ponto ponto2 = new Ponto(652, null, null, 732);
		Jornada jornada = db.buscarUltimaJornadaAberta(ponto2.getIdPessoa());
		
		jornada.getPontos().add(ponto2);
		
		db.atualizar(jornada);
		
		assertTrue(db.buscarUltimaJornadaAberta(732).getPontos().contains(ponto2));
	}
	
	@Test
	public void testBuscarUltimaJornadaFechadaRetornaNull() {
		Ponto ponto3 = new Ponto(653, null, null, 732);
		Ponto ponto4 = new Ponto(654, null, null, 732);
		ArrayList<Ponto> pontosJornada = new ArrayList<Ponto>();
		Jornada jornada = new Jornada();
		JornadaDAO db = JornadaDAO.getInstance();
		
		pontosJornada.add(ponto3);
		pontosJornada.add(ponto4);
		jornada.setId(732);
		jornada.setPontos(pontosJornada);
		jornada.setAberta(false);
		
		db.atualizar(jornada);
		
		assertEquals(null, db.buscarUltimaJornadaAberta(732));
	}
	
}