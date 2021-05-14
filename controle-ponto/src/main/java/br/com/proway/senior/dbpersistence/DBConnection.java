package br.com.proway.senior.dbpersistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

/**
 * Essa classe faz a integra��o com o banco de dados usando JPA/Hibernate
 * 
 * @author Lucas W
 * @author Tharlys D <tharlys.souza@outlook.com>
 * @author Vitor A
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
					.addAnnotatedClass(Jornada.class).addAnnotatedClass(PessoaDoPonto.class)
					.addAnnotatedClass(Ponto.class).addAnnotatedClass(Turno.class)
					.addAnnotatedClass(PessoaDoPonto.class).addAnnotatedClass(Ponto.class)
					.addAnnotatedClass(Turno.class).buildSessionFactory();
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed: " + e.getMessage());
			throw new ExceptionInInitializerError(e.toString());
		}

	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void shutdown() {
		session.close();
		getSessionFactory().close();
	}

	public static Session getSession() {
		getSessionFactory();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}
}
