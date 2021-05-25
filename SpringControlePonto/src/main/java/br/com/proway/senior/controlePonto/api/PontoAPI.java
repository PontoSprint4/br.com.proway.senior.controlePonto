package br.com.proway.senior.controlePonto.api;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.PontoDTO;
import br.com.proway.senior.controlePonto.services.PontoService;

@RestController
@RequestMapping(path = "api/ponto")
public class PontoAPI {
	
private final PontoService pontoService;
	
	public PontoAPI(PontoService service) {
		this.pontoService = service;
	}
	
	@PostMapping("/")
	Integer criarPonto(
			@RequestBody Ponto Ponto
	)throws Exception 
	{
		return pontoService.createPonto(Ponto);
	}
	
	@GetMapping("/{idPonto}")
	PontoDTO getPonto(
			@PathVariable("idPonto") Integer idPonto
	) throws Exception 
	{
		return pontoService.getPonto(idPonto);
	}
	
	@PostMapping("/{PontoId}")
	boolean atualizarPonto(
			@RequestBody Ponto Ponto,
			@PathVariable("idPonto") Integer idPonto
	)throws Exception 
	{
		return pontoService.updatePonto(idPonto, Ponto);
	}
	
	@DeleteMapping("/{idPonto}")
	boolean deletePonto(
			@PathVariable("idPonto") Integer idPonto
	) throws Exception 
	{
		return pontoService.deletePonto(idPonto);
	}

}
