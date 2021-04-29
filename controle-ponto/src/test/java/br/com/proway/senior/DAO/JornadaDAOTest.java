package br.com.proway.senior.DAO;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.proway.senior.modelos.Jornada;
import br.com.proway.senior.modelos.Ponto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JornadaDAOTest {
	
	@Test
	public void ATesteCadastrar() {
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
	public void BTesteCadastrarJornadaDois() {
		Jornada jornada = new Jornada();
		ArrayList<Ponto> pontosJornada = new ArrayList<Ponto>();
		Ponto ponto = new Ponto(984, null, null, 735);
		JornadaDAO db = JornadaDAO.getInstance();
		
		pontosJornada.add(ponto);
		
		jornada.setIdPessoa(ponto.getIdPessoa());
		jornada.setId(245);
		jornada.setPontos(pontosJornada);
	
		assertTrue(db.cadastrar(jornada));
	}
	
	@Test
	public void CTesteBuscarUltimaRetornaJornadaAberta() {
		JornadaDAO db = JornadaDAO.getInstance();
		
		Integer idEsperado = 222;

		assertTrue(db.buscarUltimaJornadaAberta(732).getId().equals(idEsperado));
	}
	
	@Test
	public void DATesteAtualizarJornada() {
		JornadaDAO db = JornadaDAO.getInstance();
		Ponto ponto2 = new Ponto(652, null, null, 732);
		Jornada jornada = db.buscarUltimaJornadaAberta(ponto2.getIdPessoa());
		
		jornada.getPontos().add(ponto2);
		
		db.atualizar(jornada);
		
		assertTrue(db.buscarUltimaJornadaAberta(732).getPontos().contains(ponto2));
	}
	
	@Test
	public void ETesteBuscarUltimaRetornaJornadaAbertaJornadaDois() {
		JornadaDAO db = JornadaDAO.getInstance();
		
		Integer idEsperado = 245;

		assertTrue(db.buscarUltimaJornadaAberta(735).getId().equals(idEsperado));
	}
	
	@Test
	public void FTesteBuscarUltimaJornadaFechadaRetornaNull() {
		Ponto ponto3 = new Ponto(653, null, null, 732);
		Ponto ponto4 = new Ponto(654, null, null, 732);
		JornadaDAO db = JornadaDAO.getInstance();
		Jornada jornada = db.buscarUltimaJornadaAberta(732);
		
		jornada.getPontos().add(ponto3);
		jornada.getPontos().add(ponto4);
		jornada.setAberta(false);
		
		db.atualizar(jornada);
		
		assertNull(db.buscarUltimaJornadaAberta(732));
	}
}