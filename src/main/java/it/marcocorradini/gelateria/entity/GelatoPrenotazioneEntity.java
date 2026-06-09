package it.marcocorradini.gelateria.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
public class GelatoPrenotazioneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String nomeCliente;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public GustiGelato gusto;
    @Column(nullable = false)
    public Integer quantita;
    @Column(nullable = false)
    public LocalDate dataRitiro;
    @Column(nullable = false)
    public Boolean eliminato = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public GustiGelato getGusto() {
        return gusto;
    }

    public void setGusto(GustiGelato gusto) {
        this.gusto = gusto;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    public Boolean getEliminato() {
        return eliminato;
    }

    public void setEliminato(Boolean eliminato) {
        this.eliminato = eliminato;
    }
}
