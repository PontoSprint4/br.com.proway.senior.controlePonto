package br.com.proway.senior.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class PontoTest {

	static int id;
	static LocalDateTime momentoPonto;
	
	@BeforeClass
	void setUp() {
		id = 1;
		momentoPonto = LocalDateTime.now().plusHours(1);
	}
	
	@Test
	void testPonto() {
		Ponto ponto = new Ponto();
		assertNotNull(ponto);
	}
	
	@Test
	void testPontoConstructor3() {
		Ponto ponto = new Ponto(momentoPonto);
		assertNotNull(ponto);
	}

	@Test
	void testPontoIntegerLocalDateTime() {
		Ponto ponto = new Ponto(id, momentoPonto);
		assertNotNull(ponto);
	}

	@Test
	void testGetIdPonto() {
		Ponto ponto = new Ponto(id, momentoPonto);
		assertEquals(id, ponto.getIdPonto());
	}

	@Test
	void testSetIdPonto() {
		Ponto ponto = new Ponto(id, momentoPonto);
		int novoId = 2;
		ponto.setIdPonto(novoId);
		assertNotEquals(id, ponto.getIdPonto());
		assertEquals(novoId, ponto.getIdPonto());
	}

	@Test
	void testGetMomentoPonto() {
		Ponto ponto = new Ponto(id, momentoPonto);
		assertEquals(momentoPonto, ponto.getMomentoPonto());
	}

	@Test
	void testSetMomentoPonto() {
		Ponto ponto = new Ponto(id, momentoPonto);
		LocalDateTime novoMomentoPonto = LocalDateTime.now().plusHours(2);
		ponto.setMomentoPonto(novoMomentoPonto);
		assertNotEquals(momentoPonto, ponto.getMomentoPonto());
		assertEquals(novoMomentoPonto, ponto.getMomentoPonto());
	}

}
