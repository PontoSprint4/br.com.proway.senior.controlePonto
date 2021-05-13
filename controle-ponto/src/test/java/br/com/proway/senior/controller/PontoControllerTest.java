package br.com.proway.senior.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.junit.Test;

import br.com.proway.senior.DAO.PontoDAO;
import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import junit.framework.TestCase;

public class PontoControllerTest extends TestCase {

//	@Test
//	public void testDiferencaHoras() {
//		// Cenário
//		Ponto registro1 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 8, 0));
//		Ponto registro2 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 8, 0));
//		Ponto registro3 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 9, 0));
//		Ponto registro4 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 9, 10));
//		Long dif1 = ChronoUnit.MINUTES.between(registro1.getMomentoPonto(), registro2.getMomentoPonto());
//		Long dif2 = ChronoUnit.MINUTES.between(registro3.getMomentoPonto(), registro4.getMomentoPonto());
//		Long htdia01 = dif1 + dif2;
//
//		Ponto registro5 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 8, 0));
//		Ponto registro6 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 9, 0));
//		Ponto registro7 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 9, 5));
//		Ponto registro8 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 9, 6));
//		Long dif3 = ChronoUnit.MINUTES.between(registro5.getMomentoPonto(), registro6.getMomentoPonto());
//		Long dif4 = ChronoUnit.MINUTES.between(registro7.getMomentoPonto(), registro8.getMomentoPonto());
//		Long htdia02 = dif3 + dif4;
//
////        //Ação a ser testada
////        System.out.println(htdia01);
////        System.out.println(htdia02);
////        System.out.println("Saldo: " + (htdia01 + htdia02));
//
//	}
//
//	@Test
//	public void testCreate() throws Exception {
//		PontoDAO pontoDao = PontoDAO.getInstance();
//		PontoController controller = new PontoController();
//
//		Jornada jornada = new Jornada(1, 13, LocalDate.of(2021, 05, 11), 7);
//		controller.create(jornada);
//	}
//
//	@Test
//	public void testCreate2() throws Exception {
//		PontoDAO pontoDao = PontoDAO.getInstance();
//		PontoController controller = new PontoController();
//
//		Jornada jornada = new Jornada(1, 3, LocalDate.of(2021, 05, 9), 7);
//		controller.create(jornada);
//
//	}
//
//	@Test
//    public void testReadByJornada() throws Exception {
//    	PontoDAO pontoDAO = PontoDAO.getInstance();
//    	PontoController controller = new PontoController();
//    	int idJornada = 114;
//    	
//    	ArrayList<Ponto> registros = controller.readByIdJornada(idJornada);
//    	for(Ponto ponto : registros) {
//    		System.out.println(ponto);
//    		assertSame(idJornada, ponto.getIdJornada());
//    	}
//    }
//	
//	@Test
//	public void testReadAll() {
//		PontoDAO pontoDAO = PontoDAO.getInstance();
//    	PontoController controller = new PontoController();
//    	
//    	ArrayList<Ponto> lista = controller.readAll();
//    	for(Ponto item : lista) {
//    		System.out.println(item);
//    	}
//    	
//    	assertEquals(9, lista.size());
//	}
//	
//	@Test
//	public void testDelete() {
//		PontoDAO pontoDAO = PontoDAO.getInstance();
//    	PontoController controller = new PontoController();
//    	
//    	controller.delete(7);
//    	assertEquals(9, controller.readAll().size());
//	}
}
