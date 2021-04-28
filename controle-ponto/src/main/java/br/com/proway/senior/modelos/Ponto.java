package br.com.proway.senior.modelos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Ponto {
	private int idPonto;
	private LocalDateTime momentoPonto;
	private LocalDate dataPonto;
	private Integer idPessoa;
	
	public Ponto() {
		
	}
	
	public Ponto(int idPonto, LocalDateTime momentoPonto, LocalDate dataPonto, Integer idPessoa) {
		super();
		this.idPonto = idPonto;
		this.momentoPonto = momentoPonto;
		this.dataPonto = dataPonto;
		this.idPessoa = idPessoa;
	}
	
	public LocalDate getDataPonto() {
		return dataPonto;
	}
	public void setDataPonto(LocalDate dataPonto) {
		this.dataPonto = dataPonto;
	}


	public int getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(int idPonto) {
		this.idPonto = idPonto;
	}
	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}
	public void setMomentoPonto(LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
	}
	public Integer getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	@Override
	public String toString() {
		return "Ponto [idPonto=" + idPonto + ", momentoPonto=" + momentoPonto + ", dataPonto=" + dataPonto
				+ ", idPessoa=" + idPessoa + "]";
	}

}
