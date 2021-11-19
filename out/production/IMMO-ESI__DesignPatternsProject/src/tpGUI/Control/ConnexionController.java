package tpGUI.Control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import tpGUI.Noyau.*;
import javafx.scene.control.TextField;
import tpGUI.UI.ConnexionAdmins;
import tpGUI.UI.MainPage;



public class ConnexionController  {

	private ConnexionAdmins stage;

	@FXML
	private TextField nom_utilisateur;

	@FXML
	private PasswordField motdepasse;

	@FXML
	private Button suivant;


	public void setInfo(ConnexionAdmins view) {
		stage=view;
	}

	public void suivantClicked() {

		if (AdminsManager.existeAdmin(motdepasse.getText(), nom_utilisateur.getText())) {

			stage.quitAndSet();

		} else {
			MainPage.CustomErreur("Les informations que vous avez entree ne correspondent a aucun admin !\n"
					+ "Vous allez consulter l'application en mode publique.");
		}

	}
	public void contacter() {
		//Contact
		stage.close();
	}


}