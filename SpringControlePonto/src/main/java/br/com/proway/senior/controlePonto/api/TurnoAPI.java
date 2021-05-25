package br.com.proway.senior.controlePonto.api;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Autowired
//private final TurnoService service;

@RestController
public class TurnoAPI {

	@GetMapping("/obterTodos")
	ArrayList<Turno> obterTodos(){
		return TurnoService.obterTodos();
	}
}
