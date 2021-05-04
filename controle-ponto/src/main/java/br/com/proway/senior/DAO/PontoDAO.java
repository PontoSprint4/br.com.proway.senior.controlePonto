package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.interfaces.IPessoa;
import br.com.proway.senior.model.interfaces.IJornadaProvisoria;

/**
 * 
 * @author Gabriel
 * @author Enzo
 *
 */
public final class PontoDAO {

	private static PontoDAO instance;

	/**
	 * @author Gabriel
	 * 
	 * Verifica se instance é nulo. Se for, o instance é instanciado, caso contrario
	 * só retorna.
	 * 
	 */
	public static PontoDAO getInstance() {
		if (instance == null) {
			instance = new PontoDAO();
		}
		return instance;
	}
	/**
	 * @author Gabriel
	 * 
	 * Aqui o instance é somente instanciado e retorna.
	 */

	public static PontoDAO newInstance() {

		instance = new PontoDAO();
		return instance;
	}

	/** 
	 * @author Gabriel
	 * 
	 *  Insere na tabela 
	 *  
	 *A variável insert é responsável por receber o insert na tabela pontos
	 *os valores id da jornada e o momento em que o ponto foi batido e os
	 *insere dentro da tabela.
	 * 
	 */
	public void create(IJornadaProvisoria jornadaProvisoria) {

		String momentoPonto = LocalDateTime.now().toString();

		String insert = "INSERT INTO pontos (ceJornada, momentoPonto) VALUES (" + jornadaProvisoria.getIdJornada() + ","
				+ momentoPonto + ")"; 
		
		try {
			PostgresConnector.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author Gabriel
	 * 
	 * Seleciona ID
	 * 
	 *O select que vai pegar todos os registros quando o idPonto for igual 
	 *a algum id esperado.
	 * 
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
	 * A variável query vai receber o comando de deletar o ponto da tabela pontos
	 * quando o id for igual a algum id esperado.
	 * 
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
	 * A variável query recebe o comando alterar o dado da coluna quando o número é
	 * informado.
	 * 
	 * results or expetion; exibição de tudo or tratamento de execessão;
	 * 
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
	 * Exibe tudo;
	 * 
	 * Retorna todos oa dados de todas as linhas da Tabela;
	 * 
	 * @return results or expetion; exibição de tudo or tratamento de execessão;
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
