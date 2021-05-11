package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Pessoa;
import br.com.proway.senior.model.interfaces.IPessoa;

public class JornadaController {

	/**
	 * Verifica se a jornada aberta
	 * 
	 * Caso a jornada esteja aberta retorna true
	 * 
	 * @param pessoa
	 * @return true || false
	 */
	public boolean verificaJornadaAberta(IPessoa pessoa) {
		JornadaDAO jornadaDAO = JornadaDAO.getInstance();
		String dataUsuario = jornadaDAO.readByIdPessoa(pessoa).get(2);
		
		if (dataUsuario.equals(LocalDate.now().toString()))
			return true;
		else
			return false;
	}
	
	/**
	 * Dispara alerta de quantos pontos bateu
	 * 
	 * Nao foi implementado
	 * 
	 */
	public void pontosBatidos() {}

	/**
	 * Quando sair do padr�o de batidas
	 * 
	 * Sera disparado uma mensagem
	 * 
	 * N�o foi implementado
	 * 
	 */
	public void padraoBatidaPonto() {}
	
	
	/**
	 * Este método fará o calculo das horas faltas.
	 * 
	 * Pega as horas de acordo com o turno que trabalha.
	 * 
	 * Faz a diferença, caso negativo retorna horas faltas,
	 * caso >= 0 retorna null.
	 * 
	 * @author Tharlys Dias
	 * @author Vanderlei Kleinschmidt
	 * @author Vitor Gehrke
	 * @return horasFaltas
	 */
	public Double horasFalta(ArrayList<String> pontos) {
		Double horasFaltas = null;
		PontoDAO db = PontoDAO.getInstance();
		
		for (int i = 0; i < db.toString().length(); i++) {
			//if () {
				
			//}
		}
		
		return horasFaltas;
	}
}

	
