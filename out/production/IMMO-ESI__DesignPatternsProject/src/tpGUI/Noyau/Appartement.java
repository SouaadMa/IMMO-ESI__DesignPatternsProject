package tpGUI.Noyau;


import javafx.scene.text.Text;
import tpGUI.UI.CreationMessage;

import java.util.ArrayList;

public class Appartement extends Habitable
{
	private int etage ;
	private Xplexe type;
	private boolean ascenseur;

	
	public Appartement(String adresse , int wilaya , double superficie , Proprietaire coordonnees , 
			double prix , boolean negociable ,TypeTrans trans , String descriptif , String date , String photoURL ,
			int nbPieces , boolean meuble ,int etage , Xplexe type , boolean ascenseur){
		
		super(adresse,wilaya,superficie,coordonnees,prix,negociable,trans,descriptif,date,photoURL,nbPieces,meuble);
    	this.etage=etage;
    	this.type=type;
    	this.ascenseur=ascenseur;
    }
    
	
	public String visualiser()
    {
    	String s=super.visualiser();
        s=s+"13* L'etage : "+etage+"\n";  
        if( type==Xplexe.SIMPLEXE) s=s+"14* Il s'agit d'un simplexe.\n"; else s=s+"14* Il s'agit d'un duplexe.\n";
        s=s+"15* Ascenseur? "+ascenseur+"\n";
        return s;
    }
	
	
    public void afficher ()
    {
    	System.out.print("Un appartement : ");   	
    	super.afficher();
    	System.out.println();

    }

	public ArrayList<Text> visualiserInfos() {

		ArrayList<Text> infos = super.visualiserInfos();

		infos.add(CreationMessage.creerMessage(this, 9));
		infos.add(CreationMessage.creerMessage(this, 11));
		infos.add(CreationMessage.creerMessage(this, 12));


		return infos;
	}

	public ArrayList<Text> visualiserInfosDetails() {

		ArrayList<Text> infos = super.visualiserInfosDetails();

		infos.add(CreationMessage.creerMessage(this, 10));
		infos.add(CreationMessage.creerMessage(this, 13));

		return infos;
	}
    
    public double calculerPrix()
    {
    	double prix;
    	
    	prix=super.calculerPrix();
    	
    	if(trans==TypeTrans.VENTE || trans==TypeTrans.ECHANGE )
    	{
   			if(etage<=2) prix+=50000.0;
   			
    	
    	}
    	else
    	{
    	
    		if(etage<=2) prix+=5000.0;
    		
    		if(!ascenseur && etage>=6) prix-=500*superficie; 	
    		
    	}
    	
    	return prix;
    	
    
    }
    
    public Object recupererChamps(int i)
	{
		Object valeur ;
		if(i==8) valeur="Appartement";
		else
		{ 
			if(i==9) valeur=nbPieces;
			else
			{
				if(i==10) valeur = meuble;
				else if(i==11) valeur = etage;
				else if(i==12) valeur = type;
				else if(i==13) valeur = ascenseur;
				else valeur =super.recupererChamps(i);
			}
		}
		return valeur;
	
	}
    
    public String generateTitle(int i) {
    	switch(i) {
    	case 11:
    		return "Etage:";
    	case 12:
    		return "Type:";
    	case 13:
    		return "Ascenseur";
    	default:
    		return super.generateTitle(i);
    	
    	
    	}
    }
    
}
