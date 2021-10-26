package tpGUI.Control;

import javafx.event.ActionEvent;
import tpGUI.UI.DeconnexionWindow;


public class DeconnexionController {


    static Controller view ; static DeconnexionWindow view2;


    public void setInfo(DeconnexionWindow view222,Controller view22  )
    {
        view=view22;
        view2=view222;

    }
    public void deconnecter(ActionEvent event) {

        view.buttonsVbox.getChildren().removeAll(view.ajouterBienButton,view.accueilButton,view.rechercheGenerale,view.valideButton,view.archiveButton,view.envoyerMessagesButton,view.afficherMessagesButton,view.ajouterCritereButton,view.quitterButton,view.plusOptionsButton,view.listeProprietaireButton);

        view.buttonsVbox.getChildren().add(view.accueilButton);
        view.buttonsVbox.getChildren().add(view.rechercheGenerale);
        view.buttonsVbox.getChildren().add(view.ajouterBienButton);
        view.buttonsVbox.getChildren().add(view.envoyerMessagesButton);
        view.buttonsVbox.getChildren().add(view.quitterButton);

        view.connexionButton.setText("Connexion");
        view.accueilButton.fire();
        view2.close();
        Controller.admin=false;
        view.accueilClicked();
    }

    public void non(ActionEvent event) {

        view2.close();

    }



}
