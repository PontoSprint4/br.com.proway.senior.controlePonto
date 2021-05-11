package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;

public class PontoControllerTest {

	public static JornadaDAO jDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		jDao = JornadaDAO.getInstance();
	}

	@Test
	public void testListaPontosRegistradosNoDia() {
		Jornada jornada = new Jornada(4, 114, LocalDate.now(), 1);
		ArrayList<Ponto> registrosDoDia = new ArrayList<Ponto>(); 
		PontoDAO pDao = new PontoDAO();
			 registrosDoDia.add(pDao.readByIdJornada(jornada.get);	
	}


}
