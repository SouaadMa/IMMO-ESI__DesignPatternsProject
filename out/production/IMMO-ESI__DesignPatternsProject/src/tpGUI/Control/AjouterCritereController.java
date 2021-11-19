package tpGUI.Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import tpGUI.Noyau.Agence;
import tpGUI.Noyau.Biens;
import tpGUI.UI.AjouterCritere;

import java.util.Set;

public class AjouterCritereController {


    private AjouterCritere stage;

    @FXML
    private MenuItem adresse;

    @FXML
    private MenuItem wilaya;

    @FXML
    private MenuItem superficie;

    @FXML
    private MenuItem proprietaire;

    @FXML
    private MenuItem prix;

    @FXML
    private MenuItem typeTrans;

    @FXML
    private MenuItem typeBien;

    @FXML
    private MenuItem date;

    @FXML
    private MenuItem nbPieces;

    @FXML
    public MenuButton addcritere;


    public void setInfo(AjouterCritere view22 )
    {
        stage=view22;
    }

    @FXML
    void rechercher(ActionEvent event) {

        stage.quitAndSet();

        String sourceid = ((MenuItem)event.getSource()).getId();


        stage.passEvent(sourceid);


        //view.setBool2(false);
        //view.resetEnsembleBiens();

    }


}
