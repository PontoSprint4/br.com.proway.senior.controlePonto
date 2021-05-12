package br.com.proway.senior.model;

import java.time.LocalDateTime;

public class Ponto {

    private Integer idPonto;
    private Integer idJornada;
    private LocalDateTime momentoPonto;

    public Ponto(Integer idPonto, LocalDateTime momentoPonto) {
        this.idPonto = idPonto;
        this.momentoPonto = momentoPonto;
    }

    public Ponto(Integer idPonto, Jornada jornada, LocalDateTime momentoPonto) throws Exception {
        this.idPonto = idPonto;
        this.idJornada = this.validaJornada(jornada);
        this.momentoPonto = momentoPonto;
    }

    private Integer validaJornada(Jornada jornada) throws Exception{
        if (jornada != null) {
            return jornada.getIdJornada();
        }
        throw new Exception("Jornada Recebida igual a nulo");
    }

    public int getIdPonto() {
        return idPonto;
    }

    public void setIdPonto(int idPonto) {
        this.idPonto = idPonto;
    }

    public Integer getIdJornada() {
        return idJornada;
    }


    public void setIdJornada(int idJornada) { // idJornada
        this.idJornada = idJornada;
    }

    public LocalDateTime getMomentoPonto() {
        return momentoPonto;
    }

    public void setMomentoPonto(LocalDateTime momentoPonto) {
        this.momentoPonto = momentoPonto;
    }

    @Override
    public String toString() {
        return "Ponto [idPonto=" + idPonto + ", idJornada=" + idJornada + ", momentoPonto=" + momentoPonto + "]";
    }

}
