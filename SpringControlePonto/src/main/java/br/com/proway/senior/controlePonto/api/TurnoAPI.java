package br.com.proway.senior.controlePonto.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@GetMapping("/obterTodos/")
	ArrayList<TurnoDTO> obterTodos(){
		return turnoService.obterTodos();
	}
	
	@PostMapping("/novo/")
	void salvar(@RequestBody Turno turno) {
		turnoService.salvar(turno);
	}
}
