package br.com.proway.senior.DAO;

import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Tharlys de Souza Dias <tharlys.dias@senior.com.br>
 * @version Sprint5
 * Alteração da classe pontoDAO para implementar o CriteriaBuilder.
 */

public class PontoDAO implements ICRUD<Ponto> {

    private static PontoDAO instance;
    private final Session session;

    /**
     * Construtor que recebe a sessão.
     *
     * @param session sessão recebida como parâmetro
     */
    public PontoDAO(Session session) {
        this.session = session;
    }

    /**
     * Método responsável por instanciar PontoDao recebendo a sessão. A
     * sessão recebida passa pela checagem se é nula, caso positivo, uma nova
     * sessão é instanciada, caso negativo, a sessão que já está aberta é
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


    public void insert(Ponto pontoASerInserido) {
        session.save(pontoASerInserido);
    }

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

    public Ponto get(int index){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ponto> criteria = builder.createQuery(Ponto.class);
        Query query = session.createQuery(criteria);
        Root<Ponto> pontoRoot = criteria.from(Ponto.class);
        CriteriaQuery<Ponto> rootQuery = criteria.select(pontoRoot);
        Expression pontoId = (Expression) pontoRoot.get("id");
        criteria.select(pontoRoot).where(builder.equal(pontoId, index));
        return (Ponto) query.getSingleResult();
    }

    public List<Ponto> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Ponto> criteria = builder.createQuery(Ponto.class);
        criteria.from(Ponto.class);
        return session.createQuery(criteria).getResultList();
    }

}
