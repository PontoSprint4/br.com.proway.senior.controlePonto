package br.com.proway.senior.controller;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.model.Jornada;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Classe responsável por receber as requisições, tratá-las e encaminhar para o
 * DAO.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version sprint5
 */

public class JornadaController {

    private JornadaDAO dao;

    /**
     *  Construtor que instancia um Controller fazendo uma integração com o
     * 	 * {@link JornadaDAO}, espera uma sessão como parametro para repassar ao
     * 	 * {@link JornadaDAO} retornando um Controller.
     *
     * @param session recebida como parâmetro.
     */
    public JornadaController(Session session) {
        this.dao = JornadaDAO.getInstance(session);

    }

    /**
     * Método que recebe um objeto {@link Jornada} e insere no banco.
     *
     * @param jornadaASerInserida Jornada que será inserida no banco.
     */
    public void insert(Jornada jornadaASerInserida) {
        dao.insert(jornadaASerInserida);
    }

    /**
     * Método que retorna uma {@link Jornada} pelo Id.
     *
     * @param index Id da jornada a ser buscada.
     * @return retorna o objeto Jornada.
     */
    public Jornada get(int index) throws Exception {
        if (dao.get(index) != null) {
            return dao.get(index);
        }
        throw new Exception("Id inexistente");
    }

    /**
     * Método que retorna todas as {@link Jornada}.
     *
     * @return ArrayList de Jornadas
     */
    public ArrayList<Jornada> getAll() {
        return (ArrayList<Jornada>) dao.getAll();
    }

    /**
     * Método que deleta uma jornada.
     *
     * @param jornadaASerDeletada Jornada que será deletada do banco.
     */
    public void delete(Jornada jornadaASerDeletada) {
        dao.delete(jornadaASerDeletada);
    }

}
