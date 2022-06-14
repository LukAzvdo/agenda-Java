/*
 */
package br.com.esucri.agenda.procedimento;

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

@Path("procedimentos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProcedimentoController {
    
    @Inject
    private ProcedimentoService procedimentoService;

    @GET
    public List<Procedimento> findAll() {
        return this.procedimentoService.findAll();
    }

    @GET
    @Path("{id}")
    public Procedimento findById(@PathParam("id") Long id) {
        Procedimento procedimento = this.procedimentoService.findById(id);
        if (procedimento == null) {
            throw new WebApplicationException("Procedimento não encontrado", Response.Status.NOT_FOUND);
        }
        return procedimento;
    }

    @POST
    public Procedimento add(Procedimento procedimento) {
        return this.procedimentoService.add(procedimento);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        Procedimento procedimento = this.findById(id);
        if (procedimento == null) {
            throw new NotFoundException("Procedimento não encontrado");
        }
        this.procedimentoService.remove(procedimento);
    }

    @PUT
    @Path("{id}")
    public Procedimento update(@PathParam("id") Long id, Procedimento procedimentoAtualizado) {
        Procedimento procedimentoEncontrado = this.findById(id);
        if (procedimentoEncontrado == null) {
            throw new NotFoundException("Procedimento não encontrado");
        }
        procedimentoAtualizado.setId(id);
        return this.procedimentoService.update(procedimentoAtualizado);
    }
}
