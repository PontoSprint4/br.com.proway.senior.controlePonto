package br.com.proway.senior.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.proway.senior.model.interfaces.IPessoa;

@Entity
public class PessoaDoPonto implements IPessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private int id;
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<Jornada> jornada;

	/**
	 * Construtor vazio.
	 */
	public PessoaDoPonto() {}

	/**
	 * Construtor com parametros.
	 * 
	 * @param id
	 */
	public PessoaDoPonto(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "PessoaDoPonto [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaDoPonto other = (PessoaDoPonto) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
