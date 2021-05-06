package br.com.proway.senior.model;

import br.com.proway.senior.model.interfaces.IPessoa;

public class Pessoa implements IPessoa {

	private int id = 118;
	private int idTurno = 1;

	public int getIdPessoa() {
		return this.id;
	}

	public int getIdTurno() {
		return this.idTurno;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
}
