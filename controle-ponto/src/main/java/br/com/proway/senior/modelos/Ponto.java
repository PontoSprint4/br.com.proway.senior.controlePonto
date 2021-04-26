package br.com.proway.senior.modelos;

import java.time.LocalDateTime;

import br.com.proway.senior.interfaces.JornadaServico;

public class Ponto {
	private Integer idPonto;
	private LocalDateTime momentoPonto;
	private Integer idPessoa;
	private JornadaServico jornada;

	public Integer getIdPonto() {
		return idPonto;
	}
	public void setIdPonto(Integer idPonto) {
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
	public JornadaServico getJornada() {
		return jornada;
	}
	public void setJornada(JornadaServico jornada) {
		this.jornada = jornada;
	}
	@Override
	public String toString() {
		return "Ponto [idPonto=" + idPonto + ", momentoPonto=" + momentoPonto + ", idPessoa=" + idPessoa + ", jornada="
				+ jornada + "]";
	}
}
