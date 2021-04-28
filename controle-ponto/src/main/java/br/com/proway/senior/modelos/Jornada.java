package br.com.proway.senior.modelos;

import java.util.ArrayList;

public class Jornada {
	private Integer idJornada;
	private Integer idPessoa;
	private boolean aberta = true;
	private ArrayList<Ponto> pontos;

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getId() {
		return idJornada;
	}

	public void setId(Integer id) {
		this.idJornada = id;
	}

	public ArrayList<Ponto> getPontos() {
		return pontos;
	}

	public void setPontos(ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}

	public boolean isAberta() {
		return aberta;
	}

	public void setAberta(boolean aberta) {
		this.aberta = aberta;
	}

	@Override
	public String toString() {
		return "Jornada [idJornada=" + idJornada + ", idPessoa=" + idPessoa + ", aberta=" + aberta + ", pontos="
				+ pontos + "]";
	}
}
