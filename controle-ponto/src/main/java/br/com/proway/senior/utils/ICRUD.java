package br.com.proway.senior.utils;

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

	Integer create(T entidade);

	T get(Class<T> classeTabela, int id) throws Exception;

	boolean update(T objetoAtualizado);

	boolean delete(Class<T> classeTabela,int id);
	
	List<T> getAll(Class<T> classeTabela);
	
	boolean deleteAll(String nomeTabela);

}
