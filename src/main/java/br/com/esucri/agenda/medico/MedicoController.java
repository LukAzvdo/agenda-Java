/*

 */
package br.com.esucri.agenda.medico;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("medicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicoController {
    
    @Inject
    private MedicoService medicoService;

    @GET
    public List<Medico> findAll() {
        return this.medicoService.findAll();
    }

    @GET
    @Path("{id}")
    public Medico findById(@PathParam("id") Long id) {
        Medico medico = this.medicoService.findById(id);
        if (medico == null) {
            throw new WebApplicationException("Medico não encontrado", Response.Status.NOT_FOUND);
        }
        return medico;
    }

    @POST
    public Medico add(Medico medico) {
        return this.medicoService.add(medico);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Medico medico = this.findById(id);
        if (medico == null) {
            throw new NotFoundException("Medico não encontrado");
        }
        this.medicoService.remove(medico);
    }

    @PUT
    @Path("{id}")
    public Medico update(@PathParam("id") Long id, Medico medicoAtualizado) {
        Medico medicoEncontrado = this.findById(id);
        if (medicoEncontrado == null) {
            throw new NotFoundException("Medico não encontrado");
        }
        medicoAtualizado.setId(id);
        return this.medicoService.update(medicoAtualizado);
    }
}
