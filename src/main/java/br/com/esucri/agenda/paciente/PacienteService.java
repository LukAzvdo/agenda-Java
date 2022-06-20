/*
 */

package br.com.esucri.agenda.paciente;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@Stateless
public class PacienteService {

   @PersistenceContext(unitName = "AgendasPU")
    private EntityManager entityManager;

    public List<Paciente> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Paciente p", Paciente.class) // JPQL
                .getResultList();
    }

    public Paciente findById(Long id) {
        Paciente paciente = entityManager.find(Paciente.class, id);
        if(paciente == null) {
            throw new NotFoundException("Paciente com o id " + id + " não encontrado");
        }
        return paciente;
    }

    public Paciente add(Paciente paciente) {
        validaNome(paciente);
        validaExistenciaPaciente(paciente);
        entityManager.persist(paciente);
        return paciente;
    }
    
    public Paciente update(Paciente paciente) {    
        Long id = paciente.getId();
        findById(id);        
        validaNome(paciente);   
        return entityManager.merge(paciente);
    }

    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    private void validaNome(Paciente paciente) {
        if (paciente.getNome().length()< 5) {
            throw new BadRequestException("O nome do paciente não pode conter menos que cinco caracteres");
        }
    }

    private void validaExistenciaPaciente(Paciente paciente) {
        List<Paciente> resultList = entityManager
                .createQuery("SELECT p FROM Paciente p WHERE LOWER(p.nome) = :nome", Paciente.class)
                .setParameter("nome", paciente.getNome().toLowerCase())
                .getResultList();
        
        if (resultList != null && !resultList.isEmpty()) {
            throw new BadRequestException("O paciente já está cadastrado em nossa base");
        }
    }
    
}
