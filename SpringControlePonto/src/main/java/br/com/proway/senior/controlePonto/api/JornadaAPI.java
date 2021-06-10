package br.com.proway.senior.controlePonto.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.proway.senior.controlePonto.controller.TurnoController;
import br.com.proway.senior.controlePonto.dbPersistence.DBConnection;
import br.com.proway.senior.controlePonto.model.IntervaloTempo;
import br.com.proway.senior.controlePonto.model.Jornada;
import br.com.proway.senior.controlePonto.model.JornadaDTO;
import br.com.proway.senior.controlePonto.model.Ponto;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.services.JornadaService;

@CrossOrigin
@RestController
@RequestMapping(path = "api/")
public class JornadaAPI {

	private final JornadaService jornadaService;

	public JornadaAPI(JornadaService service) {
		this.jornadaService = service;
	}

	// CRUD Jornada

	@PostMapping("/jornada/")
	Integer criarJornada(@RequestBody Jornada jornada) throws Exception {
		return jornadaService.createJornada(jornada);
	}

	@GetMapping("/jornada/{idJornada}")
	JornadaDTO getJornada(
				@PathVariable("idJornada") Integer idJornada
			) throws Exception {
		return jornadaService.getJornada(idJornada);
	}

	@PutMapping("/jornada/{idJornada}")
	boolean atualizarJornada(
				@RequestBody Jornada jornada, 
				@PathVariable("idJornada") Integer idJornada
			) throws Exception {
		return jornadaService.updateJornada(idJornada, jornada);
	}

	@DeleteMapping("/jornada/{idJornada}")
	boolean deleteJornada(
				@PathVariable("idJornada") Integer idJornada
			) throws Exception {
		return jornadaService.deleteJornada(idJornada);
	}

	// Filtrando Jornadas
	
	@GetMapping("/jornada/dia/{idPessoa}")
	JornadaDTO jornadaDoDia(
				@PathVariable("idPessoa") Integer idPessoa
			) throws Exception {
		return jornadaService.jornadaDoDia(idPessoa);
	}
	
	@GetMapping("/jornadas/todas/{idPessoa}")
	ArrayList<JornadaDTO> todasJornadas(
				@PathVariable("idPessoa") Integer idPessoa
			) throws Exception {
		return jornadaService.todasJornadas(idPessoa);
	}

	@GetMapping("/jornadas/periodo/{idPessoa}")
	ArrayList<JornadaDTO> jornadasPorPeriodo(
				@PathVariable("idPessoa") Integer idPessoa,
				@RequestBody IntervaloTempo intervalo
			) throws Exception {
		return jornadaService.jornadasPorPeriodo(idPessoa, intervalo.inicio, intervalo.fim);
	}

	// Actions

		@PostMapping("/jornada/marcar_ponto/{idPessoa}")
		void marcarPonto(
					@PathVariable("idPessoa") Integer idPessoa, 
					@RequestBody Ponto ponto
				) throws Exception {
			jornadaService.marcarPonto(idPessoa, ponto);
		}
		
		@GetMapping("/jornadas/periodo/trabalhado/{idPessoa}")
		long minutosTrabalhadosNoPeriodo(
					@PathVariable("idPessoa") Integer idPessoa, 
					@RequestBody IntervaloTempo intervalo
				) throws Exception {
			return jornadaService.minutosTrabalhadosNoPeriodo(idPessoa, intervalo.inicio, intervalo.fim);
		}

}