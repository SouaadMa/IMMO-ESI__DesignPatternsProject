package tpGUI.UI;

import com.gluonhq.charm.glisten.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tpGUI.Noyau.*;

public class ModifTerrain extends ModifBien{
    public ModifTerrain(Agence model, String id, VBox vb) {
        super(model, id, vb);
    }

    @Override
    protected void step2(Button confirm) {

        TextField statutJuridique = new TextField();
        statutJuridique.setText((String)theOne.recupererChamps(10));
        HBox ligneStJu = new HBox(new Label("Statut juridique: "), statutJuridique);
        ligneStJu.setSpacing(40);

        newVBox.getChildren().add(ligneStJu);

        TextField nbFacades = new TextField();
        nbFacades.setText(((Integer)theOne.recupererChamps(11)).toString());
        HBox ligneNbFacades = new HBox(new Label("Nombre de facades: "), nbFacades);

        ligneNbFacades.setSpacing(20);

        nbFacades.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nbFacades.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        newVBox.getChildren().add(ligneNbFacades);

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
                terminer.setOnAction(actionEvent1 -> {

                    model.archiverBiens(theOne);
                    model.insereBien(bienModifie);
                    model.valideBien(bienModifie);
                    stage.close();

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

                MainPage.EmptyField("Empty field");

            }

        });


    }
}
