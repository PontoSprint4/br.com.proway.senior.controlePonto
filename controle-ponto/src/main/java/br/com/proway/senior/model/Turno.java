package br.com.proway.senior.model;

import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.proway.senior.model.interfaces.ITurno;

/**
 * Classe que estrutura a entidade Turno.
 * Possui um id único, duas datas no formato {@link LocalTime} sendo
 * horaInicio e horaFim. Possui uma relacao de {@link OneToMany}
 * com {@link Jornada}.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @version sprint5
 */
@Entity
public class Turno implements ITurno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_turno")
	private Integer id;

	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String nomeTurno;

	@OneToMany (cascade = CascadeType.ALL)
	private List<Jornada> jornada;

	public Turno() {}

	public Turno(Integer id, LocalTime horaInicio, LocalTime horaFim, String nomeTurno) {
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.nomeTurno = nomeTurno;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(LocalTime horaFim) {
		this.horaFim = horaFim;
	}

	public String getNomeTurno() {
		return nomeTurno;
	}

	public void setNomeTurno(String nomeTurno) {
		this.nomeTurno = nomeTurno;
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
		Turno other = (Turno) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return " " + id + ", inicio: " + horaInicio + ", " +
				"fim: " + horaFim + ", nome: " + nomeTurno;
	}
	
}