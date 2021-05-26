package br.com.proway.senior.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Turno;

class JornadaControllerPreparaParaDTOTest {
	static JornadaController jornadaController;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jornadaController = new JornadaController(DBConnection.getSession());
	}
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}

	@Test
	void testObterTodasJornadasPessoa() throws Exception {
		int idPessoa = 88;
		
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno turno = new Turno(inicio, fim, "Turno com tolerancia");
		
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,10), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,11), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,26), idPessoa, turno));
    	
    	assertEquals(3, jornadaController.obterTodasJornadasDaPessoa(idPessoa).size());
	}
	
	@Test
	void testObterTodasJornadasPessoaNoDia() throws Exception {
		int idPessoa = 88;
		
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno turno = new Turno(inicio, fim, "Turno com tolerancia");
		
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,11), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,11), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,26), idPessoa, turno));
    	
    	assertEquals(2, jornadaController.obterJornadasDoDia(idPessoa, LocalDate.of(2021,6,11)).size());
	}
	
	@Test
	void testObterTodasJornadasPessoaNoDiaInvalido() throws Exception {
		int idPessoa = 88;
    	assertThrows(Exception.class, () -> jornadaController.obterJornadasDoDia(idPessoa, null));
	}
	
	@Test
	void testObterJornadasPessoaEntreDatas() throws Exception {
		int idPessoa = 88;
		
		LocalTime inicio = LocalTime.of(22, 0);
		LocalTime fim = LocalTime.of(4, 0);
		Turno turno = new Turno(inicio, fim, "Turno com tolerancia");
		
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,11), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,19), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,6,26), idPessoa, turno));
    	jornadaController.create(new Jornada(LocalDate.of(2021,12,26), idPessoa, turno));
    	
    	assertEquals(3, jornadaController.obterJornadasEntreDatas(idPessoa, LocalDate.of(2021,6,11), LocalDate.of(2021,6,30)).size());
	}
	
	@Test
	void testObterTodasJornadasPessoaEntreDatasInvalidas1() throws Exception {
		int idPessoa = 88;
    	assertThrows(Exception.class, () -> jornadaController.obterJornadasEntreDatas(idPessoa, null, LocalDate.of(2021,6,11)));
	}
	
	@Test
	void testObterTodasJornadasPessoaEntreDatasInvalidas2() throws Exception {
		int idPessoa = 88;
    	assertThrows(Exception.class, () -> jornadaController.obterJornadasEntreDatas(idPessoa,LocalDate.of(2021,6,11), null));
	}


}
