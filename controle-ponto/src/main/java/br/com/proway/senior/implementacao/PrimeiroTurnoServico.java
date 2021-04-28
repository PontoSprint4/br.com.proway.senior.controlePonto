package br.com.proway.senior.implementacao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.interfaces.JornadaDiurnaServico;
import br.com.proway.senior.modelos.Jornada;
import br.com.proway.senior.modelos.Ponto;

public class PrimeiroTurnoServico implements JornadaDiurnaServico {

	protected final List<LocalTime> HORARIO_TURNO1 = Arrays.asList(LocalTime.of(6, 0), LocalTime.of(10, 0),
			LocalTime.of(11, 0), LocalTime.of(14, 0));

//	private List<LocalDateTime> horariosBatidos = new ArrayList<LocalDateTime>();

	public boolean registraPonto(int idPessoa, LocalDateTime batidaPonto) {
		Ponto registroPonto = new Ponto();
		JornadaDAO db = JornadaDAO.getInstance();

		registroPonto.setIdPessoa(idPessoa);
		registroPonto.setMomentoPonto(batidaPonto);
		registroPonto.setIdPonto(0);

		registroPonto.setIdPonto(registroPonto.getIdPonto() + 1);

		Jornada jornada = db.buscarUltimaJornadaAberta(idPessoa);
		
		System.out.println(jornada);
		System.out.println(jornada.isAberta());
		if (jornada.isAberta()) {
			jornada.getPontos().add(registroPonto);
			fechaJornadaDiurna(jornada);
			return db.atualizar(jornada);
		} else {
			return db.cadastrar(jornada);
		}
	}

	/**
	 * DOCUMENTAR
	 */

	public boolean fechaJornadaDiurna(Jornada jornada) {
		LocalTime horario = LocalTime.of(23, 59, 59);
		if (jornada.getPontos().size() == HORARIO_TURNO1.size() || LocalTime.now() == horario) {
			int valorFalta = HORARIO_TURNO1.size() - jornada.getPontos().size();
			jornada.setAberta(false);

			// A linha abaixo foi criada apenas como orientação mas pode ser deletada a
			// qualquer momento.

			System.out.println("Faltam " + valorFalta + " para fechar sua jornada");
			return false;
		} else {
			jornada.setAberta(true);
			return true;
		}

	}

}
