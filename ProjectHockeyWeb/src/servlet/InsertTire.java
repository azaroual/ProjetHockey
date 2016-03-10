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

import metier.GardienManagerRemote;
import metier.TireManagerRemote;
import entite.Gardien;
import entite.Tire;

/**
 * Servlet implementation class InsertTire
 */
@WebServlet("/InsertTire")
public class InsertTire extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("**************************************");
		try {
			ObjectInputStream entree = new ObjectInputStream(request.getInputStream());
					String tabTire[] = (String[])entree.readObject();
					for (int i = 0; i < tabTire.length; i++) {
						System.out.println(tabTire[i]);
					}
					GardienManagerRemote g=EjbLocator.getLocator().getGardienManagerRemote();
					List<Gardien> listGardien=g.findGardienById(Integer.parseInt(tabTire[4]));					
					Tire tire= new Tire();
					tire.setZoneTire(Integer.parseInt(tabTire[0]));
					tire.setZoneCage(tabTire[1]);
					tire.setNbBut(Integer.parseInt(tabTire[2]));
					listGardien.get(0).getMatche().setIdMatche(Integer.parseInt(tabTire[3]));
					tire.setGardien(listGardien.get(0));
					TireManagerRemote t=EjbLocator.getLocator().insertTireManagerRemote();
					t.ajouterTire(tire);
					ObjectOutputStream sortie=new ObjectOutputStream(response.getOutputStream());
					sortie.writeObject("insert ok");
					
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
