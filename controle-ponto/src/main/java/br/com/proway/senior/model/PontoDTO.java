package br.com.proway.senior.model;

import java.time.LocalDateTime;

public class PontoDTO {
	
	private Integer idPonto;
	private LocalDateTime momentoPonto;

	public PontoDTO(Ponto ponto) {
		this.idPonto = ponto.getIdPonto();
		this.momentoPonto = ponto.getMomentoPonto();
	}

	public Integer getIdPonto() {
		return idPonto;
	}

	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}	
	
}
