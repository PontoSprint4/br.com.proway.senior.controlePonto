package br.com.proway.senior.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

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
	
	public List<PessoaDoPonto> getAll(){
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<PessoaDoPonto> criteria = builder.createQuery(PessoaDoPonto.class);
		criteria.from(PessoaDoPonto.class);
		return session.createQuery(criteria).getResultList();
	}
	
	public PessoaDoPonto find(int id) {
		return session.get(PessoaDoPonto.class, id);
		
//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<PessoaDoPonto> criteria = builder.createQuery(PessoaDoPonto.class);
//		Root<PessoaDoPonto> root = criteria.from(PessoaDoPonto.class);
//		Query query = session.createQuery(criteria);
//
//		CriteriaQuery<PessoaDoPonto> rootQuery = criteria.select(root);
//		
//		criteria.select(root).where(builder.equal(, id));		
//		return (PessoaDoPonto) query.getSingleResult();
	}
}
