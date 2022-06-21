/*

 */
package br.com.esucri.agenda.medico;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@Stateless
public class MedicoService {
    
    @PersistenceContext(unitName = "AgendasPU")
    private EntityManager entityManager;

    public List<Medico> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Medico p")
                .getResultList();
    }
    
    public Medico findById(Long id) {
        Medico medico = entityManager.find(Medico.class, id);
        if(medico == null) {
            throw new NotFoundException("Médico com o id " + id + " não encontrado");
        }
        return medico;
    }

    public Medico add(Medico medico) {
        validaNome(medico);
        validaExistenciaCRM(medico);
        entityManager.persist(medico);
        return medico;
    }

    public Medico update(Medico medico) {
        Long id = medico.getId();
        findById(id);        
        validaNome(medico);   
        return entityManager.merge(medico);
    }

    public void remove(Long id) {
        entityManager.remove(findById(id));
    }

    private void validaNome(Medico medico) {
        if (medico.getNome().length()< 5) {
            throw new BadRequestException("O nome do médico não pode conter menos que cinco caracteres");
        }
    }

    private void validaExistenciaCRM(Medico medico) {
        List<Medico> resultList = entityManager
                .createQuery("SELECT m FROM Medico m WHERE (m.crm) = :crm", Medico.class)
                .setParameter("crm", medico.getCrm())
                .getResultList();
        
        if (resultList != null && !resultList.isEmpty()) {
            throw new BadRequestException("O CRM já está cadastrado em nossa base");
        }
    }

}
