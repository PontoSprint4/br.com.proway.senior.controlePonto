package br.com.proway.senior.model.interfaces;

import java.time.LocalTime;

public interface ITurno {

	public void cadastrarTurno(LocalTime horaInicio, LocalTime horaFim, LocalTime horaIntervaloInicio,
			LocalTime horaIntervaloFim);

	public void atualizaTurno(int id);

	public void apagarTurno(int id);

	public void buscarTurnoId(int id);

	public void buscarTurnoHoraInicio(LocalTime turnoHoraInicio);

	public void buscarTurnoHoraFim(LocalTime turnoHoraFim);

}
