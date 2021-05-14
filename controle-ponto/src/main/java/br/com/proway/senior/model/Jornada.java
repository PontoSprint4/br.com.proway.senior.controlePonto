package br.com.proway.senior.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Jornada {
	
	/**
	 * Atributos da classe jornada
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_jornada")
	private int id;
	
	@Column(name = "data")
	private LocalDate data;

	@OneToOne(cascade = CascadeType.ALL)
	private PessoaDoPonto pessoa;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Turno turno;

	@OneToMany(targetEntity = Ponto.class, cascade = CascadeType.ALL)
	private List<Ponto> listaPonto;

	public Jornada() {
	}

	public Jornada(int id, LocalDate data, PessoaDoPonto pessoa, Turno turno) {
		this.id = id;
		this.data = data;
		this.pessoa = pessoa;
		this.turno = turno;
	}
	
//	public void setPessoa(IPessoa pessoa) {
//		this.pessoa = pessoa;
//	}
	
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	
	public void setPessoa(PessoaDoPonto pessoa){
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

	public List<Ponto> getListaPonto() {
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