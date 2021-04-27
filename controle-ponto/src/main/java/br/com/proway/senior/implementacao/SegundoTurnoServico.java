package br.com.proway.senior.implementacao;

import java.time.LocalDateTime;

import br.com.proway.senior.interfaces.JornadaDiurnaServico;

public class SegundoTurnoServico implements JornadaDiurnaServico {

	public boolean registraPonto(Integer idPessoa, LocalDateTime momentoBatida) {
		return false;
	}

	public void fechaJornada() {
		// TODO Auto-generated method stub
	}

	public boolean comparaSeDataEhIgual() {
		// TODO Auto-generated method stub
		return false;
	}
}
