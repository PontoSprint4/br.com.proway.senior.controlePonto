package br.com.proway.senior.model.interfaces;

import java.util.List;

/**
 * Interface ICRUD Generica.
 * <p>
 * Utilizada para padronizacao e implementacao de metodos CRUD com o banco de
 * dados
 *
 * @author Lucas W <lucas.walim@senior.com.br>
 * @author Tharlys D <tharlys.dias@senior.com.br>
 * @author Vitor A <vitor.gehrke@senior.com.br>
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @param <T>
 */
public interface ICRUD<T> {

	void insert(T obj);

	T get(int index) throws Exception;

	List<T> getAll();

	boolean update(T obj);

	boolean delete(T obj);

}
