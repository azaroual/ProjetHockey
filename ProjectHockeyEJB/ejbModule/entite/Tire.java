package entite;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tire implements Serializable{
		
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int idTire;
	private int zoneTire;
	private String zoneCage;
	private int but;
	@ManyToOne
	@JoinColumns({
	        @JoinColumn(name="idGardien"),
	        @JoinColumn(name="idMatche")
	    })
	private Gardien gardien;
	public Tire() {}

	public Tire(int zoneTire, String zoneCage, int nbTire, int but) {
		this.zoneTire = zoneTire;
		this.zoneCage = zoneCage;
		this.but = but;
	}

	public int getIdTire() {
		return idTire;
	}

	public void setIdTire(int idTire) {
		this.idTire = idTire;
	}

	public int getZoneTire() {
		return zoneTire;
	}

	public void setZoneTire(int zoneTire) {
		this.zoneTire = zoneTire;
	}

	public String getZoneCage() {
		return zoneCage;
	}

	public void setZoneCage(String zoneCage) {
		this.zoneCage = zoneCage;
	}

	

	public int getNbBut() {
		return but;
	}

	public void setNbBut(int nbBut) {
		this.but = nbBut;
	}

	@Override
	public String toString() {
		return "Tire [idTire=" + idTire + ", zoneTire=" + zoneTire
				+ ", zoneCage=" + zoneCage + ", nbBut=" + but + "]";
	}

	public Gardien getGardien() {
		return gardien;
	}

	public void setGardien(Gardien gardien) {
		this.gardien = gardien;
	}

	

}
