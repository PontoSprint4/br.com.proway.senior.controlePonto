package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.JornadaDTO;

public class JornadaAPI {
	JornadaController controller = new JornadaController(DBConnection.getSession());
	
	public List<JornadaDTO> converteListaParaDTO ( List<Jornada> jornadasObtidas ) throws Exception{
		List<JornadaDTO> saida = new ArrayList<JornadaDTO>();
		for(Jornada jornada : jornadasObtidas) {
			saida.add(new JornadaDTO(jornada));
		}
		return saida;
	}
	
	public JornadaDTO getJornada(Integer idJornada) throws Exception {
		return new JornadaDTO(controller.get(idJornada));

	}

	public List<JornadaDTO> getTodasJornadasDaPessoa(Integer idPessoa) throws Exception{
		List<Jornada> jornadasObtidas = controller.obterTodasJornadasDaPessoa(idPessoa);
		return converteListaParaDTO(jornadasObtidas);
	}
	
	public List<JornadaDTO> getJornadasDaPessoaPorPeriodo(int idPessoa, LocalDate inicio, LocalDate fim) throws Exception{
		List<Jornada> jornadasObtidas = controller.obterJornadasEntreDatas(idPessoa, inicio, fim);
		return converteListaParaDTO(jornadasObtidas);
	}
	
	public long getHorasTrabalhadasDaPessoaPorPeriodo(int idPessoa, LocalDate inicio, LocalDate fim) throws Exception{
		List<Jornada> jornadasObtidas = controller.obterJornadasEntreDatas(idPessoa, inicio, fim);
		List<JornadaDTO> jornadasDTO = converteListaParaDTO(jornadasObtidas);
		long sum = 0;
		for (JornadaDTO jornada : jornadasDTO) {
			sum += jornada.getMinutosTrabalhados();
		}
		return sum;
	}
	
	public JornadaDTO getJornadaDaPessoaNoDia(int idPessoa, LocalDate data) throws Exception {
		return new JornadaDTO(controller.obterJornadasDoDia(idPessoa, data).get(0));
	}

}
