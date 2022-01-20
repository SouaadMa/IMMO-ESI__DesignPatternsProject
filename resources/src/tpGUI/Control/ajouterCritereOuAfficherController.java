package tpGUI.Control;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tpGUI.Noyau.Agence;
import tpGUI.Noyau.Biens;
import java.util.*;
import tpGUI.UI.*;




public class ajouterCritereOuAfficherController {

    private Map<Integer, Biens> ensembleBiens;
    private AjouterCritereOuAfficher view;


    @FXML
    private Button ajouterUnAutreCritereButton;

    @FXML
    private Button AfficherLeResultatDeRechercheButton;

    @FXML
    void AfficherLeResultatDeRecherche(ActionEvent event ) {


        Iterator<Map.Entry<Integer, Biens>> it=ensembleBiens.entrySet().iterator(); int i=1; Biens b;

        VBox vb = new VBox();

        Scene scene = new Scene(vb);
        Stage stage=new Stage();

        /*
        while(it.hasNext())
        {
            b = it.next().getValue();

            stage = new InfoBiens(b, scene, vb);
            stage.setScene(scene);

        }*/

        stage.show();

        view.quitAndReturn();

    }


    @FXML
    void ajouterUnAutreCritere(ActionEvent event) {

        view.close();
      AjouterCritere fenetre=new AjouterCritere(this);
      
      //fenetre.
      fenetre.show();

    }


    public void setInfo(AjouterCritereOuAfficher stage, Map<Integer, Biens> ensembleBiens )
    {

        this.ensembleBiens=ensembleBiens;
        this.view=stage;

    }

    public void setEnsembleBiens() {
        this.view.setEnsembleBiens(ensembleBiens);
        this.view.setBool2(true);
    }

    public void fireNextEvent(String sourceid) {
        this.view.passEvent(sourceid);
    }


}
