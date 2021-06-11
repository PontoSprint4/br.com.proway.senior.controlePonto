package br.com.proway.senior.controlePonto;

import java.time.LocalTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.proway.senior.controlePonto.api.TurnoAPI;
import br.com.proway.senior.controlePonto.model.ListaDePessoas;
import br.com.proway.senior.controlePonto.model.Turno;
import br.com.proway.senior.controlePonto.services.TurnoService;

@SpringBootApplication
public class ControlePontoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ControlePontoApplication.class, args);
		
		TurnoAPI api = new TurnoAPI(new TurnoService());
			LocalTime horaInicio = LocalTime.now();
			LocalTime horaFim = horaInicio.plusHours(8);
			String nomeTurno = "Wawawewa";
			Turno turno = new Turno(horaInicio, horaFim, nomeTurno);
			Integer id = api.salvar(turno);
			
			Integer idPessoa1 = 44;
			Integer idPessoa2 = 1;
			ListaDePessoas listaPessoas = new ListaDePessoas();
			listaPessoas.add(idPessoa1);
			listaPessoas.add(idPessoa2);
			
			api.adicionarPessoaNoTurno(listaPessoas, id);
	}

}
