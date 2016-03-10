package entite;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity 
@IdClass(GardienId.class)
public class Gardien implements Serializable{
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idGardien;
	private String nomGardien;
	@ManyToOne
	@JoinColumn(name="idMatche")
	private Matche matche;
	
	@OneToMany(targetEntity=Tire.class , cascade={CascadeType.REMOVE, CascadeType.PERSIST},mappedBy="gardien")
	private List<Tire> tires;
	
	public Gardien() {}

	public Gardien(String nomGardien) {
		this.nomGardien = nomGardien;
	}

	public int getIdGardien() {
		return idGardien;
	}

	public void setIdGardien(int idGardien) {
		this.idGardien = idGardien;
	}

	public String getNomGardien() {
		return nomGardien;
	}

	public void setNomGardien(String nomGardien) {
		this.nomGardien = nomGardien;
	}

	@Override
	public String toString() {
		return "Gardien [idGardien=" + idGardien + ", nomGardien=" + nomGardien
				+ "]";
	}

	public Matche getMatche() {
		return matche;
	}

	public void setMatche(Matche matche) {
		this.matche = matche;
	}
	
}
