package br.com.proway.senior.controlePonto.controller;

import java.util.List;

import org.hibernate.Session;

import br.com.proway.senior.controlePonto.DAO.PontoDAO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.utils.Validadores;

/**
 * Classe responsavel por tratar de pedidos de visualizacao e acoes.
 * 
 * @author Vitor Gehrke <vitor.gehrke@senior.com.br>
 * @version Sprint6
 *
 */
public class PontoController {

	private PontoDAO pdao;

	/**
	 * Construtor que instancia um Controller fazendo uma integracao com o
	 * {@link PontoDAO}, espera uma sessao como parametro para repassar ao
	 * {@link PontoDAO} retornando um Controller.
	 * 
	 * @param session Session
	 */
	public PontoController(Session session) {
		this.pdao = PontoDAO.getInstance(session);
	}

	/**
	 * Metodo para inserir no banco de dados atraves do {@link PontoDAO} um objeto
	 * do tipo {@link Ponto}. Retorna seu id cadastrado no DB.
	 * 
	 * @param ponto
	 * @return idCadastrado
	 */
	public Integer create(Ponto ponto) {
		return pdao.create(ponto);
	}

	/**
	 * Metodo para buscar no banco de dados atraves do {@link PontoDAO} um objeto
	 * do tipo {@link Ponto} usando seu index.
	 * 
	 * @param index
	 * @return ponto
	 * @throws Exception Id invalido
	 */
	public Ponto get(int index) throws Exception {
		if(Validadores.ehMenorIgualZeroOuNulo(index)) 
			throw new Exception("Id invalido");
		return pdao.get(index);
	}

	/**
	 * Metodo para buscar todos os objetos do tipo {@link Ponto} no banco de dados
	 * atraves do {@link PontoDAO}.
	 * 
	 * @return List<Ponto>
	 */
	public List<Ponto> getAll() {
		return pdao.getAll();
	}

	/**
	 * Metodo para atualizar um objeto do tipo {@link Ponto} no banco de dados
	 * atraves do {@link PontoDAO}.
	 * 
	 * @param ponto
	 * @param id do Ponto.
	 * @return true || false se atualizado com sucesso ou não.
	 * @throws Exception Ponto não existe no banco de dados.
	 * @throws Exception Ponto nulo.
	 */
	public boolean update(Ponto ponto, int id) throws Exception {
		if (Validadores.ehObjetoNulo(get(id)))
			throw new Exception("O Ponto não existe no banco de dados.");
		if (Validadores.ehObjetoNulo(ponto))
			throw new Exception("O Ponto não pode ser nulo.");
		Ponto pontoPersistido = pdao.get(id);
		pontoPersistido.setIdPessoa(ponto.getIdPessoa());
		pontoPersistido.setIdPonto(ponto.getIdPonto());
		pontoPersistido.setMomentoPonto(ponto.getMomentoPonto());
		return pdao.update(pontoPersistido); // pdao.update chama GenericDAO.update que retorna boolean
	}

	/**
	 * Metodo para apagar do banco de dados atraves do {@link PontoDAO} um objeto
	 * do tipo {@link Ponto}.
	 * 
	 * @param id do ponto a ser apagado.
	 * @return true || false se apagado com sucesso ou não.
	 * @throws Exception Id invalido.
	 * @throws Exception Ponto nao existe no banco de dados.
	 */
	public boolean delete(int id) throws Exception {
		if(Validadores.ehMenorIgualZeroOuNulo(id))
			throw new Exception("Id invalido");
		if(Validadores.ehObjetoNulo(get(id)))
			throw new Exception("O ponto nao existe no banco de dados");
		return pdao.delete(id); // pdao.delete chama GenericDAO.delete que retorna boolean
	}
	
	/**
	 * Metodo para apagar do banco de dados atraves do {@link PontoDAO} todos os {@link Pont}s 	
	 * @return boolean
	 */
	public boolean deleteAll() {
		return pdao.deleteAll();
	}
}
