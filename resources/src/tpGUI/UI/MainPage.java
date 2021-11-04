package tpGUI.UI;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainPage {
	
	
	
	static public void Erreur(String m) {    
		 Alert alert = new Alert(AlertType. INFORMATION );    
		 alert.setTitle("Information erron�e");    
		 alert.setHeaderText(null);    
		 alert.setContentText("L'information entr�e dans le champs "+m+" a g�n�r� une erreur. "+"Veuillez r�essayer!");
		 alert.showAndWait(); 
		 } 
	static public void EmptyField(String m) {    
		 Alert alert = new Alert(AlertType. INFORMATION );    
		 alert.setTitle("Champs vide");    
		 alert.setHeaderText(null);    
		 alert.setContentText("Vous avez laiss� un ou des champs vide(s). "+"Veuillez les remplir!");    
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
