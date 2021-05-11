package br.com.proway.senior.controller;

import java.time.LocalDate;

import br.com.proway.senior.DAO.JornadaDAO;
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
	 * Quando sair do padrão de batidas
	 * 
	 * Sera disparado uma mensagem
	 * 
	 * Não foi implementado
	 * 
	 */
	public void padraoBatidaPonto() {}


}

	
