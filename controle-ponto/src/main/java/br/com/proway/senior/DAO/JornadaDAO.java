package br.com.proway.senior.DAO;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.interfaces.IPessoa;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public final class JornadaDAO {

    private static JornadaDAO instance;
    private Session session;

    private JornadaDAO(Session session) {
        this.session = session;
    }

    /**
     * Se nao existir nenhuma instancia do DAO, cria uma nova instancia e a retorna;
     *
     * @return
     */
    public static JornadaDAO getInstance(Session session) {
        if (instance == null) {
            instance = new JornadaDAO(session);
        }
        return instance;
    }

    /**
     * Recebe um parametro do tipo IPessoa que fornece a idPessoa e a idTurno para
     * cadastrar uma jornada para a pessoa na data do momento do cadastro.
     *
     * @param pessoa
     * @return void
     */
    public void create(Jornada jornada) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.save(jornada);
        } catch (Exception e) {
            e.getMessage();
        }

        session.getTransaction().commit();


    }

    /**
     * Busca uma jornada do banco de dados;
     * <p>
     * Recebe uma id de Jornada, busca no banco de dados e retorna esta jornada.
     *
     * @param id
     * @return
     */
    public Jornada read(int id) {
        return session.get(Jornada.class, id);
    }

    public List<Jornada> readByIdPessoa(IPessoa pessoa) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Jornada> criteria = builder.createQuery(Jornada.class);
        Root<Jornada> root = criteria.from(Jornada.class);
        Query query = session.createQuery(criteria);

        CriteriaQuery<Jornada> rootQuery = criteria.select(root);
        javax.persistence.criteria.Expression<Object> idPessoa = root.get("idPessoa");

        criteria.select(root).where(builder.equal(idPessoa, pessoa.getId()));
        List<Jornada> jornadasPorIdPessoa = query.getResultList();
        return jornadasPorIdPessoa;
    }

    /**
     * Deleta uma jornada espec�fica do banco de dados;
     * <p>
     * Busca uma jornada no banco de dados a partir de sua id e remove esta jornada
     * do banco de dados.
     *
     * @param id
     * @return void
     */
    public void delete(Jornada jornadaASerDeletada) {
        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        try {
            session.delete(jornadaASerDeletada);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.getMessage();
        }

        /**
         * Atualiza um dado espec�fico do banco de dados;
         *
         * Recebe uma id que identifica qual item do banco a ser selecionado. Escolhe
         * qual coluna deve ser alterada com o parametro col e insere o valor data na
         * coluna col;
         *
         * @param id
         * @param col
         * @param data
         */
        public void update (Jornada jornadaASerAtualizada){
            if (!session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            try {
                session.beginTransaction();
                session.update(jornadaASerAtualizada);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.getMessage();
            }

        }

        /**
         * Retorna todos os itens do banco de dados
         *
         * Busca cada itenm do banco de dados, faz o parse toString() dos dados das
         * colunas e insere no array. Insere o array de cada item do banco em um
         * ArrayList<ArrayList<String>> Retorna estes valores neste arraylist
         *
         * @return ArrayList<ArrayList < String>>
         */
        public List<Jornada> readAll () {

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Jornada> criteria = builder.createQuery(Jornada.class);
            Root<Jornada> root = criteria.from(Jornada.class);
            Query query = session.createQuery(criteria);

            List<Jornada> listaTodasJornadas = query.getResultList();
            return listaTodasJornadas;
        }
    }
}
	
	