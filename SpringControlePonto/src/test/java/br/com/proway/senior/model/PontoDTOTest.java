package br.com.proway.senior.model;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.PontoDTO;

class PontoDTOTest {
	static Integer id;
	static LocalDateTime hora;
	static Ponto ponto;
	
	static PontoDTO pontoDTO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		id = 666;
		hora = LocalDateTime.now();
		ponto = new Ponto(id, null, hora);
		
		pontoDTO = new PontoDTO(ponto);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testPontoDTO() {
		assertNotNull(pontoDTO);
	}

	@Test
	void testGetIdPonto() {
		assertEquals(id, pontoDTO.getIdPonto());
	}

	@Test
	void testGetMomentoPonto() {
		assertEquals(hora, pontoDTO.getMomentoPonto());
	}

}
