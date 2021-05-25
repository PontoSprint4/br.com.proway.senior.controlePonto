package br.com.proway.senior.controlePonto.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.model.TurnoDTO;
import br.com.proway.senior.controlePonto.services.TurnoService;



@RestController
@RequestMapping(path = "api/turno")
public class TurnoAPI {

	private final TurnoService turnoService;
	
	public TurnoAPI(TurnoService service) {
		this.turnoService = service;
	}
	
	@GetMapping("/obterTodos")
	ArrayList<TurnoDTO> obterTodos(){
		return turnoService.obterTodos();
	}
	
	@PostMapping("/salvar/")////////////////////////////////
	void salvar(@RequestBody Turno turno) {
		turnoService.salvar(turno);
	}
	
	@DeleteMapping("/deletar/{id}")///////////////////////////
	void deletar(@PathVariable int id) throws Exception {
		turnoService.deletar(id);
	}
	
	@PutMapping("/alterar/{id}")
	void alterar(@PathVariable int id, @RequestBody Turno turno) throws Exception{
		turnoService.alterar(id, turno);
	}
	
	@GetMapping("/obter/{id}")
	TurnoDTO obter(@PathVariable int id) throws Exception{
		return turnoService.obter(id);
	}
	
	@PutMapping("/adicionarPessoas/{idPessoa}/{idTurno}")
	void adicionarPessoaNoTurno(@PathVariable Integer idPessoa, @PathVariable int idTurno) throws Exception{
		turnoService.adicionarPessoa(idPessoa, idTurno);
	}
	
	@PutMapping("/removerPessoas/{idPessoa}/{idTurno}")
	void removerPessoaNoTurno(@PathVariable Integer idPessoa, @PathVariable int idTurno) throws Exception{
		turnoService.removerPessoa(idPessoa, idTurno);
	}
	
	@GetMapping("/obter/todasPessoas/{idTurno}")
	ArrayList<Integer> todasPessoasDoTurno(@PathVariable int idTurno) throws Exception{
		return turnoService.obterTodasPessoasDoTurno(idTurno);
	}
	
	@GetMapping("/obter/turnoDaPessoa/{idPessoa}")
	List<Turno> turnoDaPessoa(@PathVariable int idPessoa) throws Exception{
		return turnoService.turnoDaPessoa(idPessoa);
	}	
}
