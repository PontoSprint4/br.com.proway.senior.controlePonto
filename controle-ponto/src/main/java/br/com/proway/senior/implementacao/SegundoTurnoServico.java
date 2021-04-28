package br.com.proway.senior.implementacao;

import java.time.LocalDateTime;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.interfaces.JornadaDiurnaServico;
import br.com.proway.senior.modelos.Ponto;

public class SegundoTurnoServico implements JornadaDiurnaServico {

	public boolean registraPonto(Integer idPessoa, LocalDateTime momentoBatida) {
		Ponto ponto = new Ponto();
		ponto.setIdPessoa(idPessoa);
		ponto.setMomentoPonto(momentoBatida);
		
		/*
		 * if() { PontoDAO db = PontoDAO.getInstance(ponto); db.cadastrarPonto(ponto); }
		 */
		JornadaDAO db = JornadaDAO.getInstance(ponto);
		db.cadastrarPonto(ponto);
		
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
