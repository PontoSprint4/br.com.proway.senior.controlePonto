package br.com.proway.senior.model;

import java.time.LocalDate;

public class Jornada implements br.com.proway.senior.model.interfaces.Jornada {
	
	/**
	 * Atributos da classe jornada
	 */
	private int id;
	private int idPessoa;  // FK
	private LocalDate data;
	private int idTurno;   // FK

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
		this.id = id;
		this.idPessoa = idPessoa;
		this.data = data;
		this.idTurno = idTurno;
	}
	
	/**
	 * Implementa��o da interface IJornada
	 * 
	 * Foi utilizada pela classe ponto
	 * 
	 * @return id
	 */
	public Integer getIdJornada() {
		Integer id = this.id;
		return id;
	}
}