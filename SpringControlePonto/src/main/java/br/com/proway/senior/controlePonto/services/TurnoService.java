package br.com.proway.senior.controlePonto.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.ListaDePessoas;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.model.TurnoDTO;

/**
 * @author Willian Kenji Nishizawa <willian.kenji@senior.com.br>
 * @author Vitor André Gehrke <vitor.gehrke@senior.com.br>
 * @author Leonardo Pereira <leonardo.pereira@senior.com.br>
 * @author Thiago Barbieri <thiago.barbieri@senior.com.br>
 * @author Enzo Muñoz Moura <enzo.moura@senior.com.br> (Documentation)
 */
@Service
public class TurnoService {
	
	/**
	 * 
	 * Instancia o {@link TurnoController}, criando uma sessao no BD para ser passado como parametro ao mesmo. 
	 */
	TurnoController controllerTurno = new TurnoController(DBConnection.getSession());
	
	/**
	 * Cria uma lista que contem todos os {@link Turno} persistidos no BD;
	 * Converte os mesmos para a sua versao {@link TurnoDTO};
	 * Retorna uma lista criada, que contem todos estes anteriores.    
	 * 
	 * @see Turno
	 * @see TurnoDTO
	 * @see TurnoController
	 * @return listaDTO; {ArrayList<ListaDTO>}. 
	 */
	public ArrayList<TurnoDTO> obterTodos(){
		ArrayList<Turno> listaTurnos = (ArrayList<Turno>) controllerTurno.getAll();
		ArrayList<TurnoDTO> listaDTO = new ArrayList<TurnoDTO>();
		
		for(Turno turno : listaTurnos) {
			listaDTO.add(new TurnoDTO(turno));
		}
		
		return listaDTO;
	}
	
	public void salvar(Turno turno) {
		controllerTurno.create(turno);
	}
	
	public void deletar(int id) throws Exception {
		controllerTurno.delete(id);
	}
	
	public void alterar(int id, Turno turno) throws Exception {
		controllerTurno.update(id, turno);
	}

	/**
	 * Instancia o {@link Turno} desejado e persistido no BD, por ID;	  
	 * Retorna a vesao {@link TurnoDTO} do mesmo (dados especificos em relacao ao Model). 
	 * 
	 * @see Turno
	 * @see TurnoDTO
	 * @see TurnoController
	 * @param id, (int).
	 * @return turno; (TurnoDTO).
	 * @throws @throws Exception; ha a possibilidade de excecoes.
	 */
	public TurnoDTO obter(int id) throws Exception {
		Turno turno = controllerTurno.get(id);
		return(new TurnoDTO(turno));
	}
	
	/**
	 * Instancia o {@link Turno} desejado e persistido no BD, por ID;
	 * Para cada 'ID de pessoa' contido na lista (passada como parametro), eh feito uma verificacao se
	 * o mesmo esta contido na lista de 'ID de pessoas' do {@link Turno} (passado como parametro) (chave estrangeira);
	 * Caso simnão, o 'ID da pessoa' eh adicionado nesta mesma;
	 * 
	 * Depois do processo ser feito com todos os 'ID de pessoas' da lista (parametro), e feito a 
	 * atuaizacao no BD, para substituicao
	 * do {@link Turno} original para com ele atualizado (lista de 'ID de pessoas' modificado).  
	 * 
	 * @see Turno
	 * @see TurnoController
	 * @param objetoListaPessoa; (ListaDePessoas).
	 * @param idTurno; (int).
	 * @throws Exception; ha a possibilidade de excecoes.
	 */
	public void adicionarPessoa(ListaDePessoas objetoListaPessoa, int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);	
		for(Integer id : objetoListaPessoa.listaDeIdPessoa) {
			if(turno.getPessoasNoTurno().contains(id)) {
				continue;
			}
			turno.adicionaPessoaNoTurno(id);			
		}		
		controllerTurno.update(idTurno, turno);
	}
	
	/**
	 * Instancia o {@link Turno} desejado e persistido no BD, por ID;
	 * Para cada 'ID de pessoa' contido na lista (passada como parametro), eh feito uma verificacao se
	 * o mesmo esta contido na lista de 'ID de pessoas' do {@link Turno} (passado como parametro) (chave estrangeira);
	 * Caso sim, o 'ID da pessoa' eh removida desta mesma;
	 * 
	 * Depois do processo ser feito com todos os 'ID de pessoas' da lista (parametro), e feito a 
	 * atuaizacao no BD, para substituicao
	 * do {@link Turno} original para com ele atualizado (lista de 'ID de pessoas' modificado).  
	 * 
	 * @see Turno
	 * @see TurnoController
	 * @param objetoListaPessoa; (ListaDePessoas).
	 * @param idTurno; (int).
	 * @throws Exception; ha a possibilidade de excecoes.
	 */
	public void removerPessoa(ListaDePessoas objetoListaPessoa, int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);
		for(Integer id : objetoListaPessoa.listaDeIdPessoa) {
			if(turno.getPessoasNoTurno().contains(id)) {
				turno.getPessoasNoTurno().remove(id);
			}									
		}
		controllerTurno.update(idTurno, turno);
	}

	/**
	 * Instancia o {@link Turno} desejado e persistido no BD, por ID;
	 * Retorna a lista das 'pessoas' que estao nele (chave estrangeira);
	 * 
	 * @see Turno
	 * @see TurnoController
	 * @param idTurno; (int).
	 * @return listaPessoas; (ArrayList<Integer>).
	 * @throws Exception; ha a possibilidade de excecoes.
	 */
	public ArrayList<Integer> obterTodasPessoasDoTurno(int idTurno) throws Exception {
		Turno turno = controllerTurno.get(idTurno);		
		return turno.getPessoasNoTurno(); 
	}
	
	/**
	 *  Instancia uma lista com todos os {@link Turno} persistidos no BD; 
	 *  
	 *  Para cada {@link Turno} persistido, eh isntanciada a lista das 'pessoas' que estao nele
	 *  (chave estrangeira);
	 *  Para cada 'pessoa' deste mesmo {@link Turno}, eh feito uma verificacao se o 'id' dela eh o mesmo que 
	 *  o informado como 'parametro'. Caso sim, a lista de {@link Turno} da 'pessoa' (informada no parametro) criada
	 *  anteriormente e antes vazia, acaba adicionando este determinado {@link Turno} a ela (chave estrangeira);
	 *  
	 *  Depois do processo ser feito com todos os {@link Turno} persistidos, a lista 
	 *  de {@link Turno} da pessoa eh retornado.  
	 * 
	 * @see Turno
	 * @see TurnoController
	 * @param idPessoa, (int).
	 * @return listaDeTurnosDaPessoa; (List<Turno>). 
	 * @throws Exception; ha a possibilidade de excecoes. 
	 */
	public List<Turno> turnoDaPessoa(int idPessoa) throws Exception {
		List<Turno> listaDeTurnoDoBanco = controllerTurno.getAll();
		List<Turno> listaDeTurnosDaPessoa = new ArrayList<Turno>();
		for (Turno turno : listaDeTurnoDoBanco) {
			List<Integer> listaDePessoasDoTurno = turno.getPessoasNoTurno();
			for (Integer idDaPessoaNoTurno : listaDePessoasDoTurno) {
				if(idPessoa == idDaPessoaNoTurno) {
					listaDeTurnosDaPessoa.add(turno);
				}
			}
		}
		return listaDeTurnosDaPessoa;
	}
	
}
