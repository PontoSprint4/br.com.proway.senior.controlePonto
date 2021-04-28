package br.com.proway.senior.implementacao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.proway.senior.DAO.JornadaDAO;
import br.com.proway.senior.interfaces.JornadaDiurnaServico;
import br.com.proway.senior.modelos.Jornada;
import br.com.proway.senior.modelos.Ponto;

public class SegundoTurnoServico implements JornadaDiurnaServico {

	protected final List<LocalTime> HORARIO_TURNO2 = Arrays.asList(LocalTime.of(14, 0), LocalTime.of(17, 0),
			LocalTime.of(18, 0), LocalTime.of(22, 0));
	/**
	 * <h1>Registra o objeto Ponto</h1>
	 * <br>
	 * 
	 * Este metodo tem como objetivo instanciar um objeto Ponto e adiciona-lo
	 * em uma Jornada. <br>
	 * Se a ultima jornada estiver aberta, ele ira acrescentar o ponto a esta jornada
	 * e chamar o metodo fecharJornada(), conferindo se a jornada precisa ser fechada,<br>
	 * caso contrario, vai cadastrar uma nova jornada e adicionar o Ponto a ela.
	 * 
	 * @param idPessoa int, recebe usuário que está batendo o ponto
	 * @param batidaPonto LocalDateTime, recebe o horário e a data da batida do ponto
	 * 
	 * @return boolean, Para true, adiciona o Ponto a uma jornada existente.
	 * Para false, Cria uma nova jornada e acrescenta o Ponto a ela. 
	 */
	public boolean registraPonto(int idPessoa, LocalDateTime batidaPonto) {
		Ponto registroPonto = new Ponto();
		ArrayList<Ponto> pontos = new ArrayList<Ponto>();
		JornadaDAO db = JornadaDAO.getInstance();
		Jornada jornada = new Jornada();
		
		registroPonto.setIdPessoa(idPessoa);
		registroPonto.setMomentoPonto(batidaPonto);
		registroPonto.setIdPonto(0);

		registroPonto.setIdPonto(registroPonto.getIdPonto() + 1);
		
		pontos.add(registroPonto);
		
		if(db.buscarUltimaJornadaAberta(idPessoa) != null) {
			jornada = db.buscarUltimaJornadaAberta(idPessoa);
			jornada.getPontos().add(registroPonto);
			db.atualizar(jornada);
			fechaJornadaDiurna(jornada);
			return true;
		} else {
			jornada.setPontos(pontos);
			jornada.setIdPessoa(idPessoa);
			db.cadastrar(jornada);
			return false;
		}
	}

	/**
	 * <h1>Fecha jornada de trabalho</h1>
	 * <br>
	 * Este metodo tem como finalidade conferir se a quantidade de batidas
	 * de ponto pre definidas foram executadas ou se foram incompletas e 
	 * fechar a jornada de trabalho.<br>
	 * A jornada de trabalho é fechada a partir das batidas pre definidas ou
	 * automaticamente atraves de um horario pre estabelecido(variavel horario).
	 * 
	 *  @param jornada Jornada, Recebe um objeto do tipo Jornada, gerado atraves
	 *  do metodo registrarPonto()
	 *  
	 *  @return boolean Retorna false quando a Jornada não precisa ser fechada,
	 *  e true quando a Jornada precisa ser fechada.
	 * 
	 * 
	 */
	public boolean fechaJornadaDiurna(Jornada jornada) {
		LocalTime horario = LocalTime.of(23, 59, 59);
		if (jornada.getPontos().size() == HORARIO_TURNO2.size() || LocalTime.now() == horario) {
			jornada.setAberta(false);
			return false;
		} else {
			jornada.setAberta(true);
			return true;
		}

	}

}
