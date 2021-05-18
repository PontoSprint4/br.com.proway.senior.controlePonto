package br.com.proway.senior.DAO;

import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @version Sprint5
 *
 * AlteraÃ§Ã£o da classe pontoDAO para implementar o CriteriaBuilder.
 *
 */

public class PontoDAO implements ICRUD<Ponto> {

	private static PontoDAO instance;
	private Session session;

	/**
	 * Construtor que recebe a sessão.
	 *
	 * @param session sessão recebida como parâmetro
	 */
	public PontoDAO(Session session) {
		this.session = session;
	}

	/**
	 * Método responsável por instanciar {@link PontoDAO} recebendo uma sessão
     * A sessão recebida passa pela checagem se é nula, caso positivo, uma
     * nova sessão instanciada, caso negativo, a sessão que já está aberta é
     * retornada.
	 *
	 * @param session Sessão ativa
	 * @return instance a instancia da sessão.
	 */
	public static PontoDAO getInstance(Session session) {
		if (instance == null)
			instance = new PontoDAO(session);
		return instance;
	}

    /**
     * Recebe um objeto {@link Ponto} e insere no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id nulo no
     * construtor, pois esse parâmetro será atribuído no banco de dados.
     * @param pontoASerInserido objeto a ser inserido no banco.
     */
	public void insert(Ponto pontoASerInserido) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.save(pontoASerInserido);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
    /**
     * Recebe um objeto {@link Ponto} e altera no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir as outras informações que serão atualizadas.
     * @param pontoASerAlterado objeto a ser alterado no banco.
     */
    public boolean update(Ponto pontoASerAlterado) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			session.update(pontoASerAlterado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
    /**
     * Recebe um objeto {@link Ponto} e deleta no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é deletado usando o método delete da session e a transação é
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir o objeto que será excluído.
     * @param pontoASerDeletado objeto a ser excluído no banco.
     */
	public boolean delete(Ponto pontoASerDeletado) {
		if (!session.getTransaction().isActive()) {
			session.beginTransaction();
		}
		try {
			session.delete(pontoASerDeletado);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	 /**
     * Recebe um inteiro que referencia o Id do ponto a ser recebido.
     * 
     * O objeto a ser buscado deve ter o parâmetro Id válido no banco de dados.
     * 
     * @param index objeto a ser retornado.
     */
	public Ponto get(int index) {
		return session.get(Ponto.class, index);
	}

	 /**
     * Busca todos os elementos do tipo {@link Ponto} e retorna o resultado.
     * 
     * Através de um CriteriaBuilder uma lista do tipo Ponto é alimentada com todos os
     * valores existentes no banco de dados. É o equivalente a query SQL: SELECT*FROM pontos.
     * 
     */
	public List<Ponto> getAll() {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Ponto> criteria = builder.createQuery(Ponto.class);
		criteria.from(Ponto.class);
		List<Ponto> selectedPontos = session.createQuery(criteria).getResultList();
		return selectedPontos;
	}

}
