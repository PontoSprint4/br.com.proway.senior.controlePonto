package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.controller.JornadaController;
import br.com.proway.senior.controlePonto.controller.JornadaDTOController;
import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

class JornadaDTOControllerTest {

	static JornadaDTOController jornadaDTOController;
	static Jornada jornada;
	
	static JornadaController controllerJ;
	static TurnoController controllerT;
	static PontoController controllerP;
	
	static Turno turno;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		jornadaDTOController = new JornadaDTOController();
		controllerJ = new JornadaController(DBConnection.getSession());
		controllerT = new TurnoController(DBConnection.getSession());
		controllerP = new PontoController(DBConnection.getSession());
	}
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}
	
	@Test
	public void getJornadaTest() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(20), "Turno da API");
		controllerT.create(turno);
		int idPessoa= 52;

		jornada = new Jornada(LocalDate.of(2021, 5, 24), idPessoa, turno);
		
		Integer saida = controllerJ.create(jornada);
		assertNotNull(jornadaDTOController.getJornada(saida));
	}
	
	@Test
	public void getTodasJornadasDaPessoaTest() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(20), "Turno da API");
		controllerT.create(turno);
		int idPessoa= 69;
		
		Jornada jornada1 = new Jornada(LocalDate.of(2021, 5, 24), idPessoa, turno);
		Jornada jornada2 = new Jornada(LocalDate.of(2021, 5, 28), idPessoa, turno);
		Jornada jornada3 = new Jornada(LocalDate.of(2021, 6, 8), idPessoa, turno);
		
		controllerJ.create(jornada1);
		controllerJ.create(jornada2);
		controllerJ.create(jornada3);
		
		assertEquals(3,jornadaDTOController.getTodasJornadasDaPessoa(idPessoa).size());
	}
	
	@Test
	public void getJornadasDaPessoaPorPeriodoTest() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(20), "Turno da API");
		controllerT.create(turno);
		int idPessoa= 420;
		
		Jornada jornada1 = new Jornada(LocalDate.of(2021, 8, 12), idPessoa, turno);
		Jornada jornada2 = new Jornada(LocalDate.of(2021, 8, 16), idPessoa, turno);
		Jornada jornada3 = new Jornada(LocalDate.of(2021, 6, 8), idPessoa, turno);
		
		controllerJ.create(jornada1);
		controllerJ.create(jornada2);
		controllerJ.create(jornada3);
		
		assertEquals(
			2,jornadaDTOController.getJornadasDaPessoaPorPeriodo(idPessoa, LocalDate.of(2021, 8, 1), LocalDate.of(2021, 8, 20)).size());
	}
	
	@Test
	public void getJornadaDaPessoaNoDiaTest() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(20), "Turno da API");
		controllerT.create(turno);
		int idPessoa= 123;
		
		Jornada jornada1 = new Jornada(LocalDate.of(2021, 8, 12), idPessoa, turno);
		Jornada jornada2 = new Jornada(LocalDate.of(2021, 6, 8), idPessoa, turno);
		
		controllerJ.create(jornada1);
		controllerJ.create(jornada2);
		
		assertNotNull(jornadaDTOController.getJornadaDaPessoaNoDia(idPessoa, LocalDate.of(2021, 8, 12)));
	}
	
	@Test
	public void getJornadaDaPessoaNoDiaExcessaoSemJornadaTest() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(20), "Turno da API");
		controllerT.create(turno);
		int idPessoa= 123;
		
		assertThrows(Exception.class, () -> jornadaDTOController.getJornadaDaPessoaNoDia(idPessoa, LocalDate.of(2021, 8, 12)));
	}
	
	@Test
	public void getHorasTrabalhadasDaPessoaPorPeriodo() throws Exception {
		turno = new Turno(LocalTime.now(), LocalTime.now().plusHours(20), "Turno da API");
		
		int idPessoa= 123;
		
		Jornada jornada1 = new Jornada(LocalDate.of(2021, 8, 12), idPessoa, controllerT.get(controllerT.create(turno)));
		Integer id1 = controllerJ.create(jornada1);
		
		Ponto ponto = new Ponto(idPessoa, LocalDateTime.of(2021, 8, 12, 10, 0));
		Integer idPonto = PontoDAO.getInstance(DBConnection.getSession()).create(ponto);
		Ponto ponto2 = new Ponto(idPessoa, LocalDateTime.of(2021, 8, 12, 14, 0));
		Integer idPonto2 = PontoDAO.getInstance(DBConnection.getSession()).create(ponto2);
		controllerJ.adicionarPontoNaJornada(id1, PontoDAO.getInstance(DBConnection.getSession()).get(idPonto));
		controllerJ.adicionarPontoNaJornada(id1, PontoDAO.getInstance(DBConnection.getSession()).get(idPonto2));
		
		
		Jornada jornada2 = new Jornada(LocalDate.of(2021, 8, 8), idPessoa, controllerT.get(controllerT.create(turno)));
		Integer id2 = controllerJ.create(jornada2);
		
		Ponto ponto3 = new Ponto(idPessoa, LocalDateTime.of(2021, 8, 8, 15, 0));
		Integer idPonto3 = PontoDAO.getInstance(DBConnection.getSession()).create(ponto3);
		Ponto ponto4 = new Ponto(idPessoa, LocalDateTime.of(2021, 8, 8, 17, 0));
		Integer idPonto4 = PontoDAO.getInstance(DBConnection.getSession()).create(ponto4);
		controllerJ.adicionarPontoNaJornada(id2, PontoDAO.getInstance(DBConnection.getSession()).get(idPonto3));
		controllerJ.adicionarPontoNaJornada(id2, PontoDAO.getInstance(DBConnection.getSession()).get(idPonto4));
		

		assertEquals(6*60 ,jornadaDTOController.getHorasTrabalhadasDaPessoaPorPeriodo(idPessoa, LocalDate.of(2021, 8, 1), LocalDate.of(2021, 8, 30)));
	}
	
}
