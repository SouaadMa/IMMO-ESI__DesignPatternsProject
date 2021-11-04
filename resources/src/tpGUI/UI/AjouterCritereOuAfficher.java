package tpGUI.UI;

import javafx.fxml.FXMLLoader;
import tpGUI.Control.Controller;
import tpGUI.Control.ajouterCritereOuAfficherController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.io.File;
import java.net.URL;
import java.util.*;
import tpGUI.Noyau.*;


public class AjouterCritereOuAfficher extends Stage  {

    public AjouterCritereOuAfficher(Controller view, Set<Biens> ensembleBiens , Agence agence )
    {

        this.setTitle("recherche avec succes");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("ajouterCritereOuRechercher.fxml"));
        Parent root;

        try
        {

        	 URL url = new File("resources/fxml/ajouterCritereOuRechercher.fxml").toURI().toURL();
       	  
       	  
         	
   	      	root = FXMLLoader.load(url);
   	      	
   	      	
   	      	loader.setRoot(root);
   	      	
   	      	
             
         	
             ajouterCritereOuAfficherController controller = new ajouterCritereOuAfficherController();
           
         	
         		loader.setController(controller);

             
             controller.setInfo(this,view,ensembleBiens,agence);

            Scene scene=new Scene(root);
            this.setScene(scene);




        }
        catch(Exception e)
        {
            e.printStackTrace();
        }






    }


}
