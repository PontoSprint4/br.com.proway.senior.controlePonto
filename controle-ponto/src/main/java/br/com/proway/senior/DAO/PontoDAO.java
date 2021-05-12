package br.com.proway.senior.DAO;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import br.com.proway.senior.dbpersistence.PostgresConnector;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;


/**
 * @author Gabriel
 * @author Enzo
 */

public final class PontoDAO {

    private static PontoDAO instance;

    /**
     * Verifica se ha uma instancia, se haver e retornada. Caso contrario e criada
     * uma.
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
     * <p>
     * Recebe o ID da Jornada como chave estrangeira e "pega" o momento em que o
     * Ponto foi batido. Criando uma linha na tabela para ele, com seu devido ID.
     */
    public void create(Jornada jornada) {

        String insert = "INSERT INTO pontos (idJornada, momentoPonto) VALUES (" + jornada.getId() + ",'"
                + LocalDateTime.now() + "')";
        try {
            PostgresConnector.executeUpdate(insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
	/**
	 * Exibe determinado Ponto
	 * 
	 * Exibe todos os dados referentes ao Ponto que correspode ao seu proprio ID
	 * informado como parametro.
	 */
	public Ponto read(int id) {
		String query = "SELECT * FROM pontos WHERE id = " + id;
		ResultSet rs;
		try {
			rs = PostgresConnector.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			//int totalColumns = rsmd.getColumnCount();
			if (rs.next()) {
					LocalDateTime momentoPonto = rs.getTimestamp("momentoponto").toLocalDateTime();
					Ponto ponto = new Ponto(rs.getInt("id"), momentoPonto);
					ponto.setIdJornada(rs.getInt("idjornada"));
					//System.out.println(ponto);
					return ponto;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

    /**
     * Retorna lista de pontos pelo da jornada (idJornada)
     *
     * @param jornada id da jornada correspondente.
     * @return ArrayList<String> result
     */
    public ArrayList<Ponto> readByIdJornada(int jornada) {
        ArrayList<Ponto> result = new ArrayList<Ponto>();
        String query = "SELECT * FROM pontos WHERE idJornada = " + jornada;
        ResultSet rs;
        try {
            rs = PostgresConnector.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();
            while (rs.next()) {
                LocalDateTime momentoPonto = rs.getTimestamp("momentoponto").toLocalDateTime();
                Ponto ponto = new Ponto(rs.getInt("id"), momentoPonto);
                ponto.setIdJornada(rs.getInt("idjornada"));
                //System.out.println(ponto);
                result.add(ponto);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deleta da tabela
     * <p>
     * Procura na tabela o Ponto correspondente ao seu proprio ID como parametro, e
     * o remove da tabela.
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
     * <p>
     * Procura na tabela o Ponto correspondente ao seu proprio ID como parametro, e
     * de acordo com a coluna desejada muda o valor do seu dado.
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
     * <p>
     * Retorna todos os dados de todas as linhas da Tabela (exibe todos os Pontos).
     */
    public ArrayList<Ponto> readAll() {
        ArrayList<Ponto> result = new ArrayList<Ponto>();
        String query = "SELECT * FROM pontos";
        ResultSet rs;
        try {
            rs = PostgresConnector.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int totalColumns = rsmd.getColumnCount();
            while (rs.next()) {
            	LocalDateTime momentoPonto = rs.getTimestamp("momentoponto").toLocalDateTime();
                Ponto ponto = new Ponto(rs.getInt("id"), momentoPonto);
                ponto.setIdJornada(rs.getInt("idjornada"));
                //System.out.println(ponto);
                result.add(ponto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

}
