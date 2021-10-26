package tpGUI.UI;

import java.io.File;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import tpGUI.Control.ExitController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;


public class AucunBienNeVerifieLesConditions extends Stage {



    public AucunBienNeVerifieLesConditions()
    {


        this.setTitle("Bien non trouve");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource("notFound2.fxml"));
        Parent root;

        try
        {
        	URL url = new File("notFound.fxml").toURI().toURL();
      	  
      	    
        	
  	      	root = FXMLLoader.load(url);

  	      	loader.setRoot(root);
  	
            ExitController controller = new ExitController() ;
            
            

            Scene scene=new Scene(root);
            this.setScene(scene);
            
            
            
            controller.setInfo(this);
            loader.setController(controller);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }






    }
}
