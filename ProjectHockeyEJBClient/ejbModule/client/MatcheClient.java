package client;

import javax.swing.JApplet;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import java.awt.Font;

public class MatcheClient extends JDialog {
	private String tabMatche [][];
	private String tabLibelleMatche[];
	private String tabGardien[][];
	private String tabNomGardien[];
	private String idMatche="";
	private String libMatche="";
	private String idGardien;
	private String nomGardien;
	
	private JComboBox comboBox;
	private JComboBox comboGardien;
	private JComboBox comboBut;
	private JComboBox comboTire;
	private JComboBox comboCage;
	private JButton btnValider;
	
	
	// Initialisation de l'applet
	/*public void init() {
	    try {
	      jbInit();
	      
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }*/
	
		 public MatcheClient(){
			 try {
			      jbInit();
			    }
			    catch(Exception e) {
			      e.printStackTrace();
			    }
		 }



		  // Initialiser le composant
		  private void jbInit() throws Exception {
			  try
			   {
			    // Connexion à la servlet
				URL url=new URL("http://localhost:8080/ProjectHockeyWeb/ListMatcheApplet");
				HttpURLConnection connexion=(HttpURLConnection) url.openConnection();
				connexion.setRequestMethod("POST");
			    connexion.setDoOutput(true);
			    System.out.println("connection etablie");
			    // Récupération du flux de sortie
			    ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
			    // Envoi du nom à rechercher
			    fluxsortie.writeObject("toto");
			    // Récupération du flux d’entrée
			    ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
			    // Récupération du résultat de la requête
			    tabMatche=(String[][])fluxentree.readObject();
			    tabLibelleMatche= new String[tabMatche[0].length];
			    for (int i = 0; i < tabMatche[0].length; i++) {
			    	//b.add(tab[1][i].toString());
			    	tabLibelleMatche[i]=tabMatche[1][i];
					System.out.println("id: "+tabMatche[0][i]+" Libele: "+tabMatche[1][i]);
					
				}
			    
			   }
			  catch(Exception e){
				 
			  }
			  
		    // Taille de l'applet
		    this.setSize(new Dimension(500, 650));

		    // Intégration du panel principal dans l'applet
		    getContentPane().setMinimumSize(new Dimension(500, 500));
		    GridBagLayout gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[]{172, 63, 66, 63, 0, 0, 0, 0, 0};
			gridBagLayout.rowHeights = new int[]{47, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			getContentPane().setLayout(gridBagLayout);
			
			JLabel lblSaisirLesDonnes = new JLabel("Saisir les donn\u00E9es des matches");
			lblSaisirLesDonnes.setFont(new Font("Tahoma", Font.PLAIN, 16));
			GridBagConstraints gbc_lblSaisirLesDonnes = new GridBagConstraints();
			gbc_lblSaisirLesDonnes.gridwidth = 4;
			gbc_lblSaisirLesDonnes.insets = new Insets(0, 0, 5, 5);
			gbc_lblSaisirLesDonnes.gridx = 0;
			gbc_lblSaisirLesDonnes.gridy = 0;
			getContentPane().add(lblSaisirLesDonnes, gbc_lblSaisirLesDonnes);
			
			JLabel lblChoisirUnMatche = new JLabel("Choisir un matche");
			GridBagConstraints gbc_lblChoisirUnMatche = new GridBagConstraints();
			gbc_lblChoisirUnMatche.anchor = GridBagConstraints.EAST;
			gbc_lblChoisirUnMatche.insets = new Insets(0, 0, 5, 5);
			gbc_lblChoisirUnMatche.gridx = 0;
			gbc_lblChoisirUnMatche.gridy = 1;
			getContentPane().add(lblChoisirUnMatche, gbc_lblChoisirUnMatche);
			comboBox = new JComboBox();			 
			for (int i = 0; i < tabLibelleMatche.length; i++) {
				comboBox.addItem(tabLibelleMatche[i]);
			}
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.gridwidth = 2;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 1;
			gbc_comboBox.gridy = 1;
			getContentPane().add(comboBox, gbc_comboBox);
			
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comboBut.setEnabled(true);
					comboTire.setEnabled(true);
					comboCage.setEnabled(true);
					comboGardien.setEnabled(true);
					btnValider.setEnabled(true);
					libMatche=comboBox.getSelectedItem().toString();
					for (int i = 0; i < tabMatche[0].length; i++) {
						if(tabMatche[1][i]==libMatche)
							idMatche=tabMatche[0][i];
					}
					System.out.println(idMatche);
					try
					   {
						// Connexion à la servlet
						URL url=new URL("http://localhost:8080/ProjectHockeyWeb/ListGardienApplet");
						HttpURLConnection connexion=(HttpURLConnection) url.openConnection();
						connexion.setRequestMethod("POST");
					    connexion.setDoOutput(true);
					    System.out.println("connection etablie");
					    // Récupération du flux de sortie
					    ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
					    // Envoi du nom à rechercher
					    fluxsortie.writeObject(idMatche);
					    // Récupération du flux d’entrée
					    ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
					    // Récupération du résultat de la requête
					    tabGardien=(String[][])fluxentree.readObject();
					    tabNomGardien= new String[tabGardien[0].length];
					    for (int i = 0; i < tabGardien[0].length; i++) {
					    	tabNomGardien[i]=tabGardien[1][i];							
						}
					    comboGardien.removeAllItems();
					    for (int i = 0; i < tabNomGardien.length; i++) {
							comboGardien.addItem(tabNomGardien[i]);
						}
					   }
					  catch(Exception ex){
						 
					  }
					
				}
			});
			GridBagConstraints gbc_btnOk = new GridBagConstraints();
			gbc_btnOk.insets = new Insets(0, 0, 5, 5);
			gbc_btnOk.gridx = 3;
			gbc_btnOk.gridy = 1;
			getContentPane().add(btnOk, gbc_btnOk);
			
			
			
			JLabel lblNewLabel = new JLabel("");
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel.gridx = 7;
			gbc_lblNewLabel.gridy = 1;
			getContentPane().add(lblNewLabel, gbc_lblNewLabel);
			
			JLabel lblChoisirGardien = new JLabel("Choisir gardien");
			GridBagConstraints gbc_lblChoisirGardien = new GridBagConstraints();
			gbc_lblChoisirGardien.insets = new Insets(0, 0, 5, 5);
			gbc_lblChoisirGardien.anchor = GridBagConstraints.EAST;
			gbc_lblChoisirGardien.gridx = 0;
			gbc_lblChoisirGardien.gridy = 3;
			getContentPane().add(lblChoisirGardien, gbc_lblChoisirGardien);
			
			comboGardien = new JComboBox();
			
			GridBagConstraints gbc_comboGardien = new GridBagConstraints();
			gbc_comboGardien.gridwidth = 2;
			gbc_comboGardien.insets = new Insets(0, 0, 5, 5);
			gbc_comboGardien.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboGardien.gridx = 1;
			gbc_comboGardien.gridy = 3;
			getContentPane().add(comboGardien, gbc_comboGardien);
			
			JLabel lblZoneTire = new JLabel("Zone Tire");
			GridBagConstraints gbc_lblZoneTire = new GridBagConstraints();
			gbc_lblZoneTire.insets = new Insets(0, 0, 5, 5);
			gbc_lblZoneTire.anchor = GridBagConstraints.EAST;
			gbc_lblZoneTire.gridx = 0;
			gbc_lblZoneTire.gridy = 4;
			getContentPane().add(lblZoneTire, gbc_lblZoneTire);
			
			comboTire = new JComboBox();
			comboTire.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6"}));
			GridBagConstraints gbc_comboTire = new GridBagConstraints();
			gbc_comboTire.insets = new Insets(0, 0, 5, 5);
			gbc_comboTire.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboTire.gridx = 1;
			gbc_comboTire.gridy = 4;
			getContentPane().add(comboTire, gbc_comboTire);
			comboTire.setEnabled(false);
			comboGardien.setEnabled(false);
			
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("C:\\workspace\\ProjectHockeyEJBClient\\ejbModule\\client\\tire.png"));
			GridBagConstraints gbc_label = new GridBagConstraints();
			gbc_label.gridwidth = 4;
			gbc_label.insets = new Insets(0, 0, 5, 5);
			gbc_label.gridx = 0;
			gbc_label.gridy = 5;
			getContentPane().add(label, gbc_label);
			
			JLabel lblZoneCage = new JLabel("Zone Cage");
			GridBagConstraints gbc_lblZoneCage = new GridBagConstraints();
			gbc_lblZoneCage.anchor = GridBagConstraints.EAST;
			gbc_lblZoneCage.insets = new Insets(0, 0, 5, 5);
			gbc_lblZoneCage.gridx = 0;
			gbc_lblZoneCage.gridy = 6;
			getContentPane().add(lblZoneCage, gbc_lblZoneCage);
			
			comboCage = new JComboBox();
			comboCage.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "I"}));
			GridBagConstraints gbc_comboCage = new GridBagConstraints();
			gbc_comboCage.insets = new Insets(0, 0, 5, 5);
			gbc_comboCage.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboCage.gridx = 1;
			gbc_comboCage.gridy = 6;
			getContentPane().add(comboCage, gbc_comboCage);
			comboCage.setEnabled(false);
			
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String tabTire[] = new String[5];
					nomGardien=comboGardien.getSelectedItem().toString();
					for (int i = 0; i < tabGardien[0].length; i++) {
						if(tabGardien[1][i]==nomGardien)
							idGardien=tabGardien[0][i];
					} 
					tabTire[0]=comboTire.getSelectedItem().toString();
					tabTire[1]=comboCage.getSelectedItem().toString();
					if(comboBut.getSelectedItem().toString()=="OUI")
						tabTire[2]="1";
					else
						tabTire[2]="0";
					tabTire[3]=idMatche;
					tabTire[4]=idGardien;
				
					try
				      {
							URL url = new URL("http://localhost:8080/ProjectHockeyWeb/InsertTire");
							URLConnection connexion=url.openConnection();
						    connexion.setDoOutput(true);
						    // Récupération du flux de sortie
						    ObjectOutputStream fluxsortie = new ObjectOutputStream(connexion.getOutputStream());
						 
						    //int id=Integer.parseInt(LabelNiveau[i].getText());
						    fluxsortie.writeObject(tabTire);
						    // Récupération du flux d’entrée
						    ObjectInputStream fluxentree = new ObjectInputStream(connexion.getInputStream());
						    System.out.println(fluxentree.readObject().toString());
				      }
				      catch (MalformedURLException e) 
						{
							e.printStackTrace();
						} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					JOptionPane.showMessageDialog(getContentPane(), "L'ajout effectué avec succés",
                            "Information",
                            JOptionPane.INFORMATION_MESSAGE);
					setVisible(true);
					
				}
			});
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("C:\\workspace\\ProjectHockeyEJBClient\\ejbModule\\client\\cage.png"));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.gridwidth = 5;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 7;
			getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			JLabel lblBut = new JLabel("But");
			GridBagConstraints gbc_lblBut = new GridBagConstraints();
			gbc_lblBut.insets = new Insets(0, 0, 5, 5);
			gbc_lblBut.anchor = GridBagConstraints.EAST;
			gbc_lblBut.gridx = 0;
			gbc_lblBut.gridy = 8;
			getContentPane().add(lblBut, gbc_lblBut);
			
			comboBut = new JComboBox();
			comboBut.setModel(new DefaultComboBoxModel(new String[] {"OUI", "NON"}));
			GridBagConstraints gbc_comboBut = new GridBagConstraints();
			gbc_comboBut.insets = new Insets(0, 0, 5, 5);
			gbc_comboBut.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBut.gridx = 1;
			gbc_comboBut.gridy = 8;
			getContentPane().add(comboBut, gbc_comboBut);
			comboBut.setEnabled(false);
			
			GridBagConstraints gbc_btnValider = new GridBagConstraints();
			gbc_btnValider.insets = new Insets(0, 0, 0, 5);
			gbc_btnValider.gridx = 1;
			gbc_btnValider.gridy = 9;
			getContentPane().add(btnValider, gbc_btnValider);
			btnValider.setEnabled(false);

		  }	  

}
