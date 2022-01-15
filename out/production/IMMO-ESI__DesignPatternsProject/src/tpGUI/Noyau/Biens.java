
package tpGUI.Noyau;


import javafx.scene.text.Text;
import tpGUI.UI.CreationMessage;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public abstract class Biens implements Comparable<Biens>, Serializable {
	
	
	protected String adresse;
	protected int wilaya;
	protected double superficie;
	protected Proprietaire coordonnees;
	protected double prix;
	protected boolean negociable;
	protected TypeTrans trans;
	protected String descriptif;
	protected String date;
	protected String photoURL;
	protected boolean archive;
	protected boolean suppr;
	protected ArrayList<String> tabMessages = new ArrayList<String>();
	protected int nbMsgs=0;

	public Biens() {}

	public Biens(String adresse, int wilaya, double superficie, Proprietaire coordonnees, double prix,
			boolean negociable, TypeTrans trans, String descriptif, String date, String photoURL)
	{
		this.adresse = adresse;
		this.wilaya = wilaya;
		this.superficie = superficie;
		this.prix = prix;
		this.negociable = negociable;
		this.trans = trans;
		this.descriptif = descriptif;
		this.date = date;
		this.photoURL = photoURL;
		this.coordonnees = coordonnees; //if(coordonnees!=null) coordonnees.ajouterBiens(this);
	}

	public void afficher() {
		System.out.print("Adresse: "+adresse+" Wilaya: "+Wilaya.getNom(wilaya)+" Date: " +date);	
	}
	
	public String visualiser() {
		System.out.println();
		String s;
		if(this.getClass().getSimpleName().equals("NonHabitable")) s ="\nVisualise Terrain" ;
		else s="\nVisualise "+this.getClass().getSimpleName();
		
			s=s+":\n1* L'adresse: "+adresse+"\n2* La wilaya: "+Wilaya.getNom(wilaya)+"\n3* La superficie: "+superficie+"\n4* Le proprietaire: "+coordonnees.getNomPrenom()+"\n5* Le prix: "+prix+"\n6* Negociable? "+negociable+"\n7* La transaction: "+trans.toString()+"\n8* La description: "+descriptif+"\n9* La date: "+ date+"\n10* L'URL de la photo du bien: "+photoURL+"";
			return s;
	}

	public ArrayList<Text> visualiserInfos() {
		ArrayList<Text> infos = new ArrayList<>();
		infos.add(CreationMessage.creerMessage(this,22));
		return infos;
	}

	public ArrayList<Text> visualiserInfosDetails() {
		ArrayList<Text> infos = new ArrayList<>();
		return infos;
	}
	
	public double calculerPrix() {
		
		double prixbien=prix;

		if(trans==TypeTrans.VENTE) {
			prixbien = PriceManager.ajouterFraisVente(prixbien, Wilaya.getPrix(wilaya));
		}
		if(trans==TypeTrans.ECHANGE) {
			prixbien = PriceManager.ajouterFraisEchange(prixbien, Wilaya.getPrix(wilaya));
		}
		if(trans==TypeTrans.LOCATION) {
			prixbien = PriceManager.ajouterFraisLocation(prixbien, superficie, Wilaya.getPrix(wilaya));
		}

		return prixbien;
	}
	
	public int compareTo(Biens B) {
		int i=0;
		try {
		i=date.substring(6,10).compareTo(B.date.substring(6,10));
		if(i==0)
		{
			i=date.substring(3, 5).compareTo(B.date.substring(3, 5));
			if(i==0) i=date.substring(0, 2).compareTo(B.date.substring(0, 2));
			if(i==0) { if(this.equals(B)) return 0; else return -1; } 
		}
		
		}
		catch(Exception e)
		{  }
		return -i;
	}
	
	public boolean equals(Biens P) {
		if((P.coordonnees.equals(coordonnees)) && (this.adresse.equalsIgnoreCase(P.adresse)) && (wilaya==P.getWilaya())) return true;
		else return false;
	}
	
	public int getWilaya() {
		return wilaya;
	}
	
	public void supprimer()
	{
		suppr=true;
	}
	
	public void archiver()
	{
		archive=true;
	}
	

	
	public void ajouterMessage(String msg) {
		tabMessages.add(msg);
	}
	
	
	public void afficherMessages() {
		if(nbMsgs>0) System.out.println("Les messages de ce bien: ");
		for(int i=0; i<nbMsgs; i++) {
			System.out.println("\n Message"+(i+1)+" : ");
			System.out.println(tabMessages.get(i));
		}
	}
	
	public boolean existe () {
		return (!suppr);
	}
	public boolean archive () {
		return (archive);
	}
	public void desarchiver() {
		archive = false;
	}
	
	public Object recupererChamps(int i)
	{
		switch(i)
		{
		
		case 1:
			return adresse;
			
		case 2:
			return wilaya;
			
		case 3:
			return superficie;
			
		case 4:
			return coordonnees;
		
		case 5:
			return prix;
			
		case 6:
			return trans.name();
			
		case 7:
			return date;
			
		case 20:
			return photoURL;
		
		case 21:
			return negociable;
			
		case 22:
			return descriptif;
			
		default: return null;
		}
	}
	
	public String generateTitle(int i) {
		
		switch(i) {
		case 1:
			return "Adresse:";
		case 2:
			return "Wilaya:";
		case 3:
			return "Superficie:";
		case 4:
			return "Proprietaire:";
		case 5:
			return "Prix:";
		case 6:
			return "Type Transaction:";
		case 7:
			return "Date:";
		case 20:
			return "L'URL de la photo:";
		case 21:
			return "Negociabilite:";
		case 22:
			return "Description:";
			
		default: return null;
		
		
		}

	}
	
	public ArrayList<String> getMessages() {
		return tabMessages;
	}

	
	public Double getPrix() {
		return prix;
	}

	public void setCoordonnees(Proprietaire coordonnees) {
		this.coordonnees = coordonnees;
	}

	
}
