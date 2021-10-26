package tpGUI.Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import tpGUI.UI.AucunBienNeVerifieLesConditions;

public class ExitController    {

    static AucunBienNeVerifieLesConditions view ;


    public void setInfo(AucunBienNeVerifieLesConditions vie )
    {
        view=vie;

    }
    public void exit() {

        view.close();

    }
}
