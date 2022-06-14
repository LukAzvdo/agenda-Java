/*

 */
package br.com.esucri.agenda.agenda;

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

@Path("agendas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AgendaController {
    
   @Inject
    private AgendaService agendaService;
    
    @GET
    public List<Agenda> findAll() {
        return this.agendaService.findAll();
    }
    
    @GET
    @Path("{id}")
    public Agenda findById(@PathParam("id") Long id) {
        return this.agendaService.findById(id);
    }
    
    @POST
    public Agenda add(Agenda agenda) {
        return this.agendaService.add(agenda);
    }
    
    @PUT
    @Path("{id}") 
    public Agenda update(@PathParam("id") Long id, Agenda agenda) {
        agenda.setId(id);
        return this.agendaService.update(agenda);        
    }
    
    @DELETE    
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.agendaService.remove(id);
    }
}
