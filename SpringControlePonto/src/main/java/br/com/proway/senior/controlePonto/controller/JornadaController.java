package br.com.proway.senior.controlePonto.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.Session;

import br.com.proway.senior.controlePonto.DAO.JornadaDAO;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.utils.FormatacaoDeTempo;
import br.com.proway.senior.utils.Validadores;

/**
 * Classe responsável por receber as requisições, tratá-las e encaminhar para o
 * DAO.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version sprint5
 */

public class JornadaController {

    private JornadaDAO dao;

    /**
     * Construtor que instancia um Controller fazendo uma integração com o
     * {@link JornadaDAO}, espera uma sessão como parametro para repassar ao
     * {@link JornadaDAO} retornando um Controller.
     *
     * @param session recebida como parâmetro.
     */
    public JornadaController(Session session) {
        this.dao = JornadaDAO.getInstance(session);

    }

    /**
     * Método que recebe um objeto {@link Jornada} e insere no banco.
     *
     * @param jornadaASerInserida Jornada que será inserida no banco.
     * @return id Id da Jornada inserida no banco.
     * @throws Exception 
     */
	public Integer create(Jornada jornadaASerInserida) throws Exception {
		if (Validadores.ehObjetoNulo(jornadaASerInserida.getTurno())) {
			throw new Exception("O turno da jornada nao pode ser nulo.");
		} 
		if (Validadores.ehObjetoNulo(jornadaASerInserida.getData())) {
			throw new Exception("A data da jornada nao pode ser nula.");
		}
		return dao.create(jornadaASerInserida);
	}

    /**
     * Método que retorna uma {@link Jornada} pelo Id.
     *
     * @param index Id da jornada a ser buscada.
     * @return retorna o objeto Jornada.
     */
	public Jornada get(int index) throws Exception {
		if (Validadores.ehMenorIgualZeroOuNulo(index))
			throw new Exception("Id inexistente");
		return dao.get(index);
	}

    /**
     * Método que retorna todas as {@link Jornada}.
     *
     * @return List de Jornadas
     */
	public List<Jornada> getAll() {
		return dao.getAll();
	}

	/**
	 * Metodo para atualizar um objeto do tipo {@link Jornada}, no banco de dados
	 * através do {@link JornadaDAO}. Caso tenha sucesso operacao retorna true.
	 * 
	 * @param idJornada
	 * @param jornada
	 * @return boolean
	 * @throws Exception
	 */
	public boolean update(int idJornada, Jornada jornada) throws Exception {
		if (Validadores.ehObjetoNulo(get(idJornada)))
			throw new Exception("A Jornada nao existe no banco de dados.");
		if (Validadores.ehObjetoNulo(jornada))
			throw new Exception("A Jornada nao pode ser nula.");
		Jornada jornadaPersistida = dao.get(idJornada);
		jornadaPersistida.setData(jornada.getData());
		jornadaPersistida.setTurno(jornada.getTurno());
		jornadaPersistida.setIdPessoa(jornada.getIdPessoa());
		jornadaPersistida.trocarListaPonto(jornada.getListaPonto());
		dao.update(jornadaPersistida);
		return true;
	}
	
	/**
	 * Adiciona um novo ponto na lista de pontos da jornada.
	 * 
	 * @param int : idJornada
	 * @param Ponto : ponto
	 * @return boolean para sucesso da operacao
	 * @throws Exception
	 */
	public boolean adicionarPontoNaJornada(int idJornada, Ponto ponto) throws Exception {
		if (Validadores.ehObjetoNulo(get(idJornada)))
			throw new Exception("A Jornada nao existe no banco de dados.");
		if (Validadores.ehObjetoNulo(ponto))
			throw new Exception("O Ponto nao pode ser nulo.");
		
		Jornada jornadaPersistida = dao.get(idJornada);
		jornadaPersistida.setListaPonto(ponto);
		update(idJornada, jornadaPersistida);
		return true;
	}


    /**
     * Método que deleta uma jornada.
     *
     * @param jornadaASerDeletada Jornada que será deletada do banco.
     * @throws Exception 
     */
    public boolean delete(int id) throws Exception {
    	if(Validadores.ehObjetoNulo(dao.get(id)))
    		throw new Exception("Id invalido.");
        return dao.delete(id);
    }
    
    /**
     * Metodo que deleta todas as jornadas persistidas no Banco de Dados, caso
     * tenha sucesso na operação retorna true.
     * .
     * @return boolean
     */
	public boolean deleteAll() {
		return dao.deleteAll();
	}
	
	/**
	 * Calcula o tempo trabalhado durante uma {@link Jornada} FECHADA (numero par de pontos)
	 * e retorna esse valor em MINUTOS.
	 * 
	 * Caso a jornada nao esteja fechada (numero impar de pontos), o retorno de horas trabalhadas vai ser ZERO.
	 * 
	 * @param Jornada : jornada
	 * @return long : Minutos trabalhados na Jornada.
	 * 
	 * @see FormatacaoDeTempo
	 * @throws Exception
	 */
	public long calcularHorasTrabalhadas(Jornada jornada) throws Exception {
		if(Validadores.ehObjetoNulo(jornada)) {
			throw(new Exception("Jornada esta nula!"));
		}
		
		List<Ponto> pontosDaJornada = jornada.getListaPonto();
		if (pontosDaJornada.size() % 2 != 0 ) {
			return 0;
			// throw(new Exception("A Jornada tem um numero impar de pontos e esta incompleta!"));
		}
		
		long minutosTrabalhados = 0;
		int pointer = 0;
				
		while (pointer < pontosDaJornada.size() - 1) {
			LocalDateTime pontoAtual = pontosDaJornada.get(pointer).getMomentoPonto();
			LocalDateTime proximo = pontosDaJornada.get(pointer+1).getMomentoPonto();
			
			minutosTrabalhados += FormatacaoDeTempo.tempoEntreRegistros(pontoAtual, proximo);
			pointer+=2; // Temos que considerar os intervalos entre pares de pontos!
		}
		return minutosTrabalhados;
	}
	
	/**
	 * Calcula o tempo trabalhado durante UM CONJUNTO DE {@link Jornada} FECHADAS (numero par de pontos)
	 * e retorna esse valor em MINUTOS.
	 * 
	 * Caso a jornada nao esteja fechada (numero impar de pontos), o retorno de horas trabalhadas vai ser ZERO.
	 * 
	 * @param List<Jornada> : jornadas
	 * @return long : Minutos trabalhados na Jornada.
	 * 
	 * @see FormatacaoDeTempo
	 * @throws Exception
	 */
	public long calcularHorasTrabalhadas(List<Jornada> listaDeJornadas) throws Exception{
		if(listaDeJornadas.isEmpty()) {
    		throw new Exception("Não possui jornadas fechadas.");
    	}
		long sum = 0;
		for (Jornada jornada : listaDeJornadas) {
			sum += calcularHorasTrabalhadas(jornada);
		}
		return sum;
	}
	
	/**
     * <h1>Funcao que informa se o {@link Ponto} desejado se enquadra dentro do periodo
     * estabelecido pelo {@link Turno}.</h1>
     * 
     * <br>
     * Existem dois casos:
     * <br>
     * Caso 1 : O turno nao cruza a meia noite.
     * <br>
     * Caso 2 : O turno cruza a meia noite.
     * <br>
     * 
     * @param ponto
     * @param turno
     * @return boolean : ponto pertence ao turno
	 * @throws Exception 
     */
    public boolean pontoDentroDoTurno(Ponto ponto, Turno turno) throws Exception {
    	return pontoDentroDoTurno(ponto, turno, 0);
    }
    
    /**
     * <h1>Funcao que informa se o {@link Ponto} desejado se enquadra dentro do periodo
     * estabelecido pelo {@link Turno} com uma tolerancia estabecida em MINUTOS.</h1>
     * 
     * <br>
     * Existem dois casos:
     * <br>
     * Caso 1 : O turno nao cruza a meia noite.
     * <br>
     * Caso 2 : O turno cruza a meia noite.
     * <br>
     * 
     * @param Ponto : ponto
     * @param Turno : turno
     * @param int: Tolerancia em minutos
     * @return boolean : ponto pertence ao turno
     * @throws Exception 
     */
    public boolean pontoDentroDoTurno(Ponto ponto, Turno turno, int toleranciaMinutos) throws Exception {
    	if(Validadores.ehObjetoNulo(ponto)) {
    		throw new Exception("Ponto invalido.");
    	}
    	if(Validadores.ehObjetoNulo(turno)) {
    		throw new Exception("Turno invalido.");
    	}
    	if(toleranciaMinutos < 0) {
    		throw new Exception("Tolerância invalida.");
    	}
    	// Manter apenas horario comercial
    	LocalDateTime horaPonto = ponto.getMomentoPonto();
    	
    	LocalTime horaPontoLocal = LocalTime.of(horaPonto.getHour(), horaPonto.getMinute(), horaPonto.getSecond());
    	LocalTime inicioTurno = turno.getHoraInicio();
    	LocalTime fimTurno = turno.getHoraFim();
    	
    	if (inicioTurno.isBefore(fimTurno)) {
    		// CASO1 - inicio e fim de Turno no mesmo dia
    		if (horaPontoLocal.isBefore(fimTurno.plusMinutes(toleranciaMinutos)) && 
        			horaPontoLocal.isAfter(inicioTurno.minusMinutes(toleranciaMinutos))) {
        		return true;
        	}
    		return false;
    	}
    	else {
    		// CASO 2 - inicio e fim de turno em dias diferentes (madrugada)
    		// Antes da meia noite
    		if (horaPontoLocal.isAfter(inicioTurno.minusMinutes(toleranciaMinutos)) && 
        			horaPontoLocal.isAfter(fimTurno.plusMinutes(toleranciaMinutos))) {
        		return true;
        	}
    		// Depois da meia noite
    		if (horaPontoLocal.isBefore(inicioTurno.minusMinutes(toleranciaMinutos)) && 
        			horaPontoLocal.isBefore(fimTurno.plusMinutes(toleranciaMinutos))) {
        		return true;
        	}
    		return false;	
    	}	
    }
    
    /**
     * Retorna uma lista de todas as jornadas da pessoa.
     * 
     * @param idPessoa
     * @return obterTodasJornadasDaPessoa : lista de jornadas da pessoa consultada
     * @throws Exception
     */
    public List<Jornada> obterTodasJornadasDaPessoa(int idPessoa) throws Exception{
    	return dao.readByIdPessoa(idPessoa);
    }
    
    /**
     * Retorna uma lista de jornadas do dia desejado de uma pessoa.
     * 
     * @param idPessoa
     * @param data
     * @return List<Jornada>
     * @throws Exception
     */
    public List<Jornada> obterJornadasDoDia(int idPessoa, LocalDate data) throws Exception{
    	if(Validadores.ehObjetoNulo(data)) throw new Exception("Data invalida.");
    	return obterJornadasEntreDatas(idPessoa, data, data);
    }

    /**
     * Retorna uma lista de jornadas num intervalo de datas (inclusive) de uma pessoa.
     * 
     * @param idPessoa
     * @param inicio
     * @param fim
     * @return List<Jornada>
     * @throws Exception
     */
    public List<Jornada> obterJornadasEntreDatas(int idPessoa, LocalDate inicio, LocalDate fim) throws Exception{
    	if(Validadores.ehObjetoNulo(inicio)) 
    		throw new Exception("Data inicio invalida.");
    	if(Validadores.ehObjetoNulo(fim)) 
    		throw new Exception("Data fim invalida.");
    	
    	List<Jornada> retorno = dao.obterJornadasEntreDatas(idPessoa, inicio, fim);
    	return retorno;
    }
    
}
