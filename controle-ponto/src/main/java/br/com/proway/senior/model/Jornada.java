package br.com.proway.senior.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Jornada {
	
	/**
	 * Atributos da classe jornada
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private LocalDate data;

	private int idPessoa;
	private int idTurno;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ArrayList<Ponto> listaPonto;

	public Jornada() {
	}

	public Jornada(int id, LocalDate data, int idPessoa, int idTurno) {
		this.id = id;
		this.data = data;
		this.idPessoa = idPessoa;
		this.idTurno = idTurno;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public int getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}

	public ArrayList<Ponto> getListaPonto() {
		return listaPonto;
	}

	@Override
	public String toString() {
		return "Jornada{" +
				"id=" + id +
				", data=" + data +
				", idPessoa=" + idPessoa +
				", idTurno=" + idTurno +
				", listaPonto=" + listaPonto +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Jornada jornada = (Jornada) o;
		return id == jornada.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}