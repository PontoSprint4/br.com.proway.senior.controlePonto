package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.proway.senior.model.interfaces.IPessoa;

public final class JornadaDAO {
	private static JornadaDAO instance;
	/**
	 * Se nao existir nenhuma instância do DAO,
	 * cria uma nova instância e a retorna;
	 * 
	 * @return
	 */
	public static JornadaDAO getInstance() {
		if (instance == null) {
			instance = new JornadaDAO();
		}
		return instance;
	}
	/**
	 * Cria uma nova instancia do DAO
	 * 
	 * @return JornadaDAO
	 */
	public static JornadaDAO newInstance() {
		instance = new JornadaDAO();
		return instance;
	}
	
	/**
	 * Recebe um parâmetro do tipo IPessoa que fornece
	 * a idPessoa e a idTurno para cadastrar uma jornada
	 * para a pessoa na data do momento do cadastro.
	 * 
	 * @param pessoa
	 * @return void
	 */
	public void create(IPessoa pessoa) {

		pessoa.getIdPessoa();
		pessoa.getIdTurno();

		String insert = "INSERT INTO jornadas (idPessoa, data, idTurno) VALUES (" + pessoa.getIdPessoa() + ",'"
				+ LocalDate.now().toString() + "'," + pessoa.getIdTurno() + ")";

		try {
			PostgresConnector.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Busca uma jornada do banco de dados;
	 * 
	 * Recebe uma id de Jornada, busca no banco de dados e retorna esta jornada.
	 * 
	 * @param id
	 * @return
	 */
	public ArrayList<String> read(int id) {
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM jornadas WHERE id = " + id;
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

	public ArrayList<String> readByIdPessoa(IPessoa pessoa) {
		ArrayList<String> result = new ArrayList<String>();
		String query = "SELECT * FROM jornadas WHERE idPessoa = " + pessoa.getIdPessoa();
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

	
	/**
	 * Deleta uma jornada específica do banco de dados;
	 * 
	 * Busca uma jornada no banco de dados a partir de sua id e 
	 * remove esta jornada do banco de dados.
	 * 
	 * @param id
	 * @return void
	 */
	public void delete(int id) {
		String query = "DELETE FROM jornadas WHERE id =" + id;
		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Atualiza um dado específico do banco de dados;
	 * 
	 * Recebe uma id que identifica qual item do banco a ser selecionado.
	 * Escolhe qual coluna deve ser alterada com o parametro col e insere o
	 * valor data na coluna col;
	 * 
	 * @param id
	 * @param col
	 * @param data
	 */
	public void update(int id, String col, LocalDate data) {
		String query = "UPDATE jornadas" + " SET " + col + " = '" + data + "' WHERE id = " + id;

		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna todos os itens do banco de dados
	 * 
	 * Busca cada itenm do banco de dados,
	 * faz o parse toString() dos dados das colunas e insere no array.
	 * Insere o array de cada item do banco em um ArrayList<ArrayList<String>>
	 * Retorna estes valores neste arraylist
	 * 
	 * @return ArrayList<ArrayList<String>>
	 */
	public ArrayList<ArrayList<String>> readAll() {
		ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
		String query = "SELECT * FROM jornadas";
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