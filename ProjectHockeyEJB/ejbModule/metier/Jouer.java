package metier;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entite.User;

@Stateless
@LocalBean
public class Jouer implements JouerRemote {
	private static final long serialVersionUID = 1L;
	public Jouer(){}
	@PersistenceContext
	EntityManager em;
	
	@Override
	public int conncexion(String identifiant, String password) {
		List<User> l=em.createQuery("SELECT c FROM User c WHERE identifiant='"+identifiant+"' and password='"+password+"'").getResultList();
		return l.size();
	}
	@Override
	public List<User> tconncexion() {
		List<User> l=em.createNamedQuery("findAlluser").getResultList();
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i).getIdentifiant());
		}
		return l;
	}
	@Override 
	public User ajouterContact(User user) {
    	em.persist(user);
    	return user;
    }
	/*@Override 
	public Matche ajouterMatche(Matche matche) {
    	em.persist(matche);
    	return matche;
    }
	@Override
	public List<Matche> listMatche() {
		List<Matche> l=em.createNamedQuery("findAllMatche").getResultList();
		for (int i = 0; i < l.size(); i++) {
			System.out.println(l.get(i).getLibelleMatche());
		}
		return l;
		//return em.createQuery("SELECT * FROM matche").getResultList();
	}*/
}
