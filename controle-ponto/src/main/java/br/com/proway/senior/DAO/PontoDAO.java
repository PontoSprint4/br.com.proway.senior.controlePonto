package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.proway.senior.model.interfaces.IJornada;

/**
 * @author Gabriel 
 * @author Enzo
 */

public final class PontoDAO {

	private static PontoDAO instance;

	/** 
	 * Verifica se ha uma instancia, se haver e retornada. Caso contrario e
	 * criada uma. 
	 */
	public static PontoDAO getInstance() {
		if (instance == null) {
			instance = new PontoDAO();
		}
		return instance;
	}
	
	/**
	 * Objeto PontoDAO instanciado e retornado.
	 */
	public static PontoDAO newInstance() {
		instance = new PontoDAO();
		return instance;
	}

	/** 
	 * Insere na tabela 
	 *  
	 * Recebe o ID da Jornada como chave estrangeira e "pega" o momento em que o Ponto foi batido. Criando
	 * uma linha na tabela para ele, com seu devido ID.
	 */
	public void create(IJornada jornada) {
		
		String momentoPonto = LocalDateTime.now().toString();

		String insert = "INSERT INTO pontos (ceJornada, momentoPonto) VALUES (" + jornada.getIdJornada() + ","
				+ momentoPonto + ")"; 
		
		try {
			PostgresConnector.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exibe determinado Ponto
	 * 	  
	 * Exibe todos os dados referentes ao Ponto que correspode ao seu proprio
	 * ID informado como parametro.	  
	 */
	public ArrayList<String> read(int id) {
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM pontos WHERE idPonto = " + id;
		ResultSet rs;
		try {
			rs = PostgresConnector.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColumns = rsmd.getColumnCount();
			if (rs.next()) {
				for (int i = 1; i <= totalColumns; i++) {
					result.add(rs.getString(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Deleta da tabela
	 * 
	 * Procura na tabela o Ponto correspondente ao seu proprio ID 
	 * como parametro, e o remove da tabela.
	 */
	public void delete(int id) {
		String query = "DELETE FROM pontos WHERE id =" + id;
		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Atualiza o ponto
	 * 
	 * Procura na tabela o Ponto correspondente ao seu proprio ID 
	 * como parametro, e de acordo com a coluna desejada muda o valor do seu
	 * dado.
	 */
	public void update(int id, String col, String dado) {
		String query = "UPDATE pontos SET " + col + "=" + dado + " WHERE id =" + id;
		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Exibe tudo 
	 * 
	 * Retorna todos os dados de todas as linhas da Tabela (exibe todos os
	 * Pontos). 
	 */
	public ArrayList<ArrayList<String>> readAll() {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		String query = "SELECT * FROM pontos";
		ResultSet rs;
		try {
			rs = PostgresConnector.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int totalColumns = rsmd.getColumnCount();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList<String>();
				for (int i = 1; i <= totalColumns; i++) {
					row.add(rs.getString(i));
				}
				results.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}

}
