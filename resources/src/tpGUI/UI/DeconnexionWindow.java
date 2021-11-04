package tpGUI.UI;

import java.io.File;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tpGUI.Control.ConnexionController;
import tpGUI.Control.Controller;
import tpGUI.Control.DeconnexionController;
import tpGUI.Noyau.Agence;

public class DeconnexionWindow extends Stage {



    public DeconnexionWindow(Controller view) {



        this.setTitle("deconnexion");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("deconnecterWindow.fxml"));
        Parent root;

        try {
        	
        	URL url = new File("deconnecterWindow.fxml").toURI().toURL();
       	  
       	  
         	
   	      	root = FXMLLoader.load(url);
   	      	
   	      	
   	      	loader.setRoot(root);


            Scene scene = new Scene(root);
            this.setScene(scene);
            DeconnexionController controller = new DeconnexionController();
            loader.setController(controller);
            controller.setInfo(this,view);
            this.show();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
