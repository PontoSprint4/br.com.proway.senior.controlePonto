package br.com.proway.senior.model;

import java.time.LocalDateTime;

import br.com.proway.senior.model.interfaces.IJornada;

public class Ponto {
	
	private Integer idPonto;
	private Integer idJornada; 	// FK  idJornada
	private LocalDateTime momentoPonto;  // registroPonto
	
	public Ponto(Integer idPonto, LocalDateTime momentoPonto) {
		this.idPonto = idPonto;
		this.momentoPonto = momentoPonto;
	}
	
	public int getIdPonto() {
		return idPonto;
	}
	
	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}
	
	public Integer getIdJornada() {
		return idJornada;
	}	
	
	public void setIdJornada(int idJornada) { // idJornada
		this.idJornada = idJornada;
	}
	
	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}
	
	public void setMomentoPonto(LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
	}
	
	@Override
	public String toString() {
		return "Ponto [idPonto=" + idPonto + ", idJornada=" + idJornada + ", momentoPonto=" + momentoPonto + "]";
	}
	
}
