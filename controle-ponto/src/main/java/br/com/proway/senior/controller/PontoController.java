package br.com.proway.senior.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Ponto;

/**
 * Classe responsável por tratar de pedidos de visualização e ações.
 * 
 * @author Lucas Walim da Silva <lucas.walim@senior.com.br>
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @author Vitor Gehrke <vitor.gehrke@senior.com.br>
 *
 */
public class PontoController {

	private PontoDAO pdao;

	/**
	 * Construtor que instancia um Controller fazendo uma integração com o 
	 * {@link PontoDAO}, espera uma sessão como parametro para repassar ao 
	 * {@link PontoDAO} retornando um Controller.
	 * 
	 * @param session Session
	 */
	public PontoController(Session session) {
		this.pdao = PontoDAO.getInstance(session);
	}

	/**
	 * Método para inserir no banco de dados através do {@link PontoDAO}, 
	 * um objeto do tipo {@link Ponto}.
	 * 
	 * @param ponto do tipo Ponto
	 * @throws Exception
	 */
	public void insert(Ponto ponto) throws Exception {
		try {
			pdao.insert(ponto);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * Precisa ser implementado.
	 * 
	 * @param index
	 * @return ArrayList<Ponto>
	 * @throws Exception
	 */
//	public ArrayList<Ponto> readByIdJornada(int index) throws Exception {
//		if (pdao.readByIdJornada(index) != null) {
//			return pdao.readByIdJornada(index);
//		}
//		throw new Exception("Index Inexistente");
//	}

	/**
	 * Método para buscar no banco de dados através do {@link PontoDAO}, 
	 * um objeto do tipo {@link Ponto}, usando seu index.
	 * 
	 * @param index
	 * @return objeto de ponto
	 * @throws Exception
	 */
	public Ponto get(int index) throws Exception {
		if (pdao.get(index) != null) {
			return pdao.get(index);
		}
		throw new Exception("Index Inexistente");
	}

	/**
	 * Método para buscar todos os objetos do tipo {@link Ponto}, 
	 * no banco de dados através do {@link PontoDAO}.
	 * 
	 * @return ArrayList<Ponto>
	 */
	public ArrayList<Ponto> getAll() {
		return (ArrayList<Ponto>) pdao.getAll();
	}

	/**
	 * Método para apagar do banco de dados através do {@link PontoDAO}, 
	 * um objeto do tipo {@link Ponto}.
	 * 
	 * @param ponto do tipo Ponto a ser apagado.
	 * @return true || false
	 */
	public boolean delete(Ponto ponto) {
		try {
			pdao.delete(ponto);
			return true;
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}
}
