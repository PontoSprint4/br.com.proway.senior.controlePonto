package br.com.proway.senior.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.utils.Validadores;

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
     * Construtor que instancia um Controller fazendo uma integração com o
     * {@link JornadaDAO}, espera uma sessão como parametro para repassar ao
     * {@link JornadaDAO} retornando um Controller.
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
     * @return id Id da Jornada inserida no banco.
     */
	public Integer create(Jornada jornadaASerInserida) {
		return dao.create(jornadaASerInserida);
	}

    /**
     * Método que retorna uma {@link Jornada} pelo Id.
     *
     * @param index Id da jornada a ser buscada.
     * @return retorna o objeto Jornada.
     */
	public Jornada get(int index) throws Exception {
		if (Validadores.ehZeroOuNulo(index))
			throw new Exception("Id inexistente");
		return dao.get(index);
	}

    /**
     * Método que retorna todas as {@link Jornada}.
     *
     * @return List de Jornadas
     */
	public List<Jornada> getAll() {
		return dao.getAll();
	}

	/**
	 * Metodo para atualizar um objeto do tipo {@link Jornada}, no banco de dados
	 * através do {@link JornadaDAO}. Caso tenha sucesso operacao retorna true.
	 * 
	 * @param idJornada
	 * @param jornada
	 * @return boolean
	 * @throws Exception
	 */
	public boolean update(int idJornada, Jornada jornada) throws Exception {
		if (Validadores.ehObjetoNulo(get(idJornada)))
			throw new Exception("A Jornada nao existe no banco de dados.");
		if (Validadores.ehObjetoNulo(jornada))
			throw new Exception("A Jornada nao pode ser nula.");
		Jornada jornadaPersistida = dao.get(idJornada);
		jornadaPersistida.setData(jornada.getData());
		jornadaPersistida.setTurno(jornada.getTurno());
		jornadaPersistida.setIdPessoa(jornada.getIdPessoa());
		jornadaPersistida.trocarListaPonto(jornada.getListaPonto());
		dao.update(jornadaPersistida);
		return true;
	}


    /**
     * Método que deleta uma jornada.
     *
     * @param jornadaASerDeletada Jornada que será deletada do banco.
     */
    public void delete(int id) {
        dao.delete(id);
    }
    
    /**
     * Metodo que deleta todas as jornadas persistidas no Banco de Dados, caso
     * tenha sucesso na operação retorna true.
     * .
     * @return boolean
     */
	public boolean deleteAll() {
		return dao.deleteAll();
	}
    
}
