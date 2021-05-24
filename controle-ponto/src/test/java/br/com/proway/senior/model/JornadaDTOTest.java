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
		
		jornada = new Jornada(id, data, idPessoa, turno);
		jornada.setListaPonto(new Ponto(12 , LocalDateTime.now()));
		jornada.setListaPonto(new Ponto(12 , LocalDateTime.now().plusHours(3)));
		jornadaDTO = new JornadaDTO(jornada);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testJornadaDTO() {
		// Garantir que os pontos da lista de pontos na Jornada foram feitos na ORDEM CERTA.
		assertNotNull(jornadaDTO);
	}
	
	@Test
	void testJornadaDTOComPontosImpares() throws Exception {
		jornada.setListaPonto(new Ponto(12 , LocalDateTime.now().plusHours(8)));
		jornadaDTO = new JornadaDTO(jornada);
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
		assertEquals(2, jornadaDTO.getListaPonto().size());
	}

	@Test
	void testGetIdPessoa() {
		assertEquals(idPessoa, jornadaDTO.getIdPessoa());
	}

}
