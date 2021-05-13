package br.com.proway.senior.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.utils.ICRUD;

public class PontoController {

	PontoDAO pdao = PontoDAO.getInstance();

	@Override
	public void create(Ponto obj) {

	}

	public void create(Jornada jornada) throws Exception {
		try {
			pdao.create(jornada);
		} catch (Exception e) {
			e.getMessage();
		}
	}

	@Override
	public ArrayList<Ponto> readByIdJornada(int index) throws Exception {
		if (pdao.readByIdJornada(index) != null) {
			return pdao.readByIdJornada(index);
		}
		throw new Exception("Index Inexistente");
	}

	@Override
	public ArrayList<Ponto> readAll() {
		return pdao.readAll();
	}

//	@Override
//	public boolean update(Ponto obj, int index) {
//		return false;
//	}
//
//	@Override
//	public boolean delete(int index) {
//		try {
//			pdao.delete(index);
//		} catch (Exception e) {
//			e.getMessage();
//		}
//		return true;
//	}
//
//	@Override
//	public Ponto read(int index) throws Exception {
//		if (pdao.read(index) != null) {
//			return pdao.read(index);
//		}
//		throw new Exception("Index Inexistente");
//	}
//
//}
