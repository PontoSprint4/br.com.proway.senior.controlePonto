package br.com.proway.senior.controlePonto.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.ListaDePessoas;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.services.TurnoService;

class TurnoAPITest {
	TurnoAPI api = new TurnoAPI(new TurnoService());
	
	@AfterEach
	void cleanDB() {
		JornadaDAO.getInstance(DBConnection.getSession()).deleteAll();
		TurnoDAO.getInstance(DBConnection.getSession()).deleteAll();
		PontoDAO.getInstance(DBConnection.getSession()).deleteAll();
	}
	
	@Test
	void testObterTodos() {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		api.salvar(turno);
		
		assertEquals(1, api.obterTodos().size());
	}

	@Test
	void testSalvar() {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		assertNotNull(id);
	}

	@Test
	void testDeletar() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		assertTrue(api.deletar(id));
	}

	@Test
	void testAlterar() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		turno.setNomeTurno("OutroNome");
		api.alterar(id, turno);
		assertEquals(turno.getNomeTurno(), api.obter(id).getNomeTurno());
	}

	@Test
	void testObter() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		assertNotNull(api.obter(id));
	}

	@Test
	void testAdicionarPessoaNoTurno() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		Integer idPessoa = 44;
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(idPessoa);
		
		api.adicionarPessoaNoTurno(listaPessoas, id);
		assertTrue(api.obter(id).getPessoasNoTurno().contains(idPessoa));
	}

	@Test
	void testRemoverPessoaNoTurno() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		Integer idPessoa = 44;
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(idPessoa);
		
		api.adicionarPessoaNoTurno(listaPessoas, id);
		api.removerPessoaNoTurno(listaPessoas, id);
		assertFalse(api.obter(id).getPessoasNoTurno().contains(idPessoa));
	}

	@Test
	void testTodasPessoasDoTurno() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		Integer idPessoa = 44;
		Integer idPessoa2 = 420;
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(idPessoa);
		listaPessoas.add(idPessoa2);
		api.adicionarPessoaNoTurno(listaPessoas, id);
		
		ArrayList<Integer> pessoas = api.todasPessoasDoTurno(id);
		assertTrue(pessoas.contains(idPessoa));
		assertTrue(pessoas.contains(idPessoa2));
	}

	@Test
	void testTurnoDaPessoa() throws Exception {
		LocalTime horaInicio = LocalTime.now();
		LocalTime horaFim = horaInicio.plusHours(8);
		String nomeTurno = "Wawawewa";
		Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
		Integer id = api.salvar(turno);
		
		Integer idPessoa = 44;
		ListaDePessoas listaPessoas = new ListaDePessoas();
		listaPessoas.add(idPessoa);
		
		api.adicionarPessoaNoTurno(listaPessoas, id);
		assertNotNull(api.turnoDaPessoa(idPessoa));
	}

}
