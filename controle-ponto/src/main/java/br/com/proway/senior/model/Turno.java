package br.com.proway.senior.model;

import java.time.LocalTime;

public class Turno {

	private int id;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private LocalTime horaIntervaloInicio;
	private LocalTime horaIntervaloFim;

	public Turno() {

	}

	public Turno(int id, LocalTime horaInicio, LocalTime horaFim, LocalTime horaIntervaloInicio,
			LocalTime horaIntervaloFim) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.horaIntervaloInicio = horaIntervaloInicio;
		this.horaIntervaloFim = horaIntervaloFim;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public LocalTime getHoraIntervaloInicio() {
		return horaIntervaloInicio;
	}

	public void setHoraIntervaloInicio(LocalTime horaIntervaloInicio) {
		this.horaIntervaloInicio = horaIntervaloInicio;
	}

	public LocalTime getHoraIntervaloFim() {
		return horaIntervaloFim;
	}

	public void setHoraIntervaloFim(LocalTime horaIntervaloFim) {
		this.horaIntervaloFim = horaIntervaloFim;
	}

}
