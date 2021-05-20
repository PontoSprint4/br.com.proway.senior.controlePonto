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

/**
 * Classe que estrutura a entidade PessoaDoPonto.
 * <p>
 * Possui apenas id(int) como parametro.
 * <p>
 * PessoaDoPonto criada apenas para ser vinculada a uma {@link Jornada}
 *
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version sprint5
 */
@Entity
public class PessoaDoPonto implements IPessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa")
	private int id;	

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

}
