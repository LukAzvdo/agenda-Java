/*

 */
package br.com.esucri.agenda.medico;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
        return this.medicoService.findById(id);
    }

    @POST
    public Medico add(Medico medico) {
        return this.medicoService.add(medico);
    }
    
    @PUT
    @Path("{id}") 
    public Medico update(@PathParam("id") Long id, Medico medico) {
        medico.setId(id);
        return this.medicoService.update(medico);        
    }
    
    @DELETE    
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.medicoService.remove(id);
    }

}
