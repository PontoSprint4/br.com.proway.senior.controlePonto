package br.com.proway.senior.controlePonto.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.proway.senior.DAO.TurnoDAO;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.utils.Validadores;

/**
 * Classe responsável por tratar de pedidos de visualização e ações.
 * 
 * @author Thiago Luiz Barbieri <thiago.barbieri@senior.com.br>
 * @author Vitor Gehrke <vitor.gehrke@senior.com.br>
 * @version Sprint6
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
	 * Método para inserir no banco de dados através do {@link TurnoDAO}, um objeto
	 * do tipo {@link Turno}.
	 * 
	 * @param turno do tipo {@link Turno}
	 */
	public Integer create(Turno turno) {
		int id = tdao.create(turno);
		return id;
	}

	/**
	 * Método para buscar no banco de dados através do {@link TurnoDAO}, um objeto
	 * do tipo {@link Turno}, usando seu index.
	 * 
	 * @param index do tipo int
	 * @return objeto de {@link Turno}
	 * @throws Exception
	 */
	public Turno get(int index) throws Exception {
		if (Validadores.ehMenorIgualZeroOuNulo(index))
			throw new Exception("Id invalido.");
		return tdao.get(index);
	}

	/**
	 * Método para buscar todos os objetos do tipo {@link Turno}, no banco de dados
	 * através do {@link TurnoDAO}.
	 * 
	 * @return ArrayList<Turno>
	 */
	public List<Turno> getAll() {
		return tdao.getAll();
	}

	/**
	 * Método para atualizar um objeto do tipo {@link Turno}, no banco de dados
	 * através do {@link TurnoDAO}
	 * 
	 * @param turno do tipo {@link Turno}
	 * @throws Exception
	 */
	public boolean update(int idDoTurnoASerAlterado, Turno turno) throws Exception {
		if (Validadores.ehObjetoNulo(get(idDoTurnoASerAlterado)))
			throw new Exception("O Turno não existe no banco de dados.");
		if (Validadores.ehObjetoNulo(turno))
			throw new Exception("O Turno não pode ser nulo.");
		Turno persistido = tdao.get(idDoTurnoASerAlterado);
		persistido.setHoraFim(turno.getHoraFim());
		persistido.setHoraInicio(turno.getHoraInicio());
		persistido.setNomeTurno(turno.getNomeTurno());
		tdao.update(persistido);
		return true;
	}

	/**
	 * Método para apagar do banco de dados através do {@link TurnoDAO}, um objeto
	 * do tipo {@link Turno}.
	 * 
	 * @param id do {@link Turno} a ser apagado.
	 * @throws Exception
	 */
	/**
	 * Método para apagar do banco de dados através do {@link TurnoDAO}, um objeto
	 * do tipo {@link Turno}.
	 * 
	 * @param id do {@link Turno} a ser apagado.
	 * @return boolean
	 * @throws Exception
	 */
	public boolean delete(int id) throws Exception {
		if (Validadores.ehMenorIgualZeroOuNulo(id)) 
			throw new Exception("Id invaliddo.");
		if (Validadores.ehObjetoNulo(get(id)))
			throw new Exception("O Turno não existe no banco de dados.");
		tdao.delete(id);
		return true;
	}
	
	/**
	 * Método para apagar do banco de dados todos os registros atraves do {@link TurnoDAO}, 
	 * os objetos do tipo {@link Turno}.
	 * 
	 * @return boolean
	 */
	public boolean deleteAll() {
		return tdao.deleteAll();
	}
}
