package br.com.proway.senior.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.model.Ponto;


class PontoTest {

	static int id;
	static int idPessoa;
	static LocalDateTime momentoPonto;
	
	@BeforeAll
	static void setUp() {
		id = 1;
		idPessoa = 12;
		
		momentoPonto = LocalDateTime.now().plusHours(1);
	}
	
	@Test
	void testPonto() {
		Ponto ponto = new Ponto();
		assertNotNull(ponto);
	}
	
	@Test
	void testPontoConstructor3() {
		Ponto ponto = new Ponto(idPessoa ,momentoPonto);
		assertNotNull(ponto);
	}

	@Test
	void testPontoIntegerLocalDateTime() {
		Ponto ponto = new Ponto(id, idPessoa, momentoPonto);
		assertNotNull(ponto);
	}

	@Test
	void testGetIdPonto() {
		Ponto ponto = new Ponto(id, null, momentoPonto);
		assertEquals(id, ponto.getIdPonto());
	}

	@Test
	void testSetIdPonto() {
		Ponto ponto = new Ponto(id, null, momentoPonto);
		int novoId = 2;
		ponto.setIdPonto(novoId);
		assertNotEquals(id, ponto.getIdPonto());
		assertEquals(novoId, ponto.getIdPonto());
	}

	@Test
	void testGetMomentoPonto() {
		Ponto ponto = new Ponto(id, null, momentoPonto);
		assertEquals(momentoPonto, ponto.getMomentoPonto());
	}
	
	@Test
	void testSetIdPessoa() {
		Ponto ponto = new Ponto(id, null, momentoPonto);
		ponto.setIdPessoa(42);
		assertEquals(42, ponto.getIdPessoa());
	}

	@Test
	void testSetMomentoPonto() {
		Ponto ponto = new Ponto(id, null, momentoPonto);
		LocalDateTime novoMomentoPonto = LocalDateTime.now().plusHours(2);
		ponto.setMomentoPonto(novoMomentoPonto);
		assertNotEquals(momentoPonto, ponto.getMomentoPonto());
		assertEquals(novoMomentoPonto, ponto.getMomentoPonto());
	}

}
