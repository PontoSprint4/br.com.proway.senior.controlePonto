package br.com.proway.senior.DAO;

import br.com.proway.senior.model.PessoaDoPonto;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Classe respons�vel pela persist�ncia do objeto {@link PessoaDoPonto} no
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
     * Construtor que recebe a sess�o.
     *
     * @param session sess�o recebida como par�metro
     */
    private PessoaDAO(Session session) {
        this.session = session;
    }

    /**
     * M�todo respons�vel por instanciar {@link PessoaDAO} recebendo uma sess�o
     * A sess�o recebida passa pela checagem se � nula, caso positivo, uma
     * nova sess�o instanciada, caso negativo, a sess�o que j� est� aberta �
     * retornada.
     *
     * @param session Sess�o ativa
     * @return instance a instancia da sess�o.
     */
    public static PessoaDAO getInstance(Session session) {
        if (instance == null) {
            instance = new PessoaDAO(session);
        }
        return instance;
    }

    /**
     * Recebe um objeto {@link PessoaDoPonto} e insere no banco de dados.
     * � realizado um teste para saber se a transa��o atual est� ativa, se
     * estiver � retornada, caso contr�rio � iniciada uma nova transa��o com
     * o banco.
     * O objeto � salvo usando o m�todo save da session e a transa��o �
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o par�metro id nulo no
     * construtor, pois esse par�metro ser� atribu�do no banco de dados.
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
     * � realizado um teste para saber se a transa��o atual est� ativa, se
     * estiver � retornada, caso contr�rio � iniciada uma nova transa��o com
     * o banco.
     * O objeto � salvo usando o m�todo save da session e a transa��o �
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o par�metro id informado no
     * construtor, pois esse par�metro ser� usado no banco de dados, para
     * definir as outras informa��es que ser�o atualizadas.
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
     * � realizado um teste para saber se a transa��o atual est� ativa, se
     * estiver � retornada, caso contr�rio � iniciada uma nova transa��o com
     * o banco.
     * O objeto � deletado usando o m�todo delete da session e a transa��o �
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o par�metro id informado no
     * construtor, pois esse par�metro ser� usado no banco de dados, para
     * definir o objeto que ser� exclu�do.
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
     * O objeto a ser buscado deve ter o par�metro Id v�lido no banco de dados.
     *
     * @param index objeto a ser retornado.
     */
    public PessoaDoPonto get(int index) {
        return session.get(PessoaDoPonto.class, index);
    }

    /**
     * Busca todos os elementos do tipo {@link PessoaDoPonto} e retorna o resultado.
     * <p>
     * Atrav�s de um CriteriaBuilder uma lista do tipo PessoaDoPonto �
     * alimentada com todos os valores existentes no banco de dados. � o
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
