package br.com.proway.senior.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class TurnoDTO {

	private Integer id;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String nomeTurno;	
	
	private ArrayList<Integer> pessoasNoTurno;
	
	/**
     * Construtor do {@link TurnoDTO}, recebe um objeto {@link Turno} vindo do BD.
     * 
     * @see DBConnection
     * 
     * @param Turno : turno
     */
	public TurnoDTO(Turno turno) {
		this.id = turno.getId();
		this.horaInicio = turno.getHoraInicio();
		this.horaFim = turno.getHoraFim();
		this.nomeTurno = turno.getNomeTurno();
		this.pessoasNoTurno = turno.getPessoasNoTurno();
	}

	public Integer getId() {
		return id;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public LocalTime getHoraFim() {
		return horaFim;
	}

	public String getNomeTurno() {
		return nomeTurno;
	}	
	
	public ArrayList<Integer> getPessoasNoTurno() {
		return pessoasNoTurno;
	}
}
