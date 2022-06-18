/*
 */

package br.com.esucri.agenda.paciente;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PacienteService {

   @PersistenceContext(unitName = "AgendasPU")
    private EntityManager entityManager;

    public Paciente findById(Long id) {
        return entityManager.find(Paciente.class, id);
    }

    public Paciente add(Paciente paciente) {
        entityManager.persist(paciente);
        return paciente;
    }

    public void remove(Paciente paciente) {
        entityManager.remove(findById(paciente.getId()));
    }

    public Paciente update(Paciente pacienteAtualizado) {
        entityManager.merge(pacienteAtualizado);
        return pacienteAtualizado;
    }

    public List<Paciente> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Paciente p", Paciente.class) // JPQL
                .getResultList();
    }
}
