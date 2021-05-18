package br.com.proway.senior.controller;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PessoaDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;
import org.hibernate.Session;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class JornadaControllerTest {
    static Session session = DBConnection.getSession();
    static JornadaController controller = new JornadaController(session);
    static JornadaDAO dao = JornadaDAO.getInstance(session);
    static PessoaDoPonto pessoa;
    static PessoaDAO pessoaDAO = PessoaDAO.getInstance(session);
    static Turno turno;
    static TurnoDAO turnoDAO = TurnoDAO.getInstance(session);


    @Test
    void testInserindoUmaJornadaAtravesDaController() {
        pessoa = new PessoaDoPonto(172);
        pessoaDAO.insert(pessoa);
        turno = new Turno(173, LocalTime.now(), LocalTime.now().plusHours(7), "Turno Test");
        turnoDAO.insert(turno);
        Jornada jornada = new Jornada(174, LocalDate.of(2020, 3, 3),
                pessoa, turno);
        int tamanhoInicial = dao.getAll().size();
        controller.insert(jornada);
        int tamanhoFinal = dao.getAll().size();

        Assertions.assertEquals(tamanhoInicial + 1, tamanhoFinal);
    }

    @Test
    void testGet() throws Exception {
        pessoa = new PessoaDoPonto(26);
        pessoaDAO.insert(pessoa);
        turno = new Turno(999, LocalTime.now(), LocalTime.now().plusHours(7),
                "Turno Test");
        turnoDAO.insert(turno);
        Jornada jornada = new Jornada(25, LocalDate.of(2555, 12, 29),
                pessoa, turno);
        controller.insert(jornada);

        Assertions.assertEquals(2555, controller.get(26).getData().getYear());
    }

    @Test
    void testGetAll() {
        Assertions.assertNotNull(controller.getAll());
    }

    @Test
    void testDelete() {
        pessoa = new PessoaDoPonto(26);
        pessoaDAO.insert(pessoa);
        turno = new Turno(999, LocalTime.now(), LocalTime.now().plusHours(7),
                "Turno Test");
        turnoDAO.insert(turno);
        Jornada jornada = new Jornada(25, LocalDate.of(2555, 12, 29),
                pessoa, turno);
        controller.insert(jornada);
        int tamanhoInicial = controller.getAll().size();
        controller.delete(jornada);

        Assertions.assertEquals(tamanhoInicial - 1, controller.getAll().size());
    }
}

//adfja