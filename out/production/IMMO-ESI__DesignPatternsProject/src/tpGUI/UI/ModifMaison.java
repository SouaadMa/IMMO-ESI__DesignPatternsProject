package tpGUI.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tpGUI.Noyau.*;

public class ModifMaison extends ModifBien{
    public ModifMaison(Agence model, String id, VBox vb) {
        super(model, id, vb);
    }

    @Override
    protected void step2(Button confirm) {

        TextField nb1 = new TextField();
        nb1.setText(((Integer)theOne.recupererChamps(11)).toString());
        HBox lignenbEtages = new HBox(new Label("Nombre d'étages: "), nb1);
        nb1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nb1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        newVBox.getChildren().add(lignenbEtages);

        TextField nb2 = new TextField();
        nb2.setText(((Integer)theOne.recupererChamps(9)).toString());
        HBox lignenbPiecesM = new HBox(new Label("Nombre de pieces: "), nb2);
        nb2.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nb2.setText(newValue.replaceAll("[^\\d]", ""));
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
        confirm.setOnAction(actionEvent -> {

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
                terminer.setOnAction(actionEvent1 -> {

                    model.archiverBiens(theOne);
                    model.insereBien(bienModifie);
                    model.valideBien(bienModifie);
                    stage.close();

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


        });

    }
}
