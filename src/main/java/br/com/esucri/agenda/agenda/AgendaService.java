/*
 */
package br.com.esucri.agenda.agenda;

import br.com.esucri.agenda.medico.Medico;
import br.com.esucri.agenda.paciente.Paciente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@Stateless
public class AgendaService {
    
    @PersistenceContext(unitName = "AgendasPU")
    private EntityManager entityManager;

    public List<Agenda> findAll() {
        return entityManager
                .createQuery("SELECT a FROM Agenda a")
                .getResultList();
    }

    public Agenda findById(Long id) {    
        Agenda agenda = entityManager.find(Agenda.class, id);
        if(agenda == null) {
            throw new NotFoundException("Agenda com o id " + id + " não encontrada");
        }
        return agenda;
    }

    public Agenda add(Agenda agenda) {
        validaPaciente(agenda);
        validaMedico(agenda);
        validaNome(agenda);
        validaExistenciaAgenda(agenda);
        entityManager.persist(agenda);
        return agenda;
    }

    public Agenda update(Agenda agenda) {
        Long id = agenda.getId();
        findById(id);        
        validaPaciente(agenda);
        validaMedico(agenda);
        validaNome(agenda);   
        return entityManager.merge(agenda);
    }

    public void remove(Long id) {
        entityManager.remove(findById(id));
    }
    
    private void validaPaciente(Agenda agenda) {
        List<Paciente> pacientes = agenda.getPacientes();
        if (pacientes != null && pacientes.size() > 1) {
            throw new WebApplicationException(
                    "Uma agenda não pode conter mais que um paciente",
                    Response.Status.REQUEST_ENTITY_TOO_LARGE
            );
        }
    }

    private void validaMedico(Agenda agenda) {
        List<Medico> medicos = agenda.getMedicos();
        if (medicos != null && medicos.size() > 1) {
            throw new WebApplicationException(
                    "Uma agenda não pode conter mais que um médico",
                    Response.Status.REQUEST_ENTITY_TOO_LARGE
            );
        }
    }
    
    private void validaNome(Agenda agenda) {
        if (agenda.getDescricao().length()< 5) {
            throw new BadRequestException("O nome da agenda não pode conter menos que cinco caracteres");
        }
    }

    private void validaExistenciaAgenda(Agenda agenda) {
        List<Agenda> resultList = entityManager
                .createQuery("SELECT a FROM Agenda a WHERE LOWER(a.descricao) = :descricao", Agenda.class)
                .setParameter("descricao", agenda.getDescricao().toLowerCase())
                .getResultList();
        
        if (resultList != null && !resultList.isEmpty()) {
            throw new BadRequestException("A agenda já está cadastrada em nossa base");
        }
    }
    
}
