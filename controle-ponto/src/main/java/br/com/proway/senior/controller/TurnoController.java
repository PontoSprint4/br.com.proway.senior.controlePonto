package br.com.proway.senior.controller;

import java.util.ArrayList;

import org.hibernate.Session;

import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.model.Turno;

/**
 * Classe responsável por tratar de pedidos de visualização e ações.
 * 
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @author Vitor Gehrke <vitor.gehrke@senior.com.br>
 * @version Sprint5
 *
 */
public class TurnoController {
	
	private TurnoDAO tdao;

	/**
	 * Construtor que instancia um Controller fazendo uma integração com o
	 * {@link TurnoDAO}, espera uma sessão como parametro para repassar ao
	 * {@link TurnoDAO} retornando um Controller.
	 * 
	 * @param session Session
	 */
	public TurnoController(Session session) {
		this.tdao = TurnoDAO.getInstance(session);
	}

	/**
	 * Método para inserir no banco de dados através do {@link TurnoDAO}, um
	 * objeto do tipo {@link Turno}.
	 * 
	 * @param turno do tipo {@link Turno}
	 * @throws Exception
	 */
	public void insert(Turno turno) {
		tdao.insert(turno);
	}

	/**
	 * Método para buscar no banco de dados através do {@link TurnoDAO}, um objeto
	 * do tipo {@link Turno}, usando seu index.
	 * 
	 * @param index do tipo int
	 * @return objeto de {@link Turno}
	 * @throws Exception
	 */
	public Turno get(int index) throws Exception{
		return tdao.get(index);
	}

	/**
	 * Método para buscar todos os objetos do tipo {@link Turno}, no banco de dados
	 * através do {@link TurnoDAO}.
	 * 
	 * @return ArrayList<Turno>
	 */
	public ArrayList<Turno> getAll() {
		return (ArrayList<Turno>) tdao.getAll();
	}
	
	/**
	 * Método para atualizar um objeto do tipo {@link Turno}, no banco de dados
	 * através do {@link TurnoDAO}
	 * 
	 * @param turno do tipo {@link Turno}
	 */
	public void update(Turno turno) {
		tdao.update(turno);
	}

	/**
	 * Método para apagar do banco de dados através do {@link TurnoDAO}, um objeto
	 * do tipo {@link Turno}.
	 * 
	 * @param turno do tipo {@link Turno} a ser apagado.
	 */
	public void delete(Turno ponto) {
		tdao.delete(ponto);
	}
}
