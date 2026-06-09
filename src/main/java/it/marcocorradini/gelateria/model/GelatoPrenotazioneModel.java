package it.marcocorradini.gelateria.model;

import it.marcocorradini.gelateria.entity.GustiGelato;

import java.time.LocalDate;

public class GelatoPrenotazioneModel {
    public Long id;
    public String nomeCliente;
    public GustiGelato gusto;
    public Integer quantita;
    public LocalDate dataRitiro;
    public Boolean eliminato;
}
