package br.com.proway.senior.model;

import java.time.LocalDate;

public class Jornada {
	private int id;
	private int idPessoa;
	private LocalDate data;
	private int idTurno;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdPessoa() {
		return idPessoa;
	}
	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public Jornada(int id, int idPessoa, LocalDate data, int idTurno) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.data = data;
		this.idTurno = idTurno;
	}
}