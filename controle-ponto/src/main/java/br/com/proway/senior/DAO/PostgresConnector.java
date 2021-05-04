package br.com.proway.senior.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresConnector {

	static String url = "jdbc:postgresql://localhost:5432/controlepontodb";
	static String user = "postgres";
	static String password = "admin";
	static Connection con;

	public static void connect() throws SQLException {
		con = DriverManager.getConnection(url, user, password);
	}

	public static ResultSet executeQuery(String query) throws SQLException {
		if (con == null) {
			connect();
		}
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	public static String dbVersion() {
		try {
			if (con == null) {
				connect();
			}
			String query = "SELECT VERSION()";
			ResultSet rs = executeQuery(query);
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void executeUpdate(String query) throws SQLException {
		if (con == null) {
			connect();
		}
		Statement st = con.createStatement();
		st.executeUpdate(query);
	}
}
