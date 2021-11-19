package tpGUI.Control;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import tpGUI.UI.*;



public class FirstWindowController {

    private Stage view;

    public void setInfo(Stage view)
    {
        this.view=view;
    }

    @FXML
    public void commencer () throws Exception{
        view.close();
        PrincipalWindow fenetre = new PrincipalWindow();
        fenetre.show();

    }

}
