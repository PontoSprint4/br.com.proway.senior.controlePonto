package br.com.proway.senior.controlePonto.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public void deletar(int id) throws Exception {
		controllerTurno.delete(id);
	}
	
	public void alterar(int id, Turno turno) throws Exception {
		controllerTurno.update(id, turno);
	}

	public TurnoDTO obter(int id) throws Exception {
		Turno turno = controllerTurno.get(id);
		return(new TurnoDTO(turno));
	}
	
	public void adicionarPessoa(Integer idPessoa, int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);
		turno.setPessoasNoTurno(idPessoa);
		controllerTurno.update(idTurno, turno);
	}
	
	public void removerPessoa(Integer idPessoa, int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);
		turno.getPessoasNoTurno().remove(idPessoa);
		controllerTurno.update(idTurno, turno);
	}

	public ArrayList<Integer> obterTodasPessoasDoTurno(int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);
		ArrayList<Integer> listaPessoas = new ArrayList<Integer>();
		turno.getPessoasNoTurno().forEach((t) -> listaPessoas.add(t));
		return listaPessoas; 
	}
	
	public List<Turno> turnoDaPessoa(int idPessoa) throws Exception {
		List<Turno> listaDeTurnoDoBanco = controllerTurno.getAll();
		List<Turno> listaDeTurnosDaPessoa = new ArrayList<Turno>();
		for (Turno turno : listaDeTurnoDoBanco) {
			List<Integer> listaDePessoasDoTurno = turno.getPessoasNoTurno();
			for (Integer idDaPessoaNoTurno : listaDePessoasDoTurno) {
				if(idPessoa == idDaPessoaNoTurno) {
					listaDeTurnosDaPessoa.add(turno);
				}
			}
		}
		return listaDeTurnosDaPessoa;
	}
	
}
