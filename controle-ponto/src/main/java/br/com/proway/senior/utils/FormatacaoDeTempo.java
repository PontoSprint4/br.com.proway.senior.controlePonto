package br.com.proway.senior.utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.Turno;

/**
 * Classe utilitária de tratamento de tempo.
 * 
 * Converte unidades de tempo como horas, minutos e segundos entre si de acordo 
 * com a necessidade.
 * 
 * @author Thiago Luiz Barbieri <thiago.barbieri@senior.com.br>
 * @author Vitor André Gehrke <vitor.gehrke@senior.com.br>
 * @version Sprint6
 */
public class FormatacaoDeTempo {

	/**
     * Retorna o valor em minutos entre dois momentos no tempo.
     *
     * @param registroInicial LocalDateTime,
     * @param registroFinal
     * @return
     */
    public static Long tempoEntreRegistros(LocalDateTime registroInicial,
                                    LocalDateTime registroFinal) throws Exception {

        if (registroInicial.isBefore(registroFinal)) {
            return ChronoUnit.MINUTES.between(registroInicial, registroFinal);
        }
        	throw new Exception("A data inicial deve ser anterior a data final");
    }

    /**
     * Retorna o tempo formatado baseado no tempo informado.
     * Exibe apenas até a ordem de grandeza das horas.
     *
     * @param quantidade   Long Quantidade do tempo
     * @param unidadeTempo {@link TimeUnit} É a definição da ordem de
     *                     grandeza da quantidade de tempo.
     * @version Sprint5
     * @author Lucas Walim <lucas.walim@senior.com.br>
     * @author Samuel Levi <samuel.levi@senior.com.br>
     * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
     */
    public static String minutosParaHoras(long quantidade, TimeUnit unidadeTempo) {
        long hora = unidadeTempo.toHours(quantidade);
        long minuto = unidadeTempo.toMinutes(quantidade) % 60;
        long segundo = unidadeTempo.toSeconds(quantidade) % 60;
        if (hora > 0) {
            return String.format("%02d:%02d:%02d", hora, minuto, segundo);
        } else if (minuto > 0) {
            return String.format("%d:%02d", minuto, segundo);
        } else {
            return String.format("%02d", segundo);
        }
    }
	
}
