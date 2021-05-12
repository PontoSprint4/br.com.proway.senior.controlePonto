package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.proway.senior.dbpersistence.PostgresConnector;

public class TurnoDAO {

	private static TurnoDAO instance;

	public static TurnoDAO getInstance() {
		if (instance == null) {
			instance = new TurnoDAO();
		}
		return instance;
	}

	public static TurnoDAO newInstance() {
		instance = new TurnoDAO();
		return instance;
	}

	/*
	 * Metodo que cria/insere no banco de dados as informacoes de:
	 * nome de turno, horario de inicio do turno e hora de fim do turno.
	 * 
	 * @param String nomeTurno
	 * @param String horaInicio 
	 * @param String horaFim
	 *  
	 */
		
	public void create(String nomeTurno, String horaInicio, String horaFim) {
				
		String insert = "INSERT INTO turnos (nomeTurno, horaInicio, horaFim) VALUES (" + nomeTurno + ",'" + horaInicio
				+ "','" + horaFim + "')";

		try {
			PostgresConnector.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Metodo que busca no banco de dados informacoes referente a uma linha (row),
	 * com base no id fornecido. 
	 * 
	 * @return ArrayList<String>
	 *   
	 */
	
	public ArrayList<String> read(int id) {
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM turnos WHERE id = " + id;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * Metodo que deleta as informacoes de uma linha (row) contida no banco de dados,
	 * com base no id fornecido. 
	 * 
	 *    
	 */
	
	public void delete(int id) {
		String query = "DELETE FROM turnos WHERE id =" + id;
		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*
	 * Metodo que realiza a atualizacao de um dado especifico, atraves do 
	 * fornecimento do id e nome da coluna. 
	 *    
	 */
		
	public void update(int id, String col, String data) {
		String query = "UPDATE turnos SET " + col + "='" + data + "' WHERE id =" + id;
		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Metodo que busca no banco de dados todas as informacoes existentes. 
	 *  
	 * @return ArrayList<String>    
	 */
	
	public ArrayList<ArrayList<String>> readAll() {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		String query = "SELECT * FROM turnos";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}

}
