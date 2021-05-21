package br.com.proway.senior.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TurnoDTOTest {
	static Integer id;
	static LocalTime inicio;
	static LocalTime fim;
	static String nome;
	static Turno turno;
	
	static TurnoDTO turnoDTO;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		id = 420;
		inicio = LocalTime.MIDNIGHT;
		fim = LocalTime.NOON;
		nome = "Turno Teste";
		
		turno = new Turno(id, inicio, fim, nome);
		turnoDTO = new TurnoDTO(turno);
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testTurnoDTO() {
		assertNotNull(turnoDTO);
	}

	@Test
	void testGetId() {
		assertEquals(id, turnoDTO.getId());
	}

	@Test
	void testGetHoraInicio() {
		assertEquals(inicio, turnoDTO.getHoraInicio());
	}

	@Test
	void testGetHoraFim() {
		assertEquals(fim, turnoDTO.getHoraFim());
	}

	@Test
	void testGetNomeTurno() {
		assertEquals(nome, turnoDTO.getNomeTurno());
	}

}
