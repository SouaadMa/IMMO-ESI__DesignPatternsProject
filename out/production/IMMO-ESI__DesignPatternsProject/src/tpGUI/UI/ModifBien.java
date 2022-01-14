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
	
	protected Agence model;
	protected Biens theOne;
	protected String cheminVersPhoto;

	protected VBox newVBox;
	protected RadioButton rbVente = new RadioButton ("Vente");
	protected RadioButton rbLocation = new RadioButton ("Location");
	protected RadioButton rbEchange = new RadioButton ("Echange");
	protected HBox lignewilaya;
	protected TextField adr = new TextField();
	protected HBox ligneadr;
	protected TextField nom = new TextField();
	protected TextField prenom = new TextField();
	protected TextField tel = new TextField();
	protected NumberTextField sup = new NumberTextField();
	protected TextField adre = new TextField();
	protected TextField adrem = new TextField();
	protected ComboBox<String> cb = new ComboBox<>();
	protected NumberTextField prix = new NumberTextField();
	protected CheckBox nego = new CheckBox("Negociable");
	protected TextField des = new TextField();
	protected Date date = new Date();
	protected String format = "dd/MM/yyyy";
	protected SimpleDateFormat f = new SimpleDateFormat(format);

	public ModifBien(Agence model, String id, VBox vb) {

		this.model = model;
		this.theOne = model.getBien(Integer.parseInt(id));
		
		HBox bigHbx = new HBox(); /* Le plus grand Hbox qui contient tout (Old version + New Version)*/
		
		bigHbx.setSpacing(10);



		HBox titre = new HBox(new Label("Modification d'un bien de type: "+ theOne.recupererChamps(8)));
		
		newVBox = collecteModifications();
		
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
	
	public VBox collecteModifications() {
		
		
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
		

		
		for(int i=0; i<48; i++) {
			cb.getItems().add(i, Wilaya.getNom(i+1));
		}
		cb.setValue(Wilaya.getNom((int)theOne.recupererChamps(2)));

		lignewilaya = new HBox(new Label("Wilaya: "), cb);
		lignewilaya.setSpacing(81);

		adr.setText((String)theOne.recupererChamps(1));
		ligneadr = new HBox(new Label("Adresse : "), adr);
		ligneadr.setSpacing(71);
		

		sup.setText(CreationMessage.fixDoubleDigits(((Double)theOne.recupererChamps(3))));
		HBox lignesuperficie = new HBox(new Label("Superficie: "), sup);
		lignesuperficie.setSpacing(65);

		nom.setText(((Proprietaire)theOne.recupererChamps(4)).getNom());

		prenom.setText(((Proprietaire)theOne.recupererChamps(4)).getPrenom());

		tel.setText(((Proprietaire)theOne.recupererChamps(4)).getTel());
		tel.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		        if (!newValue.matches("\\d*")) {
		            tel.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});

		adre.setText(((Proprietaire)theOne.recupererChamps(4)).getAdresse());

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
		
		


		prix.setText(CreationMessage.fixDoubleDigits((Double)theOne.recupererChamps(5)));

		nego.setSelected((Boolean)theOne.recupererChamps(21));
		VBox prixnego = new VBox(prix, nego);
		
		HBox ligneprix = new HBox(new Label("Prix: "), prixnego);
		ligneprix.setSpacing(99);
		
		

		
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

		

		des.setText((String)theOne.recupererChamps(22));
		HBox lignedescriptif = new HBox(new Label("Description: "), des);
		lignedescriptif.setSpacing(50);
		

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


		newVBox = new VBox( ligneadr, lignewilaya, lignesuperficie, partieproprietaire, ligneprix, tr, lignedescriptif, lignephotoURL);
		newVBox.setAlignment(Pos.CENTER);
		Button confirm=new Button();
		confirm.setPrefSize(140,40);    
		confirm.setFont(Font. font ("Verdana", 20));
		


		step2(confirm);
		
		
		
		return newVBox;
		
		
	}

	protected void step2(Button confirm) {
	}


}
