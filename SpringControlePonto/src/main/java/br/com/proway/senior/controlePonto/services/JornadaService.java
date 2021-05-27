package br.com.proway.senior.controlePonto.services;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import br.com.proway.senior.controlePonto.controller.JornadaController;
import br.com.proway.senior.controlePonto.controller.JornadaDTOController;
import br.com.proway.senior.controlePonto.controller.PontoController;
import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;

@Service
public class JornadaService {
	JornadaController controllerJornada = 
			new JornadaController(DBConnection.getSession());
	PontoController controllerPonto = 
			new PontoController(DBConnection.getSession());
	TurnoController controllerTurno = 
			new TurnoController(DBConnection.getSession());
	
	JornadaDTOController controllerDTOJornada = 
			new JornadaDTOController();
	
	//CRUDZAO
	public Integer createJornada(Jornada jornada) throws Exception {
		Integer idTurno = jornada.getTurno().getId();
		Turno turno = new TurnoController(DBConnection.getSession()).get(idTurno);
		jornada.setTurno(turno);
		return controllerJornada.create(jornada);
	}
	
	public JornadaDTO getJornada(Integer idJornada) throws Exception {
		return controllerDTOJornada.getJornada(idJornada);
	}
	
	public boolean updateJornada(Integer idJornada, Jornada jornada) 
		throws Exception 
	{
		return controllerJornada.update(idJornada, jornada);
	}
	
	public boolean deleteJornada(Integer idJornada) throws Exception {
		return controllerJornada.delete(idJornada);
	}
	
	public ArrayList<JornadaDTO> todasJornadas(Integer idPessoa) throws Exception {
		return (ArrayList<JornadaDTO>) 
				controllerDTOJornada.getTodasJornadasDaPessoa(idPessoa);
	}
	
	// Filtrando Jornadas
	
	public ArrayList<JornadaDTO> jornadasPorPeriodo(
			Integer idPessoa, LocalDate inicio, LocalDate fim
	) throws Exception
	{
		return (ArrayList<JornadaDTO>) 
				controllerDTOJornada.getJornadasDaPessoaPorPeriodo(
						idPessoa, inicio, fim
				);
	}
	
	public long minutosTrabalhadosNoPeriodo(
			Integer idPessoa, LocalDate inicio, LocalDate fim
	) throws Exception
	{
		return controllerDTOJornada.getHorasTrabalhadasDaPessoaPorPeriodo(
				idPessoa, inicio, fim
		);
	}
	
	public JornadaDTO jornadaDoDia(Integer idPessoa) throws Exception {
		LocalDate dataAtual = LocalDate.now();
		ArrayList<Jornada> retorno = (ArrayList<Jornada>) 
				controllerJornada.obterJornadasDoDia(idPessoa, dataAtual);
		
		switch(retorno.size()) {
			case 1 : {
				return controllerDTOJornada.getJornada(retorno.get(0).getId());
			}
			default: {
				throw new Exception("Nenhuma ou Multiplas Jornadas nesse dia!");
			}
		}
	}
	
	// ACTIONS
	
	public void marcarPonto(Integer idPessoa, Ponto ponto) throws Exception {
		LocalDate dataAtual = ponto.getMomentoPonto().toLocalDate();
		
		ArrayList<Jornada> jornadasDoDia = (ArrayList<Jornada>) 
				controllerJornada.obterJornadasDoDia(idPessoa, dataAtual);
		
		switch(jornadasDoDia.size()) {
			case 0 : {
				int pontoId = controllerPonto.create(ponto);
				
				// Obtem o turno que possui idPessoa
				TurnoService serviceTurno = new TurnoService();
				
				ArrayList<Turno> turnosDaPessoa = (ArrayList<Turno>) serviceTurno.turnoDaPessoa(idPessoa);
				
				if(turnosDaPessoa.size()==0) throw new Exception("Sem turnos registrados para essa Pessoa!");
				
				Turno turno = serviceTurno.turnoDaPessoa(idPessoa).get(0);
				// Instancia uma nova Jornada
				Jornada jornada = new Jornada(LocalDate.now(), idPessoa, turno);
				Integer idJornada = controllerJornada.create(jornada);
				
				// Instancia a jornada
				controllerJornada.adicionarPontoNaJornada(idJornada, controllerPonto.get(pontoId));
				break;
			} 
			case 1 : {
				int pontoId = controllerPonto.create(ponto);
				controllerJornada.adicionarPontoNaJornada(
						jornadasDoDia.get(0).getId(),
						controllerPonto.get(pontoId)
				);
				break;
			}
			default: {
				throw new Exception("Mais do que uma jornada armazenada no dia!");
			}
		}
	}
}
