package br.com.proway.senior.controlePonto.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.PontoDTO;

@Service
public class PontoService {
	PontoController controllerPonto = new PontoController(DBConnection.getSession());

	public Integer createPonto(Ponto Ponto) throws Exception {
		return controllerPonto.create(Ponto);
	}

	public PontoDTO getPonto(Integer idPonto) throws Exception {
		return new PontoDTO(controllerPonto.get(idPonto));
	}

	public boolean updatePonto(Integer idPonto, Ponto Ponto) throws Exception {
		return controllerPonto.update(Ponto, idPonto);
	}

	public boolean deletePonto(Integer idPonto) throws Exception {
		return controllerPonto.delete(idPonto);
	}
	
	public List<PontoDTO> getAll() {
		List<Ponto> listaPontos = controllerPonto.getAll();
		List<PontoDTO> listaDTO = new ArrayList<PontoDTO>();
		
		for(Ponto ponto : listaPontos) {
			listaDTO.add(new PontoDTO(ponto));
		}
		
		return listaDTO;
	}
}
