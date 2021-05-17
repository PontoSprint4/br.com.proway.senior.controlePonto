package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.hibernate.Session;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.interfaces.IPessoa;

/**
 * Classe responsável por receber as requisições, tratá-las e encaminhar para o
 * DAO.
 * 
 * @author Vanderlei Kleinschmidt
 * @author Samuel Levi
 * @version sprint5
 */

public class JornadaController {

	private JornadaDAO dao;

	/**
	 * Construtor que instancia uma controller fazendo a integração com o
	 * {@link JornadaDAO}.
	 * 
	 * @param session recebida como parâmetro.
	 */
	public JornadaController(Session session) {
		this.dao = JornadaDAO.getInstance(session);

	}

	/**
	 * Método que recebe um objeto {@link Jornada} e insere no banco.
	 * 
	 * @param jornadaASerInserida
	 */
	public void insert(Jornada jornadaASerInserida) {
		dao.insert(jornadaASerInserida);
	}

	/**
	 * Método que retorna uma {@link Jornada} pelo Id.
	 * 
	 * @param index Id da jornada a ser buscada.
	 * @return retorna o objeto Jornada.
	 * @throws Exception
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
	 * @param jornadaASerDeletada
	 */
	public void delete(Jornada jornadaASerDeletada) {
		dao.delete(jornadaASerDeletada);
	}
}
