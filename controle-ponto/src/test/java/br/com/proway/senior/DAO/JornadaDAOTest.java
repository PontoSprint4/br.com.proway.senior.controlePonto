package br.com.proway.senior.DAO;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.proway.senior.dbpersistence.DBConnection;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Turno;

class JornadaDAOTest {
	
	static Session session;
	static JornadaDAO jornadaDao;
	static PessoaDAO pessoaDao;
	static TurnoDAO turnoDao;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		session = DBConnection.getSession();
		jornadaDao = JornadaDAO.getInstance(session);
		pessoaDao = PessoaDAO.getInstance(session);
		turnoDao = TurnoDAO.getInstance(session);
	}

	@Test
	void testCreate() {
		Jornada jornada = new Jornada();
		jornada.setData(LocalDate.of(2019,06,14));
		jornada.setPessoa(pessoaDao.find(115));
		jornada.setTurno(turnoDao.find(113));
		jornadaDao.create(jornada);
		
		assertNotNull(jornada);
	}

	@Test
	void testRead() {
		assertEquals(119, jornadaDao.read(119).getId());
	}

	@Test
	void testPegaTodasAsJornadasPorIdPessoa() {
		pessoaDao.find(114);
		ArrayList<Jornada> listaJornadas =(ArrayList) jornadaDao.readByIdPessoa(pessoaDao.find(114));
		System.out.println("AQUI AQUI AQUI AQUI AQUI AQUI\\n\n\n\n\n\n\n\n");
		System.out.println(jornadaDao.readByIdPessoa(pessoaDao.find(114)));
		//assertNotNull(jornadaDao.readByIdPessoa(pessoaDao.find(114)));

	}

	@Test
	void testDelete() {
		
		Jornada jornada = new Jornada();
		jornada.setId(125);
		jornadaDao.delete(jornada);
		
		assertEquals(21, jornadaDao.readAll().size());
	}

	@Test
	void testUpdate() {
		session.clear();
		Turno turno = new Turno(0, LocalTime.now(), LocalTime.now().plusHours(4), "Turno 54");
		turnoDao.create(turno);
		
		Jornada jornada2 = new Jornada(134, LocalDate.of(1999, 10, 5), pessoaDao.find(115), turno);
		
		jornadaDao.update(jornada2);
		
	}

	@Test
	void testReadAll() {
		
		for (int i = 0; i < jornadaDao.readAll().size(); i++) {
		if(jornadaDao.readAll().size() == i) {
			assertEquals(i, jornadaDao.readAll().size());
		}
		}
		

	}

}
