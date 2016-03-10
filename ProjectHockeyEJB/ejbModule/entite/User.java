package entite;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries ({ 
	@NamedQuery(name="findAlluser", query="SELECT m FROM User m ORDER BY m.identifiant")
    })
public class User implements Serializable {
	@Id
	@GeneratedValue (strategy=GenerationType.AUTO)
	private int id;
	private String identifiant;
	private String password;
	public User(){}
	public User(String identifiant, String password) {
		this.identifiant = identifiant;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", identifiant=" + identifiant
				+ ", password=" + password + "]";
	}
		
}
