package tpGUI.UI;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tpGUI.Control.AccueilClickedState;
import tpGUI.Control.Controller;
import tpGUI.Noyau.*;

import java.io.*;
import java.net.URL;

public class PrincipalWindow extends Stage {
	


  	public PrincipalWindow() {

		String pathToFXML = "/fxml/Accueil.fxml";
		CreationManager.deserializeAgence();
		AdminsManager.createAdmins();

      try {

		  URL FXMLlocation = getClass().getResource(pathToFXML);
		  System.out.println(FXMLlocation);
		  FXMLLoader loader = new FXMLLoader(FXMLlocation);

		  Parent root = loader.load() ;

		  Controller controller = loader.getController();

		  controller.setStage(this);


		  this.setTitle("Accueil");
          
          URL url2 = new File("resources/img/logo-circle.png").toURI().toURL();
          this.getIcons().add(new Image(url2.toString()));


          this.setScene(new Scene(root));
          this.show();

		  controller.accueilClicked();
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
