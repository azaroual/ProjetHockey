package metier;

import javax.ejb.Remote;

import entite.Tire;

@Remote
public interface TireManagerRemote {

	void ajouterTire(Tire tire);
}
