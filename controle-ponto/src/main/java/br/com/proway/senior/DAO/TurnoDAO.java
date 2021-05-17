package br.com.proway.senior.DAO;

import br.com.proway.senior.model.Turno;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Classe respons�vel pela persist�ncia do objeto {@link Turno} no
 * banco de dados.
 *
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version Sprint5
 */
public class TurnoDAO implements ICRUD<Turno> {

    private static TurnoDAO instance;
    private Session session;

    /**
     * Construtor que recebe a sess�o.
     *
     * @param session sess�o recebida como par�metro
     */
    private TurnoDAO(Session session) {
        this.session = session;
    }

    /**
     * M�todo respons�vel por instanciar {@link TurnoDAO} recebendo uma sess�o
     * A sess�o recebida passa pela checagem se � nula, caso positivo, uma
     * nova sess�o instanciada, caso negativo, a sess�o que j� est� aberta �
     * retornada.
     *
     * @param session Sess�o ativa
     * @return instance a instancia da sess�o.
     */
    public static TurnoDAO getInstance(Session session) {
        if (instance == null) {
            instance = new TurnoDAO(session);
        }
        return instance;
    }

    /**
     * Recebe um objeto {@link Turno} e insere no banco de dados.
     * � realizado um teste para saber se a transa��o atual est� ativa, se
     * estiver � retornada, caso contr�rio � iniciada uma nova transa��o com
     * o banco.
     * O objeto � salvo usando o m�todo save da session e a transa��o �
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o par�metro id nulo no
     * construtor, pois esse par�metro ser� atribu�do no banco de dados.
     *
     * @param turnoASerInserido objeto a ser inserido no banco.
     */
    public void insert(Turno turnoASerInserido) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(turnoASerInserido);
        session.getTransaction().commit();
    }

    /**
     * Recebe um objeto {@link Turno} e altera no banco de dados.
     * � realizado um teste para saber se a transa��o atual est� ativa, se
     * estiver � retornada, caso contr�rio � iniciada uma nova transa��o com
     * o banco.
     * O objeto � salvo usando o m�todo save da session e a transa��o �
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o par�metro id informado no
     * construtor, pois esse par�metro ser� usado no banco de dados, para
     * definir as outras informa��es que ser�o atualizadas.
     *
     * @param turnoASerAtualizado objeto a ser alterado no banco.
     */
    public boolean update(Turno turnoASerAtualizado) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.update(turnoASerAtualizado);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    /**
     * Recebe um objeto {@link Turno} e deleta no banco de dados.
     * � realizado um teste para saber se a transa��o atual est� ativa, se
     * estiver � retornada, caso contr�rio � iniciada uma nova transa��o com
     * o banco.
     * O objeto � deletado usando o m�todo delete da session e a transa��o �
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o par�metro id informado no
     * construtor, pois esse par�metro ser� usado no banco de dados, para
     * definir o objeto que ser� exclu�do.
     *
     * @param turnoASerDeletado objeto a ser exclu�do no banco.
     */
    public boolean delete(Turno turnoASerDeletado) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.delete(turnoASerDeletado);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recebe um inteiro que referencia o Id da {@link Turno} a ser buscada.
     * <p>
     * O objeto a ser buscado deve ter o par�metro Id v�lido no banco de dados.
     *
     * @param index objeto a ser retornado.
     */
    public Turno get(int index) {
        return session.get(Turno.class, index);
    }

    /**
     * Busca todos os elementos do tipo {@link Turno} e retorna o resultado.
     * <p>
     * Atrav�s de um CriteriaBuilder uma lista do tipo Turno �
     * alimentada com todos os valores existentes no banco de dados. � o
     * equivalente a query SQL: SELECT*FROM turnos.
     */
    public List<Turno> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Turno> criteria = builder.createQuery(Turno.class);
        criteria.from(Turno.class);
        return session.createQuery(criteria).getResultList();
    }

}
