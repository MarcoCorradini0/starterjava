package it.marcocorradini.gelateria.resource;

import it.marcocorradini.gelateria.entity.GelatoPrenotazioneEntity;
import it.marcocorradini.gelateria.model.GelatoPrenotazioneModel;
import it.marcocorradini.gelateria.repository.GelatoPrenotazioneRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;

@Path("/prenotazioni")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GelatoPrenotazioneResource {
    @Inject
    GelatoPrenotazioneRepository repository;

    @GET
    public Response getAll(){
        return Response.ok(
                repository.list("eliminato=false")
        ).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        GelatoPrenotazioneEntity entity = repository.findById(id);
        if (entity==null||Boolean.TRUE.equals(entity.eliminato)){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prenotazione non trovata").build();
        }
        return Response.ok(entity).build();
    }

    @POST
    @Transactional
    public Response create(GelatoPrenotazioneModel model){
        Response error=validate(model);
        if (error!=null)return error;

        GelatoPrenotazioneEntity entity=new GelatoPrenotazioneEntity();
        updateEntity(entity, model);
        entity.eliminato = false;
        repository.persist(entity);
        model.id=entity.id;
        return Response.status(Response.Status.CREATED)
                .entity(model).build();
    }

    private void updateEntity(GelatoPrenotazioneEntity entity, GelatoPrenotazioneModel model) {
        entity.nomeCliente=model.nomeCliente;
        entity.gusto=model.gusto;
        entity.quantita= model.quantita;
        entity.dataRitiro=model.dataRitiro;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id) {
        GelatoPrenotazioneEntity entity=repository.findById(id);
        if (entity==null||Boolean.TRUE.equals(entity.eliminato)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prenotazione non trovata").build();
        }
        entity.eliminato=true;
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, GelatoPrenotazioneModel model) {
        GelatoPrenotazioneEntity entity=repository.findById(id);
        if (entity == null || Boolean.TRUE.equals(entity.eliminato)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Prenotazione non trovata").build();
        }
        Response error=validate(model);
        if (error!=null)return error;
        updateEntity(entity, model);
        return Response.ok(entity).build();
    }

    private Response validate(GelatoPrenotazioneModel model) {
        if (model.nomeCliente == null || model.nomeCliente.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Nome non valido").build();
        }
        if (model.gusto == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Gusto non valido").build();
        }
        if (model.quantita == null || model.quantita <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Quantità non valida").build();
        }
        if (model.dataRitiro == null || model.dataRitiro.isBefore(LocalDate.now())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Data ritiro non valido").build();
        }
        return null;
    }
}
