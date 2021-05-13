package br.com.proway.senior.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe Ponto. Estrutura e define o ponto como objeto.
 * @version Sprint 5
 * @author Samuel Levi <samuel.levi@senior.com.br>
 * @author Vanderlei Kleinschmidt <vanderlei.klein@senior.com.br>
 */

@Entity
@Table(name = "pontos")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer idPonto;
    @Column(name = "momentoPonto")
    private LocalDateTime momentoPonto;

    public Ponto() {}

    public Ponto(Integer idPonto, LocalDateTime momentoPonto) {
        this.idPonto = idPonto;
        this.momentoPonto = momentoPonto;
    }

    public Integer getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(Integer idPonto) {
        this.idPonto = idPonto;
    }

    public LocalDateTime getMomentoPonto() {
        return momentoPonto;
    }

    public void setMomentoPonto(LocalDateTime momentoPonto) {
        this.momentoPonto = momentoPonto;
    }

    @Override
    public String toString() {
        return "Ponto{" +
                "idPonto=" + idPonto +
                ", momentoPonto=" + momentoPonto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ponto ponto = (Ponto) o;

        return idPonto.equals(ponto.idPonto);
    }

    @Override
    public int hashCode() {
        return idPonto.hashCode();
    }
}
