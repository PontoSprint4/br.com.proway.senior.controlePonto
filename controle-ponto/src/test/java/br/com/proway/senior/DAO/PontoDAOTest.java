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


}
