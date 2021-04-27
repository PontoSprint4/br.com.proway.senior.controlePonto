package br.com.proway.senior.DAO;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

import br.com.proway.senior.modelos.Ponto;

public class PontoDAOTest {

	@Test
	public void testeCadastroPonto() {
		Ponto ponto = new Ponto(25, LocalDateTime.of(2021, 04, 22, 2,3,6), LocalDate.of(2021, 04, 22), 369);
		
		PontoDAO Db = PontoDAO.getInstance(ponto);
		
		assertTrue(Db.cadastrarPonto(ponto));
		System.out.println("");
	}
}
