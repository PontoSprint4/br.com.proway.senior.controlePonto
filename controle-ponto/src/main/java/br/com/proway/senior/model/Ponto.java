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

	public Ponto() {
	}

	public Ponto(LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
	}

	public Ponto(Integer idPonto, LocalDateTime momentoPonto) {
		this.idPonto = idPonto;
		this.momentoPonto = momentoPonto;
	}

	public Integer getIdPonto() {
		return idPonto;
	}

	public void setIdPonto(Integer idPonto) {
		this.idPonto = idPonto;
	}

	public LocalDateTime getMomentoPonto() {
		return momentoPonto;
	}

	public void setMomentoPonto(LocalDateTime momentoPonto) {
		this.momentoPonto = momentoPonto;
	}
}
