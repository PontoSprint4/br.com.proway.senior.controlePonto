package br.com.proway.senior.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class JornadaDTOTest {
	static int id;
	static LocalDate data;
	static int idPessoa;
	static Turno turno;
	static Ponto ponto;
	
	static Jornada jornada;
	
	static JornadaDTO jornadaDTO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		id = 12;
		data = LocalDate.now();
		idPessoa = 42;
		turno = new Turno(22, LocalTime.now(), LocalTime.now().plusMinutes(12), "Turno de Teste");
		ponto = new Ponto(LocalDateTime.now());
		jornada = new Jornada(id, data, idPessoa, turno);
		jornada.setListaPonto(ponto);
		
		jornadaDTO = new JornadaDTO(jornada);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testJornadaDTO() {
		assertNotNull(jornadaDTO);
	}

	@Test
	void testGetId() {
		assertEquals(id, jornadaDTO.getId());
	}

	@Test
	void testGetData() {
		assertEquals(data, jornadaDTO.getData());
	}

	@Test
	void testGetTurno() {
		assertEquals(turno, jornadaDTO.getTurno());
	}

	@Test
	void testGetListaPonto() {
		assertEquals(1, jornadaDTO.getListaPonto().size());
	}

	@Test
	void testGetIdPessoa() {
		assertEquals(idPessoa, jornadaDTO.getIdPessoa());
	}

}
