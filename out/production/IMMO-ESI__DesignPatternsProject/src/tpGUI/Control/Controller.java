package tpGUI.Control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import tpGUI.Noyau.Agence;
import tpGUI.Noyau.Biens;
import tpGUI.Noyau.Proprietaire;
import tpGUI.Noyau.Wilaya;
import tpGUI.UI.*;

import java.io.*;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Set;


public class Controller {



    private Agence agence;
    private Scene view;
    private PrincipalWindow fenetreprincipale;
    private boolean accueil = false;
    private boolean archive = false;
    private boolean valider = false;
    private boolean admin=false;
    private boolean bool = false;
    private String tab[] = {"Adresse", "Wilaya souhaitee", "Superficie", "Coordonnees d'un proprietaire", "Prix", "Type de transaction", "Date", "Le type du bien", "Nombre maximal des pieces"};
    private Set<Biens> ensembleBiens;
    private int i = 0;
    private boolean bool2=false;


    protected Button buttonRechercher = new Button();
    protected TextField field1 = new TextField("");
    protected TextField field2 = new TextField("");
    protected DatePicker datePicker = new DatePicker();
    protected MenuItem item1 = new MenuItem();
    protected MenuItem item2 = new MenuItem();
    protected MenuItem item3 = new MenuItem();
    protected CheckBox[] checkBoxes = new CheckBox[9];
    protected ImageView image = new ImageView();

    protected boolean firstConnexion=true;

    @FXML
    protected Pane pane;

    @FXML
    protected MenuItem adresse;

    @FXML
    protected MenuItem wilaya;

    @FXML
    protected MenuItem superficie;

    @FXML
    protected MenuItem proprietaire;

    @FXML
    protected MenuItem prix;

    @FXML
    protected MenuItem typeTrans;

    @FXML
    protected MenuItem typeBien;

    @FXML
    protected MenuItem date;

    @FXML
    protected MenuItem nbPieces;

    @FXML
    public MenuButton rechercheGenerale;

    @FXML
    protected Button accueilButton;

    @FXML
    protected Button ajouterCritereButton;

    @FXML
    protected Button valideButton;

    @FXML
    protected Button archiveButton;

    @FXML
    protected Button plusOptionsButton;

    @FXML
    protected Button quitterButton;

    @FXML
    protected Button listeProprietaireButton;


    @FXML
    protected VBox buttonsVbox;

    @FXML
    protected Button envoyerMessagesButton;

    @FXML
    protected Button afficherMessagesButton;

    @FXML
    protected Button ajouterBienButton;

    @FXML
    protected Button connexionButton ;

    @FXML
    protected Pane pane2 ;

    @FXML
    protected VBox vbox = new VBox() ;

    @FXML
    protected ComboBox<String> cb = new ComboBox<>();

    @FXML
    protected AnchorPane anchorPane;

    @FXML
    protected ScrollPane sp = new ScrollPane();

    @FXML
    protected HBox bigHbox = new HBox();


    public void setInfo(Agence agenc) {

        this.ensembleBiens = agenc.getBiens();
        agence = agenc;

    }

    public void setEnsembleBiens(Set<Biens> ensembleBien) {

        this.ensembleBiens = ensembleBien;

    }

    public void resetEnsembleBiens() {

        this.ensembleBiens = agence.getBiens();

    }

    public Scene getView() {
        return view;
    }

    public void setStage(PrincipalWindow stg) {
        fenetreprincipale = stg;
    }

    public void accueilClicked() {

        System.out.println("Accueil Clicked !");

        if(!accueil) {

            this.sp = new ScrollPane(this.vbox);
            this.bigHbox.getChildren().clear();
            this.bigHbox=new HBox(this.buttonsVbox, this.sp, this.connexionButton);


            if(agence.getBiens().isEmpty()) {

                MainPage.CustomErreur("La liste des biens est vide !");

            }
            else {
                Iterator<Biens> it=agence.getBiens().iterator(); Biens b;
                while(it.hasNext())
                {
                    b = it.next();

                    new InfoBiens(b, view, this.vbox);

                }

            }

            fenetreprincipale.setScene(new Scene(bigHbox));

            accueil = true; archive = false; valider = false;
        }
    }

    public void updateButtons() {
        if(admin) {
            this.buttonsVbox.getChildren().removeAll(accueilButton, rechercheGenerale, valideButton, archiveButton, envoyerMessagesButton, afficherMessagesButton, ajouterCritereButton, plusOptionsButton, quitterButton, listeProprietaireButton, ajouterBienButton);

            pane2.setMinHeight(2);
            buttonsVbox.getChildren().add(accueilButton);
            buttonsVbox.getChildren().add(rechercheGenerale);
            buttonsVbox.getChildren().add(ajouterCritereButton);
            buttonsVbox.getChildren().add(valideButton);
            buttonsVbox.getChildren().add(archiveButton);
            buttonsVbox.getChildren().add(listeProprietaireButton);
            buttonsVbox.getChildren().add(plusOptionsButton);
            buttonsVbox.getChildren().add(afficherMessagesButton);
            buttonsVbox.getChildren().add(quitterButton);
            if(firstConnexion)
            {
                ajouterCritereButton.setVisible(true);
                valideButton.setVisible(true);
                archiveButton.setVisible(true);
                listeProprietaireButton.setVisible(true);
                afficherMessagesButton.setVisible(true);
                plusOptionsButton.setVisible(true);
                firstConnexion=false;
            }
            this.connexionButton.setText("Deconnexion");
        }
        else {
            this.buttonsVbox.getChildren().removeAll(ajouterBienButton, accueilButton, rechercheGenerale, valideButton, archiveButton, envoyerMessagesButton, afficherMessagesButton, ajouterCritereButton, quitterButton, plusOptionsButton, listeProprietaireButton);

            this.buttonsVbox.getChildren().add(accueilButton);
            this.buttonsVbox.getChildren().add(rechercheGenerale);
            this.buttonsVbox.getChildren().add(ajouterBienButton);
            this.buttonsVbox.getChildren().add(envoyerMessagesButton);
            this.buttonsVbox.getChildren().add(quitterButton);

            this.connexionButton.setText("Connexion");
        }
    }

    @FXML
    public void rechercher(ActionEvent event) {

        bigHbox.getChildren().clear();

        Pane p = new Pane();

        bigHbox.getChildren().addAll(buttonsVbox, p, connexionButton);

        p.setPrefSize(934, 732);

        accueil = false;
        field1.clear();
        field2.clear();
        image.setFitHeight(732);
        image.setFitWidth(1335);
        image.setLayoutX(0);
        image.setLayoutY(1);
        ColorAdjust colorAdjust = new ColorAdjust(0, 0, -0.78, 0);
        image.setEffect(colorAdjust);


        if(bool2)
        {
            Label labelL2 = new Label("Veuillez remplir les champs de ce critere supplimentaire : ");
            labelL2.setStyle("-fx-text-fill:#ffffff;");
            labelL2.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 40));
            labelL2.setLayoutY(140);
            labelL2.setLayoutX(293);
            p.getChildren().add(labelL2);
        }

        field1.setMinHeight(10);
        field1.setMinWidth(200);
        field1.setLayoutX(360);
        field1.setLayoutY(300);
        field1.setStyle("-fx-background-color: #ffffff;");


        Label labelL = new Label("Recherche d'un bien immobilier ");
        labelL.setStyle("-fx-text-fill:#ffffff;");
        labelL.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 30));
        labelL.setLayoutY(190);
        labelL.setLayoutX(293);
        Label label2 = new Label("Faites entrer des informations lies au bien a rechercher");
        label2.setFont(Font.font("Century Gothic", FontPosture.REGULAR, 12));
        label2.setStyle("-fx-text-fill:#ffffff;");
        label2.setLayoutY(230);
        label2.setLayoutX(350);


        field2.setMinHeight(10);
        field2.setMinWidth(220);
        field2.setLayoutY(325);
        field2.setLayoutX(400);
        field2.setStyle("-fx-background-color: #ffffff;");


        buttonRechercher.setLayoutX(570);
        buttonRechercher.setLayoutY(300);
        buttonRechercher.setMinHeight(20);
        buttonRechercher.setMinWidth(100);
        buttonRechercher.setText("rechercher");
        buttonRechercher.setStyle("-fx-background-color: #ff8811;" + "-fx-text-fill:#353534;");

        buttonRechercher.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 14));

        if (event.getSource() == adresse) {

            i = 1;
            try {
                URL url2 = new File("resources/img/adresseImage.jpg").toURI().toURL();


                image.setImage( new Image(url2.toString() ) );
            }
            catch(Exception e) {

                e.printStackTrace();
            }

            field1.setPromptText("Entrer l'adresse");
            p.getChildren().addAll(image, field1, buttonRechercher, labelL, label2);

        } else {
            if (event.getSource() == wilaya) {

                i = 2;

                try {
                    URL url2 = new File("resources/img/wilaya.jpg").toURI().toURL();


                    image.setImage( new Image(url2.toString() ) );
                }
                catch(Exception e) {

                    e.printStackTrace();
                }
                field1.setPromptText("Entrer le nom de la wilaya");


                for(int i=0; i<48; i++) {
                    cb.getItems().add(i, Wilaya.getNom(i+1));
                }
                cb.setValue("Adrar");
                cb.setMinHeight(10);
                cb.setMinWidth(200);
                cb.setLayoutX(360);
                cb.setLayoutY(300);
                p.getChildren().addAll(image, cb, buttonRechercher, labelL, label2);

            } else {
                if (event.getSource() == superficie) {
                    i = 3;

                    try {
                        URL url2 = new File("resources/img/superficie.jpg").toURI().toURL();


                        image.setImage( new Image(url2.toString() ) );
                    }
                    catch(Exception e) {

                        e.printStackTrace();
                    }

                    field1.setPromptText("Entrer la superficie min ");
                    field2.setPromptText("Entrer la superficie max ");
                    field1.setMinWidth(220);
                    field1.setLayoutY(290);
                    field1.setLayoutX(400);
                    buttonRechercher.setMinWidth(220);
                    buttonRechercher.setLayoutX(400);
                    buttonRechercher.setLayoutY(360);

                    p.getChildren().addAll(image, field1, field2, buttonRechercher, labelL, label2);


                } else {
                    if (event.getSource() == proprietaire) {
                        i = 4;

                        try {
                            URL url2 = new File("resources/img/proprietaire.jpg").toURI().toURL();


                            image.setImage( new Image(url2.toString() ) );
                        }
                        catch(Exception e) {

                            e.printStackTrace();
                        }

                        field1.setPromptText("Entrer le nom du proprietaire ");
                        field2.setPromptText("Entrer le prenom du proprietaire");
                        field1.setMinWidth(220);
                        field1.setLayoutY(290);
                        field1.setLayoutX(400);
                        buttonRechercher.setMinWidth(220);
                        buttonRechercher.setLayoutX(400);
                        buttonRechercher.setLayoutY(360);

                        p.getChildren().addAll(image, field1, field2, buttonRechercher, labelL, label2);

                    } else {
                        if (event.getSource() == prix) {
                            i = 5;

                            try {
                                URL url2 = new File("resources/img/download.jpg").toURI().toURL();


                                image.setImage( new Image(url2.toString() ) );
                            }
                            catch(Exception e) {

                                e.printStackTrace();
                            }
                            field1.setPromptText("Entrer le prix min ");
                            field1.setMinWidth(220);
                            field1.setLayoutY(290);
                            field1.setLayoutX(400);
                            field2.setPromptText("Entrer le prix max ");
                            buttonRechercher.setMinWidth(220);
                            buttonRechercher.setLayoutX(400);
                            buttonRechercher.setLayoutY(360);

                            p.getChildren().addAll(image, field1, field2, buttonRechercher, labelL, label2);

                        } else {

                            if (event.getSource() == date) {
                                i = 7;
                                try {
                                    URL url2 = new File("resources/img/date.jpg").toURI().toURL();


                                    image.setImage( new Image(url2.toString() ) );
                                }
                                catch(Exception e) {

                                    e.printStackTrace();
                                }
                                datePicker.setLayoutX(360);
                                datePicker.setLayoutY(300);
                                datePicker.setMinHeight(10);
                                datePicker.setMinWidth(170);
                                p.getChildren().addAll(image, datePicker, buttonRechercher, labelL, label2);
                            } else {
                                if (event.getSource() == typeTrans) {
                                    i = 6;

                                    try {
                                        URL url2 = new File("resources/img/typetrans.jpg").toURI().toURL();


                                        image.setImage( new Image(url2.toString() ) );
                                    }
                                    catch(Exception e) {

                                        e.printStackTrace();
                                    }
                                    MenuButton menu = new MenuButton("Choisissez le type de transaction");
                                    menu.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 14));
                                    item1.setText("Vente                         ");
                                    item2.setText("Location                                          ");
                                    item3.setText("Echange");
                                    menu.setLayoutX(400);
                                    menu.setLayoutY(300);
                                    menu.setMinHeight(10);
                                    menu.setMinWidth(225);
                                    menu.setStyle("-fx-background-color: #ff8811");
                                    menu.getItems().addAll(item1, item2, item3);
                                    p.getChildren().addAll(image, menu, labelL, label2);

                                } else {

                                    if (event.getSource() == typeBien) {
                                        i = 8;
                                        p.getChildren().clear();

                                        p.setStyle("-fx-background-color: #ffe7a5;");

                                        MenuButton menu = new MenuButton("Choisissez le type du bien");
                                        menu.setFont(Font.font("Century Gothic", FontWeight.BOLD, FontPosture.REGULAR, 14));
                                        item1.setText("Appartement                                   ");
                                        item2.setText("Maison");
                                        item3.setText("Terrain");

                                        try {
                                            URL url2 = new File("resources/img/typeBien.jpg").toURI().toURL();


                                            image.setImage( new Image(url2.toString() ) );
                                        }
                                        catch(Exception e) {

                                            e.printStackTrace();
                                        }

                                        menu.setLayoutX(400);
                                        menu.setLayoutY(300);
                                        menu.setMinHeight(10);
                                        menu.setMinWidth(225);
                                        menu.setStyle("-fx-background-color: #ff8811");
                                        menu.getItems().addAll(item1, item2, item3);
                                        p.getChildren().addAll(image, menu, labelL, label2);
                                    } else {
                                        i = 9;
                                        try {
                                            URL url2 = new File("resources/img/nbPieces.jpg").toURI().toURL();


                                            image.setImage( new Image(url2.toString() ) );
                                        }
                                        catch(Exception e) {

                                            e.printStackTrace();
                                        }
                                        field1.setPromptText("Entrer le nombre de pieces");
                                        p.getChildren().addAll(image, field1, buttonRechercher, labelL, label2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ensembleBiens);

        item1.setOnAction(new RechercherController(this, i, ensembleBiens));
        item2.setOnAction(new RechercherController(this, i, ensembleBiens));
        item3.setOnAction(new RechercherController(this, i, ensembleBiens));
        buttonRechercher.setOnAction(new RechercherController(this, i, ensembleBiens));
    }

    public String getComboBox() {
        return cb.getValue();
    }

    @FXML
    public void ajouterUnCritere() {

        bigHbox.getChildren().clear();

        Pane p = new Pane();

        bigHbox.getChildren().addAll(buttonsVbox, p, connexionButton);

        p.setPrefSize(934, 732);


        accueil=false;


        image.setFitHeight(732);
        image.setFitWidth(1335);
        image.setLayoutX(0);
        image.setLayoutY(1);
        try {
            URL url2 = new File("resources/img/aa.jpg").toURI().toURL();


            image.setImage( new Image(url2.toString() ) );
        }
        catch(Exception e) {

            e.printStackTrace();
        }
        ColorAdjust colorAdjust = new ColorAdjust(0, 0, -0.78, 0);
        image.setEffect(colorAdjust);

        Label label1 = new Label("Choisissez les criteres à ajouter: ");
        label1.setFont(Font.font("Century Gothic", FontPosture.REGULAR, 25));
        label1.setLayoutX(220);
        label1.setLayoutY(60);
        label1.setStyle("-fx-text-fill:#ffffff;" + "-fx-font-weight:bold");
        p.getChildren().addAll(image, label1);

        int i;
        double y = 120;

        for (i = 0; i <= 8; i++) {
            y += 40;

            label1 = new Label(tab[i]);
            label1.setLayoutY(y);
            label1.setLayoutX(370);
            label1.setFont(Font.font("Century Gothic", FontPosture.REGULAR, 17));
            label1.setStyle("-fx-text-fill:#ffffff;");

            if (!bool) {
                checkBoxes[i] = new CheckBox();
                checkBoxes[i].setLayoutY(y);
                checkBoxes[i].setLayoutX(330);
                if(i==1 || i==2  || i==4 || i==5 || i==6 ) checkBoxes[i].setSelected(true);

            }
            checkBoxes[i].setOnAction(new GestionDesCriteresController(i, this, checkBoxes[i]));
            p.getChildren().addAll(checkBoxes[i], label1);
        }
        if (!bool) bool = true;

    }


    public void ajouterClicked() {
        System.out.println("Ajouter Clicked !");



        AjoutBien stage = new AjoutBien(vbox);



        stage.show();

        accueil = false; valider = false; archive = false;
    }

    public void optionsClicked() {

        System.out.println("options Clicked !");
        if(!accueil) {
            accueilClicked();
        }



        accueil = false; valider = false; archive = false;


        Iterator<Node> it = vbox.getChildren().iterator();

        Node current; int i=1;

        while(it.hasNext()) {
            current = it.next();
            Button modif=new Button("Modifier");
            modif.setId(((Integer)i).toString());
            modif.getStyleClass().add("buttons");
            modif.setOnAction(new EventHandler<>(){

                public void handle(ActionEvent actionEvent) {

                    vbox.getChildren().clear();
                    new ModifBien(agence, modif.getId(), vbox);
                    accueil=false;


                }
            });

            Button archiv=new Button("Archiver");
            archiv.setId(((Integer)i).toString());
            archiv.getStyleClass().add("buttons");
            archiv.setOnAction(new EventHandler<>(){

                public void handle(ActionEvent actionEvent) {

                    vbox.getChildren().clear();
                    agence.archiverBiens(agence.getBien(Integer.parseInt(archiv.getId())));
                    accueil=false;
                    accueilClicked();
                    optionsClicked();
                }
            });

            Button supprim=new Button("Supprimer");
            supprim.setPrefWidth(100);
            supprim.setId(((Integer)i).toString());
            supprim.getStyleClass().add("buttons");
            supprim.setOnAction(new EventHandler<>(){

                public void handle(ActionEvent actionEvent) {

                    vbox.getChildren().clear();
                    agence.supprimerBiens(agence.getBien(Integer.parseInt(supprim.getId())));
                    accueil=false;
                    accueilClicked();
                    optionsClicked();
                }
            });

            VBox buttons = new VBox(modif, archiv, supprim);

            buttons.setPrefSize(120, 50);


            ((HBox)current).getChildren().add(buttons);

            ((HBox)current).setSpacing(20);


            i++;
        }
    }



    public void archiveClicked() {
        System.out.println("archive Clicked !");


        if(!archive) {


            vbox.getChildren().clear();

            if(agence.getArchives().isEmpty()) MainPage.CustomErreur("La liste des archives est vide !");
            else {
                Iterator<Biens> it=agence.getArchives().iterator(); Biens b; int i=1;

                while(it.hasNext())
                {
                    b = it.next();

                    InfoBiens stage = new InfoBiens(b, view, vbox);

                    Button desarchiv=new Button("Desarchiver");
                    desarchiv.setId(((Integer)i).toString());
                    desarchiv.getStyleClass().add("buttons");
                    desarchiv.setOnAction(new EventHandler<>(){

                        public void handle(ActionEvent actionEvent) {

                            vbox.getChildren().clear();
                            agence.desarchiverBiens(agence.getBienArchive(Integer.parseInt(desarchiv.getId())));
                            archive=false;
                            archiveClicked();

                        }
                    });

                    Button supprim=new Button("Supprimer");
                    supprim.setId(((Integer)i).toString());
                    supprim.getStyleClass().add("buttons");
                    supprim.setOnAction(new EventHandler<>(){

                        public void handle(ActionEvent actionEvent) {

                            vbox.getChildren().clear();
                            agence.supprimerBiens(agence.getBienArchive(Integer.parseInt(supprim.getId())));
                            archive=false;
                            archiveClicked();
                        }
                    });

                    VBox buttons = new VBox(desarchiv, supprim);


                    stage.getResultHbox().getChildren().add(buttons);


                    i++;
                }
            }

            accueil = false; archive = true; valider = false;
        }



    }




    public void validerClicked() {

        System.out.println("Valider clicked !");


        accueil = false;
        archive = false;

        if(!valider) {


            vbox.getChildren().clear();

            if(agence.getBiensAValider().isEmpty()) MainPage.CustomErreur("La liste des biens � valider est vide !");
            else {

                Iterator<Biens> it=agence.getBiensAValider().iterator(); Biens b; int i=1;

                while(it.hasNext())
                {
                    b = it.next();

                    InfoBiens stage = new InfoBiens(b, view, vbox);

                    Button valid=new Button("Valider");
                    valid.setId(((Integer)i).toString());
                    valid.getStyleClass().add("buttons");
                    valid.setOnAction(new EventHandler<>(){

                        public void handle(ActionEvent actionEvent) {

                            vbox.getChildren().clear();
                            agence.valideBien(agence.getBienAValider(Integer.parseInt(valid.getId())));
                            valider=false;
                            validerClicked();

                        }
                    });

                    Button supprim=new Button("Supprimer");
                    supprim.setId(((Integer)i).toString());
                    supprim.getStyleClass().add("buttons");
                    supprim.setOnAction(new EventHandler<>(){

                        public void handle(ActionEvent actionEvent) {

                            vbox.getChildren().clear();
                            agence.getBiensAValider().remove(agence.getBienAValider(Integer.parseInt(supprim.getId())));
                            valider=false;
                            validerClicked();
                        }
                    });

                    VBox buttons = new VBox(valid, supprim);


                    stage.getResultHbox().getChildren().add(buttons);


                    i++;
                }
            }

            accueil = false; archive = false;
        }




    }


    public void propClicked() {

        System.out.println("Prop clicked !");

        accueil = false;

        Iterator<Proprietaire> it= agence.getProprietaires().iterator(); Proprietaire p;
        vbox.getChildren().clear();

        (vbox).getChildren().clear(); int i=0;

        while(it.hasNext()) {

            p = it.next();

            InfoProprietaire stage = new InfoProprietaire(p, view, vbox);

            Button affichebiens=new Button("Afficher ses biens");
            affichebiens.setId(((Integer)i).toString());
            affichebiens.getStyleClass().add("buttons");
            affichebiens.setOnAction(new EventHandler<>(){

                public void handle(ActionEvent actionEvent) {

                    VBox vb = new VBox();

                    ScrollPane sP = new ScrollPane(vb);


                    Scene fenetre = new Scene(sP);



                    Iterator<Biens> it= agence.getProprietaires().get(Integer.parseInt(affichebiens.getId())).getPossesions().iterator();
                    Biens b;


                    while(it.hasNext()) {
                        b = it.next();

                        InfoBiens stage = new InfoBiens(b, fenetre, vb);
                        stage.setScene(fenetre);

                        if(!it.hasNext()) stage.show();

                    }



                    //valider=false;



                }
            });

            stage.getResultHBx().getChildren().add(affichebiens);


            i++;

        }



    }


    public void connexionClicked() {


        //ExpansionPanel ep= new ExpansionPanel();
        if (admin)
        {

            DeconnexionWindow fenetre =new DeconnexionWindow(this);
            fenetre.show();


        }
        else {

            ConnexionAdmins fenetre = new ConnexionAdmins(this);
            fenetre.show();
            System.out.println("Connexion !");

        }
    }



    public void envoyerMessageClicked() {

        if(!accueil) {
            accueilClicked();
        }



        accueil = false; valider = false; archive = false;

        Iterator<Node> it = vbox.getChildren().iterator();
        Node current; int i=1;

        while(it.hasNext()) {

            current = it.next();
            Button envoi=new Button("Envoyer un message");
            envoi.autosize();
            envoi.setId(((Integer)i).toString());
            envoi.getStyleClass().add("buttons");
            envoi.setOnAction(new EventHandler<>(){

                public void handle(ActionEvent actionEvent) {

                    int idBien = Integer.parseInt(envoi.getId());

                    new EnvoiMessage(agence.getBien(idBien).getMessages());

                    accueil=false;

                }
            });

            ((HBox)current).getChildren().add(envoi);

            i++;

        }


    }


    public void afficheMessagesClicked() {

        if (!accueil) {
            accueilClicked();
        }
        accueil = false;
        valider = false;
        archive = false;

        Iterator<Node> it = vbox.getChildren().iterator();
        Node current;
        int i = 1;

        while (it.hasNext()) {

            current = it.next();
            Button envoi = new Button("Afficher les messages");
            envoi.setId(((Integer) i).toString());
            envoi.getStyleClass().add("buttons");
            envoi.setOnAction(new EventHandler<>() {

                public void handle(ActionEvent actionEvent) {

                    int idBien = Integer.parseInt(envoi.getId());
                    new AfficheMessages(agence.getBien(idBien).getMessages());

                    accueil = false;

                }
            });

            ((HBox) current).getChildren().add(envoi);

            i++;

        }
    }


    public void quitterClicked() throws Exception {

        ObjectOutputStream out;
        //écriture les objets dans un fichier
        out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("resources/Sauvegarde.txt")));

        out.writeObject(agence); /*Sauvegarde de l'agence*/
        out.close();

        Platform.exit();

    }


    public String getField1()
    {
        return field1.getText();
    }

    public String getField2()
    {
        return field2.getText();
    }

    public String getDatePicker()
    {
        return datePicker.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public void setView(Scene view) {
        this.view = view;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setBool2(boolean bool2) {
        this.bool2 = bool2;
    }

}

