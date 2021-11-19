package tpGUI.Control;

import javafx.event.ActionEvent;
import tpGUI.UI.DeconnexionWindow;


public class DeconnexionController {


    private DeconnexionWindow stage;

    public void setInfo(DeconnexionWindow view)
    {
        stage=view;
    }

    public void deconnecter(ActionEvent event) {
        stage.quitAndSet();
    }

    public void annuler(ActionEvent event) {
        stage.close();
    }

}
