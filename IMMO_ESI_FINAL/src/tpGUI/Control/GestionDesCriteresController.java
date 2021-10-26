package tpGUI.Control;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;

public class GestionDesCriteresController implements EventHandler<ActionEvent> {


    int i ; Controller view; CheckBox checkBox;
    
    public GestionDesCriteresController(int i , Controller view , CheckBox checkBox)
    {
        this.i=i;
        this.view=view;
        this.checkBox=checkBox;
    }


    @Override
    public void handle(ActionEvent event) {

        boolean visible=(checkBox.isSelected());

        switch(i)
        {
            case 0: view.adresse.setVisible(visible);
                break;
            case 1: view.wilaya.setVisible(visible);
                break;
            case 2: view.superficie.setVisible(visible);
                break;
            case 3: view.proprietaire.setVisible(visible);
                break;
            case 4: view.prix.setVisible(visible);
                break;
            case 5: view.typeTrans.setVisible(visible);
                break;
            case 6: view.date.setVisible(visible);
                break;
            case 7: view.typeBien.setVisible(visible);
                break;
            case 8: view.nbPieces.setVisible(visible);
                break;
            default:
                    break;

        }
        view.ajouterUnCritere();

    }


    
}
