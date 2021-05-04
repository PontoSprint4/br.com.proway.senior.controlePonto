package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import br.com.proway.senior.model.interfaces.IPessoa;

public final class JornadaDAO {
	private static JornadaDAO instance;

	public static JornadaDAO getInstance() {
		if (instance == null) {
			instance = new JornadaDAO();
		}
		return instance;
	}

	public static JornadaDAO newInstance() {
		instance = new JornadaDAO();
		return instance;
	}

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

	public void delete(int id) {
		String query = "DELETE FROM jornadas WHERE id =" + id;
		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(int id, String col, LocalDate data) {
		String query = "UPDATE jornadas" + " SET " + col + " = '" + data + "' WHERE id = " + id;

		try {
			PostgresConnector.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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