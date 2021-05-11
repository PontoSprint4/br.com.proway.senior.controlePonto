package br.com.proway.senior.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.utils.ICRUD;

public class PontoController implements ICRUD<Ponto> {

    PontoDAO pdao = PontoDAO.getInstance();

    @Override
    public void create(Ponto obj) {

    }

    @Override
    public Ponto readById(int index) {
        return null;
    }

    @Override
    public ArrayList<Ponto> readAll() {
        return null;
    }

    @Override
    public boolean update(Ponto obj, int index) {
        return false;
    }

    @Override
    public boolean delete(int index) {
        return false;
    }
}
