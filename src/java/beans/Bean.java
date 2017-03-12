/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Pokemon;
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
    public Trainer findTrainerByName(String name){
        EntityManager em = emf.createEntityManager();
        Trainer encontrado = em.find(Trainer.class, name);
        em.close();
        
        return encontrado;
    }
    
    public List<Trainer> getTrainersForAddPokemons(){
        EntityManager em = emf.createEntityManager();
        
        /*Query q = em.createQuery("SELECT trainer FROM Trainer trainer WHERE trainer.pokemonCollection < 6");
        List<Trainer> trainers = q.getResultList();*/
        List<Trainer> trainers = em.createNamedQuery("Trainer.findAll").getResultList();
        trainers.stream().filter((t) -> (t.getPokemonCollection().size() >= 6)).forEachOrdered((t) -> {
            trainers.remove(t);
        });
        em.close();
        return trainers;
    }
    
    public boolean insertPokemon(Pokemon p) {
        EntityManager em = emf.createEntityManager();
        Pokemon encontrado = em.find(Pokemon.class, p.getName());
        
        if (encontrado == null) {
            em.persist(p);
            em.flush();   //Para forzar que se haga ahora
            em.close();
            return true;
        }
        return false;
    }
    
    public List<Pokemon> GetAllPokemons(){
        
        List<Pokemon> pokemons =emf.createEntityManager().createNamedQuery("Pokemon.findAll").getResultList();
        
        return pokemons;
    }
    
    public boolean DelPokemon(String name){
        EntityManager em = emf.createEntityManager();
        Pokemon p = em.find(Pokemon.class, name);
        if(p != null){
            em.remove(p);
            return true;
        }
        return false;
    }
    
    public List<Trainer> GetTrainersBattle(){
        EntityManager em = emf.createEntityManager();
        
        List<Trainer> trainers = em.createNamedQuery("Trainer.findAll").getResultList();
        trainers.stream().filter((t) -> (t.getPokemonCollection().size() < 1)).forEachOrdered((t) -> {
            trainers.remove(t);
        });
        em.close();
        return trainers;
    }
}
