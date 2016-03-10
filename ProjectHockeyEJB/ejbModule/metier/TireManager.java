package metier;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entite.Matche;
import entite.Tire;

/**
 * Session Bean implementation class TireManager
 */
@Stateless
@LocalBean
public class TireManager implements TireManagerRemote {

    public TireManager() {}
    @PersistenceContext
    EntityManager em;
	@Override
	public void ajouterTire(Tire tire) {
		em.persist(tire);
	}
	

}
