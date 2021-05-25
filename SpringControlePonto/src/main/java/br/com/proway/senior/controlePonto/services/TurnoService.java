package br.com.proway.senior.controlePonto.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.model.TurnoDTO;

@Service
public class TurnoService {
	
	TurnoController controllerTurno = new TurnoController(DBConnection.getSession());
	
	public ArrayList<TurnoDTO> obterTodos(){
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) controllerTurno.getAll();
		ArrayList<TurnoDTO> listaDTO = new ArrayList<TurnoDTO>();
		
		for(Turno turno : listaTurnos) {
			listaDTO.add(new TurnoDTO(turno));
		}
		
		return listaDTO;
	}
	
	public void salvar(Turno turno) {
		controllerTurno.create(turno);
	}
}
