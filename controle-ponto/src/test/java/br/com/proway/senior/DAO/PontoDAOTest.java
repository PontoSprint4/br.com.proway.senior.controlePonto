package br.com.proway.senior.DAO;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Ponto;
import org.hibernate.Session;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PontoDAOTest {
<<<<<<< HEAD

    static PontoDAO instance;
    static Session session;
    static Ponto ponto;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        session = DBConnection.getSession();
        instance = PontoDAO.getInstance(session);
    }

    @Test
    public void testAInsert() {
        Ponto ponto1 = new Ponto(null, LocalDateTime.of(2021, 5, 13, 23, 59));
        Ponto ponto2 = new Ponto(null, LocalDateTime.of(2020, 6, 15, 9, 15));
        instance.insert(ponto1);
        instance.insert(ponto2);
        System.out.println("\n\n\n\n\n" + instance.getAll().size()
                + "\n\n" + instance.getAll());
        assertNotNull(ponto1);
    }

    @Test
    public void testCDelete() {
        ponto = new Ponto(3, LocalDateTime.of(2222, 5, 13, 20, 59));
        instance.insert(ponto);
        assertTrue(instance.delete(ponto));
    }

    @Test
    public void testDGetAll() {
        System.out.println("\n\n**************************\n"
                + instance.getAll().stream().count()
                + "\n**************************\n"
                + instance.getAll()
                + "\n**************************\n\n");
        assertNotNull(instance.getAll());
    }

    @Test
    public void testEUpdate() {
        session.clear();
        ponto = new Ponto(21, LocalDateTime.of(2999, 6, 6, 20, 59));
        assertTrue(instance.update(ponto));
    }

    @Test
    public void testFGet() {
        System.out.println(instance.get(21).toString());
        assertEquals(21, (int) instance.get(21).getIdPonto());
    }


=======
	
//	@BeforeClass
//	public static void refreshDatabase() {
//		String queryDrop = "DROP TABLE pontos";
//		
//		String queryCreate = "CREATE TABLE pontos ("
//				+ "id SERIAL PRIMARY KEY NOT NULL,"
//				+ "idJornada INT NOT NULL,"
//				+ "momentoPonto TIMESTAMP NOT NULL)";
//				 	
//		String query1 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 111 + ",'" + LocalDateTime.now().plusHours(3) + "')"; 
//		String query2 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 112 + ",'" + LocalDateTime.now().plusMinutes(51) + "')";
//		String query3 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 113 + ",'" + LocalDateTime.now().plusMinutes(17) + "')";
//		String query4 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 111 + ",'" + LocalDateTime.now().plusHours(9) + "')"; 
//		// pessoa id = 114
//		String query5 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 114 + ",'" + LocalDateTime.now() + "')";
//		String query6 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 114 + ",'" + LocalDateTime.now().plusHours(4) + "')";
//		String query7 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 114 + ",'" + LocalDateTime.now().plusHours(6) + "')";
//		String query8 = "INSERT INTO pontos (idJornada, momentoPonto) VALUES ("
//				+ 114 + ",'" + LocalDateTime.now().plusHours(10) + "')";
//		
//		try {
//			PostgresConnector.executeUpdate(queryDrop);
//			PostgresConnector.executeUpdate(queryCreate);
//			PostgresConnector.executeUpdate(query1);
//			PostgresConnector.executeUpdate(query2);
//			PostgresConnector.executeUpdate(query3);
//			PostgresConnector.executeUpdate(query4);
//			PostgresConnector.executeUpdate(query5);
//			PostgresConnector.executeUpdate(query6);
//			PostgresConnector.executeUpdate(query7);
//			PostgresConnector.executeUpdate(query8);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}	
//	@Before
//	public void cleanDAO() {
//		PontoDAO.newInstance();
//	}	
//	
//	@Test
//	public void testRead() {
//		PontoDAO db = PontoDAO.getInstance();
//		db.read(2);
//	}
//	
//	@Test
//	public void testReadByIdJornada() {
//		PontoDAO db = PontoDAO.getInstance();
//		db.readByIdJornada(111);
//		assertFalse(db.readAll().size() == 5);
//	}
//	
//	@Test
//	public void testCreate() {
//		PontoDAO db = PontoDAO.getInstance();
//		Jornada jornada = new Jornada(555, 2, LocalDate.now(), 3);
//
//		db.create(jornada); // 9 itens no db		
//		assertTrue(db.readAll().size() == 9);
//	}
//
//	@Test
//	public void testDelete() {
//		PontoDAO db = PontoDAO.getInstance();
//		db.delete(2);
//		assertEquals(db.readAll().size(), 8);
//	}
	
>>>>>>> 713098d011c3b0ebf01481ec19485836a6b57605
}
