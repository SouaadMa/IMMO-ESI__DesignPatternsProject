package tpGUI.Control;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import tpGUI.Noyau.*;
import javafx.scene.control.TextField;
import tpGUI.UI.ConnexionAdmins;
import tpGUI.UI.MainPage;



public class ConnexionController  {

	protected static  Agence agence;
	protected static Controller view;
	protected static ConnexionAdmins view2;

	@FXML
	protected TextField nom_utilisateur;

	@FXML
	protected PasswordField motdepasse;

	@FXML
	protected Button suivant;

	


	public void setInfo(ConnexionAdmins view22, Controller view1 , Agence mode) {

		view = view1;
		agence = mode;
		view2=view22;
	}

	public void suivantClicked() {

		if(agence==null) System.out.println("SUIVANT");


		if (agence.existeAdmin(motdepasse.getText(), nom_utilisateur.getText())) {



			        view.buttonsVbox.getChildren().removeAll(view.accueilButton,view.rechercheGenerale,view.valideButton,view.archiveButton,view.envoyerMessagesButton,view.afficherMessagesButton,view.ajouterCritereButton,view.plusOptionsButton,view.quitterButton,view.listeProprietaireButton,view.ajouterBienButton);

					view.pane2.setMinHeight(2);
			        view.buttonsVbox.getChildren().add(view.accueilButton);
					view.buttonsVbox.getChildren().add(view.rechercheGenerale);
					view.buttonsVbox.getChildren().add(view.ajouterCritereButton);
					view.buttonsVbox.getChildren().add(view.valideButton);
					view.buttonsVbox.getChildren().add(view.archiveButton);
					view.buttonsVbox.getChildren().add(view.listeProprietaireButton);
					view.buttonsVbox.getChildren().add(view.plusOptionsButton);
					view.buttonsVbox.getChildren().add(view.afficherMessagesButton);
					view.buttonsVbox.getChildren().add(view.quitterButton);
			        if(view.firstConnexion)
		        	{
				     view.ajouterCritereButton.setVisible(true);
				     view.valideButton.setVisible(true);
				     view.archiveButton.setVisible(true);
					 view.listeProprietaireButton.setVisible(true);
					 view.afficherMessagesButton.setVisible(true);
					 view.plusOptionsButton.setVisible(true);
				     view.firstConnexion=false;
			       }

		        	view.connexionButton.setText("deconnexion");
			        Controller.admin=true;
			        view2.close();

		} else {
			MainPage.CustomErreur("Les informations que vous avez entree ne correspondent a aucun admin !\n"
					+ "Vous allez consulter l'application en mode publique.");
		}

	}
	public void contacter() {
		
			view.envoyerMessageClicked();
			view2.close();
		}
	
	

}