package br.com.proway.senior.DAO;

import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @version Sprint5
 * 
 * Alteração da classe pontoDAO para implementar o CriteriaBuilder.
 * 
 */

public class PontoDAO implements ICRUD<Ponto> {

	private static PontoDAO instance;
	private Session session;

	/**
	 * Construtor que recebe a sess�o.
	 *
	 * @param session sess�o recebida como par�metro
	 */
	public PontoDAO(Session session) {
		this.session = session;
	}

	/**
	 * M�todo respons�vel por instanciar PontoDao recebendo a sess�o. A sess�o
	 * recebida passa pela checagem se � nula, caso positivo, uma nova sess�o �
	 * instanciada, caso negativo, a sess�o que j� est� aberta � retornada.
	 *
	 * @param session Sess�o ativa
	 * @return instance a instancia da sess�o.
	 */
	public static PontoDAO getInstance(Session session) {
		if (instance == null)
			instance = new PontoDAO(session);
		return instance;
	}

	public void insert(Ponto pontoASerInserido) {
		session.save(pontoASerInserido);
	}

	public boolean update(Ponto pontoASerAlterado) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			session.update(pontoASerAlterado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(Ponto pontoASerDeletado) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			session.delete(pontoASerDeletado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Ponto get(int index) {
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<Ponto> criteria = builder.createQuery(Ponto.class);
//		Query query = session.createQuery(criteria);
//		Root<Ponto> pontoRoot = criteria.from(Ponto.class);
//		CriteriaQuery<Ponto> rootQuery = criteria.select(pontoRoot);
//		Expression pontoId = (Expression) pontoRoot.get("id");
//		criteria.select(pontoRoot).where(builder.equal(pontoId, index));
//		return (Ponto) query.getSingleResult();
		return null;
	}

	public List<Ponto> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ponto> criteria = builder.createQuery(Ponto.class);
		criteria.from(Ponto.class);
		List<Ponto> selectedPontos = session.createQuery(criteria).getResultList();
		return selectedPontos;
	}

}
