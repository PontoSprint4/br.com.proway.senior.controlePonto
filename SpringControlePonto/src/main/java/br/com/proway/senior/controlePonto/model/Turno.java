package br.com.proway.senior.controlePonto.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.proway.senior.model.controlePonto.interfaces.ITurno;

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

	@Column(name = "hora_inicio")
	private LocalTime horaInicio;
	
	@Column(name = "hora_fim")
	private LocalTime horaFim;
	
	@Column(name = "nome_turno")
	private String nomeTurno;	
	
	@Column(name = "pessoas_no_turno")
	private ArrayList<Integer> pessoasNoTurno;

	public Turno() {this.pessoasNoTurno = new ArrayList<Integer>();}

	public Turno(LocalTime horaInicio, LocalTime horaFim, String nomeTurno) {
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.nomeTurno = nomeTurno;
		
		this.pessoasNoTurno = new ArrayList<Integer>();
	}
	
	public Turno(Integer id, LocalTime horaInicio, LocalTime horaFim, String nomeTurno) {
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.nomeTurno = nomeTurno;
		
		this.pessoasNoTurno = new ArrayList<Integer>();
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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

	public ArrayList<Integer> getPessoasNoTurno() {
		return pessoasNoTurno;
	}
	
	// Esse com lista é o correto
	public void setPessoasNoTurno(ArrayList<Integer> idPessoa) {
		this.pessoasNoTurno = idPessoa;
	}	
	
	public void adicionaPessoaNoTurno(Integer idPessoa) {
		this.pessoasNoTurno.add(idPessoa);
	}	

}