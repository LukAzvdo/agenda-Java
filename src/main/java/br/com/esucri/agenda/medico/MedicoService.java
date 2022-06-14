/*

 */
package br.com.esucri.agenda.medico;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class MedicoService {
    
    @PersistenceContext(unitName = "AgendasPU")
    private EntityManager entityManager;

    public Medico findById(Long id) {
        return entityManager.find(Medico.class, id);
    }

    public Medico add(Medico medico) {
        entityManager.persist(medico);
        return medico;
    }

    public void remove(Medico medico) {
        entityManager.remove(findById(medico.getId()));
    }

    public Medico update(Medico medicoAtualizado) {
        entityManager.merge(medicoAtualizado);
        return medicoAtualizado;
    }

    public List<Medico> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Medico p", Medico.class) // JPQL
                .getResultList();
    }
}
