
package br.com.proway.senior.DAO;

import java.util.ArrayList;

import br.com.proway.senior.modelos.Jornada;

public final class JornadaDAO {
	private static JornadaDAO instance;
	public ArrayList<Jornada> jornadas = new ArrayList<Jornada>();
	
	private JornadaDAO() {}

	
	public static JornadaDAO getInstance() {
		if (instance == null) {
			instance = new JornadaDAO();
		}
		return instance;
	}
	
	/**
	 * <h1>
	 * Cadastra uma jornada
	 * </h1>
	 * </br>
	 * Recebe um objeto Jornada e cadastra na lista de jornadas 
	 * @param jornada Jornada
	 * @return boolean
	 */
	public boolean cadastrar(Jornada jornada) {
		if(jornada != null) {
			jornadas.add(jornada);
			return true;
		}
		return false;
	}

	/**
	 * <h>
	 * Busca a ultima jornada da pessoa
	 * </h>
	 * 
	 * Recebe a id da pessoa; Se a ultima jornada estiver aberta
	 * </br>
	 * retorna a jornada; Se estiver fechada, retorna null;
	 * 
	 * @param idPessoa
	 * @return Jornada
	 */

	public Jornada buscarUltimaJornadaAberta(Integer idPessoa) {
		for(Jornada jornada : jornadas) {
			if(jornada.getIdPessoa().equals(idPessoa) && jornada.isAberta() == true) {
				return jornada;
			}
		}
		return null;
	}
	
	/**
	 * <h>
	 * Adiciona pontos a uma jornada aberta
	 * </h>
	 * 
	 * 
	 * @param jornada
	 * @return boolean
	 */
	public boolean atualizar(Jornada jornada) {
		for(Jornada jornadaBuscada : jornadas) {
			if(jornadaBuscada.getId() == jornada.getId()) {
				jornadaBuscada.setPontos(jornada.getPontos());
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <h>
	 * Remove uma jornada
	 * </h>
	 * 
	 * Procura uma jornada pela idJornada e remove esta jornada da lista
	 * 
	 * @param idJornada
	 * @return boolean
	 */
	public boolean remover(Integer idJornada) {
		for(Jornada jornadaBuscada : jornadas) {
			if(jornadaBuscada.getId() == idJornada) {
				jornadas.remove(jornadaBuscada);
				return true;
			}
		}
		return false;
	}
	
	public static JornadaDAO newInstance() {
		instance = new JornadaDAO();
		return instance;
	}
}
