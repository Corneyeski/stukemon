/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Trainer;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import net.sf.ehcache.search.expression.Criteria;

/**
 *
 * @author Alan
 */
@Stateless
public class Bean {

    @PersistenceUnit
    EntityManagerFactory emf;
    
    public boolean insertTrainer(Trainer t) {
        if (!existTrainer(t)) {
            EntityManager em = emf.createEntityManager();
            em.persist(t);
//        em.flush();   Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }
    public boolean existTrainer(Trainer t) {
        EntityManager em = emf.createEntityManager();
        Trainer encontrado = em.find(Trainer.class, t.getName());
        em.close();
        return encontrado != null;
    }
    
    public List<Trainer> getTrainersForAddPokemons(){
        EntityManager em = emf.createEntityManager();
        
        /*Query q = em.createQuery("SELECT trainer FROM Trainer trainer WHERE trainer.pokemonCollection < 6");
        List<Trainer> trainers = q.getResultList();*/
        List<Trainer> trainers = em.createNamedQuery("Trainer.findAll").getResultList();
        for(Trainer t : trainers){
            if(t.getPokemonCollection().size() >= 6){
                trainers.remove(t);
            }
        }
        em.close();
        return trainers;
    }
}
