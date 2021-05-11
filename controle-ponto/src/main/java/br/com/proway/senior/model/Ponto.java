package br.com.proway.senior.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.proway.senior.model.interfaces.IJornada;

public class Ponto {
	
	private Integer idPonto;
	private Integer idJornada;
	private LocalDateTime momentoPonto;

	public Ponto(Integer idPonto, LocalDateTime momentoPonto) {
		this.idPonto = idPonto;
		this.momentoPonto = momentoPonto;
	}

	public Ponto(Integer idPonto, Integer idJornada, LocalDateTime momentoPonto) {
		this.idPonto = idPonto;
		this.idJornada = idJornada;
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
	
	public void setIdJornada(IJornada iJornada) { // idJornada
		this.idJornada = iJornada.getIdJornada();
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
