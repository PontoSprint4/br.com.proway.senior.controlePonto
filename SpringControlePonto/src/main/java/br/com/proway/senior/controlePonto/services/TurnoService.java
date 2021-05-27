package br.com.proway.senior.controlePonto.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.ListaDePessoas;
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
	
	/**
	 * Metodo de adicionar Pessoas na lista de pessoa do tuno.
	 * 
	 * @param objetoListaPessoa
	 * @param idTurno
	 * @throws Exception
	 */
	public void adicionarPessoa(ListaDePessoas objetoListaPessoa, int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);	
		for(Integer id : objetoListaPessoa.listaDeIdPessoa) {
			if(turno.getPessoasNoTurno().contains(id)) {
				continue;
			}
			turno.adicionaPessoaNoTurno(id);			
		}		
		controllerTurno.update(idTurno, turno);
	}
	
	public void removerPessoa(ListaDePessoas objetoListaPessoa, int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);
		for(Integer id : objetoListaPessoa.listaDeIdPessoa) {
			//if(turno.getPessoasNoTurno().contains(id)) {
				turno.getPessoasNoTurno().remove(id);
			//}									
		}
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
