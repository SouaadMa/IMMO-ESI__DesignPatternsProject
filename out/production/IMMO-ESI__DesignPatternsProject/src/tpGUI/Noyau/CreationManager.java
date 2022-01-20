package tpGUI.Noyau;

import java.io.*;

public class CreationManager {


    public static void deserializeAgence() {

        Agence ImmoESI = null;
        ObjectInputStream in ;
/*
        try {

            File sauvtextfile = new File("resources/Sauvegarde.txt");

            in = new ObjectInputStream( new BufferedInputStream( new FileInputStream( sauvtextfile )));

            ImmoESI = (Agence)in.readObject();

            in.close();

            System.out.println("After serialize");

        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch
        (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/

        if(ImmoESI == null) {

            ImmoESI = Agence.getInstance();

                     Proprietaire Prop1 = new Proprietaire ("Benelhadj" , "Saadia" , "@esi.dz" , "Cité 05 Juillet" , "05" ) ;
                     ImmoESI.ajouterProprietaire(Prop1);
                     Proprietaire Prop2 = new Proprietaire ("Hamlaoui" , "Yasmine" , "@esi.dz" , "1er Novembre" , "06") ;
                     ImmoESI.ajouterProprietaire(Prop2);
                     Proprietaire Prop3 = new Proprietaire ("Mecheri" , "Hadia" , "@esi.dz" , "Ma lebyed" , "05" ) ;
                     ImmoESI.ajouterProprietaire(Prop3);
                     Proprietaire Prop4 = new Proprietaire ("Rouabhia" , "Anfel" , "@esi.dz" , "Tafchoun" , "07") ;
                     ImmoESI.ajouterProprietaire(Prop4);

                     Biens Bien1 = new Appartement ("adr 1", 2, 120, Prop2, 4000000,
                     false, TypeTrans.VENTE, "descrip 1", "31/12/2019",
                     "resources/img/appa1.jpg",
                     4, false, 1, Xplexe.DUPLEXE, false);
                     ImmoESI.insereBien(Bien1);
                     ImmoESI.valideBien(Bien1);

                     Biens Bien2 = new Maison ("adr 2", 3, 200, Prop1, 10000000,
                     false, TypeTrans.VENTE, "descrip 2", "02/12/2019",
                     "resources/img/mais1.jpg",
                     2, false, 1, false, false, true, 120);
                     ImmoESI.insereBien(Bien2);
                     ImmoESI.valideBien(Bien2);

                     Biens Bien3 = new NonHabitable("adr 3", 1, 500, Prop1, 20000000,
                     false, TypeTrans.VENTE, "descrip 3", "10/11/2019",
                     "resources/img/terrain1.jpg",
                     "statut juridique 3", 3);
                     ImmoESI.insereBien(Bien3);
                     ImmoESI.valideBien(Bien3);

                     Biens Bien4 = new Appartement ("adr 4", 3, 100, Prop2, 40000,
                     false, TypeTrans.LOCATION, "descrip 4", "01/11/2019",
                     "resources/img/appa2.jpg",
                     3, false, 1, Xplexe.DUPLEXE, false);
                     ImmoESI.insereBien(Bien4);
                     ImmoESI.valideBien(Bien4);

                     Biens Bien5 = new Maison ("adr 5", 2, 160, Prop3, 150000,
                     false, TypeTrans.LOCATION, "descrip 5", "01/09/2019",
                     "resources/img/mais2.jpg",
                     3, true, 1, false, true, false, 120);
                     ImmoESI.insereBien(Bien5);
                     ImmoESI.valideBien(Bien5);

                     Biens Bien6 = new Appartement ("adr 6", 3, 50, Prop2, 600000,
                     false, TypeTrans.LOCATION, "descrip 6", "02/08/2019",
                     "resources/img/appa3.jpg",
                     1, true, 6, Xplexe.SIMPLEXE, false);
                     ImmoESI.insereBien(Bien6);
                     ImmoESI.valideBien(Bien6);

                     Biens Bien7 = new NonHabitable ("adr 7", 1, 650, Prop1, 18000000,
                     false, TypeTrans.ECHANGE, "descrip 7", "13/07/2019",
                     "resources/img/terrain2.jpg",
                     "Statut Juridique 7", 1);
                     ImmoESI.insereBien(Bien7);
                     ImmoESI.valideBien(Bien7);

                     Biens Bien8 = new Maison ("adr 8", 2, 200, Prop2, 14000000,
                     true, TypeTrans.ECHANGE, "descrip 8", "12/12/2018",
                     "resources/img/mais3.jpg",
                     3, true, 1, true, false, false, 120);
                     ImmoESI.insereBien(Bien8);
                     ImmoESI.valideBien(Bien8);



        }

        //Agence.setInstance(ImmoESI);
    }

    public static Proprietaire createProprietaire(String nom , String prenom , String adresseMail , String adresse , String numTelephone) {

        Proprietaire p = Agence.getInstance().getProprietaire(nom+" "+prenom);

        if(p==null) {
            System.out.println("NEW PROPRRRR");
            p = new Proprietaire(nom, prenom, adresseMail, adresse, numTelephone);
        }

        return p;
    }

    public static Biens createMaison(String adresse, int wilaya, double superficie, Proprietaire coordonnees, double prix,
                                     boolean negociable, TypeTrans trans, String descriptif, String date, String photoURL,
                                     int nbPieces, boolean meuble, int nbEtages, boolean garage, boolean piscine,
                                     boolean jardin, double suph, boolean insertion)
            throws SuperficieHabitableTresGrandeException
    {

        if(suph>superficie) throw new SuperficieHabitableTresGrandeException();

        Biens b = new Maison(adresse, wilaya, superficie, coordonnees, prix,
                negociable, trans, descriptif, date, photoURL,
                nbPieces, meuble, nbEtages, garage, piscine,
                jardin, suph);

        if(insertion) Agence.getInstance().insereBien(b);
        return b;
    }

    public static Biens createAppartement(String adresse , int wilaya , double superficie , Proprietaire coordonnees ,
                                          double prix , boolean negociable ,TypeTrans trans , String descriptif , String date , String photoURL ,
                                          int nbPieces , boolean meuble ,int etage , Xplexe type , boolean ascenseur, boolean insertion)
            throws WrongSurfaceException, WrongPriceException, WrongChoiceException
    {

        if(descriptif.isEmpty()) descriptif = "Pas de description";

        if(superficie<=10) throw new WrongSurfaceException();
        if(prix<=10) throw new WrongPriceException();
        if(( nbPieces==1 && type!=Xplexe.SIMPLEXE) || (nbPieces>1 && type!=Xplexe.DUPLEXE )) throw new WrongChoiceException();

        Biens b = new Appartement(adresse, wilaya, superficie, coordonnees, prix,
                negociable, trans, descriptif, date, photoURL,
                nbPieces, meuble, etage, type, ascenseur);

        if(insertion) Agence.getInstance().insereBien(b);
        return b;
    }

    public static Biens createTerrain(String adresse , int wilaya , double superficie , Proprietaire coordonnees ,
                                      double prix , boolean negociable , TypeTrans trans , String descriptif ,
                                      String date , String photoURL ,String statutJurdique , int nbFacades, boolean insertion )
            throws WrongSurfaceException, WrongPriceException
    {


        if(descriptif.isEmpty()) descriptif= "Pas de description";

        if(superficie<=10) throw new WrongSurfaceException();
        if(prix<=10) throw new WrongPriceException();


        Biens b = new NonHabitable(adresse, wilaya, superficie, coordonnees, prix,
                negociable, trans, descriptif, date, photoURL,
                statutJurdique, nbFacades);

        if(insertion) Agence.getInstance().insereBien(b);
        return b;
    }


}
