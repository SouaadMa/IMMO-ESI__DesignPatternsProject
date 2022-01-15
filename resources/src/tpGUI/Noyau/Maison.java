package tpGUI.Noyau;

import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import tpGUI.UI.CreationMessage;

public class Maison extends Habitable{
	
	private int nbEtages;
	private boolean garage;
	private boolean piscine;
	private boolean jardin;
	private double superficiehabitable;

	public Maison() {}
	
	public Maison(String adresse, int wilaya, double superficie, Proprietaire coordonnees, double prix,
			boolean negociable, TypeTrans trans, String descriptif, String date, String photoURL,
			int nbPieces, boolean meuble, int nbEtages, boolean garage, boolean piscine,
			boolean jardin, double suph) {
		
		super(adresse, wilaya, superficie, coordonnees, prix, negociable, trans, descriptif, date, photoURL, nbPieces, meuble);
		this.nbEtages = nbEtages;
		this.garage = garage;
		this.piscine = piscine;
		this.jardin = jardin;
		superficiehabitable=suph;
	}
	
	public void afficher() {
		System.out.print("Une maison: ");
		super.afficher();
		System.out.println();

	}
	
	public String visualiser() {
		
		String s=super.visualiser();
		s=s+"\n13* Nbr d'étages: "+nbEtages+" ";
		if(garage) s=s+"\n14* Contient un garage";
		if(piscine) s=s+"\n15* Contient une piscine";
		if(jardin) s=s+"\n16* Contient un jardin";
		s=s+"\n17* Superficie habitable: "+superficiehabitable+"";
		return s;
	}

	public ArrayList<Text> visualiserInfos() {

		ArrayList<Text> infos = super.visualiserInfos();

		infos.add(CreationMessage.creerMessage(this, 9));
		infos.add(CreationMessage.creerMessage(this, 11));
		infos.add(CreationMessage.creerMessage(this, 15));


		return infos;
	}

	public ArrayList<Text> visualiserInfosDetails() {

		ArrayList<Text> infos = super.visualiserInfosDetails();

		if((Boolean)recupererChamps(10)) infos.add(CreationMessage.creerMessage(this, 10));
		if((Boolean)recupererChamps(12)) infos.add(CreationMessage.creerMessage(this, 12));
		if((Boolean)recupererChamps(13)) infos.add(CreationMessage.creerMessage(this, 13));
		if((Boolean)recupererChamps(14)) infos.add(CreationMessage.creerMessage(this, 14));


		return infos;
	}
	
	public double calculerPrix() {
		
		double prixbien= super.calculerPrix();
		if(trans==TypeTrans.VENTE || trans==TypeTrans.ECHANGE) { 
			if(garage || piscine || jardin) {
				prixbien += 0.005*prix;
			}
		}
		if(trans==TypeTrans.LOCATION) {
			if(piscine) prixbien+=50000;
		}
		return prixbien;
	}
	
	public Object recupererChamps(int i)
	{
		Object valeur ;
		if(i==8) valeur="Maison";
		else
		{ 
			if(i==9) valeur=nbPieces;
			else
			{
				if(i==10) valeur = meuble;
				else if(i==11) valeur = nbEtages;
				else if(i==12) valeur = garage;
				else if(i==13) valeur = piscine;
				else if(i==14) valeur = jardin;
				else if(i==15) valeur = superficiehabitable;
				else valeur =super.recupererChamps(i);
			}
		}
		return valeur;
	}
	
	public String generateTitle(int i) {
    	switch(i) {
    	case 11:
    		return "Nombre d'etages:";
    	case 12:
    		return "Dispose d'un garage";
    	case 13:
    		return "Dispose d'une Piscine";
    	case 14:
    		return "Dispose d'un jardin";
    	case 15:
    		return "Superficie Habitable";
    	default:
    		return super.generateTitle(i);
    	
    	
    	}
    }

	public void setNbEtages(int nbEtages) {
		this.nbEtages = nbEtages;
	}
	public void setGaragePiscineJardin(boolean g, boolean p, boolean j) {
		this.garage = g;
		this.piscine = p;
		this.jardin = j;
	}
	public void setSuperficiehabitable(Double superficiehabitable) {
		this.superficiehabitable = superficiehabitable;
	}

	
	
}
