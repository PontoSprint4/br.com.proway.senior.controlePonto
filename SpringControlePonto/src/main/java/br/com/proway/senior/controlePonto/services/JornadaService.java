package br.com.proway.senior.controlePonto.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

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
	public final int LIMITE_TEMPO = 10;
	
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
	
	public List<JornadaDTO> getAll() throws Exception{
		return controllerDTOJornada.getAll();
	}
	
	// Filtrando Jornadas
	
	public ArrayList<JornadaDTO> todasJornadas(Integer idPessoa) throws Exception {
		return (ArrayList<JornadaDTO>) 
				controllerDTOJornada.getTodasJornadasDaPessoa(idPessoa);
	}
	
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
		Integer toleranciaDeHorarioEmMinutos = 30;
		
		// Obtem o turno que possui idPessoa
		TurnoService serviceTurno = new TurnoService();
		Turno turno = serviceTurno.turnoDaPessoa(idPessoa);
		LocalTime inicioTurno = turno.getHoraInicio();
		
		// Informacoes do Ponto
		LocalDate dataPonto = ponto.getMomentoPonto().toLocalDate();
		LocalTime horaPonto = ponto.getMomentoPonto().toLocalTime();
		
		// Esse ponto esta sendo marcado em um horario permitido?
		if(!controllerJornada.pontoDentroDoTurno(ponto, turno, toleranciaDeHorarioEmMinutos)) {
			throw new Exception("Ponto fora do Turno considerando uma tolerancia de "+toleranciaDeHorarioEmMinutos);
		}
		
		// Verifica se o Turno pertence a jornada do dia atual ou anterior (caso madrugada);
		ArrayList<Jornada> jornadasDoDia;
		if(horaPonto.isAfter(inicioTurno)) {
			jornadasDoDia = (ArrayList<Jornada>) 
					controllerJornada.obterJornadasDoDia(idPessoa, dataPonto);
		}
		// Se o Ponto foi marcado em um horario anterior ao inicio do turno
		// Ele pertence a jornada anterior (caso madrugada)
		else {
			jornadasDoDia = (ArrayList<Jornada>) 
					controllerJornada.obterJornadasDoDia(idPessoa, dataPonto.minusDays(1));
		}
		
		switch(jornadasDoDia.size()) {
			case 0 : {
				int pontoId = controllerPonto.create(ponto);

				// Instancia uma nova Jornada
				Jornada jornada = new Jornada(LocalDate.now(), idPessoa, turno);
				Integer idJornada = controllerJornada.create(jornada);
				
				// Instancia a jornada
				controllerJornada.adicionarPontoNaJornada(
						idJornada, controllerPonto.get(pontoId));
				break;
			} 
			case 1 : {
				// Verifica pontos sucessivos
				Jornada jornada = jornadasDoDia.get(0);
				Integer indice_ponto = jornada.listaPonto.size() - 1;
				
				if(indice_ponto >=0) {
					LocalDateTime momentoPonto = jornada.getListaPonto().get(indice_ponto).getMomentoPonto();
					if(ChronoUnit.SECONDS.between(momentoPonto.toLocalTime(), horaPonto) < LIMITE_TEMPO){
					
					throw new Exception("Ponto anterior foi batido menos do que "+LIMITE_TEMPO+" segundos atras!");
				}}
				
				// Marca o ponto
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
