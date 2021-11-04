package tpGUI.UI;

import java.io.File;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tpGUI.Control.FirstWindowController;

//Launch first page of app
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        //chargement de la première fenêtre
        FXMLLoader loader = new FXMLLoader();
        Parent root ;

        loader.setLocation(new File("resources/fxml/window1.fxml").toURI().toURL());

        try {
            root = loader.load();
            FirstWindowController controller = loader.getController(); //creation du contrôleur
            controller.setInfo(primaryStage);
            primaryStage.setTitle("ImmoESI");
            primaryStage.setScene(new Scene(root));
            URL url2 = new File("resources/src/tpGUI/UI/logo-circle.png").toURI().toURL();
            primaryStage.getIcons().add(new Image(url2.toString()));
            primaryStage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
