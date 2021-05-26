package br.com.proway.senior.model;

import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.model.Turno;

class TurnoTest {

	static int id;
	static LocalTime inicio;
	static LocalTime fim ;
	static String nome ;
	
	@BeforeAll
	static void prepararVariaveisStatic() {
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
	void testTurnoConstructor3() {
		Turno instancia = new Turno(inicio, fim, nome);
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
	
	@Test
	void testSetListaPessoas() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		instancia.setPessoasNoTurno(12);
		assertEquals(1, instancia.getPessoasNoTurno().size());
	}
	
	@Test
	void testUpdateListaPessoas() {
		Turno instancia = new Turno(id, inicio, fim, nome);
		ArrayList<Integer> pessoas = new ArrayList<Integer>();
		pessoas.add(12);
		pessoas.add(42);
		
		instancia.trocarListaPessoasNoTurno(pessoas);
		assertEquals(2, instancia.getPessoasNoTurno().size());
	}

}
