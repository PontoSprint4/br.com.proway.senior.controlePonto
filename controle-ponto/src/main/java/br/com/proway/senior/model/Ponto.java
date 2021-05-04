package br.com.proway.senior.model;

import java.time.LocalDateTime;

import br.com.proway.senior.model.interfaces.IJornada;

public class Ponto {
	
	private Integer idPonto;
	private Integer ceJornada; // ce = Chave Estrangeira ID
	private LocalDateTime momentoPonto;
	
	public Ponto(Integer idPonto, LocalDateTime momentoPonto) {
		this.idPonto = idPonto;
		this.momentoPonto = momentoPonto;
	}
	
	public Ponto() {		
	}
		
	public int getIdPonto() {
		return idPonto;
	}
	
	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}
	
	public Integer getCeJornada() {
		return ceJornada;
	}	
	
	public void setCeJornada(IJornada iJornada) {
		this.ceJornada = iJornada.getIdJornada();
	}
	
	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}
	
	public void setMomentoPonto(LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
	}
	
	@Override
	public String toString() {
		return "Ponto [idPonto=" + idPonto + ", idJornada=" + ceJornada + ", momentoPonto=" + momentoPonto + "]";
	}
	
}
