package br.com.proway.senior.DAO;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.hibernate.*;
import org.junit.*;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Ponto;

public class PontoDAOTest {

	static PontoDAO instance;
	static Session session;
	static Ponto ponto;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		instance = PontoDAO.getInstance(session);
		ponto = new Ponto();
	}

	@Test
	public void testInsert() {
		ponto.setMomentoPonto(LocalDateTime.of(2021,05,13, 23,59));
		instance.insert(ponto);
		System.out.println("\n\n\n\n\n"+instance.getAll().size() + "\n\n\n\n\n");
		System.out.println("\n\n\n\n\n"+instance.getAll().get(7).getIdPonto() + "\n\n\n\n\n");		
		
		assertEquals(instance.getAll().get(7).getIdPonto(), ponto.getIdPonto());
	}

	@Test
	public void testUpdate() {
//		ponto = new Ponto();
//		ponto.getIdPonto();
//		ponto.setMomentoPonto(LocalDateTime.of(2019,05,13, 20,59));
		ponto = new Ponto(114, LocalDateTime.of(2019,05,13, 20,59));
		assertTrue(instance.update(ponto));
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAll() {
		System.out.println("\n\n\n\n\n\n"+instance.getAll()+"\n\n\n\n\n\n");
		assertNotNull(instance.getAll());
	}

}
