package br.com.proway.senior.controlePonto.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.controlePonto.model.IntervaloTempo;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.services.JornadaService;


@RestController
@RequestMapping(path = "api/jornada")
public class JornadaAPI {

	private final JornadaService jornadaService;
	
	public JornadaAPI(JornadaService service) {
		this.jornadaService = service;
	}
	
	// Actions
	
	@PostMapping("/marcarPonto/{idPessoa}")
	void marcarPonto(
			@PathVariable("idPessoa") Integer idPessoa,
			@RequestBody Ponto ponto
	)throws Exception 
	{
		jornadaService.marcarPonto(idPessoa, ponto);
	}
	
	// CRUD Jornada
	
	@PostMapping("/jornada/")
	Integer criarJornada(
			@RequestBody Jornada jornada
	)throws Exception 
	{
		return jornadaService.createJornada(jornada);
	}
	
	@GetMapping("/jornada/{idJornada}")
	JornadaDTO getJornada(
			@PathVariable("idJornada") Integer idJornada
	) throws Exception 
	{
		return jornadaService.getJornada(idJornada);
	}
	
	@PostMapping("/jornada/{jornadaId}")
	boolean atualizarJornada(
			@RequestBody Jornada jornada,
			@PathVariable("idJornada") Integer idJornada
	)throws Exception 
	{
		return jornadaService.updateJornada(idJornada, jornada);
	}
	
	@DeleteMapping("/jornada/{idJornada}")
	boolean deleteJornada(
			@PathVariable("idJornada") Integer idJornada
	) throws Exception 
	{
		return jornadaService.deleteJornada(idJornada);
	}
	
	// Filtrando Jornadas
	
	@GetMapping("/jornadas/{idPessoa}")
	ArrayList<JornadaDTO> todasJornadas(
			@PathVariable("idPessoa") Integer idPessoa
	) throws Exception 
	{
		return jornadaService.todasJornadas(idPessoa);
	}
	
	@GetMapping("/jornadaDoDia/{idPessoa}")
	JornadaDTO jornadaDoDia(
			@PathVariable("idPessoa") Integer idPessoa
	) throws Exception 
	{
		return jornadaService.jornadaDoDia(idPessoa);
	}
	
	@GetMapping("/jornadasPorPeriodo/{idPessoa}")
	ArrayList<JornadaDTO> jornadasPorPeriodo(
			@PathVariable("idPessoa") Integer idPessoa,
			@RequestBody IntervaloTempo intervalo
	) throws Exception 
	{
		return jornadaService.jornadasPorPeriodo(
				idPessoa, intervalo.inicio, intervalo.fim
		);
	}
	
	@GetMapping("/minutosTrabalhadosPorPeriodo/{idPessoa}")
	long minutosTrabalhadosNoPeriodo(
			@PathVariable("idPessoa") Integer idPessoa,
			@RequestBody IntervaloTempo intervalo
	) throws Exception 
	{
		return jornadaService.minutosTrabalhadosNoPeriodo(
				idPessoa, intervalo.inicio, intervalo.fim
		);
	}
	
	
}