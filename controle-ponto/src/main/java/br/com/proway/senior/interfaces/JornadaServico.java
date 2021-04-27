package br.com.proway.senior.interfaces;

import java.time.LocalDateTime;

public interface JornadaServico {
	public boolean registraPonto(Integer idPessoa, LocalDateTime momentoBatida);
	public void fechaJornada();
}
