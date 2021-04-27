package br.com.proway.senior.interfaces;

import java.time.LocalDateTime;

public interface JornadaServico {
	public boolean registraPonto(int idPessoa, LocalDateTime momentoBatida);
	
}
