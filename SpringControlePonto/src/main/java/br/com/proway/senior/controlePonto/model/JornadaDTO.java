package br.com.proway.senior.controlePonto.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.proway.senior.controller.JornadaAPI;
import br.com.proway.senior.controller.JornadaController;
import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.enums.EstadosJornada;

/**
 * DTO da entidade {@link Jornada} com os atributos relevantes para interface com outras
 * partes do sistema.
 *
 * 
 * @author Enzo
 * @author Willian
 *
 */
public class JornadaDTO {
	// Do objeto Jornada
    private int id;
    private LocalDate data; 
    private Turno turno;    // Possui as informacoes de comeco e fim do turno
    public List<Ponto> listaPonto = new ArrayList<Ponto>();        
    private int idPessoa;
    
    // Auxiliares proprias do DTO
    public short dia;
    public short mes;
    public short ano;
    
    public long minutosTrabalhados;
    
    public EstadosJornada estado; // Ainda nao muda!
    
    /**
     * Construtor do {@link JornadaDTO}, recebe um objeto {@link Jornada} vindo do BD.
     * 
     * O objeto JornadaDTO compoe a estrutura de dados que vai para fora do nosso sistema.
     * 
     * @see DBConnection
     * 
     * @param Jornada : jornada
     * @throws Exception 
     */
    public JornadaDTO(Jornada jornada) throws Exception {
        this.id = jornada.getId();
        this.data = jornada.getData();
        this.turno = jornada.getTurno();
        this.listaPonto = jornada.getListaPonto();
        this.idPessoa = jornada.getIdPessoa(); 
        
        this.dia = (short) this.data.getDayOfMonth();
        this.mes = (short) this.data.getMonthValue();
        this.ano = (short) this.data.getYear();
        
        if(this.listaPonto.size() % 2 == 0) {
        	this.minutosTrabalhados = new JornadaController(DBConnection.getSession()).calcularHorasTrabalhadas(jornada);
        	this.estado = EstadosJornada.FECHADO;
        }
        else {
        	this.minutosTrabalhados = 0;
        	this.estado = EstadosJornada.ABERTO;
        }
    }

	public int getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public Turno getTurno() {
		return turno;
	}

	public List<Ponto> getListaPonto() {
		return listaPonto;
	}

	public int getIdPessoa() {
		return idPessoa;
	} 
	
	public short getDia() {
		return dia;
	}

	public short getMes() {
		return mes;
	}

	public short getAno() {
		return ano;
	}

	public long getMinutosTrabalhados() {
		return minutosTrabalhados;
	}

	public EstadosJornada getEstado() {
		return estado;
	}
	
}
