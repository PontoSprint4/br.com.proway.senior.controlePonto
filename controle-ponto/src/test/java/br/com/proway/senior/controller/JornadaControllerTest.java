package br.com.proway.senior.controller;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PessoaDAO;
import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

class JornadaControllerTest {
    static Session session = DBConnection.getSession();
    static JornadaController controller;
    static JornadaDAO dao = JornadaDAO.getInstance(session);
    static PessoaDoPonto pessoa;
    static PessoaDAO pessoaDAO = PessoaDAO.getInstance(session);
    static Turno turno;
    static TurnoDAO turnoDAO = TurnoDAO.getInstance(session);


    @Test
    void testInserindoUmaJornadaAtravesDaController() {
        PessoaDoPonto pessoa1 = new PessoaDoPonto(172);
        pessoaDao.create(pessoa1);

        Turno turno1 = new Turno(173, LocalTime.now(), LocalTime.now().plusHours(7), "Turno Test");
        turnoDao.create(turno1);

        Jornada jornada = new Jornada(174, LocalDate.of(2020, 3, 3),
                pessoa1, turno1);

        jornadaController.insert(jornada);




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