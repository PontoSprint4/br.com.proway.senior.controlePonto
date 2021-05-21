package br.com.proway.senior.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ValidadoresTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@Test
	void instance() {
		assertNotNull(new Validadores());
	}
	
	@Test
	void testEhZeroOuNulo() {
		Integer testing = 0;
		Integer nulled = null;
		assertTrue(Validadores.ehZeroOuNulo(testing));
		assertTrue(Validadores.ehZeroOuNulo(nulled));
	}

	@Test
	void testEhObjetoNulo() {
		Object nulled = null;
		assertTrue(Validadores.ehObjetoNulo(nulled));
	}

	@Test
	void testApenasCaracteresValidos() {
		String validChars = "val1do";
		String invalidChars = "1nv4l!d0";
		assertTrue(Validadores.apenasCaracteresValidos(validChars));
		assertFalse(Validadores.apenasCaracteresValidos(invalidChars));
	}

}
