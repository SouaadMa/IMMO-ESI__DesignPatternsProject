package tpGUI.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tpGUI.Noyau.*;

public class AfficheMessages extends Stage {
	
	private Biens model;
	private Scene view;
	
	public AfficheMessages(Biens model, Scene view) {
		
		this.model = model; this.view = view;
		
		VBox vb = new VBox();
		
		vb.setAlignment(Pos.TOP_CENTER );
		
		AnchorPane aP = new AnchorPane(vb);
		
		Scene scene = new Scene(aP);
		
		aP.setPrefSize(500, 300);
		
		this.setScene(scene);
		
		
		int i=0;
		
		
		if(model.getMessages().isEmpty()) MainPage.CustomErreur("Ce bien n'a aucun message !");
		else {
			this.show();
		
			for(String s : model.getMessages()) {
				
				Button supprim = new Button("Supprimer");
				supprim.setId(Integer.toString(i));
				
				supprim.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						
						model.getMessages().remove(Integer.parseInt(supprim.getId()));
						
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
