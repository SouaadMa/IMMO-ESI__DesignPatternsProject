package tpGUI.UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;



import com.gluonhq.charm.glisten.control.ExpansionPanel;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tpGUI.Control.NoyauFacade;

import javafx.scene.image.*;

import java.io.FileInputStream; 

public class InfoBiens extends Stage implements CreationMessage {
	
	//private Biens model;
	private HBox resultHBox;
	private int idBiens;
	private NoyauFacade facade = NoyauFacade.getInstance();
	
	
	public InfoBiens(int i, Scene scene, VBox ctrl) {
		
		//this.model = model;
		this.idBiens = i;

		this.setTitle("Information du bien");    
		this.setResizable(false); 
		this.setScene(scene);

		resultHBox = AfficheBien(ctrl);
		resultHBox.setStyle("-fx-background-color: #fff7cc;");

	}
	
	public HBox getResultHbox() {
		return resultHBox;
	}
	
	
	public HBox AfficheBien(VBox parent) {
		
		ExpansionPanel eP = new ExpansionPanel() ;
		eP.setMinWidth(580);
		eP.setPrefWidth(580);
		//eP.setMaxWidth(700);
		eP.setPrefHeight(110);
		//eP.setMaxHeight(200);
		
		HBox bigHbx = new HBox(); /* Le plus grand Hbox qui contient tout (Image + Expansion Panel)*/
		
		bigHbx.setSpacing(10);
		
		Image image=null;
		ImageView imgv1=new ImageView();
		
		try {
			image = new Image(new FileInputStream( (String) facade.recupererChamps(idBiens, 20)));
			System.out.println((String) facade.recupererChamps(idBiens, 20));
		}
		catch (Exception e) {
			
			try {
				image = new Image(new FileInputStream("resources/img/notfound.png"));
			}
			catch (Exception ex) {
				System.out.println("Image de remplacement non trouvée !");
			}
		}
		
		imgv1.setImage(image);
		imgv1.setFitWidth(200);
		imgv1.setFitHeight(150);
		
		bigHbx.getChildren().add(imgv1);

		HBox hbx = new HBox(); /* Le petit hbox qui contient les informations non détaillées */
		
		hbx.setPadding(new Insets(20,20,20,20));    
		hbx.setAlignment(Pos.CENTER_LEFT ); 
		hbx.setSpacing(20);
		
		hbx.getStyleClass().add("BUENEO");

		hbx.getChildren().add(CreationMessage.creerMessage(idBiens, 0));
		hbx.getChildren().add(CreationMessage.creerMessage(idBiens, 1));
		hbx.getChildren().add(CreationMessage.creerMessage(idBiens, 5));
		hbx.getChildren().add(CreationMessage.creerMessage(idBiens, 7));
		
		eP.setCollapsedContent(hbx); 
		hbx.setPadding(new Insets(20,20,20,20));    
		hbx.setAlignment(Pos.CENTER_LEFT ); 
		hbx.setSpacing(25);
		
		
		
		HBox hbxdetails = new HBox();  /* Le hbox qui contient les informations détaillées */
		VBox vbx1 = new VBox(); 
		VBox vbx2 = new VBox();
		VBox vbx3 = new VBox();

		
		for(int i=3; i<5; i++)	vbx1.getChildren().add(CreationMessage.creerMessage(idBiens, i));
		vbx1.getChildren().add(CreationMessage.creerMessage(idBiens, 55));
		Label l;
		if((Boolean)facade.recupererChamps(idBiens, 21)) l=new Label("Negociable");
		else l = new Label("Pas negociable");
		 l.setAlignment(Pos. BASELINE_LEFT );     
		 l.setFont(Font. font ("Berlin Sans FB", 18));     
		 l.setLineSpacing(40); 
		 vbx1.getChildren().add(l);
		vbx1.getChildren().add(CreationMessage.creerMessage(idBiens, 6));
		
		vbx1.setPrefWidth(400);

		for(Text info : facade.visualiserInfos(idBiens)) {
			vbx2.getChildren().add(info);
		}
		for(Text detail : facade.visualiserInfosDetails(idBiens)) {
			vbx3.getChildren().add(detail);
		}

		
		hbxdetails.getChildren().add(vbx1);
		
		vbx1.getStyleClass().add("BUENEO");
		vbx1.setPadding(new Insets(10,10,10,10));
		hbxdetails.getChildren().add(vbx2);
		
		vbx2.getStyleClass().add("BUENEO");
		vbx2.setPadding(new Insets(10,10,10,10));
		hbxdetails.getChildren().add(vbx3);
	
		vbx3.getStyleClass().add("BUENEO");
		vbx3.setPadding(new Insets(10,10,10,10));
		
		
		eP.setExpandedContent(hbxdetails);
		
		
		
		hbxdetails.setPrefWidth(700);
		
		bigHbx.getChildren().add(eP);
		
		parent.setPadding(new Insets(20,20,20,20));    
		parent.setAlignment(Pos.CENTER_LEFT ); 
		parent.setSpacing(15);

		bigHbx.setId(String.valueOf(idBiens));
		System.out.println("Infos bien de id " + bigHbx.getId());
		(parent).getChildren().add(bigHbx);
		return bigHbx;
		
	}

	
}
