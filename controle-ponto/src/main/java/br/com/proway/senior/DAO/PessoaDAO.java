package br.com.proway.senior.DAO;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;

public class PessoaDAO {
	
	private static PessoaDAO instance;
	private Session session;
	
	public static PessoaDAO getInstance(Session session) {
		if (instance == null) {
			instance = new PessoaDAO(session);
		}
		return instance;
	}
	
	private PessoaDAO(Session session) {
		this.session = session;
	}

	public void create(PessoaDoPonto pessoa) {
		session.getTransaction();
		session.beginTransaction();
		session.save(pessoa);
		session.getTransaction().commit();
	}
	
	public PessoaDoPonto find(int id) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PessoaDoPonto> criteria = builder.createQuery(PessoaDoPonto.class);
		Root<PessoaDoPonto> root = criteria.from(PessoaDoPonto.class);
		Query query = session.createQuery(criteria);
		
		CriteriaQuery<PessoaDoPonto> rootQuery = criteria.select(root);
		javax.persistence.criteria.Expression<Object> idPessoa =  root.get("id");
		
		criteria.select(root).where(builder.equal(idPessoa, id));		
		return (PessoaDoPonto) query.setMaxResults(1).getSingleResult();
	}
}
