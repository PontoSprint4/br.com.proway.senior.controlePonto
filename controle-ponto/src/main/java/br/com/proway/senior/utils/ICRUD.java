package br.com.proway.senior.utils;

import java.util.ArrayList;

import br.com.proway.senior.model.Jornada;

public interface ICRUD<T> {

    public void create (T obj);

    public ArrayList<T> readByIdJornada(int index) throws Exception;
    
    public T read(int index) throws Exception;

    public ArrayList<T> readAll();

    public boolean update(T obj, int index);

    public boolean delete(int index);

}
