/*
 */

package br.com.esucri.agenda.paciente;

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

@Path("pacientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteController {
    
    @Inject
    private PacienteService pacienteService;

    @GET
    public List<Paciente> findAll() {
        return this.pacienteService.findAll();
    }

    @GET
    @Path("{id}")
    public Paciente findById(@PathParam("id") Long id) {
        return this.pacienteService.findById(id);
    }
    
    @POST
    public Paciente add(Paciente paciente) {
        return this.pacienteService.add(paciente);
    }

    @PUT
    @Path("{id}") 
    public Paciente update(@PathParam("id") Long id, Paciente paciente) {
        paciente.setId(id);
        return this.pacienteService.update(paciente);        
    }
    
    @DELETE    
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        this.pacienteService.remove(id);
    }

}
