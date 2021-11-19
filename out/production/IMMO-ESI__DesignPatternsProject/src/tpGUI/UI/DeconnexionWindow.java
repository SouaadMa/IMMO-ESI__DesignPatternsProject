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

    private Controller principalController;

    public DeconnexionWindow(Controller view) {

        this.setTitle("Deconnexion");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);

        principalController = view;
        //loader.setLocation(getClass().getResource("deconnecterWindow.fxml"));

        String pathToFXML = "/fxml/deconnecterWindow.fxml";
        try {

            URL FXMLlocation = getClass().getResource(pathToFXML);
            System.out.println(FXMLlocation);
            FXMLLoader loader = new FXMLLoader(FXMLlocation);

            Parent root = loader.load() ;

            DeconnexionController controller = loader.getController();
         	
            Scene scene = new Scene(root);
            this.setScene(scene);
            controller.setInfo(this);
            this.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void quitAndSet() {

        this.close();
        principalController.setAdmin(false);
        principalController.updateButtons();
        principalController.accueilClicked();

    }

}
