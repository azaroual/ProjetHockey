package metier;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entite.Matche;

/**
 * Session Bean implementation class MatcheManager
 */
@Stateless
@LocalBean
public class MatcheManager implements MatcheManagerRemote {
    public MatcheManager() {}
    @PersistenceContext
    EntityManager em;
	@Override
	public List<Matche> listMatche() {
		List<Matche> l=em.createNamedQuery("findAllMatche").getResultList();
		return l;
	}
	@Override
	public Matche ajouterMatche(Matche matche) {
		em.persist(matche);
    	return matche;
	}
	  
    
}
