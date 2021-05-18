package br.com.proway.senior.DAO;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JornadaDAOTest {

    static Session session;
    static JornadaDAO jornadaDao;
    static PessoaDAO pessoaDao;
    static TurnoDAO turnoDao;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        session = DBConnection.getSession();
        jornadaDao = JornadaDAO.getInstance(session);
        pessoaDao = PessoaDAO.getInstance(session);
        turnoDao = TurnoDAO.getInstance(session);
    }


    @Test
    void pontoNaJornada() {
        PessoaDoPonto pessoa = new PessoaDoPonto(123);
        Turno turno = new Turno(467898, LocalTime.of(8, 0), LocalTime.of(18, 0),
                "teste123");
        Jornada jornada = new Jornada(789, LocalDate.now(), pessoa, turno);
        Ponto ponto1 = new Ponto(5645665, LocalDateTime.now());
        Ponto ponto2 = new Ponto(8946515, LocalDateTime.now().plusHours(8));
        jornada.listaPonto.add(ponto1);
        jornada.listaPonto.add(ponto2);
        jornadaDao.insert(jornada);
        List<Jornada> jornadas = jornadaDao.getAll();
        System.out.println(jornadas);
    }

    @Test
    void testCreate() {
        Jornada jornada = new Jornada();
        jornada.setData(LocalDate.of(2333, 8, 24));
        jornada.setPessoa(pessoaDao.get(119));
        jornada.setTurno(turnoDao.get(120));
        jornadaDao.insert(jornada);

        assertNotNull(jornada);
    }

    @Test
    void testRead() {
        assertEquals(35, jornadaDao.get(35).getId());
    }

    @Test
    void testPegaTodasAsJornadasPorIdPessoa() {
        pessoaDao.get(114);
        ArrayList<Jornada> listaJornadas = (ArrayList) jornadaDao.readByIdPessoa(pessoaDao.get(114));
        System.out.println(jornadaDao.readByIdPessoa(pessoaDao.get(114)));
        // assertNotNull(jornadaDao.readByIdPessoa(pessoaDao.find(114)));

    }

    @Test
    void testDelete() {
        Jornada jornada = new Jornada(19, LocalDate.of(3526, 12, 5),
                pessoaDao.get(115), turnoDao.get(120));
        jornadaDao.insert(jornada);
        int inicio = jornadaDao.getAll().size();
        jornadaDao.delete(jornada);
        assertEquals(inicio - 1, jornadaDao.getAll().size());
    }

    @Test
    void testUpdate() {
        session.clear();
        Turno turno = new Turno(0, LocalTime.now(), LocalTime.now().plusHours(4), "Turno 54");
        turnoDao.insert(turno);

        Jornada jornada2 = new Jornada(134, LocalDate.of(1999, 10, 5), pessoaDao.get(115), turno);

        jornadaDao.update(jornada2);

    }

    @Test
    void testReadAll() {

        for (int i = 0; i < jornadaDao.getAll().size(); i++) {
            if (jornadaDao.getAll().size() == i) {
                assertEquals(i, jornadaDao.getAll().size());
            }
        }

    }

    @Test
    void minutosParaTempoTotal() {
        Long minutos = 1000L;
        System.out.println(jornadaDao.minutosParaTempoTotal(minutos,
                TimeUnit.MINUTES));
    }

    @Test
    void minutosParaTempoSemDias() {
        Long minutos = 1000L;
        System.out.println(jornadaDao.minutosParaTempoSemDias(minutos, TimeUnit.MINUTES));

    }

    @Test
    void tempoEntreRegistros() throws Exception {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fim = LocalDateTime.now().plusHours(3);
        Assertions.assertEquals(180L, jornadaDao.tempoEntreRegistros(inicio,
                fim));
        Assertions.assertNotEquals(188L, jornadaDao.tempoEntreRegistros(inicio,
                fim));
        Assertions.assertThrows(Exception.class,
                () -> jornadaDao.tempoEntreRegistros(fim, inicio));
    }
}
