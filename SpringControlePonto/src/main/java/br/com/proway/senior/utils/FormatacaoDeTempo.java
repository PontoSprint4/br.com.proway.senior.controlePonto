package br.com.proway.senior.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

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
	public static Long tempoEntreRegistros(LocalDateTime registroInicial, LocalDateTime registroFinal)
			throws Exception {
		if (registroInicial.isBefore(registroFinal)) {
			//if (registroInicial.getDayOfMonth() == registroFinal.getDayOfMonth()) {
				// CASO1 - inicio e fim do turno no mesmo dia
				return ChronoUnit.MINUTES.between(registroInicial, registroFinal);
			//} else {
				// CASO 2 - inicio e fim de turno em dias diferentes (madrugada)
				//return calculaMadrugada(registroInicial.toLocalTime(), registroFinal.toLocalTime());
				//return (ChronoUnit.MINUTES.between(registroInicial, registroFinal));
			//}
		}
		throw new Exception("A data inicial deve ser anterior a data final");
	}
	/**
	 * Metodo que retorna os minutos esperados num turno.
	 * por exemplo iniciando as 22:00 de um dia e finalizando as 04:00 do proximo dia.
	 * CASO 1 - Faz o calculo normal quando o turno esta entre 00:00 e 23:59.
	 * CASO 2 - Faz o calculo com o adicional de 1 dia (LocalDate.now().plusDays(1), quando se localizar
	 * entre os dias.
	 * @param horaInicio
	 * @param horaFim
	 * @return
	 */
	public static Long calculaMinutosEsperadosNoTurno(LocalTime horaInicio, LocalTime horaFim) {
		if(horaInicio.isBefore(horaFim)) {
			//CASO1
			LocalDateTime horaInicioTurno = horaInicio.atDate(LocalDate.now());
			LocalDateTime horaFimTurno = horaFim.atDate(LocalDate.now());
			return ChronoUnit.MINUTES.between(horaInicioTurno, horaFimTurno);
		} else {
			//CASO2
			LocalDateTime horaInicioTurno = horaInicio.atDate(LocalDate.now());
			LocalDateTime horaFimTurno = horaFim.atDate(LocalDate.now().plusDays(1));
			return ChronoUnit.MINUTES.between(horaInicioTurno, horaFimTurno);
		}
		
	}

	/**
	 * Retorna o tempo formatado baseado no tempo informado. Exibe apenas até a
	 * ordem de grandeza das horas.
	 *
	 * @param quantidade   Long Quantidade do tempo
	 * @param unidadeTempo {@link TimeUnit} É a definição da ordem de grandeza da
	 *                     quantidade de tempo.
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
