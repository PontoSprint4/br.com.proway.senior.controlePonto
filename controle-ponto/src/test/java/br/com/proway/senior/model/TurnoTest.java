package br.com.proway.senior.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

class TurnoTest {

	static int id;
	static LocalTime inicio;
	static LocalTime fim ;
	static String nome ;
	
	@BeforeClass
	void prepararVariaveisStatic() {
		id = 1;
		inicio = LocalTime.now();
		fim = LocalTime.now().plusHours(7);
		nome = "Turno Teste";
	}
	
	
	@Test
	void testTurnoConstrutorVazio() {
		Turno instancia = new Turno();
		assertNotNull(instancia);
	}

	@Test
	void testTurnoIntegerLocalTimeLocalTimeString() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		assertNotNull(instancia);
	}

	@Test
	void testGetId() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		assertEquals(id, instancia.getId());
	}

	@Test
	void testSetId() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		int novoId = 12;
		instancia.setId(novoId);
		assertNotEquals((Integer) id, instancia.getId());
		assertEquals((Integer) novoId, instancia.getId());
	}

	@Test
	void testGetHoraInicio() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		assertEquals(inicio, instancia.getHoraInicio());
	}

	@Test
	void testSetHoraInicio() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		LocalTime novoHoraInicio = LocalTime.now().plusMinutes(666);
		instancia.setHoraInicio(novoHoraInicio);
		assertNotEquals(inicio, instancia.getHoraInicio());
		assertEquals( novoHoraInicio, instancia.getHoraInicio());
	}

	@Test
	void testGetHoraFim() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		assertEquals(fim, instancia.getHoraFim());
	}

	@Test
	void testSetHoraFim() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		LocalTime novoHoraFim = LocalTime.now().plusMinutes(666);
		instancia.setHoraFim(novoHoraFim);
		assertNotEquals(fim, instancia.getHoraFim());
		assertEquals( novoHoraFim, instancia.getHoraFim());
	}

	@Test
	void testGetNomeTurno() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		assertEquals(nome, instancia.getNomeTurno());
	}

	@Test
	void testSetNomeTurno() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		String novoNome = "Wah";
		instancia.setNomeTurno(novoNome);
		assertNotEquals(nome, instancia.getNomeTurno());
		assertEquals(novoNome, instancia.getNomeTurno());
	}

}