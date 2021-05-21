package br.com.proway.senior.dbpersistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

/**
 * Classe Singleton faz a integracao com o banco de dados usando JPA/Hibernate
 * 
 * @author Lucas W <lucas.walim@senior.com.br>
 * @author Tharlys D <tharlys.dias@senior.com.br>
 * @author Vitor G <vitor.gehrke@senior.com.br>
 *
 */
public class DBConnection {

	private static SessionFactory sessionFactory;

	private static Session session;

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
					.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/controlepontodb")
					.setProperty("hibernate.connection.username", "postgres")
					.setProperty("hibernate.connection.password", "admin")
					.setProperty("hibernate.jdbc.time_zone", "America/Sao_Paulo")
					.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
					.setProperty("hibernate.show__sql", "true").setProperty("hibernate.format_sql", "true")
					.setProperty("hibernate.hbm2ddl.auto", "update")
					.setProperty("hibernate.connection.autocommit", "true").setProperty("hibernate.sql", "false")
					.addAnnotatedClass(Jornada.class)
					.addAnnotatedClass(Ponto.class)
					.addAnnotatedClass(Turno.class)
					.buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed: " + e.getMessage());
			throw new ExceptionInInitializerError(e.toString());
		}

	}

	/**
	 * Retorna uma sessionFactory.
	 * <p>
	 * Faz a validacao se a sessionFactory esta nula, caso esteja retorna a
	 * mesma.
	 * @return sessionFactory
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	/**
	 * Fecha a conexao com o banco de dados
	 * @throws Exception
	 */
	public static void shutdown() throws Exception {
		session.close();
		getSessionFactory().close();
	}

	/**
	 * Retorna uma session com o Banco de Dados.
	 * <p>
	 * Verifica se a session com o banco de dados esta nula ou fechada, caso
	 * esteja, reabre a session com getSessionFactory, se nao, só retorna a
	 * session.
	 * @return session Session
	 */
	public static Session getSession() {
		getSessionFactory();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}
}
