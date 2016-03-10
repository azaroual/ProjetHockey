package entite;

import java.io.Serializable;

import javax.persistence.*;

public class GardienId implements Serializable{
	
	private int idGardien;
    private Matche matche;
 
    public GardienId(){
        // Your class must have a no-arq constructor
    }
 
    public int getIdGardien() {
		return idGardien;
	}

	public void setIdGardien(int idGardien) {
		this.idGardien = idGardien;
	}

	public Matche getMatche() {
		return matche;
	}

	public void setMatche(Matche matche) {
		this.matche = matche;
	}

	@Override
    public boolean equals(Object obj) {
        if(obj instanceof GardienId){
        	GardienId gardienId = (GardienId) obj;
 
            if(!(gardienId.getIdGardien()==idGardien)){
                return false;
            }
 
            if(!gardienId.getMatche().equals(matche)){
                return false;
            }
 
            return true;
        }
 
        return false;
    }
 
    @Override
    public int hashCode() {
        return idGardien + matche.hashCode();
    }
 


}
