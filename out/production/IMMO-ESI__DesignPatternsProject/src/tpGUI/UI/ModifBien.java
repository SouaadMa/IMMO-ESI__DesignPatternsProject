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

public class ModifBien extends Stage implements CreationMessage {
	
	private Agence model;
	private Biens theOne;
	private String cheminVersPhoto;
	
	public ModifBien(Agence model, String id, VBox vb) {

		this.model = model;
		this.theOne = model.getBien(Integer.parseInt(id));
		
		HBox bigHbx = new HBox(); /* Le plus grand Hbox qui contient tout (Old version + New Version)*/
		
		bigHbx.setSpacing(10);

		int choix=0;
		
		if(((String)theOne.recupererChamps(8)).equals("Maison")) {
			choix=1;
		}
		else if(((String)theOne.recupererChamps(8)).equals("Appartement")) {
			choix=2;
		} 
		else choix=3;
		

		HBox titre = new HBox(new Label("Modification d'un bien de type: "+ (String)theOne.recupererChamps(8)));
		
		VBox newVBox = collecteModifications(choix);
		
		Label bigMessage = new Label(theOne.visualiser());
		
		
		newVBox.setPadding(new Insets(20,20,20,20));
		newVBox.setAlignment(Pos.CENTER);
		newVBox.setSpacing(20);
		
		bigMessage.setStyle("-fx-background-color: #ffd970dc;");
		bigMessage.setPadding(new Insets(20,20,30,20));
		bigMessage.setOpacity(50);
		bigMessage.setFont(Font.font("Century Gothic", 14));
		
		bigHbx.getChildren().add(newVBox);
		bigHbx.getChildren().add(bigMessage);
		
		vb.getChildren().add(titre);
		vb.getChildren().add(bigHbx);
		
	
		
	}
	
	public VBox collecteModifications(int choix) {
		
		
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
		cb.setValue(Wilaya.getNom((int)theOne.recupererChamps(2)));

		HBox lignewilaya = new HBox(new Label("Wilaya: "), cb);
		lignewilaya.setSpacing(81);
		
		TextField adr = new TextField();
		adr.setText((String)theOne.recupererChamps(1));
		HBox ligneadr = new HBox(new Label("Adresse : "), adr);
		ligneadr.setSpacing(71);
		
		NumberTextField sup = new NumberTextField();
		sup.setText(CreationMessage.fixDoubleDigits(((Double)theOne.recupererChamps(3))));
		HBox lignesuperficie = new HBox(new Label("Superficie: "), sup);
		lignesuperficie.setSpacing(65);
		
		
		TextField nom = new TextField();
		nom.setText(((Proprietaire)theOne.recupererChamps(4)).getNom());
		TextField prenom = new TextField();
		prenom.setText(((Proprietaire)theOne.recupererChamps(4)).getPrenom());
		TextField tel = new TextField();
		tel.setText(((Proprietaire)theOne.recupererChamps(4)).getTel());
		tel.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		            tel.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		TextField adre = new TextField();
		adre.setText(((Proprietaire)theOne.recupererChamps(4)).getAdresse());
		TextField adrem = new TextField();
		adrem.setText(((Proprietaire)theOne.recupererChamps(4)).getAdresseMail());
		
		
		VBox titres = new VBox(new Label("Proprietaire: "),
							new Label("Nom: "),
							new Label("Prénom: "),
							new Label("Numéro de téléphone: "),
							new Label("Adresse: "), 
							new Label("Adresse Mail: "));
		titres.setSpacing(9);
		
		VBox tfP = new VBox( new Label(" "), nom, prenom, tel, adre, adrem);
		tfP.setSpacing(2);
		
		HBox partieproprietaire = new HBox( titres, tfP);
		
		

		NumberTextField prix = new NumberTextField();
		prix.setText(CreationMessage.fixDoubleDigits((Double)theOne.recupererChamps(5))); CheckBox nego = new CheckBox("Negociable");
		nego.setSelected((Boolean)theOne.recupererChamps(21));
		VBox prixnego = new VBox(prix, nego);
		
		HBox ligneprix = new HBox(new Label("Prix: "), prixnego);
		ligneprix.setSpacing(99);
		
		
		RadioButton rbVente = new RadioButton ("Vente");
		RadioButton rbLocation = new RadioButton ("Location");
		RadioButton rbEchange = new RadioButton ("Echange");
		
		ToggleGroup tg2 = new ToggleGroup();
		
		rbVente.setToggleGroup(tg2);
		rbLocation.setToggleGroup(tg2);
		rbEchange.setToggleGroup(tg2);
		
		String tra = (String)theOne.recupererChamps(6);
		
		if(tra.equals("ECHANGE")) {
			tg2.selectToggle(rbEchange);
		}
		else {
			if(tra.equals("LOCATION")) tg2.selectToggle(rbLocation);
			else tg2.selectToggle(rbVente);
		}
		
		
		
		
		HBox tr = new HBox(new Label("Transaction: "), rbVente, rbLocation, rbEchange);
		tr.setSpacing(12);

		
		TextField des = new TextField();
		des.setText((String)theOne.recupererChamps(22));
		HBox lignedescriptif = new HBox(new Label("Description: "), des);
		lignedescriptif.setSpacing(50);
		
		
		Date date = new Date();
	    String format = "dd/MM/yyyy";
		SimpleDateFormat f = new SimpleDateFormat(format);
		

		FileChooser photoURL = new FileChooser();
		
		photoURL.setInitialDirectory(new File("resources"));
		
		Button choisirPhoto = new Button("Choisir");
		Label path = new Label();
		cheminVersPhoto = ((String)theOne.recupererChamps(20));
		
		
		choisirPhoto.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				cheminVersPhoto = photoURL.showOpenDialog(getOwner()).getPath();
				path.setText(cheminVersPhoto);
				
			}
		}
				);
		
		path.setText((String)theOne.recupererChamps(20));
		HBox lignephotoURL = new HBox(new Label("Photo : "), path, choisirPhoto);
		
		
		

		VBox newVBox = new VBox( ligneadr, lignewilaya, lignesuperficie, partieproprietaire, ligneprix, tr, lignedescriptif, lignephotoURL);
		newVBox.setAlignment(Pos.CENTER);
		Button confirm=new Button();
		confirm.setPrefSize(140,40);    
		confirm.setFont(Font. font ("Verdana", 20));
		
		switch(choix) {
		case 1:
			
			
			TextField nb1 = new TextField();
			nb1.setText(((Integer)theOne.recupererChamps(11)).toString());
			HBox lignenbEtages = new HBox(new Label("Nombre d'étages: "), nb1);
			nb1.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nb1.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			newVBox.getChildren().add(lignenbEtages);
			
			TextField nb2 = new TextField();
			nb2.setText(((Integer)theOne.recupererChamps(9)).toString());
			HBox lignenbPiecesM = new HBox(new Label("Nombre de pieces: "), nb2);
			nb2.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nb2.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			newVBox.getChildren().add(lignenbPiecesM);
			
			CheckBox garage = new CheckBox("Garage"); garage.setSelected((Boolean)theOne.recupererChamps(12));
			CheckBox piscine = new CheckBox("Piscine"); piscine.setSelected((Boolean)theOne.recupererChamps(13));
			CheckBox jardin = new CheckBox("Jardin"); jardin.setSelected((Boolean)theOne.recupererChamps(14));
			CheckBox meuble = new CheckBox("Meuble"); meuble.setSelected((Boolean)theOne.recupererChamps(10));
			
			HBox ligneAvantages = new HBox(new Label("Autres details"), new VBox(garage, piscine, jardin, meuble));
			
			newVBox.getChildren().add(ligneAvantages);
			
			
			TextField nb3 = new TextField();
			nb3.setText(CreationMessage.fixDoubleDigits((Double)theOne.recupererChamps(15)));
			HBox lignesupHab = new HBox(new Label("Superficie habitable: "), nb3);
			//nb3.setTextFormatter(textFormattersuperficieh);
			
			newVBox.getChildren().add(lignesupHab);
			 
			 confirm=new Button("Sauvegarder"); 
			 newVBox.getChildren().add(confirm);
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
					 
					 try {

						 Proprietaire p = CreationManager.createProprietaire(nom.getText() , prenom.getText() , adrem.getText() , adre.getText() , tel.getText());

						 Biens bienModifie = CreationManager.createMaison(adr.getText(), Wilaya.getNumWilaya(cb.getValue()), Double.parseDouble(sup.getText()), p, Double.parseDouble(prix.getText()),
								 nego.isSelected(), trans, des.getText(), f.format(date), cheminVersPhoto,
								 Integer.parseInt(nb2.getText()), meuble.isSelected(), Integer.parseInt(nb1.getText()), garage.isSelected(), piscine.isSelected(),
								 jardin.isSelected(), Double.parseDouble(nb3.getText()), false);


						 AnchorPane ap = new AnchorPane();
					 VBox vb = new VBox();
					 
					 vb.setId("GrilleAffichage");
					 ap.getChildren().add(vb);
					 vb.setAlignment(Pos.CENTER);
					 
					 Button terminer=new Button("Terminer");  
					 
					 
					 Scene scene = new Scene(ap);
					 
					 InfoBiens stage = new InfoBiens(bienModifie, scene,vb);
					 vb.getChildren().add(terminer);
					 vb.setAlignment(Pos.CENTER);
						
						 terminer.setPrefSize(180,90);    
						 terminer.setFont(Font. font ("Verdana", 20));    
						 terminer.setOnAction(new EventHandler<ActionEvent>(){ 
							 
							 public void handle(ActionEvent actionEvent) { 
								 
								 model.archiverBiens(theOne);
								 model.insereBien(bienModifie);
								 model.valideBien(bienModifie);
								 stage.close();
							
							 }
						 });
						
					 
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
			 
		case 2:
			
			TextField nbPiecesTextField = new TextField();
			nbPiecesTextField.setText(((Integer)theOne.recupererChamps(9)).toString());
			HBox lignenbPiecesA = new HBox(new Label("Nombre de pieces: "), nbPiecesTextField);
			nbPiecesTextField.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nbPiecesTextField.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			newVBox.getChildren().add(lignenbPiecesA);
			
			TextField numEtage = new TextField();
			numEtage.setText(((Integer)theOne.recupererChamps(11)).toString());
			HBox lignenumEtage = new HBox(new Label("Numero d'etage: "), numEtage);
			numEtage.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            numEtage.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			newVBox.getChildren().add(lignenumEtage);
			
			RadioButton rbSimplexe = new RadioButton ("Simplexe");
			RadioButton rbDuplexe = new RadioButton ("Duplexe");
			
			ToggleGroup tgX = new ToggleGroup();
			
			rbSimplexe.setToggleGroup(tgX); 
			rbDuplexe.setToggleGroup(tgX);
			
			if((Xplexe)theOne.recupererChamps(12)==Xplexe.DUPLEXE) {
				tgX.selectToggle(rbDuplexe);
			}
			else tgX.selectToggle(rbSimplexe);
			
			HBox tX = new HBox(rbSimplexe, rbDuplexe);
			
			newVBox.getChildren().add(tX);
			
			CheckBox ascenseur = new CheckBox("Ascenseur");
			ascenseur.setSelected((Boolean)theOne.recupererChamps(13));
			CheckBox meubleA = new CheckBox("Meuble");
			meubleA.setSelected((Boolean)theOne.recupererChamps(10));
			
			newVBox.getChildren().add(ascenseur); newVBox.getChildren().add(meubleA);
			
			
			confirm=new Button("Sauvegarder");     
			newVBox.getChildren().add(confirm);
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

						 Proprietaire p = CreationManager.createProprietaire(nom.getText() , prenom.getText() , adrem.getText() , adre.getText() , tel.getText());

						 Biens bienModifie = CreationManager.createAppartement(adr.getText(), Wilaya.getNumWilaya(cb.getValue()), Double.parseDouble(sup.getText()), p, Double.parseDouble(prix.getText()),
								 nego.isSelected(), trans, des.getText(), f.format(date), cheminVersPhoto,
								 Integer.parseInt(nbPiecesTextField.getText()), meubleA.isSelected(), Integer.parseInt(numEtage.getText()), xp,
								 ascenseur.isSelected(), false);

						 AnchorPane ap = new AnchorPane();
					 VBox vb = new VBox();
					 
					 vb.setId("GrilleAffichage");
					 vb.setAlignment(Pos.CENTER);
					 ap.getChildren().add(vb);
					 
					 Button terminer=new Button("Terminer");  
					 
					 
					 Scene scene = new Scene(ap);
					 
					 InfoBiens stage = new InfoBiens(bienModifie, scene, vb);
					 vb.getChildren().add(terminer);
					 vb.setAlignment(Pos.CENTER);
						
						 terminer.setPrefSize(180,90);    
						 terminer.setFont(Font. font ("Verdana", 20));    
						 terminer.setOnAction(new EventHandler<ActionEvent>(){ 
							 
							 public void handle(ActionEvent actionEvent) { 
								 
								 model.archiverBiens(theOne);
								 model.insereBien(bienModifie);
								 model.valideBien(bienModifie);
								 stage.close();
							
							 }
						 });

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
			
			
			break;
		case 3:
			
			TextField statutJuridique = new TextField();
			statutJuridique.setText((String)theOne.recupererChamps(10));
			HBox ligneStJu = new HBox(new Label("Statut juridique: "), statutJuridique);
			ligneStJu.setSpacing(40);
			
			newVBox.getChildren().add(ligneStJu);
			
			TextField nbFacades = new TextField();
			nbFacades.setText(((Integer)theOne.recupererChamps(11)).toString());
			HBox ligneNbFacades = new HBox(new Label("Nombre de facades: "), nbFacades);
			
			ligneNbFacades.setSpacing(20);
			
			nbFacades.textProperty().addListener(new ChangeListener<String>() {
			    @Override
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			        if (!newValue.matches("\\d*")) {
			            nbFacades.setText(newValue.replaceAll("[^\\d]", ""));
			        }
			    }
			});
			
			newVBox.getChildren().add(ligneNbFacades);
			
			confirm=new Button("Sauvegarder");  
			newVBox.getChildren().add(confirm);
			     
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
					 try {

						 Proprietaire p = CreationManager.createProprietaire(nom.getText() , prenom.getText() , adrem.getText() , adre.getText() , tel.getText());


						 Biens bienModifie = CreationManager.createTerrain(adr.getText(), Wilaya.getNumWilaya(cb.getValue()), Double.parseDouble(sup.getText()),
								 p, Double.parseDouble(prix.getText()),nego.isSelected(), trans, des.getText(), f.format(date),
								 cheminVersPhoto, statutJuridique.getText(), Integer.parseInt(nbFacades.getText()), false );


						 AnchorPane ap = new AnchorPane();
					 VBox vb = new VBox();
					 vb.setAlignment(Pos.CENTER);
					 
					 vb.setId("GrilleAffichage");
					 ap.getChildren().add(vb);
					 
					 Button terminer=new Button("Terminer");  
					 
					 
					 Scene scene = new Scene(ap);
					 
					 InfoBiens stage = new InfoBiens(bienModifie, scene, vb);
					 vb.getChildren().add(terminer);
					 vb.setAlignment(Pos.CENTER);
						
						 terminer.setPrefSize(100,70);    
						 terminer.setFont(Font. font ("Verdana", 20));    
						 terminer.setOnAction(new EventHandler<ActionEvent>(){ 
							 
							 public void handle(ActionEvent actionEvent) { 
								 
								 model.archiverBiens(theOne);
								 model.insereBien(bienModifie);
								 model.valideBien(bienModifie);
								 stage.close();
							
							 }
						 });
						
					 
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
		
		
		
		return newVBox;
		
		
	}
	
	
	

}
