package br.com.proway.senior.modelos;

import java.util.ArrayList;

public class Jornada {
	private Integer id;
	private ArrayList<Ponto> pontos;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ArrayList<Ponto> getPontos() {
		return pontos;
	}
	public void setPontos(ArrayList<Ponto> pontos) {
		this.pontos = pontos;
	}
	@Override
	public String toString() {
		return "Jornada [id=" + id + ", pontos=" + pontos + "]";
	}
	
}
