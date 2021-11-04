package tpGUI.UI;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tpGUI.Control.Controller;
import tpGUI.Noyau.*;

import java.io.*;
import java.net.URL;

public class PrincipalWindow extends Stage {
	
	public Agence prepare() {
		
		
		ObjectInputStream in;
		Agence ImmoESI=null;


		try {

			File sauvtextfile = new File("resources/Sauvegarde.txt");

			in = new ObjectInputStream( new BufferedInputStream( new FileInputStream( sauvtextfile )));

			ImmoESI = (Agence)in.readObject();

			in.close();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch
			(FileNotFoundException e) {
			e.printStackTrace();
			}
		catch (IOException e) {
			e.printStackTrace();
			}




		if(ImmoESI == null) {

			ImmoESI = new Agence();

			Proprietaire Prop1 = new Proprietaire ("Benelhadj" , "Saadia" , "@esi.dz" , "Cité 05 Juillet" , "05" ) ;
			ImmoESI.ajouterProprietaire(Prop1);
			Proprietaire Prop2 = new Proprietaire ("Hamlaoui" , "Yasmine" , "@esi.dz" , "1er Novembre" , "06") ;
			ImmoESI.ajouterProprietaire(Prop2);
			Proprietaire Prop3 = new Proprietaire ("Mecheri" , "Hadia" , "@esi.dz" , "Ma lebyed" , "05" ) ;
			ImmoESI.ajouterProprietaire(Prop3);
			Proprietaire Prop4 = new Proprietaire ("Rouabhia" , "Anfel" , "@esi.dz" , "Tafchoun" , "07") ;
			ImmoESI.ajouterProprietaire(Prop4);

			Biens Bien1 = new Appartement ("adr 1", 2, 120, Prop2, 4000000,
					false, TypeTrans.VENTE, "descrip 1", "31/12/2019",
					"resources/img/appa1.jpg",
					4, false, 1, Xplexe.DUPLEXE, false);
			ImmoESI.valideBien(Bien1);

			Biens Bien2 = new Maison ("adr 2", 3, 200, Prop1, 10000000,
					false, TypeTrans.VENTE, "descrip 2", "02/12/2019",
					"resources/img/mais1.jpg",
					2, false, 1, false, false, true, 120);
			ImmoESI.valideBien(Bien2);

			Biens Bien3 = new NonHabitable("adr 3", 1, 500, Prop1, 20000000,
					false, TypeTrans.VENTE, "descrip 3", "10/11/2019",
					"resources/img/terrain1.jpg",
					"statut juridique 3", 3);
			ImmoESI.valideBien(Bien3);

			Biens Bien4 = new Appartement ("adr 4", 3, 100, Prop2, 40000,
					false, TypeTrans.LOCATION, "descrip 4", "01/11/2019",
					"resources/img/appa2.jpg",
					3, false, 1, Xplexe.DUPLEXE, false);
			ImmoESI.valideBien(Bien4);

			Biens Bien5 = new Maison ("adr 5", 2, 160, Prop3, 150000, /*Nbpieces=3*/
					false, TypeTrans.LOCATION, "descrip 5", "01/09/2019",
					"resources/img/mais2.jpg",
					3, true, 1, false, true, false, 120);
			ImmoESI.valideBien(Bien5);

			Biens Bien6 = new Appartement ("adr 6", 3, 50, Prop2, 600000,
					false, TypeTrans.LOCATION, "descrip 6", "02/08/2019",
					"resources/img/appa3.jpg",
					1, true, 6, Xplexe.SIMPLEXE, false);
			ImmoESI.valideBien(Bien6);

			Biens Bien7 = new NonHabitable ("adr 7", 1, 650, Prop1, 18000000,
					false, TypeTrans.ECHANGE, "descrip 7", "13/07/2019",
					"resources/img/terrain2.jpg",
					"Statut Juridique 7", 1);
			ImmoESI.valideBien(Bien7);

			Biens Bien8 = new Maison ("adr 8", 2, 200, Prop2, 14000000,
					true, TypeTrans.ECHANGE, "descrip 8", "12/12/2018",
					"resources/img/mais3.jpg",
					3, true, 1, true, false, false, 120);
			ImmoESI.valideBien(Bien8);

		}
		/*
		ObjectOutputStream out;

		try {
			out = new
			ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("Sauvegarde.txt"))));

			out.writeObject(ImmoESI);

			out.close();
		}
		catch
			(FileNotFoundException
			e) {
			e.printStackTrace();
			} catch (IOException e)
			{
			e.printStackTrace();
			}



		Platform.exit();
*/

		
			
		
		
		
		return ImmoESI;
		
	}

		

  public PrincipalWindow() {
	  
	  
	  
			Agence agence = prepare();


      //lire l'agence à partir du fichier
		
		

      FXMLLoader loader = new FXMLLoader();
      Parent root ;

      

      try {
    	  
    	  URL url = new File("resources/fxml/Accueil.fxml").toURI().toURL();

		  System.out.println(url);

	      	root = FXMLLoader.load(url);

	      	loader.setRoot(root);

		  Controller controller = new Controller();
		  controller.setStage(this);

		  loader.setController(controller);


          controller.setInfo(agence.getBiens(),agence);


          this.setTitle("Accueil");
          
          URL url2 = new File("resources/img/logo-circle.png").toURI().toURL();
          this.getIcons().add(new Image(url2.toString()));


          this.setScene(new Scene(root));
          this.show();


	  }
      
      catch(FileNotFoundException e)
      {
          System.out.println("imposible d'ouvrir le fichier des données");
      }
      catch(IOException e)
      {
          System.out.println("un problème est survenu");
      }
      catch(Exception e)
      {
          e.printStackTrace();
      }

    }


}
