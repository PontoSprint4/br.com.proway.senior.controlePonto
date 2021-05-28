package br.com.proway.senior.controlePonto.model;

import java.util.ArrayList;
import java.util.List;

public class ListaDePessoas {
	public ListaDePessoas(){
		this.listaDeIdPessoa = new ArrayList<Integer>();
	}
	
	public List<Integer> add(Integer id){
		listaDeIdPessoa.add(id);
		return listaDeIdPessoa;
	}
	
	public List<Integer> remove(Integer id){
		listaDeIdPessoa.remove(id);
		return listaDeIdPessoa;
	}
	
	public List<Integer> get(){
		return listaDeIdPessoa;
	}
	
	public List<Integer> listaDeIdPessoa;
	
}
