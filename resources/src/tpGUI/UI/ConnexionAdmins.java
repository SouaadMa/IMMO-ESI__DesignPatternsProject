package tpGUI.UI;

import java.io.File;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tpGUI.Control.ConnexionController;
import tpGUI.Control.Controller;
import tpGUI.Noyau.Agence;


public class ConnexionAdmins extends Stage {

    public ConnexionAdmins(Controller view, Agence agence) {



        this.setTitle("Connexion en tant qu'admin");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("windowConnexion.fxml"));
        Parent root;

        try {

        	URL url = new File("resources/fxml/windowConnexion.fxml").toURI().toURL();
       	  
       	  
         	
   	      	root = FXMLLoader.load(url);
   	      	
   	      	
   	      	loader.setRoot(root);
   	      	
   	      	System.out.println(agence);
   	      	
   	      	
            Scene scene = new Scene(root);
            this.setScene(scene);
            ConnexionController controller = new ConnexionController() ;
            loader.setController(controller);
            controller.setInfo(this,view, agence);
            this.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}