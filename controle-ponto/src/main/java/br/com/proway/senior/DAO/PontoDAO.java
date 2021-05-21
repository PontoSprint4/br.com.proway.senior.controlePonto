package br.com.proway.senior.DAO;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.interfaces.ICRUD;

/**
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @version Sprint5
 * @author Vitor Andr� Gehrke <vitor.gehrke@senior.com.br>
 * @version Sprint5 Testes
 * @version Sprint6
 * @author Leonardo Pereira <leonardo.pereira@senior.com.br>
 *
 */

public final class PontoDAO extends GenericDAO<Ponto> {

	private static PontoDAO instance;
	private Session session;

	/**
	 * Construtor que recebe a sessão.
	 *
	 * @param sessao recebida como parametro
	 */
	private PontoDAO(Session session) {
		this.session = session;
	}

	/**
	 * Metodo responsavel por instanciar {@link PontoDAO} recebendo uma sessao
	 * A sessao recebida passa pela checagem se eh nula, caso positivo, uma nova
	 * sessoa sera instanciada, caso negativo, a sessao que ja esta aberta eh
	 * retornada.
	 *
	 * @param session sessao ativa
	 * @return instancia da sessao.
	 */
	public static PontoDAO getInstance(Session session) {
		if (instance == null)
			instance = new PontoDAO(session);
		return instance;
	}

	/**
	 * Recebe um objeto {@link Ponto} e insere no banco de dados. Na Classe
	 * GenericDAO Eh realizado um  teste para saber se a transacao atual esta 
	 * ativa, se estiver eh retornada, caso contrario eh iniciada uma nova
	 * transacao com o banco. O objeto eh salvo usando o metodo  - save - 
	 * da session e a transacao eh comitada/persistida caso o objeto seja 
	 * persistido no banco. 
	 * O objeto a ser recebido aqui, deve ter o parametro id nulo no construtor,
	 * pois esse parametro sera atribuido no banco de dados.
	 * 
	 * 
	 * @param pontoASerInserido  : objeto a ser inserido no banco.
	 */
	public Integer create(Ponto pontoASerInserido) {
		return super.create(pontoASerInserido);

	}

	/**
	 * Recebe um objeto {@link Ponto} e altera no banco de dados. Eh realizado um
	 * teste para saber se a transacao atual esta ativa, se estiver eh retornada,
	 * caso contrario eh iniciada uma nova transacao com o banco. O objeto eh
	 * salvo usando o metodo save da session e a transacao eh comitada/persistida
	 * caso o objeto seja persistido no banco. O objeto a ser recebido aqui, deve
	 * ter o parametro id informado no construtor, pois esse parameto sera usado
	 * no banco de dados, para definir as outras informacoes que serao
	 * atualizadas.
	 * 
	 * @param pontoASerAlterado objeto a ser alterado no banco.
	 */
	public boolean update(Ponto pontoASerAlterado) {
		return super.update(pontoASerAlterado);
	}

	/**
	 * Recebe um objeto {@link Ponto} e exclui no banco de dados. Eh realizado um
	 * teste para saber se a transacao atual esta ativa, se estiver eh retornada,
	 * caso contrario eh iniciada uma nova transacao com o banco. O objeto eh
	 * salvo usando o metodo save da session e a transacao eh comitada/persistida
	 * caso o objeto seja persistido no banco. O objeto a ser recebido aqui, deve
	 * ter o parametro id informado no construtor, pois esse parameto sera usado
	 * no banco de dados, para definir as outras informacoes que serao
	 * excluidas.
	 * 
	 * @param pontoASerDeletado objeto a ser excluído no banco.
	 */

	public boolean delete(int id) {
		return super.delete(Ponto.class, id);
	}

	/**
	 * Recebe um inteiro que referencia o Id do ponto a ser recebido.
	 * 
	 * O objeto a ser buscado deve ter o parametro Id valido no banco de dados.
	 * 
	 * @param id objeto a ser retornado.
	 * @throws Exception
	 */

	public Ponto get(int id) {
		return super.get(Ponto.class, id);
	}

	/**
	 * Busca todos os elementos do tipo {@link Ponto} e retorna o resultado.
	 * 
	 * Através de um CriteriaBuilder uma lista do tipo Ponto eh alimentada com
	 * todos os valores existentes no banco de dados. Eh o equivalente a query SQL:
	 * SELECT*FROM pontos.
	 * 
	 */

	public List<Ponto> getAll() {
		return super.getAll(Ponto.class);
	}
	
	/**
     * Remove todos os elementos do tipo {@link Jornada} e retorna um boolean
     * para sucesso da operacao.
     */
	public boolean deleteAll() {
		return super.deleteAll("ponto");
	}

}
