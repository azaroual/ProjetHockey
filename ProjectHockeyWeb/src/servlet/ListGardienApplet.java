package servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entite.Gardien;
import metier.GardienManagerRemote;
import metier.JouerRemote;

/**
 * Servlet implementation class ListGardienApplet
 */
@WebServlet("/ListGardienApplet")
public class ListGardienApplet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Récupération du flux d'entrée envoyé par l'applet
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
					int idGardien = Integer.parseInt(String.valueOf(entree.readObject()));
					GardienManagerRemote g= EjbLocator.getLocator().getGardienManagerByIdRemote();
					System.out.println(idGardien);
					List<Gardien> listGardien=g.getListGardienByIdMatche(idGardien);
					String tabGardien[][] = new String[2][listGardien.size()] ; 
					for (int i = 0; i < listGardien.size(); i++) {
						tabGardien[0][i]=String.valueOf(listGardien.get(i).getIdGardien());
						tabGardien[1][i]=listGardien.get(i).getNomGardien();
					}
					ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
					sortie.writeObject(tabGardien);
					
			
		} catch (Exception ex) {
			System.out.println("Erreur d'exécution de la requête SQL2 : " + ex);
		}
		
	}

}
