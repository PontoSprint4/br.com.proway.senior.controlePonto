package br.com.proway.senior.controller;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Turno;

class JornadaAPITest {

	static JornadaAPI jornadaApi;
	static Jornada jornada;
	static JornadaController controllerJ;
	static TurnoController controllerT;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jornadaApi = new JornadaAPI();
		JornadaController controllerJ = new JornadaController(DBConnection.getSession());
	}
	
	// buscar jornada X
	
	// Buscar todas jornadas da pessoa X
	
	
	// buscar jornadas da pessoa X no periodo Y-Z
	
	// horas trabalhadas da pessoa X no periodo Y-Z

	
	// buscar jornada da pessoa X no dia Y
	
	@Test
	public void getJornadaTest() throws Exception {
		Turno turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(4), "Turno da noite");
		controllerT.create(turno);
		
		jornada = new Jornada(LocalDate.of(2021, 5, 24), 12, turno);
		Integer saida = controllerJ.create(jornada);
		assertNotNull(jornadaApi.getJornada(saida));
		
		
	}
	
}
