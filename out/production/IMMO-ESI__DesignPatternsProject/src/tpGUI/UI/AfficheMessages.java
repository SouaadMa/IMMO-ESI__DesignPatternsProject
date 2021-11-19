package tpGUI.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tpGUI.Control.Controller;
import tpGUI.Noyau.*;

import java.util.ArrayList;

public class AfficheMessages extends Stage {
	
	private ArrayList<String> messages;

	public AfficheMessages(ArrayList<String> messages) {
		
		this.messages = messages;

		VBox vb = new VBox();
		
		vb.setAlignment(Pos.TOP_CENTER );
		
		AnchorPane aP = new AnchorPane(vb);
		
		Scene scene = new Scene(aP);
		
		aP.setPrefSize(500, 300);
		
		this.setScene(scene);

		int i=0;

		if(messages.isEmpty()) MainPage.CustomErreur("Ce bien n'a aucun message !");
		else {
			this.show();
		
			for(String s : messages) {
				
				Button supprim = new Button("Supprimer");
				supprim.setId(Integer.toString(i));
				
				supprim.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {

						int idMessage = Integer.parseInt(supprim.getId());
						messages.remove(idMessage);

						AfficheMessages.this.close();
						new AfficheMessages(messages);

					}
				});
				
				
				HBox hbX = new HBox(new Label(s), supprim);
				hbX.setSpacing(20);
				hbX.setAlignment(Pos.CENTER);
				
				vb.getChildren().add(hbX);
				vb.setAlignment(Pos.CENTER);
				i++;
				
			}
		}
	}


}
