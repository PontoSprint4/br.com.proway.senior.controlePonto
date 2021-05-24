package br.com.proway.senior.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

class JornadaDAOTest {
	
	static JornadaDAO dao = JornadaDAO.getInstance(DBConnection.getSession());
	
	static LocalDate data;
	static int idPessoa;
	static Turno turnoPadrao;
	static Ponto ponto1;
	static Ponto ponto2;
	
	static void popularDados() {
		data = LocalDate.now();
		idPessoa = 12;
		turnoPadrao = new Turno(LocalTime.now(), LocalTime.now().plusMinutes(42), "Turno Teste");
		ponto1 = new Ponto(LocalDateTime.now());
		ponto2 = new Ponto(LocalDateTime.now().plusMinutes(42));
	}
	
	@BeforeAll
	public static void before() {
		popularDados();
	}
	
	@AfterEach
	public void after() {
		dao.deleteAll();
		popularDados();
	}
	
	@Test
	void testGetInstance() {
		assertNotNull(JornadaDAO.getInstance(DBConnection.getSession()));
	}

	@Test
	void testCreateJornada() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		Integer idCriado = dao.create(instancia);
		assertNotNull(idCriado);
	}

	@Test
	void testGetInt() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		int idCriado = dao.create(instancia);
		assertNotNull(dao.get(idCriado));
	}

	@Test
	void testUpdateJornada() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		int idCriado = dao.create(instancia);
		
		Jornada jornadaSalva = dao.get(idCriado);
		LocalDate novaData = LocalDate.now().plusDays(12);
		jornadaSalva.setData(novaData);
		
		assertTrue(dao.update(jornadaSalva));
		assertEquals(novaData, dao.get(idCriado).getData());
	}

	@Test
	void testDeleteInt() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		int idCriado = dao.create(instancia);
		
		assertTrue(dao.delete(idCriado));
		assertNull(dao.get(idCriado));
	}

	@Test
	void testGetAll() {
		int tamanhoAntes = dao.getAll().size();
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		dao.create(instancia);
		assertEquals(tamanhoAntes+1, dao.getAll().size());
	}

	@Test
	void testDeleteAll() {
		Jornada instancia = new Jornada(data, idPessoa, turnoPadrao);
		dao.create(instancia);
		assertTrue(dao.deleteAll());
		assertEquals(0, dao.getAll().size());
		assertFalse(dao.deleteAll()); // Nada a deletar, FALSE!
	}

	@Test
	void testReadByIdPessoa() {
		int idCriado = dao.create(new Jornada(data, idPessoa, turnoPadrao));
		Jornada retornado = dao.get(idCriado);
		
		assertTrue(dao.readByIdPessoa(idPessoa).contains(retornado));
		
	}
	
	@Test
	void testBuscarPorData() {
		LocalDate dia = LocalDate.of(2021, 12, 21);
		
		dao.create(new Jornada(LocalDate.of(2021, 11, 8), idPessoa, turnoPadrao));
		dao.create(new Jornada(LocalDate.of(2021, 12, 8), idPessoa, turnoPadrao));
		dao.create(new Jornada(LocalDate.of(2021, 12, 21), idPessoa, turnoPadrao));
		
		List<Jornada> jornadas = dao.obterJornadasDoDia(idPessoa, dia);
		assertEquals(1, jornadas.size());
		
	}
	
	@Test
	void testBuscarPorIntervaloDatas() {
		LocalDate inicioFiltro = LocalDate.of(2021, 8, 7);
		LocalDate fimFiltro = LocalDate.of(2021, 9, 7);
		
		dao.create(new Jornada(LocalDate.of(2021, 4, 8), idPessoa, turnoPadrao));
		dao.create(new Jornada(LocalDate.of(2021, 2, 8), idPessoa, turnoPadrao));
		int id = dao.create(new Jornada(LocalDate.of(2021, 5, 21), idPessoa, turnoPadrao));
		
		dao.create(new Jornada(LocalDate.of(2021, 8, 8), idPessoa, turnoPadrao));
		dao.create(new Jornada(LocalDate.of(2021, 8, 16), idPessoa, turnoPadrao));
		dao.create(new Jornada(LocalDate.of(2021, 8, 21), idPessoa, turnoPadrao));
		dao.create(new Jornada(LocalDate.of(2021, 9, 4), idPessoa, turnoPadrao));
		
		List<Jornada> jornadas = dao.obterJornadasEntreDatas(idPessoa, inicioFiltro, fimFiltro);
		assertEquals(4, jornadas.size());
		
	}
}
