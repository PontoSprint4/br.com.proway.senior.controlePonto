package br.com.proway.senior.DAO;

import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Classe responsável pela persistência do objeto {@link PessoaDoPonto} no
 * banco de dados.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version Sprint5
 */
public class PessoaDAO implements ICRUD<PessoaDoPonto> {

    private static PessoaDAO instance;
    private Session session;

    /**
     * Construtor que recebe a sessão.
     *
     * @param session sessão recebida como parâmetro
     */
    private PessoaDAO(Session session) {
        this.session = session;
    }

    /**
     * Método responsável por instanciar {@link PessoaDAO} recebendo uma sessão
     * A sessão recebida passa pela checagem se é nula, caso positivo, uma
     * nova sessão instanciada, caso negativo, a sessão que já está aberta é
     * retornada.
     *
     * @param session Sessão ativa
     * @return instance a instancia da sessão.
     */
    public static PessoaDAO getInstance(Session session) {
        if (instance == null) {
            instance = new PessoaDAO(session);
        }
        return instance;
    }

    /**
     * Recebe um objeto {@link PessoaDoPonto} e insere no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id nulo no
     * construtor, pois esse parâmetro será atribuído no banco de dados.
     *
     * @param pessoaASerInserida objeto a ser inserido no banco.
     */
    public void insert(PessoaDoPonto pessoaASerInserida) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.save(pessoaASerInserida);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Recebe um objeto {@link PessoaDoPonto} e altera no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir as outras informações que serão atualizadas.
     *
     * @param pessoaASerAlterada objeto a ser alterado no banco.
     */
    public boolean update(PessoaDoPonto pessoaASerAlterada) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.update(pessoaASerAlterada);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recebe um objeto {@link PessoaDoPonto} e deleta no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é deletado usando o método delete da session e a transação é
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir o objeto que será excluído.
     *
     * @param pessoaASerDeletada objeto a ser excluído no banco.
     */
    public boolean delete(PessoaDoPonto pessoaASerDeletada) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.delete(pessoaASerDeletada);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recebe um inteiro que referencia o Id da {@link PessoaDAO} a ser buscada.
     * <p>
     * O objeto a ser buscado deve ter o parâmetro Id válido no banco de dados.
     *
     * @param index objeto a ser retornado.
     */
    public PessoaDoPonto get(int index) {
        return session.get(PessoaDoPonto.class, index);
    }

    /**
     * Busca todos os elementos do tipo {@link PessoaDoPonto} e retorna o resultado.
     * <p>
     * Através de um CriteriaBuilder uma lista do tipo PessoaDoPonto é
     * alimentada com todos os valores existentes no banco de dados. É o
     * equivalente a query SQL: SELECT*FROM pontos.
     */
    public List<PessoaDoPonto> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PessoaDoPonto> criteria = builder.createQuery(PessoaDoPonto.class);
        criteria.from(PessoaDoPonto.class);
        return session.createQuery(criteria).getResultList();
    }


//		CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaQuery<PessoaDoPonto> criteria = builder.createQuery(PessoaDoPonto.class);
//		Root<PessoaDoPonto> root = criteria.from(PessoaDoPonto.class);
//		Query query = session.createQuery(criteria);
//
//		CriteriaQuery<PessoaDoPonto> rootQuery = criteria.select(root);
//		
//		criteria.select(root).where(builder.equal(, id));		
//		return (PessoaDoPonto) query.getSingleResult();
//}
}
