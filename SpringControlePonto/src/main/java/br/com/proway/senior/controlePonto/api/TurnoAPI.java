package br.com.proway.senior.controlePonto.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.controlePonto.model.ListaDePessoas;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.model.TurnoDTO;
import br.com.proway.senior.controlePonto.services.TurnoService;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "api/")
public class TurnoAPI {

	private final TurnoService turnoService;
	
	public TurnoAPI(TurnoService service) {
		this.turnoService = service;
	}
	
	@PostMapping("/turno")
	Integer salvar(@RequestBody Turno turno) {
		return turnoService.salvar(turno);
	}
	
	@DeleteMapping("/turno/{id}")
	boolean deletar(@PathVariable Integer id) throws Exception {
		return turnoService.deletar(id);
	}
	
	@PutMapping("/turno/{id}")
	void alterar(
				@PathVariable Integer id, 
				@RequestBody Turno turno
			) throws Exception{
		turnoService.alterar(id, turno);
	}
	
	@GetMapping("/turno/{id}")
	TurnoDTO obter(@PathVariable Integer id) throws Exception{
		return turnoService.obter(id);
	}
	
	@GetMapping("turnos/")
	ArrayList<TurnoDTO> obterTodos(){
		return turnoService.obterTodos();
	}
	
	// Actions
	
	@PutMapping("/turno/pessoas_add/{idTurno}")
	void adicionarPessoaNoTurno(
				@RequestBody ListaDePessoas idPessoa,
				@PathVariable Integer idTurno
			) throws Exception{
		turnoService.adicionarPessoa(idPessoa, idTurno);
	}
	
	@PutMapping("/turno/pessoas_remove/{idTurno}")
	void removerPessoaNoTurno(
				@RequestBody ListaDePessoas objetoListaPessoa,
				@PathVariable Integer idTurno
			) throws Exception{
		turnoService.removerPessoa(objetoListaPessoa, idTurno);
	}
	
	// Filtros
	
	@GetMapping("/turno/pessoas/{idTurno}")
	ArrayList<Integer> todasPessoasDoTurno(
				@PathVariable Integer idTurno
			) throws Exception{
		return turnoService.obterTodasPessoasDoTurno(idTurno);
	}
	
	@GetMapping("/turno/pessoa/{idPessoa}")
	Turno turnoDaPessoa(@PathVariable Integer idPessoa) throws Exception{
		return turnoService.turnoDaPessoa(idPessoa);
	}	
}
