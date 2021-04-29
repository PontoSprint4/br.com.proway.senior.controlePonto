package br.com.proway.senior.DAO;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.proway.senior.modelos.Jornada;
import br.com.proway.senior.modelos.Ponto;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JornadaDAOUnitTest {

	@Before
	public void cleanDAO() {
		System.out.println("antes " + JornadaDAO.getInstance().jornadas.size());
		JornadaDAO.newInstance();
		System.out.println("depois " + JornadaDAO.getInstance().jornadas.size());
	}

	@Test
	public void testeCadastrar() {
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
	public void testeBuscarUltimaJornadaFechadaRetornaNull() {
		Ponto ponto1 = new Ponto(651, null, null, 732);
		Ponto ponto2 = new Ponto(652, null, null, 732);
		Ponto ponto3 = new Ponto(653, null, null, 732);
		Ponto ponto4 = new Ponto(654, null, null, 732);
		ArrayList<Ponto> pontos = new ArrayList<Ponto>();
		JornadaDAO db = JornadaDAO.getInstance();
		Jornada jornada = new Jornada();
		
		pontos.add(ponto1);
		jornada.setId(222);
		jornada.setIdPessoa(ponto1.getIdPessoa());
		jornada.setPontos(pontos);
		db.cadastrar(jornada);
		
		jornada = db.buscarUltimaJornadaAberta(ponto1.getIdPessoa());
		
		jornada.getPontos().add(ponto2);
		jornada.getPontos().add(ponto3);
		jornada.getPontos().add(ponto4);
		jornada.setAberta(false);
		
		db.atualizar(jornada);
		
		assertNull(db.buscarUltimaJornadaAberta(732));
	}
}
