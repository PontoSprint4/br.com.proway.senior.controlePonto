package br.com.proway.senior.controller;

import br.com.proway.senior.model.Ponto;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class PontoControllerTest extends TestCase {

    @Test
    public void testDiferencaHoras() {
        //Cenário
        Ponto registro1 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 8, 0));
        Ponto registro2 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 8, 0));
        Ponto registro3 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 9, 0));
        Ponto registro4 = new Ponto(1, LocalDateTime.of(2021, 5, 11, 9, 10));
        Long dif1 = ChronoUnit.MINUTES.between(registro1.getMomentoPonto(), registro2.getMomentoPonto());
        Long dif2 = ChronoUnit.MINUTES.between(registro3.getMomentoPonto(), registro4.getMomentoPonto());
        Long htdia01 = dif1 + dif2;

        Ponto registro5 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 8, 0));
        Ponto registro6 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 9, 0));
        Ponto registro7 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 9, 5));
        Ponto registro8 = new Ponto(1, LocalDateTime.of(2021, 5, 12, 9, 6));
        Long dif3 = ChronoUnit.MINUTES.between(registro5.getMomentoPonto(), registro6.getMomentoPonto());
        Long dif4 = ChronoUnit.MINUTES.between(registro7.getMomentoPonto(), registro8.getMomentoPonto());
        Long htdia02 = dif3 + dif4;


        //Ação a ser testada
        System.out.println(htdia01);
        System.out.println(htdia02);
        System.out.println("Saldo: " + (htdia01 + htdia02));

    }

}
