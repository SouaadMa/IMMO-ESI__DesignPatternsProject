package tpGUI.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tpGUI.Noyau.Proprietaire;

public class InfoProprietaire extends Stage {
	
	private Proprietaire model;
	private HBox resultHBox;
	
	public InfoProprietaire(Proprietaire model, Scene scene, VBox vb) {
		
		this.model = model;
		
		this.setTitle("Information du proprietaire");    
		this.setResizable(false); 
		
		
		
		resultHBox = AfficheProp(vb);
		  
	}
	
	public HBox getResultHBx() {
		return resultHBox;
	}
	
	public HBox AfficheProp(VBox bigVBox) {
		
		HBox returnHBx = new HBox();
		
		VBox container = new VBox();
		
		System.out.println("\nAdresse: "+ model.getAdresse());
		
		container.getChildren().add(new Label("Nom et Prenom: "+ model.getNomPrenom()));
		container.getChildren().add(new Label("Adresse: "+ model.getAdresse()));
		container.getChildren().add(new Label("Adresse email: "+ model.getAdresseMail()));
		container.getChildren().add(new Label("Numero de telephone: "+ model.getTel()));
		container.getChildren().add(new Label("Nombre de possesions: "+ model.getPossesions().size()));
		
		container.setPadding(new Insets(20,20,20,20));
		container.setSpacing(20);
		
		
		bigVBox.getChildren().add(returnHBx);
		bigVBox.setSpacing(40);
		bigVBox.setAlignment(Pos.CENTER);
		
		returnHBx.getChildren().add(container);
		
		return returnHBx;
	}
	
	
	
	
	
	
	
	

}
