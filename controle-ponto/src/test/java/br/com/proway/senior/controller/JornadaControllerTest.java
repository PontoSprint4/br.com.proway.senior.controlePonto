package br.com.proway.senior.controller;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PessoaDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class JornadaControllerTest {
    static Session session;
    static JornadaController controller;
    static JornadaDAO dao;
    static PessoaDoPonto pessoa;
    static PessoaDAO pessoaDAO;
    static Turno turno;
    static TurnoDAO turnoDAO;

    @BeforeAll
    static void setUp() throws Exception {
        session = DBConnection.getSession();
        controller = new JornadaController(session);
        dao = JornadaDAO.getInstance(session);
        pessoa = new PessoaDoPonto(95);
        pessoaDAO.create(pessoa);
        turno = new Turno(99, LocalTime.of(8, 10), LocalTime.of(12, 10),
                "Teste");
        turnoDAO.create(turno);
    }

    @Test
    void testInserindoUmaJornadaAtravesDaController() {
        Jornada jornada = new Jornada(null, LocalDate.of(2020, 3, 3),
                pessoa, turno);
        int tamanhoInicial = dao.getAll().size();
        controller.insert(jornada);
        int tamanhoFinal = dao.getAll().size();

        Assertions.assertEquals(tamanhoInicial + 1, tamanhoFinal);
    }

    @Test
    void get() {
    }

    @Test
    void getAll() {
    }

    @Test
    void delete() {
    }
}