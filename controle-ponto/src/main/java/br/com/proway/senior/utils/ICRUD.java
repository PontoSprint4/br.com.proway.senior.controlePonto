package br.com.proway.senior.utils;

import java.util.ArrayList;

public interface ICRUD<T> {

    public void create (T obj);

    public T readById(int index);

    public ArrayList<T> readAll();

    public boolean update(T obj, int index);

    public boolean delete(int index);

}
