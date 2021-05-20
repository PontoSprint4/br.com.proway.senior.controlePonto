package br.com.proway.senior.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.interfaces.ICRUD;

/**
 * Classe Abstrata com Metodos de interacao com a Hibernate Session.
 * @author Willian Kenji Nishizawa <willian.kenji@senior.com.br>
 *
 * @param <T> : Classe que serve de base para a Tabela do Hibernate (@Entity)
 */
public abstract class GenericDAO<T> implements ICRUD<T> {
	
	/***
	 * Insere no banco de dados o registro de um objeto.
	 *
	 * @param T entidade : Objeto a ser inserido.
	 * @return int Id :  identificador do objeto inserido.
	 */
	public Integer create(T entidade) {
		Session sessao = DBConnection.getSession();
		if (!sessao.getTransaction().isActive())
			sessao.beginTransaction();

		Integer idCadastrado = (Integer) sessao.save(entidade);
		sessao.getTransaction().commit();
		return idCadastrado;
	}
	
	/***
	 * Realiza uma busca no banco de dados pelo ID informado e retorna a 
	 * tupla com os dados correspondentes.
	 * 
	 * @param int Id do cargo a ser consultado.
	 * @param classeTabela Class classe da entidade
	 * @return Objeto encontrado no banco de dados.
	 */
	public T get(Class<T> classeTabela, int id) {
		return DBConnection.getSession().get(classeTabela, id);
	}
	
	/***
	 * Atualiza o registro de um objeto.
	 *
	 * @param T objetoAtualizado objeto atualizado a ser inserido no BD.
	 * @return boolean para sucesso da operacao
	 */
	public boolean update(T objetoAtualizado) {
        if (!DBConnection.getSession().getTransaction().isActive()) {
        	DBConnection.getSession().beginTransaction();
        }

        try {
        	DBConnection.getSession().update(objetoAtualizado);
        	DBConnection.getSession().getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
	
	/**
	 * Deleta do banco de dados um objeto.
	 * 
	 * 
	 * @param int Id do objeto a ser deletado.
	 * @param classeTabela Class classe da entidade
	 * @return boolean para sucesso da operacao
	 */
	public boolean delete(Class<T> classeTabela,int id) {
		T cargo = get(classeTabela,id);

		if (!DBConnection.getSession().getTransaction().isActive()) {
			DBConnection.getSession().beginTransaction();
		}
		DBConnection.getSession().delete(cargo);
		DBConnection.getSession().getTransaction().commit();
		return true;
	}
	
	/**
	 * Retorna todas as linhas da coluna 'nomeTabela' desejada.
	 * 
	 * @param classeTabela Class classe da entidade
	 * @return ArrayList com todas as entradas da tabela.
	 */
	public List<T> getAll(Class<T> classeTabela) {
		Session session = DBConnection.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
		criteria.from(classeTabela);
		Query<T> query = session.createQuery(criteria);
		List<T> results = query.getResultList();
		return new ArrayList<T>(results);
	}
	
	/**
	 * Remove todas as linhas da coluna 'nomeTabela' desejada.
	 * 
	 * @param classeTabela Class classe da entidade
	 * @return Boolean para sucesso da operacao 
	 */
	public boolean deleteAll(String nomeTabela) {
		Session session = DBConnection.getSession();
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		int modificados = session.createSQLQuery("DELETE FROM "+nomeTabela).executeUpdate();
		session.getTransaction().commit();
		return modificados > 0 ? true : false;
	}
	
	/**
	 * Seleciona entradas de 
	 * tabelas utilizando uma de suas colunas e seu valor como filtro.
	 * 
	 * A variavel valorColuna pode ser uma string parcial do resultado desejado.
	 * <br>
	 * ex: 
	 * <br>
	 * <br>
	 * HibernateMethods<HorasMesModel> instancia = new HibernateMethods()<HorasMesModel>
	 * <br>
	 * instancia.listarPorValorDeColima(HorasMesModel.class, "quantidade", 2)
	 * <br>
	 * <br>
	 * retorna todas as entradas da Tabela HorasMesModel com quantidade=2;
	 * 
	 * @param classeTabela: XXXModel.class
	 * @param String : nomeColuna
	 * @param String : valorColuna
	 * @return
	 */
	public List<T> listarPorValorDeColunaComStringIncompleta(
			Class<T> classeTabela, String nomeColuna, String valorColuna) 
		{
			Session session = DBConnection.getSession();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
			
			Root<T> root = criteria.from(classeTabela);
			Expression<String> selectedColumn = root.get(nomeColuna);
			
			String filter = "%" + valorColuna + "%";
			criteria.select(root)
				.where(criteriaBuilder.like(selectedColumn, filter));
				
			Query<T> query = session.createQuery(criteria);
			List<T> results = query.getResultList();
			return new ArrayList<T>(results);
		}
	
	
	/**
	 * Funcoes e suas respectivas Overloads para poder selecionar entradas de 
	 * tabelas utilizando uma de suas colunas e seu valor como filtro.
	 * <br>
	 * ex: 
	 * <br>
	 * <br>
	 * HibernateMethods<HorasMesModel> instancia = new HibernateMethods()<HorasMesModel>
	 * <br>
	 * instancia.listarPorValorDeColima(HorasMesModel.class, "quantidade", 2)
	 * <br>
	 * <br>
	 * retorna todas as entradas da Tabela HorasMesModel com quantidade=2;
	 * 
	 * @param classeTabela: XXXModel.class
	 * @param String : nomeColuna
	 * @param Integer : valorColuna
	 * @return
	 */
	public List<T> listarPorValorDeColunaExato(
		Class<T> classeTabela, String nomeColuna, Integer valorColuna) 
	{
		Session session = DBConnection.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
		Root<T> root = criteria.from(classeTabela);
		Expression<T> selectedColumn = root.get(nomeColuna);
		criteria.select(root)
			.where(criteriaBuilder.equal(selectedColumn, valorColuna));
		
		Query<T> query = session.createQuery(criteria);
		List<T> results = query.getResultList();
		return new ArrayList<T>(results);
	}
	
	/**
	 * Funcoes e suas respectivas Overloads para poder selecionar entradas de 
	 * tabelas utilizando uma de suas colunas e seu valor como filtro.
	 * <br>
	 * ex: 
	 * <br>
	 * <br>
	 * HibernateMethods<HorasMesModel> instancia = new HibernateMethods()<HorasMesModel>
	 * <br>
	 * instancia.listarPorValorDeColima(HorasMesModel.class, "quantidade", 2)
	 * <br>
	 * <br>
	 * retorna todas as entradas da Tabela HorasMesModel com quantidade=2;
	 * 
	 * @param classeTabela: XXXModel.class
	 * @param String : nomeColuna
	 * @param String : valorColuna
	 * @return
	 */
	public List<T> listarPorValorDeColunaExato(
		Class<T> classeTabela, String nomeColuna, String valorColuna) 
	{
		Session session = DBConnection.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
		Root<T> root = criteria.from(classeTabela);
		Expression<T> selectedColumn = root.get(nomeColuna);
		criteria.select(root)
			.where(criteriaBuilder.equal(selectedColumn, valorColuna));
			
		Query<T> query = session.createQuery(criteria);
		List<T> results = query.getResultList();
		return new ArrayList<T>(results);
	}
	
	/**
	 * Funcoes e suas respectivas Overloads para poder selecionar entradas de 
	 * tabelas utilizando uma de suas colunas e seu valor como filtro.
	 * <br>
	 * ex: 
	 * <br>
	 * <br>
	 * HibernateMethods<HorasMesModel> instancia = new HibernateMethods()<HorasMesModel>
	 * <br>
	 * instancia.listarPorValorDeColima(HorasMesModel.class, "quantidade", 2)
	 * <br>
	 * <br>
	 * retorna todas as entradas da Tabela HorasMesModel com quantidade=2;
	 * 
	 * @param classeTabela: XXXModel.class
	 * @param String : nomeColuna
	 * @param char : valorColuna
	 * @return
	 */
//	public List<T> listarPorValorDeColunaExato(
//		Class<T> classeTabela, String nomeColuna, char valorColuna) 
//	{
//		Session session = ConexaoHibernate.getSessao();
//		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
//		Root<T> root = criteria.from(classeTabela);
//		Expression<T> selectedColumn = root.get(nomeColuna);
//		criteria.select(root)
//			.where(criteriaBuilder.equal(selectedColumn, valorColuna));
//			
//		Query<T> query = session.createQuery(criteria);
//		List<T> results = query.getResultList();
//		return new ArrayList<T>(results);
//	}
	
	/**
	 * Funcoes e suas respectivas Overloads para poder selecionar entradas de 
	 * tabelas utilizando uma de suas colunas e seu valor como filtro.
	 * <br>
	 * ex: 
	 * <br>
	 * <br>
	 * HibernateMethods<HorasMesModel> instancia = new HibernateMethods()<HorasMesModel>
	 * <br>
	 * instancia.listarPorValorDeColima(HorasMesModel.class, "quantidade", 2)
	 * <br>
	 * <br>
	 * retorna todas as entradas da Tabela HorasMesModel com quantidade=2;
	 * 
	 * @param classeTabela: XXXModel.class
	 * @param String : nomeColuna
	 * @param boolean : valorColuna
	 * @return
	 */
	public List<T> listarPorValorDeColunaExato(
			Class<T> classeTabela, String nomeColuna, boolean valorColuna) 
		{
		Session session = DBConnection.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
		Root<T> root = criteria.from(classeTabela);
		Expression<T> selectedColumn = root.get(nomeColuna);
		criteria.select(root)
			.where(criteriaBuilder.equal(selectedColumn, valorColuna));
			
		Query<T> query = session.createQuery(criteria);
		List<T> results = query.getResultList();
		return new ArrayList<T>(results);
	}
	
	/**
	 * Funcoes e suas respectivas Overloads para poder selecionar entradas de 
	 * tabelas utilizando uma de suas colunas e seu valor como filtro.
	 * <br>
	 * ex: 
	 * <br>
	 * <br>
	 * HibernateMethods<HorasMesModel> instancia = new HibernateMethods()<HorasMesModel>
	 * <br>
	 * instancia.listarPorValorDeColima(HorasMesModel.class, "quantidade", 2)
	 * <br>
	 * <br>
	 * retorna todas as entradas da Tabela HorasMesModel com quantidade=2;
	 * 
	 * @param classeTabela: XXXModel.class
	 * @param String : nomeColuna
	 * @param Double : valorColuna
	 * @return
	 */
	public List<T> listarPorValorDeColunaExato(
		Class<T> classeTabela, String nomeColuna, Double valorColuna) 
	{
		Session session = DBConnection.getSession();
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = criteriaBuilder.createQuery(classeTabela);
		Root<T> root = criteria.from(classeTabela);
		Expression<T> selectedColumn = root.get(nomeColuna);
		criteria.select(root)
			.where(criteriaBuilder.equal(selectedColumn, valorColuna));
			
		Query<T> query = session.createQuery(criteria);
		List<T> results = query.getResultList();
		return new ArrayList<T>(results);
	}
	
}
