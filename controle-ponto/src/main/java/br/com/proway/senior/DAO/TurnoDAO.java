package br.com.proway.senior.DAO;

import br.com.proway.senior.model.Turno;
import br.com.proway.senior.utils.ICRUD;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Classe responsável pela persistência do objeto {@link Turno} no
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
     * Construtor que recebe a sessão.
     *
     * @param session sessão recebida como parâmetro
     */
    private TurnoDAO(Session session) {
        this.session = session;
    }

    /**
     * Método responsável por instanciar {@link TurnoDAO} recebendo uma sessão
     * A sessão recebida passa pela checagem se é nula, caso positivo, uma
     * nova sessão instanciada, caso negativo, a sessão que já está aberta é
     * retornada.
     *
     * @param session Sessão ativa
     * @return instance a instancia da sessão.
     */
    public static TurnoDAO getInstance(Session session) {
        if (instance == null) {
            instance = new TurnoDAO(session);
        }
        return instance;
    }

    /**
     * Recebe um objeto {@link Turno} e insere no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id nulo no
     * construtor, pois esse parâmetro será atribuído no banco de dados.
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
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir as outras informações que serão atualizadas.
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
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é deletado usando o método delete da session e a transação é
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir o objeto que será excluído.
     *
     * @param turnoASerDeletado objeto a ser excluído no banco.
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
     * O objeto a ser buscado deve ter o parâmetro Id válido no banco de dados.
     *
     * @param index objeto a ser retornado.
     */
    public Turno get(int index) {
        return session.get(Turno.class, index);
    }

    /**
     * Busca todos os elementos do tipo {@link Turno} e retorna o resultado.
     * <p>
     * Através de um CriteriaBuilder uma lista do tipo Turno é
     * alimentada com todos os valores existentes no banco de dados. É o
     * equivalente a query SQL: SELECT*FROM turnos.
     */
    public List<Turno> getAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Turno> criteria = builder.createQuery(Turno.class);
        criteria.from(Turno.class);
        return session.createQuery(criteria).getResultList();
    }

}
