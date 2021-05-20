package br.com.proway.senior.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que estrutura a entidade Jornada.
 * Possui um id único e uma data no formato {@link LocalDate}. Toda Jornada é
 * única para uma uma única {@link PessoaDoPonto}
 * ({@link OneToOne}) e possui um conjunto de no máximo 4 {@link Ponto}s,
 * ({@link OneToMany}).
 *
 * @author Lucas Walim <lucas.walim@senior.com.br>
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 * @version Sprint 5
 */
@Entity
public class Jornada {

    /**
     * Atributos da classe jornada
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_jornada")
    private int id;

    @Column(name = "data")
    private LocalDate data;
    
    private PessoaDoPonto pessoa;

    @OneToOne(cascade = CascadeType.ALL)
    private Turno turno;

    @OneToMany(targetEntity = Ponto.class, cascade = CascadeType.ALL)
    public List<Ponto> listaPonto = new ArrayList<Ponto>();

    public Jornada() {
    }

    public Jornada(Integer id, LocalDate data, PessoaDoPonto pessoa, Turno turno) {
        this.id = id;
        this.data = data;
        this.pessoa = pessoa;
        this.turno = turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setPessoa(PessoaDoPonto pessoa) {
        this.pessoa = pessoa;
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

    public List<Ponto> getListaPonto() {
        return listaPonto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jornada jornada = (Jornada) o;
        return id == jornada.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Jornada Nº " + id + ", Data da Jornada: " + data + ", \n" +
                "Colaborador: " + pessoa +
                ", Turno: " + turno + ",\n Pontos registrados: {"
                + listaPonto + "}";
    }


}