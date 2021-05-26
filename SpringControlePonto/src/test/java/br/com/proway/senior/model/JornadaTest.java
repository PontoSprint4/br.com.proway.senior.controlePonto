package br.com.proway.senior.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

class JornadaTest {

	static int id;
	static LocalDate data;	
	static int idPessoa;
	static Turno turno;
	static Ponto ponto;
	
	@BeforeAll
	static void setUp() {
		id = 1;
		data = LocalDate.of(2020, 3, 3);		
		idPessoa = 2;
		turno = new Turno(1, LocalTime.now().plusHours(1), LocalTime.now().plusHours(2), "Teste Turno");
		ponto = new Ponto(8, null,  LocalDateTime.of(2050, 6, 19, 23, 19));
	}
	@Test
	void testJornada() {
		Jornada jornada = new Jornada();
		assertNotNull(jornada);
	}
	
	@Test
	void testConstructor3() {
		Jornada jornada = new Jornada(data, idPessoa, turno);
		assertNotNull(jornada);
	}

	@Test
	void testJornadaIntegerLocalDateIntTurno() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		assertNotNull(jornada);
	}

	@Test
	void testGetId() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		assertEquals(id, jornada.getId());
	}

	@Test
	void testSetId() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		int novoId = 2;
		jornada.setId(novoId);
		assertNotEquals(id, jornada.getId());
		assertEquals(novoId, jornada.getId());
	}

	@Test
	void testGetData() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		assertEquals(data, jornada.getData());
	}

	@Test
	void testSetData() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		LocalDate novaData = LocalDate.of(2060, 6, 6);;
		jornada.setData(novaData);
		assertNotEquals(data, jornada.getData());
		assertEquals(novaData, jornada.getData());
	}

	@Test
	void testGetIdPessoa() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		assertEquals(idPessoa, jornada.getIdPessoa());
	}

	@Test
	void testSetIdPessoa() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		int novoIdPessoa = 4;
		jornada.setIdPessoa(novoIdPessoa);
		assertNotEquals(idPessoa, jornada.getIdPessoa());
		assertEquals(novoIdPessoa, jornada.getIdPessoa());
	}

	@Test
	void testGetTurno() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		assertEquals(turno, jornada.getTurno());
	}

	@Test
	void testSetTurno() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		Turno novoTurno = new Turno(2, LocalTime.now().plusHours(2), LocalTime.now().plusHours(3), "Teste Turno 2");;
		jornada.setTurno(novoTurno);
		assertNotEquals(turno, jornada.getTurno());
		assertEquals(novoTurno, jornada.getTurno());
	}

	@Test
	void testGetListaPonto() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);				
		jornada.setListaPonto(ponto);		
		assertEquals(ponto, jornada.getListaPonto().get(0)  );
	}

	@Test
	void testSetListaPonto() {
		Jornada jornada = new Jornada(id, data, idPessoa, turno);
		Ponto novoPonto = new Ponto(3, null, LocalDateTime.of(2021, 5, 13, 23, 59));
		jornada.setListaPonto(novoPonto);
		assertNotEquals(ponto, jornada.listaPonto.get(0));
		assertEquals(novoPonto, jornada.listaPonto.get(0));
	}

}
