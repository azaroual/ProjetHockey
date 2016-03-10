package metier;

import java.util.List;

import javax.ejb.Remote;

import entite.Gardien;

@Remote
public interface GardienManagerRemote {
	public List<Gardien> getListGardien();
	public List<Gardien> getListGardienByIdMatche(int id);
	public List<Gardien> findGardienById(int id);
}
