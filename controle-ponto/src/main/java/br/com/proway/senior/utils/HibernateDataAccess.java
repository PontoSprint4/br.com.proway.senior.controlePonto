package br.com.proway.senior.utils;

import java.util.ArrayList;

import org.hibernate.SessionFactory;

import br.com.proway.senior.dbpersistence.DBConnection;

/**
 * Classe abstrata para o CRUD.
 * 
 * As classes poderão herdar essa classe para implementar os métodos que
 * necessitarem.
 * 
 * @author Lucas W
 * @author Tharlys D <tharlys.souza@outlook.com>
 * @author Vitor A
 *
 */
public abstract class HibernateDataAccess {

	protected SessionFactory sessionFactory;

	public HibernateDataAccess() {
		this.sessionFactory = DBConnection.getSessionFactory();
	}

	public abstract void insert(Object object);

	public abstract void update(Object object);

	public abstract void delete(Object object);
	
	public abstract Object get(int index);

	public abstract <T> ArrayList<T> getAll();

}
