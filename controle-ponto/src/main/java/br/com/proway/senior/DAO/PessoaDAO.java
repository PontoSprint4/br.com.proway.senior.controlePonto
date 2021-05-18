package br.com.proway.senior.DAO;

import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Classe responsavel pela persistencia do objeto {@link PessoaDoPonto} no
 * banco de dados.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @version Sprint5
 */
public class PessoaDAO implements ICRUD<PessoaDoPonto> {

    private static PessoaDAO instance;
    private Session session;

    /**
     * Construtor que recebe a sessao.
     *
     * @param session sessao recebida como parametro
     */
    private PessoaDAO(Session session) {
        this.session = session;
    }

    /**
     * Metodo responsavel por instanciar {@link PessoaDAO} recebendo uma sessao
     * A sessao recebida passa pela checagem se e nula, caso positivo, uma
     * nova sessao instanciada, caso negativo, a sessao que ja esta aberta e
     * retornada.
     *
     * @param session sessao ativa
     * @return instance a instancia da sessao.
     */
    public static PessoaDAO getInstance(Session session) {
        if (instance == null) {
            instance = new PessoaDAO(session);
        }
        return instance;
    }

    /**
     * Recebe um objeto {@link PessoaDoPonto} e insere no banco de dados.
     * E realizado um teste para saber se a transacao atual esta ativa, se
     * estiver e retornada, caso contrario e iniciada uma nova transacao com
     * o banco.
     * O objeto e salvo usando o metodo save da session e a transacao e
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parametro id nulo no
     * construtor, pois esse parametro sera atribuido no banco de dados.
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
     * E realizado um teste para saber se a transacao atual esta ativa, se
     * estiver e retornada, caso contratio e iniciada uma nova transacao com
     * o banco.
     * O objeto e salvo usando o metodo save da session e a transacao e
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parametro id informado no
     * construtor, pois esse parametro sera usado no banco de dados, para
     * definir as outras informacoes que serao atualizadas.
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
     * E realizado um teste para saber se a transacao atual esta ativa, se
     * estiver e retornada, caso contrario e iniciada uma nova transacao com
     * o banco.
     * O objeto e deletado usando o metodo delete da session e a transacao e
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o parametro id informado no
     * construtor, pois esse parametro sera usado no banco de dados, para
     * definir o objeto que sera excluido.
     *
     * @param pessoaASerDeletada objeto a ser exclu�do no banco.
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
     * O objeto a ser buscado deve ter o parametro Id valido no banco de dados.
     *
     * @param index objeto a ser retornado.
     */
    public PessoaDoPonto get(int index) {
        return session.get(PessoaDoPonto.class, index);
    }

    /**
     * Busca todos os elementos do tipo {@link PessoaDoPonto} e retorna o resultado.
     * <p>
     * Atraves de um CriteriaBuilder uma lista do tipo PessoaDoPonto e
     * alimentada com todos os valores existentes no banco de dados. E o
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
