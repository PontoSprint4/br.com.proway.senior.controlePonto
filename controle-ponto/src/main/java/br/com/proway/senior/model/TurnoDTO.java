package br.com.proway.senior.model;

import java.time.LocalTime;

public class TurnoDTO {

	private Integer id;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private String nomeTurno;	
	
	public TurnoDTO(Turno turno) {
		this.id = turno.getId();
		this.horaInicio = turno.getHoraInicio();
		this.horaFim = turno.getHoraFim();
		this.nomeTurno = turno.getNomeTurno();
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
	
}
