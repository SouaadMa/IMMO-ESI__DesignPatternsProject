package tpGUI.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tpGUI.Noyau.*;

import java.util.ArrayList;

public class EnvoiMessage extends Stage {
	
	private ArrayList<String> messages;

	public EnvoiMessage(ArrayList<String> messages) {
		this.messages = messages;
		
		TextField textfield = new TextField();
		textfield.setPromptText("Ecrivez votre message ici..");
		
		textfield.setPrefSize(500, 300);
		textfield.setAlignment(Pos.TOP_LEFT);
		
		Button envoyer = new Button("Envoyer");
		
		
		VBox vb = new VBox(textfield, envoyer);
		
		vb.setAlignment(Pos.CENTER );
		
		AnchorPane aP = new AnchorPane(vb);
		
		Scene scene = new Scene(aP);
		
		
		this.setScene(scene);
		
		this.show();
		
		
		envoyer.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				
				try {
					
					EnvoiMessage.this.messages.add(textfield.getText());
				
				}
				catch(Exception ex) {
					MainPage.EmptyField("");
				}
				
				scene.getWindow().hide();
				
				
				MainPage.CustomErreur("Votre message a été bien envoyé !");
				
				
			}
		});

		
	}
	
	

}
