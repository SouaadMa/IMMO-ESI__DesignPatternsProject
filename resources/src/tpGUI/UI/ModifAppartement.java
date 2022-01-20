package tpGUI.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import tpGUI.Control.NoyauFacade;
import tpGUI.Noyau.*;

public class ModifAppartement extends ModifBien{

    public ModifAppartement(String id, VBox vb) {
        super(id, vb);
    }

    @Override
    protected void step2(Button confirm) {
        TextField nbPiecesTextField = new TextField();
        nbPiecesTextField.setText(((Integer)NoyauFacade.getInstance().recupererChamps(theOne, 9)).toString());
        HBox lignenbPiecesA = new HBox(new Label("Nombre de pieces: "), nbPiecesTextField);
        nbPiecesTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nbPiecesTextField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        newVBox.getChildren().add(lignenbPiecesA);

        TextField numEtage = new TextField();
        numEtage.setText(((Integer)NoyauFacade.getInstance().recupererChamps(theOne, 11)).toString());
        HBox lignenumEtage = new HBox(new Label("Numero d'etage: "), numEtage);
        numEtage.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                numEtage.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        newVBox.getChildren().add(lignenumEtage);

        RadioButton rbSimplexe = new RadioButton ("Simplexe");
        RadioButton rbDuplexe = new RadioButton ("Duplexe");

        ToggleGroup tgX = new ToggleGroup();

        rbSimplexe.setToggleGroup(tgX);
        rbDuplexe.setToggleGroup(tgX);

        if(NoyauFacade.getInstance().recupererChamps(theOne, 12)==Xplexe.DUPLEXE) {
            tgX.selectToggle(rbDuplexe);
        }
        else tgX.selectToggle(rbSimplexe);

        HBox tX = new HBox(rbSimplexe, rbDuplexe);

        newVBox.getChildren().add(tX);

        CheckBox ascenseur = new CheckBox("Ascenseur");
        ascenseur.setSelected((Boolean)NoyauFacade.getInstance().recupererChamps(theOne, 13));
        CheckBox meubleA = new CheckBox("Meuble");
        meubleA.setSelected((Boolean)NoyauFacade.getInstance().recupererChamps(theOne, 10));

        newVBox.getChildren().add(ascenseur); newVBox.getChildren().add(meubleA);


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

                //Button terminer=new Button("Terminer");

                Scene scene = new Scene(ap);

                NoyauFacade.getInstance().archivageBien(theOne);
                theOne = NoyauFacade.getInstance().insertionBien(bienModifie);
                NoyauFacade.getInstance().validationBien(bienModifie);

                InfoBiens stage = new InfoBiens(theOne, scene, vb);

                /*vb.getChildren().add(terminer);
                vb.setAlignment(Pos.CENTER);

                terminer.setPrefSize(180,90);
                terminer.setFont(Font. font ("Verdana", 20));
                terminer.setOnAction(actionEvent1 -> {

                    NoyauFacade.getInstance().archivageBien(theOne);
                    NoyauFacade.getInstance().insertionBien(bienModifie);
                    NoyauFacade.getInstance().validationBien(bienModifie);
                    stage.close();

                });*/

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

        });

    }

}
