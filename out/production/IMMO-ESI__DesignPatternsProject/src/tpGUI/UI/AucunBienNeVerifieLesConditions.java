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
        //loader.setLocation(getClass().getResource("notFound2.fxml"));

        String pathToFXML = "/fxml/notFound.fxml";

        try
        {

            URL FXMLlocation = getClass().getResource(pathToFXML);
            System.out.println(FXMLlocation);
            FXMLLoader loader = new FXMLLoader(FXMLlocation);

            Parent root = loader.load() ;

            ExitController controller = loader.getController();


            Scene scene=new Scene(root);
            this.setScene(scene);

            controller.setInfo(this);

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }






    }
}
