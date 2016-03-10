package metier;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entite.Gardien;
import entite.User;

@Stateless
@LocalBean
public class GardienManager implements GardienManagerRemote {
	@PersistenceContext
	EntityManager em;

	public GardienManager() {

	}

	@Override
	public List<Gardien> getListGardien() {
		List<Gardien> l = em.createQuery("SELECT DISTINCT g FROM Gardien g").getResultList();
		return l;
	}
	@Override
	public List<Gardien> getListGardienByIdMatche(int id) {
		List<Gardien> l = em.createQuery("SELECT DISTINCT g FROM Gardien g WHERE idMatche='"+id+"'").getResultList();
		return l;
	}
	@Override
	public List<Gardien> findGardienById(int id) {
		List<Gardien> l = em.createQuery("SELECT DISTINCT g FROM Gardien g WHERE idGardien='"+id+"'").getResultList();
		return l;
	}
}
