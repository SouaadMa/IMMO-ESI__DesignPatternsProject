package tpGUI.Control;

import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.scene.control.Alert;
import tpGUI.Noyau.Agence;
import tpGUI.Noyau.Biens;
import tpGUI.Noyau.ElementNonExistantException;
import tpGUI.Noyau.TextFieldEmptyException;
import tpGUI.UI.*;

import java.util.*;


public class RechercherController   implements  EventHandler<ActionEvent> {

    Controller view;

    private int i;
    private Set<Biens> ensembleBiens;
    Object valeur1 = null, valeur2 = null;
    Agence agence;

    public RechercherController(Controller view, int i, Set<Biens> ensembleBiens, Agence agence) {
        this.i = i;
        this.view = view;
        this.ensembleBiens = ensembleBiens;
        this.agence = agence;

    }


    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try {
            if (i == 6) {
                if (event.getSource() == view.item1) valeur1 = "Vente";
                else {
                    if (event.getSource() == view.item2) valeur1 = "Location";
                    else valeur1 = "Echange";
                }
            } else {
                if (i == 8) {
                    if (event.getSource() == view.item1) valeur1 = "Appartement";
                    else {
                        if (event.getSource() == view.item2) valeur1 = "Maison";
                        else valeur1 = "Terrain";
                    }

                } else {
                	
                	System.out.println(i);
                	if(i==2) valeur1 = view.getComboBox(); else {
                    if (view.field1.getText().isEmpty() && view.field2.getText().isEmpty() && view.datePicker.getValue()==null) throw new TextFieldEmptyException();
                    if (i == 7) valeur1 = view.getDatePicker();
                    else valeur1 = view.getField1();
                    valeur2 = view.getField2();
                	}
                 
                }
            }

           
            ensembleBiens = agence.filtrer(this.i, ensembleBiens, valeur1, valeur2);
            
            
            
            if (this.ensembleBiens.isEmpty()) throw new ElementNonExistantException();
            AjouterCritereOuAfficher fenetre2 = new AjouterCritereOuAfficher(view, ensembleBiens, agence);
            fenetre2.show();
        }
        

         catch (ElementNonExistantException e)
        {
          view.accueilClicked();
          AucunBienNeVerifieLesConditions fenetreError = new AucunBienNeVerifieLesConditions();
          fenetreError.show();
          view.accueilButton.fire();
        }
        

        catch(TextFieldEmptyException e) {
            alert.setTitle("Champs d'ecriture vide");
            alert.setHeaderText(null);
            alert.setContentText(" Aucune information sur le bien n'a été entrée!");
            alert.setHeight(100);
            alert.setWidth(100);
            alert.showAndWait();
        }
        catch (Exception f) {
			MainPage.CustomErreur("Votre entree a généré une erreur.");
		}
    }


    public Set<Biens> getBiens()
    {
        return ensembleBiens;
    }
}
