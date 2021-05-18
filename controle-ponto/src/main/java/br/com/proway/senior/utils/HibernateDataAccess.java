package br.com.proway.senior.utils;

import java.util.List;

import org.hibernate.SessionFactory;

import br.com.proway.senior.dbpersistence.DBConnection;

/**
 * Classe abstrata para o CRUD.
 * 
 * As classes poderão herdar essa classe para implementar os métodos que
 * necessitarem.
 * 
 * @author Lucas W <lucas.walim@senior.com.br>
 * @author Tharlys D <tharlys.dias@senior.com.br>
 * @author Vitor A <vitor.gehrke@senior.com.br>
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 *
 */
@Deprecated
public abstract class HibernateDataAccess {

	protected SessionFactory sessionFactory;

	public HibernateDataAccess() {
		this.sessionFactory = DBConnection.getSessionFactory();
	}

	public abstract void insert(Object object);

	public abstract void update(Object object);

	public abstract void delete(Object object);

	public abstract Object get(int index);

	public abstract <T> List<T> getAll();

}
