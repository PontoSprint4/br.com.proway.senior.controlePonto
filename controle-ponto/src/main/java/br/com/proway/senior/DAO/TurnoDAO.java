package br.com.proway.senior.DAO;

import java.util.List;

import org.hibernate.Session;

import br.com.proway.senior.model.Turno;

/**
 * Classe responsavel pela persistencia do objeto {@link Turno} no banco de
 * dados.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @version Sprint5
 * @author Leonardo Pereira <leonardo.pereira@senior.com.br>
 * @version Sprint6
 */
public class TurnoDAO extends GenericDAO<Turno> {

	private static TurnoDAO instance;
	private Session session;

	/**
	 * Construtor que recebe a sessao.
	 *
	 * @param session sessao recebida como parametro
	 */
	private TurnoDAO(Session session) {
		this.session = session;
	}

	/**
	 * Metodo responsavel por instanciar {@link TurnoDAO} recebendo uma sessao A
	 * sessao recebida passa pela checagem se a nula, caso positivo, uma nova sessao
	 * instanciada, caso negativo, a sessao que ja esta aberta sera retornada.
	 *
	 * @param session ativa
	 * @return instance a instancia da sessao.
	 */
	public static TurnoDAO getInstance(Session session) {
		if (instance == null) {
			instance = new TurnoDAO(session);
		}
		return instance;
	}

	/**
	 * Recebe um objeto {@link Turno} e insere no banco de dados. Sera realizado um
	 * teste para saber se a transacao atual esta ativa, se estiver e retornada,
	 * caso contrario e iniciada uma nova transacao com o banco. O objeto e salvo
	 * usando o metodo save da session e a transacao e comitada/persistida caso o
	 * objeto seja persistido no banco. O objeto a ser recebido aqui, deve ter o
	 * parametro id nulo no construtor, pois esse parametro sera atribuido no banco
	 * de dados.
	 *
	 * @param turnoASerInserido objeto a ser inserido no banco.
	 * @return
	 */
	public Integer create(Turno turnoASerInserido) {
		return super.create(turnoASerInserido);
	}

	/**
	 * Recebe um objeto {@link Turno} e altera no banco de dados. E realizado um
	 * teste para saber se a transacaoo atual esta ativa, se estiver e retornada,
	 * caso contrario e iniciada uma nova transacaodo com o banco. O objeto e salvo
	 * usando o metodo save da session e a transacao e comitada/persistida caso o
	 * objeto seja persistido no banco. O objeto a ser recebido aqui, deve ter o
	 * parametro id informado no construtor, pois esse parametro sera usado no banco
	 * de dados, para definir as outras informacoes que serao atualizadas.
	 *
	 * @param turnoASerAtualizado objeto a ser alterado no banco.
	 */
	public boolean update(Turno turnoASerAtualizado) {
		return super.update(turnoASerAtualizado);
	}

	/**
	 * Recebe um objeto {@link Turno} e deleta no banco de dados. E realizado um
	 * teste para saber se a transacaoo atual esta ativa, se estiver e retornada,
	 * caso contrario e iniciada uma nova transacao com o banco. O objeto e deletado
	 * usando o metodo delete da session e a transacao e comitada/persistida caso o
	 * objeto seja deletado no banco. O objeto a ser recebido aqui, deve ter o
	 * parametro id informado no construtor, pois esse parametro sera usado no banco
	 * de dados, para definir o objeto que sera excluido.
	 *
	 * @param turnoASerDeletado objeto a ser excluido no banco.
	 */
	public boolean delete(int id) {
		return super.delete(Turno.class, id);
	}

	/**
	 * Recebe um inteiro que referencia o Id da {@link Turno} a ser buscada.
	 * <p>
	 * O objeto a ser buscado deve ter o parametro Id valido no banco de dados.
	 *
	 * @param index objeto a ser retornado.
	 */
	public Turno get(int id) {
		return super.get(Turno.class, id);
	}

	/**
	 * Busca todos os elementos do tipo {@link Turno} e retorna o resultado.
	 * <p>
	 * Atraves de um CriteriaBuilder uma lista do tipo Turno e alimentada com todos
	 * os valores existentes no banco de dados. E o equivalente a query SQL:
	 * SELECT*FROM turnos.
	 */
	public List<Turno> getAll() {
		return super.getAll(Turno.class);
	}
	
	/**
	 * Remove os elementos do tipo {@link Turno} e retorna um boolean indicando sucesso
	 * da operacao.
	 */
	public boolean deleteAll() {
		return super.deleteAll("turno");
	}

}
