package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entite.Matche;

import java.util.List;
import java.util.Properties;

import metier.JouerRemote;
import metier.MatcheManagerRemote;

/**
 * Servlet implementation class Connexion
 */
@WebServlet("/Connexion")
public class Connexion extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			// Récupération du flux d'entrée envoyé par l'applet
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
			ObjectInputStream entree2=new ObjectInputStream(request.getInputStream());
					String login = String.valueOf(entree.readObject());
					String password = String.valueOf(entree2.readObject());
					
			JouerRemote j=EjbLocator.getLocator().getJouerRemote();
			int test = j.conncexion(login,password);
			ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
			sortie.writeObject(""+test);
			
			
		} catch (Exception ex) {
			System.out.println("Erreur d'exécution de la requête SQL2 : " + ex);
		}
	}

}
