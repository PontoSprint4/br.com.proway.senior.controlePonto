package br.com.proway.senior.implementacao;

import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import br.com.proway.senior.DAO.JornadaDAO;

public class PrimeiroTurnoServicoTest {

	@Before
	public void cleanDAO() {
		JornadaDAO.newInstance();
	}

	@Test
	public void registraPontoNovaJornadaTeste() {
		PrimeiroTurnoServico primeiroTurno = new PrimeiroTurnoServico();
		
		boolean retorno = primeiroTurno.registraPonto(325, LocalDateTime.of(2021, 5, 21, 6, 0));
		
		assertFalse(retorno);
	}

}