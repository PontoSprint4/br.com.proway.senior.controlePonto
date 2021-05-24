package br.com.proway.senior.DAO;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import br.com.proway.senior.model.Jornada;
import br.com.proway.senior.model.Ponto;
import br.com.proway.senior.model.interfaces.IPessoa;

/**
 * @author Tharlys <tharlys.@senior.com.br>
 * @author Vithor A <vithor.@senior.com.br>
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version Documentação
 */
public final class JornadaDAO extends GenericDAO<Jornada>  {

    private static JornadaDAO instance;
    private final Session session;

    /**
     * Construtor privado que recebe a sessão.
     *
     * @param session sessão recebida como parâmetro
     */
    private JornadaDAO(Session session) {
        this.session = session;
    }

    /**
     * Método responsável por instanciar {@link JornadaDAO} recebendo uma
     * sessão. A sessão recebida passa pela checagem se é nula, caso
     * positivo, uma nova sessão instanciada, caso negativo, a sessão que já
     * está aberta é retornada.
     *
     * @param session Sessão ativa
     * @return instance a instancia da sessão.
     */
    public static JornadaDAO getInstance(Session session) {
        if (instance == null) {
            instance = new JornadaDAO(session);
        }
        return instance;
    }

    /**
     * Insere no banco uma nova jornada.
     * Recebe como parâmetro um objeto do tipo {@link Jornada}.
     * Verifica se a transação está ativa, e cria uma nova, se não estiver
     * ativa, caso já tenha uma transação ativa, retorna-a.
     * O objeto jornada, informado no parâmetro é salvo, utilizando o método
     * sabe da classe Session. Em caso de sucesso a transação é comitada e
     * persistida no banco de dados. A jornada informada deve ter o id null
     * no construtor, por será criado pelo banco de dados.
     *
     * @param jornada Jornada a ser cadastrada no banco de dados.
     */
    
    public Integer create(Jornada jornada) {
        return super.create(jornada);
    }

    /**
     * Recebe um inteiro que referencia o Id do {@link Ponto} a ser recebido.
     *  <p>
     *  O objeto a ser buscado deve ter o parâmetro Id válido no banco de dados.
     *
     * @param id Id do objeto a ser retornado.
     */
    public Jornada get(int id) {
        return super.get(Jornada.class, id);
    }
    
    /**
     * Recebe um objeto {@link Jornada} e altera no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é salvo usando o método save da session e a transação é
     * comitada/persistida caso o objeto seja persistido no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir as outras informações que serão atualizadas.
     *
     * @param jornadaASerAtualizada objeto a ser alterado no banco.
     */
    public boolean update(Jornada jornadaASerAtualizada) {
        return super.update(jornadaASerAtualizada);
    }
    
    /**
     * Recebe um objeto {@link Jornada} e deleta no banco de dados.
     * É realizado um teste para saber se a transação atual está ativa, se
     * estiver é retornada, caso contrário é iniciada uma nova transação com
     * o banco.
     * O objeto é deletado usando o método delete da session e a transação é
     * comitada/persistida caso o objeto seja deletado no banco.
     * O objeto a ser recebido aqui, deve ter o parâmetro id informado no
     * construtor, pois esse parâmetro será usado no banco de dados, para
     * definir o objeto que será excluído.
     *
     * @param jornadaASerDeletada objeto a ser excluído no banco.
     */
    public boolean delete(int id) {
        return super.delete(Jornada.class, id);
    }

    /**
     * Busca todos os elementos do tipo {@link Jornada} e retorna o resultado.
     * <p>
     * Através de um CriteriaBuilder uma lista do tipo Jornada é alimentada com
     * todos os valores existentes no banco de dados. É o equivalente a query
     * SQL: SELECT*FROM jornadas.
     */
    public List<Jornada> getAll() {
    	return super.getAll(Jornada.class);
    }
    
    /**
     * Remove todos os elementos do tipo {@link Jornada} e retorna um boolean
     * para sucesso da operacao.
     */
    public boolean deleteAll() {
    	return super.deleteAll("jornada");
    }

    /**
     * Retorna as Jornadas de um pessoa específica.
     * <p>
     * Recebe um objeto {@link IPessoa}, que serve de base para a busca,
     * utilizando o CriteriaBuilder, para listar todas as jornadas, onde o id
     * da pessoa, corresponde ao id da pessoa informada no parâmetro.
     *
     * @param pessoa Pessoa de quem se quer obter todas as jornadas.
     * @return jornadasPorIdPessoa Lista de jornadas da pessoa.
     */
    public List<Jornada> readByIdPessoa(int idPessoa) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Jornada> criteria = builder.createQuery(Jornada.class);
        Root<Jornada> root = criteria.from(Jornada.class);
        Query query = session.createQuery(criteria);

        CriteriaQuery<Jornada> rootQuery = criteria.select(root);
        Expression<Object> idRef = root.get("idPessoa");
        criteria.select(root).where(builder.equal(idRef, idPessoa));

        List<Jornada> jornadasPorIdPessoa = query.getResultList();
        return jornadasPorIdPessoa;
    }
    
    // https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/criteria-api-date-time-operations.html
    
    /**
     * Retorna uma lista de jornadas do dia desejado de uma pessoa.
     * 
     * @param idPessoa
     * @param data
     * @return List<Jornada>
     */
    public List<Jornada> obterJornadasDoDia(int idPessoa, LocalDate data) {
        return obterJornadasEntreDatas(idPessoa, data, data);
    }
    
    /**
     * Retorna uma lista de jornadas num intervalo de datas (inclusive) de uma pessoa.
     * 
     * @param idPessoa
     * @param inicio
     * @param fim
     * @return List<Jornada>
     */
    public List<Jornada> obterJornadasEntreDatas(int idPessoa, LocalDate inicio, LocalDate fim) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Jornada> criteria = builder.createQuery(Jornada.class);
        Root<Jornada> root = criteria.from(Jornada.class);
        

        Predicate procuraPessoa = builder.equal(root.get("idPessoa"), idPessoa);
        Predicate procuraData = builder.between(root.get("data"), builder.literal(inicio), builder.literal(fim));
        
        criteria.select(root).where(procuraData); // Primeiro filtro vai na CriteriaQuery
	    builder.and(procuraPessoa); // Filtro adicional vai pelo CriteriaBuilder
	    
        Query query = session.createQuery(criteria);
        List<Jornada> jornadasPorIdPessoa = query.getResultList();
        return jornadasPorIdPessoa;
    }
}
