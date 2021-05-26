package br.com.proway.senior.controlePonto.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;

/** 
 * @author Willian Kenji Nishizawa <willian.kenji@senior.com.br>
 * @author Vitor André Gehrke <vitor.gehrke@senior.com.br>
 * @author Leonardo Pereira <leonardo.pereira@senior.com.br>
 * @author Thiago Barbieri <thiago.barbieri@senior.com.br>
 * @author Enzo Muñoz Moura <enzo.moura@senior.com.br>	 * 
 */
public class JornadaDTOController {
	
	
	
	JornadaController controller = new JornadaController(DBConnection.getSession());
	
	/**
	 * Coverte uma lista de objetos da classe {@link Jornada} para uma lista de objetos da classe {@link JornadaDTO}.
	 * 
	 * @see Jornada
	 * @see JornadaDTO
	 * @param jornadasObtidas; (List) objetos Jornada.
	 * @return saida; (List) objetos JornadaDTO.
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public List<JornadaDTO> converteListaParaDTO ( List<Jornada> jornadasObtidas ) throws Exception{
		List<JornadaDTO> saida = new ArrayList<JornadaDTO>();
		for(Jornada jornada : jornadasObtidas) {
			saida.add(new JornadaDTO(jornada));
		}
		return saida;
	}
	
	/**
	 * Procura um objeto da classe {@link Jornada} desejado (por ID), e ao encontra-lo, retorna a sua versao {@link JornadaDTO}.
	 * 
	 * @see JordadaController
	 * @see Jornda
	 * @see JornadaDTO 
	 * @param idJornada; (Integer) ID do objeto Jornada que se deseja sabe sobre.  
	 * @return jorndaDTO; (JorndaDTO) 
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public JornadaDTO getJornada(Integer idJornada) throws Exception {
		return new JornadaDTO(controller.get(idJornada));

	}

	/**
	 * Procura os objeto da classe {@link Jornada} desejados (por ID da pessoa), e ao encontra-los, chama o método 'ConverteListaParaDTO' referente aos mesmos (retorna as suas versoes {@link JornadaDTO}).
	 * 
	 * @param idPessoa; (Integer) referente ao dono dos Jornadas.
	 * @return jornada; (List) todos referentes a pessoa especificada.
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public List<JornadaDTO> getTodasJornadasDaPessoa(Integer idPessoa) throws Exception{
		List<Jornada> jornadasObtidas = controller.obterTodasJornadasDaPessoa(idPessoa);
		return converteListaParaDTO(jornadasObtidas);
	}
	
	/**
	 * Consulta e retorna todos os objetos da classe {@link Jornada} persistidos no banco de dados, conforme filtro.
	 * Filtro: ID do dono das mesmas e período em que elas se encontram. conforme desejado. 
	 * 
	 * @param idPessoa; (int) referente ao dono dos Jornadas.
	 * @param inicio; (LocalDate) inicio do perido desejado para a realizacao da consulta.
	 * @param fim; (LocalDate) fim do perido desejado para a realizacao da consulta.
	 * @return jornadas; (Jornada)
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public List<JornadaDTO> getJornadasDaPessoaPorPeriodo(int idPessoa, LocalDate inicio, LocalDate fim) throws Exception{
		List<Jornada> jornadasObtidas = controller.obterJornadasEntreDatas(idPessoa, inicio, fim);
		return converteListaParaDTO(jornadasObtidas);
	}
	
	/**
	 * Calcula e retorna o total de horas trabalhadas de determinada pessoa, conforme desejado periodo de tempo.
	 * 
	 * @see Jornada
	 * @see JornadaDTO
	 * @see JornadaController
	 * @param idPessoa; (int) referente ao dono dos Jornadas, e por consequencia, do total de horas trabalhadas. 
	 * @param inicio; (LocalDate) inicio do perido desejado para a realizacao da consulta.
	 * @param fim; (LocalDate) fim do perido desejado para a realizacao da consulta.
	 * @return sum; (long) total de horas trabalhadas.
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public long getHorasTrabalhadasDaPessoaPorPeriodo(int idPessoa, LocalDate inicio, LocalDate fim) throws Exception{
		List<Jornada> jornadasObtidas = controller.obterJornadasEntreDatas(idPessoa, inicio, fim);
		List<JornadaDTO> jornadasDTO = converteListaParaDTO(jornadasObtidas);
		long sum = 0;
		for (JornadaDTO jornada : jornadasDTO) {
			sum += jornada.getMinutosTrabalhados();
		}
		return sum;
	}
	
	/**
	 * Consulta e retorna todos os objetos da classe {@link Jornada} persistidos no banco de dados. Filtro: dia do {@link Jornada}.
	 * 
	 * @see JornadaDTO
	 * @see JornadaController
	 * @param idPessoa; (int) referente ao dono do Jornada. 
	 * @param data; (LocalDate) referente ao Jornada desejado.
	 * @return jornada; (Jornada) desejado.
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public JornadaDTO getJornadaDaPessoaNoDia(int idPessoa, LocalDate data) throws Exception {
		List<Jornada> retorno = controller.obterJornadasDoDia(idPessoa, data);
		switch(retorno.size()) {
		case 0: throw new Exception("Sem jornadas nesse dia!");
		default: 
			return new JornadaDTO(retorno.get(0));
		}
		
	}

}
