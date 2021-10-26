package tpGUI.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tpGUI.Control.AjouterCritereController;
import tpGUI.Control.Controller;
import tpGUI.Noyau.Agence;
import tpGUI.Noyau.Biens;

import java.io.File;
import java.net.URL;
import java.util.Set;

public class AjouterCritere extends Stage {


    public AjouterCritere(Controller view, Set<Biens> ensembleBiens , Agence agence) {


        this.setTitle("Ajouter un critere");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        FXMLLoader loader = new FXMLLoader();


        //loader.setLocation(getClass().getResource("ajouterCritere.fxml"));
        Parent root;

        try {
        	
        	 URL url = new File("ajouterCritere.fxml").toURI().toURL();
       	  
       	  System.out.println(url);
         	
   	      	root = FXMLLoader.load(url);
   	      	
   	      	
   	      	loader.setRoot(root);
   	      	
             
             AjouterCritereController controller = new AjouterCritereController();
             
             
             loader.setController(controller);
             
             controller.setInfo(this,view,ensembleBiens,agence);
             Scene scene = new Scene(root);
             this.setScene(scene);
             
             
            
            
            

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
