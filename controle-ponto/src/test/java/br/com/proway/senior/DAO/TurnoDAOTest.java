package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Turno;

class TurnoDAOTest {
	static LocalTime inicio;
	static LocalTime fim ;
	static String nome ;
	
	static TurnoDAO dao;
	
	@BeforeAll
	static void prepararVariaveisStatic() {
		dao = TurnoDAO.getInstance(DBConnection.getSession());
		
		inicio = LocalTime.now();
		fim = LocalTime.now().plusHours(7);
		nome = "Turno Teste";
	}
	
	@AfterAll
	static void after() {
		dao.deleteAll();
	}
	
	@Test
	void testGetInstance() {
		assertNotNull(dao);
	}

	@Test
	void testCreateTurno() {
		
		Turno instancia = new Turno(inicio, fim, nome);
		int id = dao.create(instancia);
		assertNotNull(id);
	}

	@Test
	void testUpdateTurno() {
		Turno instancia = new Turno(inicio, fim, nome);
		int id = dao.create(instancia);
		Turno armazenado = dao.get(id);
		
		// Temos que usar o objeto obtido do BD, nao criar um novo.
		//Turno armazenado = new Turno(id, inicio, fim, nome);
		
		String novoNome = "wah";
		armazenado.setNomeTurno(novoNome);
		assertTrue(dao.update(armazenado));
		assertEquals(novoNome, dao.get(id).getNomeTurno());
		
	}

	@Test
	void testDeleteInt() {
		int tamanhoAntes = dao.getAll().size();
		Turno instancia = new Turno(inicio, fim, nome);
		int id = dao.create(instancia);
		
		dao.delete(id);
		assertEquals(tamanhoAntes, dao.getAll().size());
		assertNull(dao.get(id));
		
	}

	@Test
	void testGetInt() {
		Turno instancia = new Turno(inicio, fim, nome);
		int id = dao.create(instancia);
		assertNotNull(dao.get(id));
	}

	@Test
	void testGetAll() {
		int tamanhoAntes = dao.getAll().size();
		Turno instancia = new Turno(inicio, fim, nome);
		Turno instancia2 = new Turno(inicio, fim, nome);
		dao.create(instancia);
		dao.create(instancia2);
		assertEquals(tamanhoAntes+2, dao.getAll().size());
	}
	
	@Test
	void testDeleteAll() {
		Turno instancia = new Turno(inicio, fim, nome);
		Turno instancia2 = new Turno(inicio, fim, nome);
		dao.create(instancia);
		dao.create(instancia2);
		int tamanhoAntes = dao.getAll().size();
		dao.deleteAll();
		assertEquals(0, dao.getAll().size());
	}

}
