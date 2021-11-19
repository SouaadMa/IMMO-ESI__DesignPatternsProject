package tpGUI.UI;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tpGUI.Control.ConnexionController;
import tpGUI.Control.Controller;


public class ConnexionAdmins extends Stage {

    private Controller principalController;

    public ConnexionAdmins(Controller view) {

        this.setTitle("Connexion en tant qu'admin");
        this.setResizable(false);
        this.setMinHeight(50);
        this.setMinWidth(100);
        //loader.setLocation(getClass().getResource("windowConnexion.fxml"));

        principalController = view;
        String pathToFXML = "/fxml/windowConnexion.fxml";

        try {


            URL FXMLlocation = getClass().getResource(pathToFXML);
            System.out.println(FXMLlocation);
            FXMLLoader loader = new FXMLLoader(FXMLlocation);

            Parent root = loader.load() ;

            ConnexionController controller = loader.getController();

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
        principalController.setAdmin(true);
        principalController.updateButtons();
        principalController.accueilClicked();
    }
}