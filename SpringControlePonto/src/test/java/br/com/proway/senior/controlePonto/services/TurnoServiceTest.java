package br.com.proway.senior.controlePonto.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.ListaDePessoas;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.model.TurnoDTO;

class TurnoServiceTest {
	static TurnoService serviceTurno;
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		serviceTurno = new TurnoService();
	}

	@Test
	void testObterTodos() {
		Integer tamanhoAntes = serviceTurno.obterTodos().size();
		
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		serviceTurno.salvar(turno);
		serviceTurno.salvar(turno);
		serviceTurno.salvar(turno);
		
		assertEquals(tamanhoAntes+3, serviceTurno.obterTodos().size());
		
	}

	@Test
	void testSalvar() throws Exception{
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id =serviceTurno.salvar(turno);
		assertNotNull(serviceTurno.obter(id));
	}

	@Test
	void testDeletar() throws Exception{
		Integer tamanhoAntes = serviceTurno.obterTodos().size();
		
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id = serviceTurno.salvar(turno);
		serviceTurno.deletar(id);
		
		assertEquals(tamanhoAntes, serviceTurno.obterTodos().size());
	}

	@Test
	void testAlterar() throws Exception {
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer Id = serviceTurno.salvar(turno);
		turno.setNomeTurno("Modificado");
		serviceTurno.alterar(Id, turno);
		
		TurnoDTO retornado = serviceTurno.obter(Id);
		assertEquals(turno.getNomeTurno(), retornado.getNomeTurno());
	}

	@Test
	void testObter() throws Exception {
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id = serviceTurno.salvar(turno);
		
		assertNotNull(serviceTurno.obter(id));
	}

	@Test
	void testAdicionarPessoa() throws Exception {
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id = serviceTurno.salvar(turno);
		
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(42);
		listaPessoas.add(52);
		listaPessoas.add(64);
		
		serviceTurno.adicionarPessoa(listaPessoas, id);
		TurnoDTO retornado = serviceTurno.obter(id);
		
		assertTrue(retornado.getPessoasNoTurno().contains((Integer) 42));
		assertTrue(retornado.getPessoasNoTurno().contains((Integer) 52));
		assertTrue(retornado.getPessoasNoTurno().contains((Integer) 64));
	}

	@Test
	void testRemoverPessoa() throws Exception {
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id = serviceTurno.salvar(turno);
		
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(42);
		listaPessoas.add(52);
		listaPessoas.add(64);
		
		serviceTurno.adicionarPessoa(listaPessoas, id);
		
		ListaDePessoas removerPessoas = new ListaDePessoas();
		removerPessoas.add(52);
		
		serviceTurno.removerPessoa(removerPessoas, id);
		
		TurnoDTO retornado = serviceTurno.obter(id);
		
		assertTrue(retornado.getPessoasNoTurno().contains((Integer) 42));
		assertFalse(retornado.getPessoasNoTurno().contains((Integer) 52));
		assertTrue(retornado.getPessoasNoTurno().contains((Integer) 64));
	}

	@Test
	void testObterTodasPessoasDoTurno() throws Exception {
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id = serviceTurno.salvar(turno);
		
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(42);
		listaPessoas.add(52);
		listaPessoas.add(64);
		
		serviceTurno.adicionarPessoa(listaPessoas, id);
		TurnoDTO retornado = serviceTurno.obter(id);
		ArrayList<Integer> pessoasNoTurno = serviceTurno.obterTodasPessoasDoTurno(id);
		
		assertTrue(pessoasNoTurno.contains((Integer) 42));
		assertTrue(pessoasNoTurno.contains((Integer) 52));
		assertTrue(pessoasNoTurno.contains((Integer) 64));
	}

	@Test
	void testTurnoDaPessoa() throws Exception {
		LocalTime inicio = LocalTime.now();
		LocalTime fim = inicio.plusHours(4);
		Turno turno = new Turno(inicio, fim, "Service");
		
		Integer id = serviceTurno.salvar(turno);
		
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(42);
		listaPessoas.add(52);
		listaPessoas.add(64);
		
		serviceTurno.adicionarPessoa(listaPessoas, id);
		assertNotNull(serviceTurno.turnoDaPessoa(42));
		assertNotNull(serviceTurno.turnoDaPessoa(52));
		assertNotNull(serviceTurno.turnoDaPessoa(64));
	}

}
