package br.com.proway.senior.utils;

import java.util.List;

public interface ICRUD<T> {

	void insert(T obj);

	T get(int index) throws Exception;

	List<T> getAll();

	boolean update(T obj);

	boolean delete(T obj);

}
