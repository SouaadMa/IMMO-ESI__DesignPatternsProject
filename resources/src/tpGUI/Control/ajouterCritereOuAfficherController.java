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



    static Set<Biens> ensembleBiens;  static Agence agence;
    static Controller view; 
    static AjouterCritereOuAfficher view2;


    @FXML
    private Button ajouterUnAutreCritereButton;

    @FXML
    private Button AfficherLeResultatDeRechercheButton;

    @FXML
    void AfficherLeResultatDeRecherche(ActionEvent event ) {


        Iterator<Biens> it=ensembleBiens.iterator(); int i=1; Biens b;
        
        VBox vb = new VBox();
        
        Scene scene = new Scene(vb);
        Stage stage=new Stage();
        
        while(it.hasNext())
        {
            b = it.next();
            
            stage = new InfoBiens(b, scene, vb);
            stage.setScene(scene);
   
        }
        
        stage.show();
        
        view2.close();
        
        view.accueilClicked();

    }

    @FXML
    void ajouterUnAutreCritere(ActionEvent event) {

        view2.close();
      AjouterCritere fenetre=new AjouterCritere(view,ensembleBiens,agence);
      
      //fenetre.
      fenetre.show();

    }


    public void setInfo(AjouterCritereOuAfficher vview2, Controller vview, Set<Biens> eensembleBiens, Agence aagence )
    {

        ensembleBiens=eensembleBiens;
        agence=aagence;
        view=vview;
        view2=vview2;

    }




}
