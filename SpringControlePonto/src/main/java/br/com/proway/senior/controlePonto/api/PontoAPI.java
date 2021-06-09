package br.com.proway.senior.controlePonto.api;

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

import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.PontoDTO;
import br.com.proway.senior.controlePonto.services.PontoService;


@RestController
@RequestMapping(path = "api/")
public class PontoAPI {

	private final PontoService pontoService;

	public PontoAPI(PontoService service) {
		this.pontoService = service;
	}
	
	@CrossOrigin
	@PostMapping("/ponto")
	Integer criarPonto(@RequestBody Ponto Ponto) throws Exception {
		return pontoService.createPonto(Ponto);
	}
	
	@CrossOrigin
	@GetMapping("/ponto/{idPonto}")
	PontoDTO getPonto(
				@PathVariable("idPonto") Integer idPonto
			) throws Exception {
		return pontoService.getPonto(idPonto);
	}
	
	@CrossOrigin
	@PutMapping("ponto/{idPonto}")
	boolean atualizarPonto(
				@PathVariable("idPonto") Integer idPonto, 
				@RequestBody Ponto Ponto
			) throws Exception {
		return pontoService.updatePonto(idPonto, Ponto);
	}
	
	@CrossOrigin
	@DeleteMapping("ponto/{idPonto}")
	boolean deletePonto(
				@PathVariable("idPonto") Integer idPonto
			) throws Exception {
		return pontoService.deletePonto(idPonto);
	}
	
	@CrossOrigin
	@GetMapping("/pontos")
	List<PontoDTO> getAllPontos() throws Exception {
		return pontoService.getAll();
	}

}
