package entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries ({ 
	@NamedQuery(name="findAllMatche", query="SELECT m FROM Matche m ORDER BY m.libelleMatche"),
    })
public class Matche implements Serializable{
	
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idMatche;
	private String libelleMatche;

	@OneToMany(targetEntity=Gardien.class, cascade={CascadeType.REMOVE, CascadeType.PERSIST},mappedBy="matche")
	private List<Gardien> gardien;
	
	

	public Matche(){}
	
	public Matche(String libelleMatche) {
		this.libelleMatche = libelleMatche;
	}
	
	public int getIdMatche() {
		return idMatche;
	}
	public void setIdMatche(int idMatche) {
		this.idMatche = idMatche;
	}
	public String getLibelleMatche() {
		return libelleMatche;
	}
	public void setLibelleMatche(String libelleMatche) {
		this.libelleMatche = libelleMatche;
	}
	@Override
	public String toString() {
		return "Matche [idMatche=" + idMatche + ", libelleMatche="
				+ libelleMatche + "]";
	}
	
	
}
