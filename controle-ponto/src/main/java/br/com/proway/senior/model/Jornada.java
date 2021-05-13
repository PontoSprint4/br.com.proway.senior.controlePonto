package br.com.proway.senior.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import br.com.proway.senior.model.interfaces.IPessoa;
import br.com.proway.senior.model.interfaces.ITurno;

@Entity
public class Jornada {
	
	/**
	 * Atributos da classe jornada
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private LocalDate data;

	@OneToOne
	private IPessoa pessoa;
	
	private ITurno turno;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private ArrayList<Ponto> listaPonto;

	public Jornada() {
	}

	public Jornada(int id, LocalDate data, IPessoa pessoa, ITurno turno) {
		this.id = id;
		this.data = data;
		this.pessoa = pessoa;
		this.turno = turno;
	}
	
//	public void setPessoa(IPessoa pessoa) {
//		this.pessoa = pessoa;
//	}
	
	void setPessoa(IPessoa pessoa){
		this.pessoa = pessoa;
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

	public ArrayList<Ponto> getListaPonto() {
		return listaPonto;
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

	@Override
	public String toString() {
		return "Jornada [id=" + id + ", data=" + data + ", pessoa=" + pessoa + ", turno=" + turno + ", listaPonto="
				+ listaPonto + "]";
	}
	
	
}