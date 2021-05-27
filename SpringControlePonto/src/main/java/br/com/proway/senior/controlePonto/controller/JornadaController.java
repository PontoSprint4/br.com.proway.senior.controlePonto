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
 * @author Willian Kenji Nishizawa <willian.kenji@senior.com.br>
 * @author Vitor André Gehrke <vitor.gehrke@senior.com.br>
 * @author Leonardo Pereira <leonardo.pereira@senior.com.br>
 * @author Thiago Barbieri <thiago.barbieri@senior.com.br>
 * @author Enzo Muñoz Moura <enzo.moura@senior.com.br>	 * 
 */
public class JornadaController {

    private JornadaDAO dao;

    /**
     * Instancia um objeto da classe {@link JornadaDAO}, por meio do padrão de projeto Singleton.     
     * 
     * @see JornadaDAO
     * @see Refactoring.guro.com
     * @param session; (Session) onde ocorrera os processos de mudancas e consultas para com o banco de dados.
     */
    public JornadaController(Session session) {
        this.dao = JornadaDAO.getInstance(session);

    }

    /**
     * Insere um objeto da classe {@link Jornada} no banco de dados.
     *
     * @see Validadores
     * @see JornadaDAO 
     * @param jornadaASerInserida; (Jornada) a ser inserido no banco de dados.
     * @return idCadastrado; (Integer) ID do Jornada inserido no banco de dados.
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
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
     * Retorna um objeto da classe {@link Jornada}. Filtro, pelo ID desejado.
     *
     * @see Validadores
     * @see JornadaDAO
     * @param index; (int) ID do Jornada desejado.
     * @return jornada; (Jornada) desejado, contido no banco de dados.
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
     */
	public Jornada get(int index) throws Exception {
		if (Validadores.ehMenorIgualZeroOuNulo(index))
			throw new Exception("Id inexistente");
		return dao.get(index);
	}

	/**
     * Retorna todos os objetos da classe {@link Jornada} contidos no banco de dados.
     *
     * @see JornadaDAO
     * @return jornadas; (List<Jornada>) ou seja, todas as linha da tabela Jornada do banco de dados.
     */
	public List<Jornada> getAll() {
		return dao.getAll();
	}

	/**
	 * Atualiza um desejado objeto da classe {@link Jornada} no banco de dados. Conforme outro desejado objeto da classe {@link Jornada} que servira como espelho de dados.
	 * 
	 * @see Validadores
	 * @see JornadaDAO
	 * @param idJornada; (int) ID do Jornada que se deseja fazer uma atualizacao. 
	 * @param jornada; (Jornada) que contem em seus atributos, os valores que serao copiados para o que se deseja atualizar.   
	 * @return true; (boolean) atualizacao feita com sucesso
	 * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
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
		jornadaPersistida.setListaPonto(jornada.getListaPonto());
		dao.update(jornadaPersistida);
		return true;
	}
	
	/**
	 * Adiciona um novo objeto da classe {@link Ponto}, na lista de mesmos em um desejado objeto da classe {@link Jornada}.
	 * 
	 * @param idJornada; (int) ID do Jornada que recebera o Ponto em seu cadastro de mesmos. 
	 * @param ponto; (Ponto) que sera adicionado no dadastro de Pontos do Jornada (relacionamento). 
	 * @return true; (boolean) relacao feita com sucesso.
	 * @throws @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
	 */
	public boolean adicionarPontoNaJornada(int idJornada, Ponto ponto) throws Exception {
		if (Validadores.ehObjetoNulo(get(idJornada)))
			throw new Exception("A Jornada nao existe no banco de dados.");
		if (Validadores.ehObjetoNulo(ponto))
			throw new Exception("O Ponto nao pode ser nulo.");
		
		Jornada jornadaPersistida = dao.get(idJornada);
		jornadaPersistida.adicionaPontoNaLista(ponto);
		update(idJornada, jornadaPersistida);
		return true;
	}



    /**
     * Deleta um objeto da classe {@link Jornada} no banco de dados.
     *
     * @param id; (int) ID do Jornada que se deseja retirar no banco de dados.     * 
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
     */
    public boolean delete(int id) throws Exception {
    	if(Validadores.ehObjetoNulo(dao.get(id)))
    		throw new Exception("Id invalido.");
        return dao.delete(id);
    }
    
    /**
     * Deleta todos os objetos da classe {@link Jornada} persistidas no banco de dados.
     * 
     * @see JornadaDAO
     * @return true/false; boolean; retirada eficaz/nao.
     */
	public boolean deleteAll() {
		return dao.deleteAll();
	}
	
	/** 
	 * Calcula o tempo trabalhado (minutos) durante: uma 'Fechada' (numero par de pontos). E retorna este valor. @see Jornada
	 * 
	 * Caso a Jornada nao esteja 'Fechada' (numero impar de pontos), o retorno de horas trabalhadas sera, zero. @see Jornada  
	 * 
	 * @see FormatacaoDeTempo
	 * @see Validadores
	 * @param jornada; (Object) classe Jornada.
	 * @return 0; (int) status: objeto Jornada incompleto.
	 * @return minutosTrabalhados; (long) status: objeto Jornada completo; minutos trabalhados no mesmo.  
	 * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
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
	 * Calcula o tempo trabalhado (minutos) durante: um conjunto de 'Fechadas' (numero par de pontos). E retorna este valor. @see Jornada
	 * 
	 * Caso a Jornada nao esteja 'Fechada' (numero impar de pontos), o retorno de horas trabalhadas sera, zero. @see Jornada  
	 * 
	 * @param listaDeJornadas; (List) objetos Jornada.
	 * @return sum; (long) minutos trabalhados no objeto Jornada.	  
	 * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
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
     * <h1>Informa se o {@link Ponto} desejado se enquadra dentro do periodo
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
     * @see Validadores
     * @param ponto; (Object) classe Ponto.
     * @param turno; (Object) classe Turno;
     * @return true/false; (boolean) objeto Ponto pertence ao determinado objeto Turno/nao.
	 * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa. 
     */
    public boolean pontoDentroDoTurno(Ponto ponto, Turno turno) throws Exception {
    	return pontoDentroDoTurno(ponto, turno, 0);
    }
    
    /**
     * <h1>Informa se o {@link Ponto} desejado se enquadra dentro do periodo
     * estabelecido pelo {@link Turno}; com uma tolerancia de horario estabecida (minutos).</h1>
     * 
     * <br>
     * Existem dois casos:
     * <br>
     * Caso 1 : O turno nao cruza a meia noite.
     * <br>
     * Caso 2 : O turno cruza a meia noite.
     * <br>
     * 
     * @see Validadores
     * @param ponto; (Object) classe Ponto.
     * @param turno; (Object) classe Turno;
     * @param toleranciaMinutos; (int) flexibilidade de horario desejada (min).
     * @return true/false; (boolean) objeto Ponto pertence ao determinado objeto Turno/nao.
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa. 
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
     * Retorna uma lista de objetos da classe {@link Jornada} de um determinado objeto da classe {@link Pessoa}. 
     * 
     * @see Validadores
     * @see JornadaDAO
     * @param idPessoa; (int) atributo de um objeto Pessoa.
     * @return obterTodasJornadasDaPessoa; (List) objetos Jornada.
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
     */
    public List<Jornada> obterTodasJornadasDaPessoa(int idPessoa) throws Exception{
    	return dao.readByIdPessoa(idPessoa);
    }
    
    /**
     * Retorna uma lista de objetos da classe {@link Jornada} de um determinado objeto da classe {@link Pessoa}. Sendo filtradas, de acordo com um dia especifico e desejado.
     * 
     * @see Validadores
     * @see JornadaDAO
     * @param idPessoa; (int) atributo de um objeto Pessoa.
     * @param data; (LocalDate) definicao do dia desejado.
     * @return List<Jornada>; (List) objetos Jornada.
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
     */
    public List<Jornada> obterJornadasDoDia(int idPessoa, LocalDate data) throws Exception{
    	if(Validadores.ehObjetoNulo(data)) throw new Exception("Data invalida.");
    	return obterJornadasEntreDatas(idPessoa, data, data);
    }

    /**
     * Retorna uma lista de objetos da classe {@link Jornada} de um determinado objeto da classe {@link Pessoa}. Sendo filtradas, de acordo com um periodo entre desejadas datas (inclusive).
     * 
     * @see Validadores
     * @see JornadaDAO
     * @param idPessoa; (int) atributo de um objeto Pessoa.
     * @param inicio; (LocalDate) definicao do inicio do periodo desejado.
     * @param fim; (LocalDate) definição do fim do periodo desejado.
     * @return List<Jornada>; (List) objetos Jornada.
     * @throws Exception; feitas as devidas verificacoes para o correto prosseguimento do programa.
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
