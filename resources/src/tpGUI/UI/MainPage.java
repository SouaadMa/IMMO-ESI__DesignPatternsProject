package tpGUI.UI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainPage {

	
	static public void Erreur(String m) {    
		 Alert alert = new Alert(AlertType. INFORMATION );    
		 alert.setTitle("Information erronée");
		 alert.setHeaderText(null);    
		 alert.setContentText("L'information entrée dans le champs "+m+" a généré une erreur. "+"Veuillez réessayer!");
		 alert.showAndWait(); 
		 } 
	static public void EmptyField(String m) {    
		 Alert alert = new Alert(AlertType. INFORMATION );    
		 alert.setTitle("Champs vide");    
		 alert.setHeaderText(null);    
		 alert.setContentText("Vous avez laissé un ou des champs vide(s). "+"Veuillez les remplir!");
		 alert.showAndWait(); 
		 } 
	
	static public void CustomErreur(String m) {
		 Alert alert = new Alert(AlertType. INFORMATION );    
		 alert.setTitle("Erreur");    
		 alert.setHeaderText(null);    
		 alert.setContentText(m);
		 alert.showAndWait(); 
	}

}
