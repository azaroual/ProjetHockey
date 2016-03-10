package client;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JApplet;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;

import javax.swing.JTextField;

import java.awt.Insets;

import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import SResultSet.SerializedResultSet;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.Security;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class JouerClient extends JApplet implements Serializable {
	private JTextField identifiant;
	private JPasswordField passwordField;

	
	public JouerClient() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{139, 186, 214, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblIdentifiant = new JLabel("Identifiant");
		GridBagConstraints gbc_lblIdentifiant = new GridBagConstraints();
		gbc_lblIdentifiant.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdentifiant.anchor = GridBagConstraints.EAST;
		gbc_lblIdentifiant.gridx = 0;
		gbc_lblIdentifiant.gridy = 1;
		getContentPane().add(lblIdentifiant, gbc_lblIdentifiant);
		
		identifiant = new JTextField();
		GridBagConstraints gbc_identifiant = new GridBagConstraints();
		gbc_identifiant.insets = new Insets(0, 0, 5, 5);
		gbc_identifiant.fill = GridBagConstraints.HORIZONTAL;
		gbc_identifiant.gridx = 1;
		gbc_identifiant.gridy = 1;
		getContentPane().add(identifiant, gbc_identifiant);
		identifiant.setColumns(10);
		
		JLabel motDePasse = new JLabel("Mot de passe");
		GridBagConstraints gbc_motDePasse = new GridBagConstraints();
		gbc_motDePasse.anchor = GridBagConstraints.EAST;
		gbc_motDePasse.insets = new Insets(0, 0, 5, 5);
		gbc_motDePasse.gridx = 0;
		gbc_motDePasse.gridy = 2;
		getContentPane().add(motDePasse, gbc_motDePasse);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 5);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 2;
		getContentPane().add(passwordField, gbc_passwordField);
		
		final JLabel labelErreur = new JLabel("");
		labelErreur.setForeground(Color.red);
		GridBagConstraints gbc_labelErreur = new GridBagConstraints();
		gbc_labelErreur.insets = new Insets(0, 0, 0, 5);
		gbc_labelErreur.gridx = 1;
		gbc_labelErreur.gridy = 4;
		getContentPane().add(labelErreur, gbc_labelErreur);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String login=identifiant.getText();
				String passwd=passwordField.getText();
				System.out.println(login+""+passwd);
				
				try
			      {
					URL url = new URL("http://localhost:8080/ProjectHockeyWeb/Connexion");
					URLConnection connexion=url.openConnection();
				    connexion.setDoOutput(true);
				    // Récupération du flux de sortie
				    ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
				    ObjectOutputStream fluxsortie2 = new ObjectOutputStream(connexion.getOutputStream());

				    //int id=Integer.parseInt(LabelNiveau[i].getText());

				    fluxsortie.writeObject(login);
				    fluxsortie2.writeObject(passwd);
				    
				    
				    // Récupération du flux d’entrée
				    ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
				    String test=fluxentree.readObject().toString();
				    System.out.println(test);
				    if(Integer.parseInt(test)==0){
						labelErreur.setText("login ou mot de passe incorrect!!!!");
				    }
				    else{
				    	labelErreur.setText("");
						System.out.println("bonjour "+login);
//						System.exit(0);
						MatcheClient m=new MatcheClient();
					     m.show();
					     getContentPane().setVisible(false);
				    }
				    
				    
			      }
			      catch (IOException | ClassNotFoundException e) 
					{
						e.printStackTrace();
					}
				
			}
		});
		GridBagConstraints gbc_btnConnexion = new GridBagConstraints();
		gbc_btnConnexion.insets = new Insets(0, 0, 5, 5);
		gbc_btnConnexion.gridx = 1;
		gbc_btnConnexion.gridy = 3;
		getContentPane().add(btnConnexion, gbc_btnConnexion);
		
		

	}
	
	 public void init() {
		    try {
		    	Dimension d= new Dimension(400, 100);
		    			    	
		      jbInit(d);
		    }
		    catch(Exception e) {
		      e.printStackTrace();
		    }
		  }



		  // Initialiser le composant
		  private void jbInit(Dimension d) throws Exception {
		    // Taille de l'applet
			getContentPane().setSize(d);
		    this.setSize(d);

		    // Intégration du panel principal dans l'applet
		    
		  }

}
