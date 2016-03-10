package metier;

import java.util.List;

import javax.ejb.Remote;

import entite.Matche;

@Remote
public interface MatcheManagerRemote {
	public List<Matche> listMatche();
	public Matche ajouterMatche(Matche matche);
}
