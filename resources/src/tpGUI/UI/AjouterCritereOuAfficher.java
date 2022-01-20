package tpGUI.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.*;

import java.net.URL;
import java.util.*;

import tpGUI.Control.RechercherController;
import tpGUI.Control.ajouterCritereOuAfficherController;
import tpGUI.Noyau.*;


public class AjouterCritereOuAfficher extends Stage  {

    RechercherController rechercherController;

    public AjouterCritereOuAfficher(RechercherController rechercherController, Map<Integer, Biens> ensembleBiens )
    {

        this.setTitle("recherche avec succes");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        this.rechercherController = rechercherController;
        //loader.setLocation(getClass().getResource("ajouterCritereOuRechercher.fxml"));

        String pathToFXML = "/fxml/ajouterCritereOuRechercher.fxml";

        try
        {

            URL FXMLlocation = getClass().getResource(pathToFXML);
            System.out.println(FXMLlocation);
            FXMLLoader loader = new FXMLLoader(FXMLlocation);

            Parent root = loader.load() ;

            ajouterCritereOuAfficherController controller = loader.getController();


             controller.setInfo(this, ensembleBiens);

            Scene scene=new Scene(root);
            this.setScene(scene);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void quitAndReturn() {
        this.close();
        rechercherController.quitAndReturn();
    }

    public void setEnsembleBiens(Map<Integer, Biens> ensemble) {
        rechercherController.setEnsembleBiens(ensemble);
    }

    public void setBool2(Boolean b) {
        rechercherController.setBool2(b);
    }

    public void passEvent(String sourceid) {
        rechercherController.fireNextSearchEvent(sourceid);
    }


}
