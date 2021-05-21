package br.com.proway.senior.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que estrutura a entidade Jornada.
 * Possui um id �nico e uma data no formato {@link LocalDate}. Toda Jornada �
 * �nica para uma uma �nica {@link PessoaDoPonto}
 * ({@link OneToOne}) e possui um conjunto de no m�ximo 4 {@link Ponto}s,
 * ({@link OneToMany}).
 *
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version Sprint 5
 */
@Entity
public class Jornada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jornada")
    private Integer id;

    @Column(name = "data")
    private LocalDate data;     

    @OneToOne(cascade = CascadeType.ALL)
    private Turno turno;

    @OneToMany(targetEntity = Ponto.class, cascade = CascadeType.ALL)
    public List<Ponto> listaPonto = new ArrayList<Ponto>();
    
    @Column(name = "pessoa_id")
    private Integer idPessoa;

    public Jornada() {
    }

    public Jornada(LocalDate data, int idPessoa, Turno turno) {       
        this.data = data;
        this.idPessoa = idPessoa;
        this.turno = turno;
    }
    
    public Jornada(Integer id, LocalDate data, int idPessoa, Turno turno) {
        this.id = id;
        this.data = data;
        this.idPessoa = idPessoa;
        this.turno = turno;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
    
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
        this.turno = turno;
    }
	 
	public List<Ponto> getListaPonto() {
        return listaPonto;
    }  
	
	public void setListaPonto(Ponto ponto) {
		this.listaPonto.add(ponto);
	}	

}