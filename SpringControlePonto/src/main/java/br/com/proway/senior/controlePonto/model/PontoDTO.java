package br.com.proway.senior.controlePonto.model;

import java.time.LocalDateTime;

import javax.persistence.Column;

/**
 * DTO da entidade {@link Ponto} com os atributos relevantes para interface com outras
 * partes do sistema.
 *
 * 
 * @author Enzo
 * @author Willian
 *
 */
public class PontoDTO {
	
	private Integer idPonto;
    private Integer idPessoa;
	private LocalDateTime momentoPonto;

	/**
     * Construtor do {@link PontoDTO}, recebe um objeto {@link Ponto} vindo do BD.
     * 
     * @see DBConnection
     * 
     * @param Ponto : ponto
     */
	public PontoDTO(Ponto ponto) {
		this.idPonto = ponto.getIdPonto();
		this.idPessoa = ponto.getIdPessoa();
		this.momentoPonto = ponto.getMomentoPonto();
	}

	public Integer getIdPonto() {
		return idPonto;
	}

	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}	
	
}
