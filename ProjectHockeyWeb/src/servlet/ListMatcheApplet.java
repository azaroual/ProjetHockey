package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import entite.Matche;
import metier.JouerRemote;
import metier.MatcheManagerRemote;

/**
 * Servlet implementation class GestionMatche
 */
@WebServlet("/ListMatcheApplet")
public class ListMatcheApplet extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hola");
		
		try {
			
			MatcheManagerRemote m=EjbLocator.getLocator().getMatcheRemote();
			List<Matche> l=m.listMatche();
			String tabMatche[][] = new String[2][l.size()] ; 
			for (int i = 0; i < l.size(); i++) {
				tabMatche[0][i]=String.valueOf(l.get(i).getIdMatche());
				tabMatche[1][i]=l.get(i).getLibelleMatche();
			}
			//request.setAttribute("tabMatche", tabMatche);			
			
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			sortie.writeObject(tabMatche);

		} catch (Exception ex) {
			System.out.println("Erreur d'exécution de la requête SQL2 : " + ex);
		}
	}

}
