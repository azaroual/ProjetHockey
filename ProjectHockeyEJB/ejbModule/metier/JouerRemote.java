package metier;

import java.util.List;

import javax.ejb.Remote;
import javax.naming.NamingException;

import entite.Matche;
import entite.User;

@Remote
public interface JouerRemote {
    public int conncexion(String login,String password);
	public User ajouterContact(User user);
	//public List<Matche> listMatche();
	//public Matche ajouterMatche(Matche matche);
	public List<User> tconncexion();
}
