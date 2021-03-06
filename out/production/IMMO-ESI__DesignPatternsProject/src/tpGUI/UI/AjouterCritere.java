package tpGUI.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tpGUI.Control.AjouterCritereController;
import tpGUI.Control.ajouterCritereOuAfficherController;

import java.net.URL;

public class AjouterCritere extends Stage {

    private ajouterCritereOuAfficherController previousController;

    public AjouterCritere(ajouterCritereOuAfficherController view) {


        this.setTitle("Ajouter un critere");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        previousController = view;

        String pathToFXML = "/fxml/ajouterCritere.fxml";

        //loader.setLocation(getClass().getResource("ajouterCritere.fxml"));

        try {

            URL FXMLlocation = getClass().getResource(pathToFXML);
            System.out.println(FXMLlocation);
            FXMLLoader loader = new FXMLLoader(FXMLlocation);

            Parent root = loader.load() ;

            AjouterCritereController controller = loader.getController();

             
             controller.setInfo(this);
             Scene scene = new Scene(root);
             this.setScene(scene);
            

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void quitAndSet() {
        this.close();
        previousController.setEnsembleBiens();
    }

    public void passEvent(String sourceid) {
        previousController.fireNextEvent(sourceid);
    }


}
