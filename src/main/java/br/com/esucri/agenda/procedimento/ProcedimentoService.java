/*
 */
package br.com.esucri.agenda.procedimento;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ProcedimentoService {

   @PersistenceContext(unitName = "AgendasPU")
    private EntityManager entityManager;

    public Procedimento findById(Long id) {
        return entityManager.find(Procedimento.class, id);
    }

    public Procedimento add(Procedimento procedimento) {
        entityManager.persist(procedimento);
        return procedimento;
    }

    public void remove(Procedimento procedimento) {
        entityManager.remove(findById(procedimento.getId()));
    }

    public Procedimento update(Procedimento procedimentoAtualizado) {
        entityManager.merge(procedimentoAtualizado);
        return procedimentoAtualizado;
    }

    public List<Procedimento> findAll() {
        return entityManager
                .createQuery("SELECT p FROM Procedimento p", Procedimento.class)
                .getResultList();
    }
}
