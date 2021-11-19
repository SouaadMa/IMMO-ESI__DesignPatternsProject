package tpGUI.Control;

import javafx.event.ActionEvent;
import javafx.event.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import tpGUI.Noyau.*;
import tpGUI.UI.*;

import java.util.*;


public class RechercherController   implements  EventHandler<ActionEvent> {

    private Controller principalController;

    private int i;
    private Set<Biens> ensembleBiens;
    private Object valeur1 = null, valeur2 = null;

    //private String[] criteres={ "adresse","wilaya souhaitee","superficie" , " coordonnees d'un proprietaire","prix" ,"type de transaction"  ,"date", "le type du bien" , "nombre maximal des pi�ces" };
    //private static int nbCriteres=9;

    private int[] numCriteres={1,2,3,4,5,6,7,8,9};

    public RechercherController(Controller view, int i, Set<Biens> ensembleBiens) {
        this.i = i;
        this.principalController = view;
        this.ensembleBiens = ensembleBiens;

    }

    private Set<Biens> remplirEnsembleBiens(int i , Set<Biens> ensembleBiens, Object valeur1 , Object valeur2) {

        Biens bien;

        //System.out.println(ensembleBiens);

        Iterator<Biens> it=ensembleBiens.iterator();

        Set<Biens> temp=new TreeSet<Biens>();

        while(it.hasNext())
        {
            bien=it.next();

            Object valeur=bien.recupererChamps(i);


            switch(i)
            {

                case 1:
                    if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
                    break;
                case 2:
                    int numWilaya= Wilaya.getNumWilaya((String) valeur1);
                    if(((Integer)numWilaya).equals((int)valeur)) temp.add(bien);
                    break;
                case 3:
                    double superficieMin=Double.parseDouble((String)valeur1);
                    double superficieMax=Double.parseDouble((String)valeur2);

                    if(((double)valeur)>=(superficieMin) && ((double)valeur)<=(superficieMax)) temp.add(bien);
                    break;
                case 4:
                    if(((String)valeur1).equalsIgnoreCase(((Proprietaire)valeur).getNom())&& ((String)valeur2).equalsIgnoreCase(((Proprietaire)valeur).getPrenom())) temp.add(bien);
                    break;
                case 5:
                    double prixMin=Double.parseDouble((String)valeur1);
                    double prixMax=Double.parseDouble((String)valeur2);
                    if(((double)valeur)>=prixMin && ((double)valeur)<=prixMax )temp.add(bien);
                    break;
                case 6:
                    if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
                    break;
                case 7:
                    if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
                    break;
                case 8:
                    if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
                    break;
                case 9:
                    String typeBien=((String)bien.recupererChamps(8));
                    if(!(typeBien.equalsIgnoreCase("Terrain")))
                    {int nbPieces=Integer.parseInt((String) valeur1);
                        if((nbPieces)>=(int)valeur) temp.add(bien);}
                    break;
            }


        }
        return temp;


    }


    public   Set<Biens>  filtrer(int i, Set<Biens> ensembleBiens , Object valeur1 ,Object  valeur2) throws Exception
    {

        i=numCriteres[i-1];

        return remplirEnsembleBiens(i,ensembleBiens,valeur1,valeur2);

    }



    @Override
    public void handle(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        try {
            if (i == 6) {
                if (event.getSource() == principalController.item1) valeur1 = "Vente";
                else {
                    if (event.getSource() == principalController.item2) valeur1 = "Location";
                    else valeur1 = "Echange";
                }
            } else {
                if (i == 8) {
                    if (event.getSource() == principalController.item1) valeur1 = "Appartement";
                    else {
                        if (event.getSource() == principalController.item2) valeur1 = "Maison";
                        else valeur1 = "Terrain";
                    }

                } else {
                	
                	System.out.println(i);
                	if(i==2) valeur1 = principalController.getComboBox(); else {
                    if (principalController.field1.getText().isEmpty() && principalController.field2.getText().isEmpty() && principalController.datePicker.getValue()==null) throw new TextFieldEmptyException();
                    if (i == 7) valeur1 = principalController.getDatePicker();
                    else valeur1 = principalController.getField1();
                    valeur2 = principalController.getField2();
                	}
                 
                }
            }

           
            ensembleBiens = filtrer(this.i, ensembleBiens, valeur1, valeur2);

            
            if (this.ensembleBiens.isEmpty()) throw new ElementNonExistantException();
            AjouterCritereOuAfficher fenetre2 = new AjouterCritereOuAfficher(this, ensembleBiens);
            fenetre2.show();
        }
        

         catch (ElementNonExistantException e)
        {
          principalController.accueilClicked();
          AucunBienNeVerifieLesConditions fenetreError = new AucunBienNeVerifieLesConditions();
          fenetreError.show();
          principalController.accueilButton.fire();
        }
        

        catch(TextFieldEmptyException e) {
            alert.setTitle("Champs d'ecriture vide");
            alert.setHeaderText(null);
            alert.setContentText(" Aucune information sur le bien n'a été entrée!");
            alert.setHeight(100);
            alert.setWidth(100);
            alert.showAndWait();
        }
        catch (Exception f) {
			MainPage.CustomErreur("Votre entree a généré une erreur.");
		}
    }

    public void fireNextSearchEvent(String source) {

        if(source.equals("adresse")) principalController.adresse.fire();
        else
        {
            if(source.equals("wilaya")) principalController.wilaya.fire();
            else
            {
                if(source.equals("superficie")) principalController.superficie.fire();
                else
                {
                    if(source.equals("proprietaire")) principalController.proprietaire.fire();
                    else
                    {
                        if(source.equals("prix")) principalController.prix.fire();
                        else
                        {
                            if(source.equals("date")) principalController.date.fire();
                            else
                            {
                                if(source.equals("typeTrans")) principalController.typeTrans.fire();
                                else
                                {
                                    if(source.equals("typeBien")) principalController.typeBien.fire();
                                    else principalController.nbPieces.fire();

                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void quitAndReturn() {
        principalController.accueilClicked();
    }

    public void setEnsembleBiens(Set<Biens> ensemble) {
        principalController.setEnsembleBiens(ensemble);
    }

    public void setBool2(Boolean b) {
        principalController.setBool2(b);
    }

    public Set<Biens> getBiens()
    {
        return ensembleBiens;
    }
}
