package br.com.proway.senior.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
	    
    private int id;
    private LocalDate data; 
    private Turno turno;    
    public List<Ponto> listaPonto = new ArrayList<Ponto>();        
    private int idPessoa;
    
    /**
     * Construtor do {@link JornadaDTO}, recebe um objeto {@link Jornada} vindo do BD.
     * 
     * @see DBConnection
     * 
     * @param Jornada : jornada
     */
    public JornadaDTO(Jornada jornada) {
        this.id = jornada.getId();
        this.data = jornada.getData();
        this.turno = jornada.getTurno();
        this.listaPonto = jornada.getListaPonto();
        this.idPessoa = jornada.getIdPessoa();        
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
	
}
