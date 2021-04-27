package br.com.proway.senior.implementacao;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.Test;

import br.com.proway.senior.DAO.JornadaDAO;

public class PrimeiroTurnoServicoTest {

	@Test
	public void registraPontoTest() {
		PrimeiroTurnoServico primeiroTurno = new PrimeiroTurnoServico();
				
		boolean retorno = primeiroTurno.registraPonto(1, LocalDateTime.of(2021, 5, 21, 6, 0));
		assertTrue(retorno);
	}
	
}