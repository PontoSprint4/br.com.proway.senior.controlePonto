package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.proway.senior.dbpersistence.PostgresConnector;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Turno;

public class TurnoDAO {

	private static TurnoDAO instance;
	private Session session;

	public static TurnoDAO getInstance(Session session) {
		if (instance == null) {
			instance = new TurnoDAO(session);
		}
		return instance;
	}

	private TurnoDAO(Session session) {
		this.session = session;
	}

	/*
	 * Metodo que cria/insere no banco de dados as informacoes de: nome de turno,
	 * horario de inicio do turno e hora de fim do turno.
	 * 
	 * @param String nomeTurno
	 * 
	 * @param String horaInicio
	 * 
	 * @param String horaFim
	 * 
	 */

	public void create(Turno turno) {
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		session.save(turno);
		session.getTransaction().commit();
	}

	public Turno find(int id) {
		return session.get(Turno.class, id);
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
	 * Metodo que deleta as informacoes de uma linha (row) contida no banco de
	 * dados, com base no id fornecido.
	 * 
	 * 
	 */

	public boolean delete(Turno turno) {
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		
		try {
			session.delete(turno);
			session.getTransaction().commit();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
//		String query = "DELETE FROM turnos WHERE id =" + id;
//		try {
//			PostgresConnector.executeUpdate(query);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/*
	 * Metodo que realiza a atualizacao de um dado especifico, atraves do
	 * fornecimento do id e nome da coluna.
	 * 
	 */

	public boolean update(Turno turnoASerAtualizado) {
		if(!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			session.update(turnoASerAtualizado);
			session.getTransaction().commit();
			return true;
		} catch(Exception e) {
			e.getMessage();
			return false;
		}
	}

	/*
	 * Metodo que busca no banco de dados todas as informacoes existentes.
	 * 
	 * @return ArrayList<String>
	 */
	public List<Turno> readAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Turno> criteria = builder.createQuery(Turno.class);
		criteria.from(Turno.class);
		return session.createQuery(criteria).getResultList();
	
	}

}
