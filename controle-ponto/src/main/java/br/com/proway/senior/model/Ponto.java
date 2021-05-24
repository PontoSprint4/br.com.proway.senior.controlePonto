package br.com.proway.senior.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe Ponto. Estrutura e define o ponto como objeto.
 * 
 * @version Sprint 5
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 */
@Entity
public class Ponto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer idPonto;

	@Column(name = "momentoPonto")
	private LocalDateTime momentoPonto;
	
	@Column(name = "pessoa_id")
    private Integer idPessoa;
	
	public Ponto() {}

	public Ponto(Integer idPessoa, LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
		this.idPessoa = idPessoa;
	}

	public Ponto(Integer idPonto, Integer idPessoa, LocalDateTime momentoPonto) {
		this.idPonto = idPonto;
		this.idPessoa = idPessoa;
		this.momentoPonto = momentoPonto;
	}

	public Integer getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(Integer idPonto) {
		this.idPonto = idPonto;
	}
	
	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}

	public void setMomentoPonto(LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
	}
}
