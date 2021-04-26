package br.com.proway.senior.DAO;

import java.time.LocalDate;
import java.util.ArrayList;

import br.com.proway.senior.modelos.Ponto;

public final class JornadaDAO {
	private static JornadaDAO instance;
	Ponto ponto = new Ponto();
	public ArrayList<Ponto> pontos = new ArrayList<Ponto>();
	
	private JornadaDAO(Ponto ponto) {
		this.ponto = ponto;
	}
	
	public static JornadaDAO getInstance(Ponto ponto) {
		if (instance == null) {
			instance = new JornadaDAO(ponto);
		}
		return instance;
	}
	
	public boolean cadastrarPonto(Ponto ponto) {
		if(ponto != null) {
			this.pontos.add(ponto);
			return true;
		}
		return false;
	}
	
	public ArrayList<Ponto> buscarTodos() {
		return this.pontos;
	}
	
	public ArrayList<Ponto> buscarTodosPontosPorPessoaId(int id) {
		ArrayList<Ponto> listaPontosBuscada = new ArrayList<Ponto>();
		for(Ponto ponto : pontos) {
			if(ponto.getIdPessoa() == id) {
				listaPontosBuscada.add(ponto);
			}
		}
		return listaPontosBuscada;
	}
	
	public ArrayList<Ponto> buscarTodosPontosData(LocalDate data) {
		ArrayList<Ponto> listaPontosBuscada = new ArrayList<Ponto>();
		for(Ponto ponto : pontos) {
			if(ponto.getDataPonto() == data) {
				listaPontosBuscada.add(ponto);
			}
		}
		return listaPontosBuscada;
	}
	
	public Ponto buscarPontoPorId(int id) {
		for(Ponto ponto : pontos) {
			if(ponto.getIdPonto() == id) {
				return ponto;
			}
		}
		return null;
	}
	
	public void alterarPonto(int id) {
		for(Ponto ponto : pontos) {
			if(ponto.getIdPonto() == id) {

			}
		}
	}
}
