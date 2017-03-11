/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Trainer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Alan
 */
@Stateless
public class Bean {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    public boolean insertTrainer(Trainer t) {
        return true;
        /*if (!existTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
//        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;*/
    }
    public boolean existTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer encontrado = em.find(Trainer.class, t.getName());
        em.close();
        return encontrado != null;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
