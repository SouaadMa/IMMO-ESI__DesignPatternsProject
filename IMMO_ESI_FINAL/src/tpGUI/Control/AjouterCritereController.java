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


    static Controller view;    static AjouterCritere view2;  static Set<Biens> ensembleBiens; static Agence agence;




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

    @FXML
    void rechercher(ActionEvent event) {
    	
    	
    	

        view2.close();
        view.setInfo(ensembleBiens,agence);
        view.bool2=true;

        if(event.getSource()==adresse) view.adresse.fire();
        else
        {
            if(event.getSource()==wilaya) view.wilaya.fire();
            else
            {
                if(event.getSource()==superficie) view.superficie.fire();
                else
                {
                    if(event.getSource()==proprietaire) view.proprietaire.fire();
                    else
                    {
                        if(event.getSource()==prix) view.prix.fire();
                        else
                        {
                            if(event.getSource()==date) view.date.fire();
                            else
                            {
                                if(event.getSource()==typeTrans) view.typeTrans.fire();
                                else
                                {
                                    if(event.getSource()==typeBien) view.typeBien.fire();
                                    else view.nbPieces.fire();

                                }
                            }
                        }
                    }
                }
            }
        }
        view.bool2=false;
        view.setInfo(agence.getBiens(),agence);

    }

    public void setInfo(AjouterCritere view22 ,Controller vieww, Set<Biens> ensembleBienss ,Agence agencee )
    {
        view=vieww;
        view2=view22;
        ensembleBiens=ensembleBienss;
        agence=agencee;
    }


}
