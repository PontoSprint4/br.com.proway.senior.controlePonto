package br.com.proway.senior.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.enums.EstadosJornada;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.model.TurnoDTO;

class JornadaDTOTest {
	static int id;
	
	static LocalDate data;
	static int ano;
	static int mes;
	static int dia;
	
	static int idPessoa;
	static Turno turno;
	static Ponto ponto;
	
	static Jornada jornada;
	
	static JornadaDTO jornadaDTO;
	static TurnoDTO turnoDaJornada;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		id = 12;
		idPessoa = 42;
		
		dia = 15;
		mes = 4;
		ano = 2022;
		
		data = LocalDate.of(ano, mes, dia);
		
		turno = new Turno(22, LocalTime.now(), LocalTime.now().plusMinutes(12), "Turno de Teste");
		jornada = new Jornada(id, data, idPessoa, turno);
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now()));
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now().plusHours(3)));
		jornadaDTO = new JornadaDTO(jornada);
		turnoDaJornada = jornadaDTO.getTurno();
		
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
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now().plusHours(8)));
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
		assertEquals(turnoDaJornada, jornadaDTO.getTurno());
	}

	@Test
	void testGetListaPonto() {
		assertEquals(2, jornadaDTO.getListaPonto().size());
	}

	@Test
	void testGetIdPessoa() {
		assertEquals(idPessoa, jornadaDTO.getIdPessoa());
	}
	
	@Test
	void testGetDia() {
		assertEquals(dia, jornadaDTO.getDia());
	}
	
	@Test
	void testGetMes() {
		assertEquals(mes, jornadaDTO.getMes());
	}
	
	@Test
	void testGetAno() {
		assertEquals(ano, jornadaDTO.getAno());
	}
	
	@Test
	void testGetMinutosTrabalhados() throws Exception {
		jornada = new Jornada(id, data, idPessoa, turno);
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now()));
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now().plusHours(3)));
		JornadaDTO jornadaDTO = new JornadaDTO(jornada);
		assertEquals(3*60, jornadaDTO.getMinutosTrabalhados());
	}
	
	@Test
	void testGetEstado() throws Exception {
		jornada = new Jornada(id, data, idPessoa, turno);
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now()));
		jornada.adicionaPontoNaLista(new Ponto(12 , LocalDateTime.now().plusHours(3)));
		JornadaDTO jornadaDTO = new JornadaDTO(jornada);
		
		assertEquals(EstadosJornada.FECHADO, jornadaDTO.getEstado());
	}

}
