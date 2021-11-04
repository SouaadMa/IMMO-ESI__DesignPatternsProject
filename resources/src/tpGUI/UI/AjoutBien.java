package tpGUI.UI;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import tpGUI.Noyau.*;
import javafx.scene.control.ComboBox;

public class AjoutBien extends Stage {
	private Agence model;
	private Scene view;
	String cheminVersPhoto;
	private VBox biggestVBox;
	
	
	public AjoutBien (Agence model, Scene view, VBox biggestVBox) {
		this.model = model;
		this.view = view;
		this.biggestVBox=biggestVBox;
		
		
		this.setTitle("Ajouter un nouveau bien");
		this.setResizable(false);
		
		VBox container = new VBox(120);
		container.setPadding(new Insets(20,20,20,20));
		container.setAlignment(Pos. CENTER);
		container.setSpacing(20);
		
		Scene scene = new Scene(container, 500,220);
		
		Label demande = new Label("Quel est le type du bien que vous voulez ajouter?");
		container.getChildren().add(demande);
		
		RadioButton radioButton1 = new RadioButton("Maison"); radioButton1.setId("Maison"); radioButton1.setSelected(true);
        RadioButton radioButton2 = new RadioButton("Appartement"); radioButton2.setId("Appartement"); 
        RadioButton radioButton3 = new RadioButton("Terrain"); radioButton3.setId("Terrain");
        
        HBox choices = new HBox(radioButton1, radioButton2, radioButton3);
        choices.setPadding(new Insets(20,20,20,20));
        choices.setSpacing(20);
        choices.setAlignment(Pos.BASELINE_CENTER);
        
        ToggleGroup tg = new ToggleGroup();
        
        radioButton1.setToggleGroup(tg);
        radioButton2.setToggleGroup(tg);
        radioButton3.setToggleGroup(tg);
        
		container.getChildren().add(choices);
		
		Button confirm=new Button("Confirmer");    
		 confirm.setPrefSize(140,40);    
		 confirm.setFont(Font. font ("Verdana", 20));    
		 confirm.setOnAction(new EventHandler<ActionEvent>(){ 

			 public void handle(ActionEvent actionEvent) {     
	    	 RadioButton selectedRadioButton = (RadioButton) tg.getSelectedToggle();
	    	 String idSelected = selectedRadioButton.getId();
	    	 System.out.println(idSelected);
	    	 int choix=1;
	    	 if(idSelected.equals("Terrain")) {
	    		 choix = 3;
	    	 }
	    	 else {
	    		 if(idSelected.equals("Appartement")) {
	    			 choix = 2;
	    		 }
	    		 else {
	    			 
	    			 choix = 1;
	    		 }
	    	 }
	    	 System.out.println("Choix "+choix);
	    	 close();
	    	 action(choix);
	     }}); 
		
		Button retour=new Button("Retour");    
		 retour.setPrefSize(140,40);    
		 retour.setFont(Font. font ("Verdana", 20));    
		 retour.setOnAction(new EventHandler<ActionEvent>(){      
		 public void handle(ActionEvent actionEvent) {       close();     }}); 
		 
		 HBox buttons = new HBox(confirm, retour);
		 buttons.setPadding(new Insets(20,20,20,20));
		 buttons.setSpacing(20);
		 buttons.setAlignment(Pos.CENTER);
		 
		 container.getChildren().add(buttons);
		 
		this.setScene(scene);
		
	}
	
	public void action (int choix) {
		
		
		
		
		System.out.println("Le choix est "+choix);
		String type="";
		switch (choix) {
		case 1: type = "Maison";
		break;
		case 2: type = "Appartement";
		break;
		case 3: type = "Terrain";
		break;
		}
		
		
		
		biggestVBox.getChildren().clear();
		
		Label t = new Label("Ajout d'un nouveau bien de type: "+ type);
		
		HBox titre = new HBox(t);
		titre.setAlignment(Pos.CENTER);
		t.setFont(Font.font("Century Gothic", 16));
	
		
		VBox newVBox = collecteInfo(choix);
		
		
		newVBox.setPadding(new Insets(20,20,20,20));
		newVBox.setAlignment(Pos.CENTER);
		newVBox.setSpacing(20);
		biggestVBox.getChildren().add(titre);
		biggestVBox.getChildren().add(newVBox);
	
		
	}
	
	public VBox collecteInfo(int choix) {
		
		
		Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

		UnaryOperator<TextFormatter.Change> filter = c -> {
		    String text = c.getControlNewText();
		    if (validEditingState.matcher(text).matches()) {
		        return c ;
		    } else {
		        return null ;
		    }
		};

		StringConverter<Double> converter = new StringConverter<Double>() {

		    @Override
		    public Double fromString(String s) {
		        if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
		            return 0.0 ;
		        } else {
		            return Double.valueOf(s);
		        }
		    }


		    @Override
		    public String toString(Double d) {
		        return d.toString();
		    }
		};

		TextFormatter<Double> textFormattersuperficie = new TextFormatter<>(converter, 0.0, filter);
		TextFormatter<Double> textFormatterprix = new TextFormatter<>(converter, 0.0, filter);
		TextFormatter<Double> textFormattersuperficieh = new TextFormatter<>(converter, 0.0, filter);
		

		
		ComboBox<String> cb = new ComboBox<String>();	
		
		for(int i=0; i<48; i++) {
			cb.getItems().add(i, Wilaya.getNom(i+1));
		}
		cb.setValue("Adrar");
		
		TextField adr = new TextField();
		adr.setPromptText("Adresse");
		
		TextField sup = new TextField();
		sup.setPromptText("Superficie en m²");
		sup.setTextFormatter(textFormattersuperficie);
		
		VBox titles1 = new VBox(new Label("Adresse : "), new Label("Wilaya : "), new Label("Superficie : "));
		titles1.setSpacing(6);
		
		VBox textfields1 = new VBox(adr, cb, sup);
		textfields1.setSpacing(2);
		VBox.setMargin(textfields1, new Insets(10,0,0,0));
		titles1.setPadding(new Insets(5,66,5,4));
		
		
		TextField nom = new TextField();
		nom.setPromptText("Nom du Proprietaire");
		TextField prenom = new TextField();
		prenom.setPromptText("Prenom du Proprietaire");
		TextField tel = new TextField();
		tel.setPromptText("Tel du Proprietaire");
		tel.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		            tel.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		
		TextField adre = new TextField();
		adre.setPromptText("Adresse du Proprietaire");
		TextField adrem = new TextField();
		adrem.setPromptText("Adresse Mail du Proprietaire");
		
		
		VBox titresProprietaire = new VBox(new Label("Nom et Prenom: "), 
									new Label("Numéro de téléphone: "),
									new Label("Adresse: "), new Label("Adresse Mail: "));
		
		titresProprietaire.setSpacing(8);
		titresProprietaire.setPadding(new Insets(5,5,5,5));
		VBox textfieldsProprietaire = new VBox(new HBox(nom, prenom), 
										tel, adre, adrem);
								
		

		TextField prix = new TextField();
		prix.setPromptText("Prix "); CheckBox nego = new CheckBox("Negociable");
		
		HBox ligneprix = new HBox( prix, nego); ligneprix.setSpacing(4);
		prix.setTextFormatter(textFormatterprix);
		
		RadioButton rbVente = new RadioButton ("Vente"); rbVente.setSelected(true);
		RadioButton rbLocation = new RadioButton ("Location");
		RadioButton rbEchange = new RadioButton ("Echange");
		
		ToggleGroup tg2 = new ToggleGroup();
		
		rbVente.setToggleGroup(tg2);
		rbLocation.setToggleGroup(tg2);
		rbEchange.setToggleGroup(tg2);
		
		HBox tr = new HBox(rbVente, rbLocation, rbEchange);
		HBox.setMargin(tr, new Insets(8,5,5,8));

		
		TextField des = new TextField();
		des.setPromptText("Donner un court descriptif..");
		
		Date date = new Date();
	    String format = "dd/MM/yyyy";
		SimpleDateFormat f = new SimpleDateFormat(format);
		
		
		FileChooser photoURL = new FileChooser();
		
		photoURL.setInitialDirectory(new File("resources"));
		
		Button choisirPhoto = new Button("Choisir");
		Label path = new Label();
		
		HBox choisirPhotohbx = new HBox(path, choisirPhoto);
		
		
		choisirPhoto.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				cheminVersPhoto = photoURL.showOpenDialog(getOwner()).getPath();
				path.setText(cheminVersPhoto);
				
			}
		}
				);
		
		 
		VBox titles3 = new VBox(new Label("Prix : "), new Label("Transaction : "), new Label("Description : "), new Label("Photo : "));
		VBox textfields3 = new VBox(ligneprix, tr, des, choisirPhotohbx);
		
		titles3.setSpacing(11);
		titles3.setPadding(new Insets(2,58,30,4));
		textfields3.setSpacing(5);
		
		VBox titles4= new VBox(), textfields4= new VBox();
		
		VBox returnVBox = new VBox(new HBox(titles1, textfields1), new Label("Proprietaire :"), new HBox(titresProprietaire, textfieldsProprietaire), new HBox(titles3, textfields3), new HBox(titles4, textfields4));
		
		Button confirm=new Button();
		confirm.setPrefSize(140,40);    
		confirm.setFont(Font. font ("Verdana", 20));
		
		
		
		
		/* ***Les champs spécifiques à un certain type*** */
		
		switch(choix) {															/* ********** */
		case 1:																   /* MAISOOOOON */
																			  /* ********** */
			
			TextField nb1 = new TextField();
			nb1.setPromptText("Nombre d'étages");
			
			nb1.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nb1.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			
			
			titles4.getChildren().add(new Label("Nombre d'étages : "));
			textfields4.getChildren().add(nb1);
			
			TextField nb2 = new TextField();
			nb2.setPromptText("Nombre de pieces");
			
			nb2.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nb2.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			titles4.getChildren().add(new Label("Nombre de pieces: "));
			textfields4.getChildren().add(nb2);
			
			CheckBox garage = new CheckBox("Garage");
			CheckBox piscine = new CheckBox("Piscine");
			CheckBox jardin = new CheckBox("Jardin");
			CheckBox meuble = new CheckBox("Meuble");
			
			titles4.getChildren().add(new Label("Autres details : \n\n\n\n"));
			titles4.setSpacing(6);
			textfields4.getChildren().add(new VBox(garage, piscine, jardin, meuble));
			
			
			TextField nb3 = new TextField();
			nb3.setPromptText("Superficie Habitable");
			nb3.setTextFormatter(textFormattersuperficieh);
			
			titles4.getChildren().add(new Label("Superficie habitable: "));
			textfields4.getChildren().add(new HBox(nb3, new Label(" m²")));
			textfields4.setSpacing(3);
			titles4.setPadding(new Insets(5,15,5,5));
			
			 
			 confirm=new Button("Confirmer"); 
			 returnVBox.getChildren().add(confirm);
			 confirm.setOnAction(new EventHandler<ActionEvent>(){ 

				 public void handle (ActionEvent actionEvent)  {
					 
					 
					 TypeTrans trans;
					 
					 if(rbLocation.isSelected()) {
							trans = TypeTrans.LOCATION;
						}
						else {
							if(rbEchange.isSelected()) {
								trans = TypeTrans.ECHANGE;
							}
							else trans = TypeTrans.VENTE;
						}
					 
					 try {
					 
					 Proprietaire p;
					 
					 p = model.getProprietaire(nom.getText()+" "+prenom.getText());
					 if(p==null) {
						 System.out.println("NEW PROPRRRR");
						 p = new Proprietaire(nom.getText() , prenom.getText() , adrem.getText() , adre.getText() , tel.getText());
					 }
					 
					 
					 
					 Maison b = new Maison(adr.getText(), Wilaya.getNumWilaya(cb.getValue()), Double.parseDouble(sup.getText()), p, Double.parseDouble(prix.getText()),
								nego.isSelected(), trans, des.getText(), f.format(date), cheminVersPhoto,
								Integer.parseInt(nb2.getText()), meuble.isSelected(), Integer.parseInt(nb1.getText()), garage.isSelected(), piscine.isSelected(),
								jardin.isSelected(), Double.parseDouble(nb3.getText()));
					 
					 
					 if(Double.parseDouble(nb3.getText())>Double.parseDouble(sup.getText())) throw new SuperficieHabitableTresGrandeException();
					 
					 System.out.println(cb.getValue());
					 
					 model.insereBien(b);
					 
					 AnchorPane ap = new AnchorPane();
					 VBox vb = new VBox();
					 
					 vb.setId("GrilleAffichage");
					 ap.getChildren().add(vb);
					 
					 Scene scene = new Scene(ap);
					 
					 InfoBiens stage = new InfoBiens(b, scene, vb);
					 
					 stage.setScene(scene);
					 stage.show();
					 }
					 catch(SuperficieHabitableTresGrandeException e) {
						 MainPage.CustomErreur("La superficie habitable ne peut pas d�passer la superficie totale.");
					 }
					 catch(Exception e) {
						 MainPage.EmptyField("");
					 }
					 
					 
		    	 
		     }}); 
			 
			 break;
			 																/* ************* */
		case 2:															   /* APPARTEMENNNT */
																		  /* ************* */
			TextField nbPiecesTextField = new TextField();
			nbPiecesTextField.setPromptText("Nombre de pieces");
			
			nbPiecesTextField.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nbPiecesTextField.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			titles4.getChildren().add(new Label("Nombre de pieces : "));
			textfields4.getChildren().add(nbPiecesTextField);
			
			TextField numEtage = new TextField();
			numEtage.setPromptText("Numero d'etage");
			numEtage.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            numEtage.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			titles4.getChildren().add(new Label("Numero d'etage : "));
			textfields4.getChildren().add(numEtage);
			
			titles4.setPadding(new Insets(5,22,5,2));
			titles4.setSpacing(5);
			textfields4.setSpacing(2);

			
			RadioButton rbSimplexe = new RadioButton ("Simplexe");
			RadioButton rbDuplexe = new RadioButton ("Duplexe");
			
			ToggleGroup tgX = new ToggleGroup();
			
			rbSimplexe.setToggleGroup(tgX); rbSimplexe.setSelected(true);
			rbDuplexe.setToggleGroup(tgX);
			
			HBox tX = new HBox(rbSimplexe, rbDuplexe);
			
			tX.setSpacing(3);
			
			titles4.getChildren().add(new Label(""));
			textfields4.getChildren().add(tX);
			
			CheckBox ascenseur = new CheckBox("Ascenseur");
			CheckBox meubleA = new CheckBox("Meuble");
			
			
			textfields4.getChildren().add(ascenseur); textfields4.getChildren().add(meubleA);
			titles4.getChildren().add(new Label("")); titles4.getChildren().add(new Label(" "));
			
			
			confirm=new Button("Confirmer");     
			returnVBox.getChildren().add(confirm);
			confirm.setOnAction(new EventHandler<ActionEvent>(){ 

				 public void handle(ActionEvent actionEvent) { 
					 
					 TypeTrans trans;
					 
					 if(rbLocation.isSelected()) {
							trans = TypeTrans.LOCATION;
						}
						else {
							if(rbEchange.isSelected()) {
								trans = TypeTrans.ECHANGE;
							}
							else trans = TypeTrans.VENTE;
						}
					 
					 
					 Xplexe xp;
					 
					 if(rbDuplexe.isSelected()) {
							xp = Xplexe.DUPLEXE;
						}
						else {
							xp = Xplexe.SIMPLEXE;
						}
					 
					 try {
					 
					 Proprietaire p;
					 
					 p = model.getProprietaire(nom.getText()+" "+prenom.getText());
					 if(p==null) {
						 System.out.println("NEW PROPRRRR");
						 p = new Proprietaire(nom.getText() , prenom.getText() , adrem.getText() , adre.getText() , tel.getText());
						 
					 }
					 
					 if(des.getText().isEmpty()) des.setText("Pas de description");
					 
					 
					 if(Double.parseDouble(sup.getText())<=10) throw new WrongSurfaceException();
					 if(Double.parseDouble(prix.getText())<=10) throw new WrongPriceException();
					 if((Integer.parseInt(nbPiecesTextField.getText())==1 && !rbSimplexe.isSelected()) || (Integer.parseInt(nbPiecesTextField.getText())>1 && rbSimplexe.isSelected()) ) throw new WrongChoiceException();

					 Appartement b = new Appartement(adr.getText(), Wilaya.getNumWilaya(cb.getValue()), Double.parseDouble(sup.getText()), p, Double.parseDouble(prix.getText()),
								nego.isSelected(), trans, des.getText(), f.format(date), cheminVersPhoto,
								Integer.parseInt(nbPiecesTextField.getText()), meubleA.isSelected(), Integer.parseInt(numEtage.getText()), xp, 
								ascenseur.isSelected());
					 
					 System.out.println(cb.getValue());
					 
					 model.insereBien(b);
					 //model.valideBien(b);
					 
					 AnchorPane ap = new AnchorPane();
					 VBox vb = new VBox();
					 
					 vb.setId("GrilleAffichage");
					 ap.getChildren().add(vb);
					 
					 Scene scene = new Scene(ap);
					 
					 InfoBiens stage = new InfoBiens(b, scene, vb);
					 
					 stage.setScene(scene);
					 stage.show();
					 }
					 catch(WrongChoiceException e) {
						 MainPage.Erreur("Duplexe/Simplexe");
					 }
					 catch(WrongSurfaceException e) {
						 MainPage.Erreur("Superficie");
					 }
					 catch(WrongPriceException e) {
						 MainPage.Erreur("Prix");
					 }
					 catch(Exception e) {
						 MainPage.EmptyField("");
					 }
		    	 
		     }}); 
			
			break;																 /* *********** */
		case 3:																	/* TERRAIIIIIN */
																			   /* *********** */
			TextField statutJuridique = new TextField();
			statutJuridique.setPromptText("Son statut juridique");
			
			
			titles4.getChildren().add(new Label("Statut juridique : "));
			textfields4.getChildren().add(statutJuridique);
			
			TextField nbFacades = new TextField();
			nbFacades.setPromptText("Nombre de facades");
			
			nbFacades.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nbFacades.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			titles4.getChildren().add(new Label("Nombre de facades : "));
			textfields4.getChildren().add(nbFacades);
			
			titles4.setPadding(new Insets(2,13,2,3));
			
			confirm=new Button("Confirmer");  
			returnVBox.getChildren().add(confirm);
			     
			confirm.setOnAction(new EventHandler<ActionEvent>(){ 

				 public void handle(ActionEvent actionEvent) { 
					 
					 TypeTrans trans;
					 
					 if(rbLocation.isSelected()) {
							trans = TypeTrans.LOCATION;
						}
						else {
							if(rbEchange.isSelected()) {
								trans = TypeTrans.ECHANGE;
							}
							else trans = TypeTrans.VENTE;
						}
					 
					 
					 
					 /*
					 if(adr.getText().isEmpty() || cb.getValue().isEmpty() || sup.getText().isEmpty() || nom.getText().isEmpty() 
							 || prenom.getText().isEmpty() || adrem.getText().isEmpty() || adre.getText().isEmpty() || tel.getText().isEmpty()
							 || prix.getText().isEmpty() || statutJuridique.getText().isEmpty() || nbFacades.getText().isEmpty() ) {
						 
						 MainPage.EmptyField("wtf");
						 
					 }
					 */
					 
					 try {
					 Proprietaire p;
					 
					 p = model.getProprietaire(nom.getText()+" "+prenom.getText());
					 if(p==null) {
						 System.out.println("NEW PROPRRRR");
						 p = new Proprietaire(nom.getText() , prenom.getText() , adrem.getText() , adre.getText() , tel.getText());
					 }
					 
					 if(des.getText().isEmpty()) des.setText("Pas de description");
					 
					 if(Double.parseDouble(sup.getText())<=10) throw new WrongSurfaceException();
					 if(Double.parseDouble(prix.getText())<=10) throw new WrongPriceException();
					 

					 NonHabitable b = new NonHabitable(adr.getText(), Wilaya.getNumWilaya(cb.getValue()), Double.parseDouble(sup.getText()), 
							 	p, Double.parseDouble(prix.getText()),nego.isSelected(), trans, des.getText(), f.format(date), 
							 	cheminVersPhoto, statutJuridique.getText(), Integer.parseInt(nbFacades.getText()) );
					 
					 System.out.println(cb.getValue());
					 
					 
					 
					 model.insereBien(b);
					//model.valideBien(b);
					 
					 AnchorPane ap = new AnchorPane();
					 VBox vb = new VBox();
					 
					 vb.setId("GrilleAffichage");
					 ap.getChildren().add(vb);
					 
					 Scene scene = new Scene(ap);
					 
					 InfoBiens stage = new InfoBiens(b, scene, vb);
					 
					 stage.setScene(scene);
					 stage.show();
					 
					 }
					 catch(WrongSurfaceException e) {
						 MainPage.Erreur("Superficie");
					 }
					 catch(WrongPriceException e) {
						 MainPage.Erreur("Prix");
					 }
					 catch (Exception e) {
						 
						 MainPage.EmptyField("wtf");
						 
					 }
					 
					 
					 
		    	 
		     }}); 
			
			break; 
				 
			 
		
		}
		
		return returnVBox;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
