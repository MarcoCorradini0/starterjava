package it.marcocorradini.gelateria.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import it.marcocorradini.gelateria.entity.GelatoPrenotazioneEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GelatoPrenotazioneRepository implements PanacheRepository<GelatoPrenotazioneEntity> {
}
