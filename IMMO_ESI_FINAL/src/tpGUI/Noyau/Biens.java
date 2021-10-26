
package tpGUI.Noyau;


import java.io.Serializable;
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
	
	public double calculerPrix() {
		
		double prixbien=prix;
		Scanner sc= new Scanner(System.in);
		
		if((trans==TypeTrans.VENTE) || (trans==TypeTrans.ECHANGE)) {
			
			if(prixbien<5000000) {
				
				if(Wilaya.getPrix(wilaya)<50000) prixbien+= prix*(0.03);
				else prixbien+= prix*(0.035);	
			}
			else { /**/
				if((prixbien<15000000) && (prixbien>=5000000)) {
					
					if(Wilaya.getPrix(wilaya)<50000)  prixbien+= prix*(0.02); 
					else prixbien+= prix*(0.025); 
				}
				else {
					
					if(Wilaya.getPrix(wilaya)<70000) prixbien+= prix*(0.01);
					else prixbien+= prix*(0.02);
				}
				
			}
			
		}
		else { 
				if(trans==TypeTrans.LOCATION) {
					if(superficie<60) {
						if(Wilaya.getPrix(wilaya)<50000) prixbien+= prixbien*(0.01);
						else prixbien+= prixbien*(0.015);
					}
					else {
						if((superficie>=60) && (superficie<150)) {
							if(Wilaya.getPrix(wilaya)<50000) prixbien+= prixbien*(0.02);
							else prixbien+= prixbien*(0.025);
						}
						else { /*Supérieure à 150*/
							if(Wilaya.getPrix(wilaya)<50000) prixbien+= prixbien*(0.03);
							else prixbien+= prixbien*(0.035);
						}
					}
				}
		}
		/*
		if(trans==TypeTrans.ECHANGE) {
			this.visualiser();
			boolean valide=false;
			System.out.println("Dans quelle wilaya voulez vous faire l'échange? ");
			while(!valide) {
				String s=sc.next();
				try {
					int n=Wilaya.getNumWilaya(s);
					if(n==-1) throw new ElementNonExistantException();
					else {
						if(n!=wilaya) prixbien+=0.0025*prix; valide = true;
					}
				}
				catch (ElementNonExistantException e) {
					System.out.println("Veuillez entrer une wilaya qui existe.");
				}
			}
		}*/
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
	
	public void modifier(int i, Agence notreAgence)
	{
		Scanner scanner=new Scanner(System.in);
		Object valeur;
		
		boolean modif=false;
	    while(!modif) {
	    	if(i!=4)
			{
				System.out.println("Donner la nouvelle valeur");
				valeur=scanner.next();
			}
			else valeur=null;
			try {
				switch(i)
				{
				
				case 1:
					adresse=(String)valeur;
					break;
				case 2:
					String ch=(String)valeur;
					if(Wilaya.getNumWilaya(ch)==-1) throw new ElementNonExistantException(); 
					wilaya=Wilaya.getNumWilaya(ch);
					break;
				case 3:
					if(Double.parseDouble((String)valeur)<=0) throw new Exception();
					superficie=Double.parseDouble((String)valeur); 
					break;
				case 4:
					System.out.println("Saisissez le nom du propriétaire.");
		    		String nom = scanner.next();
		    		System.out.println("Saisissez le prénom du propriétaire.");
		    		String prenom = scanner.next();
		    		String esp=" ";
		    		String nomprenom = nom.concat(esp.concat(prenom));
		    		Proprietaire p = notreAgence.getProprietaire(nomprenom);
		    		if(p==null) throw new ElementNonExistantException();
		    		else {  
		    			coordonnees = p;
		    			coordonnees.ajouterBiens(this);
		    		}
		    		
					break;
				case 5:
					if(Double.parseDouble((String)valeur)<=0) throw new Exception();
					prix=Double.parseDouble((String)valeur); 
					break;
				case 6:
					negociable=(boolean)valeur;
					break;
				case 7:
				    String chaine2=(String)valeur;
				    if(chaine2.equalsIgnoreCase("VENTE"))trans=TypeTrans.VENTE;
				    else if(chaine2.equalsIgnoreCase("LOCATION"))trans=TypeTrans.LOCATION;
				    else if(chaine2.equalsIgnoreCase("ECHANGE"))trans=TypeTrans.ECHANGE;
				    else throw new Exception();
					break;
				case 8:
					descriptif=(String)valeur;
					break;
				case 9:
					date=(String)valeur;
					break;
				case 10:
					photoURL=(String)valeur;
					break;
				default:
					if(this.getClass().getSimpleName().equalsIgnoreCase("NonHabitable")) {
						switch(i) {
						case 11:
							((NonHabitable)this).statutJuridique = (String)valeur;
							break;
						case 12:
							if(Integer.parseInt((String)valeur)<=0) throw new Exception();
							((NonHabitable)this).nbFaçades = Integer.parseInt((String)valeur); 
							break;
						}
					}
					else {
						if(this.getClass().getSimpleName().equalsIgnoreCase("Maison")) {
							switch(i) {
							case 11:
								if(Integer.parseInt((String)valeur)<=0) throw new Exception();
								((Maison)this).nbPieces = Integer.parseInt((String)valeur);;
								break;
							case 12:
								((Maison)this).meuble = (boolean)valeur;
								break;
							case 13:
								if(Integer.parseInt((String)valeur)<=0) throw new Exception();
								((Maison)this).nbEtages = Integer.parseInt((String)valeur);;
								break;
							case 14:
								((Maison)this).garage = (boolean)valeur;
								break;
							case 15:
								((Maison)this).piscine = (boolean)valeur;
								break;
							case 16:
								((Maison)this).jardin = (boolean)valeur;
								break;
							case 17:
								if(Double.parseDouble((String)valeur)<=0) throw new Exception();
								if(Double.parseDouble((String)valeur)>superficie) throw new SuperficieHabitableTresGrandeException();
								((Maison)this).superficiehabitable = Double.parseDouble((String)valeur); 
								if(((Maison)this).superficiehabitable > ((Maison)this).superficie) throw new Exception();
								break;
								
							}
						}
						else { /**Appartement**/
							switch(i) {
							case 11:
								if(Integer.parseInt((String)valeur)<=0) throw new Exception();
								((Appartement)this).nbPieces = Integer.parseInt((String)valeur);
								break;
							case 12:
								((Appartement)this).meuble = (boolean)valeur;
								break;
							case 13:
								if(Integer.parseInt((String)valeur)<=0) throw new Exception();
								((Appartement)this).etage = Integer.parseInt((String)valeur);
								break;
							case 14:
								String chaine3=(String)valeur;
							    if(chaine3.equalsIgnoreCase("duplexe")) ((Appartement)this).type=Xplexe.DUPLEXE;
							    else if(chaine3.equalsIgnoreCase("simplexe")) ((Appartement)this).type=Xplexe.SIMPLEXE;
							    else throw new Exception();
								break;
							case 15:
								((Appartement)this).ascenseur = (boolean)valeur;
								break;
							}
						}
					}
				}
				
				modif = true;
			}
			catch(ElementNonExistantException e) {
				System.out.println("L'élement que vous avez choisi n'existe pas!");
			}
			catch(SuperficieHabitableTresGrandeException e) {
				System.out.println("La superficie habitable ne doit pas dépasser la superficie totale.");
			}
			catch (Exception e) {
				//e.printStackTrace();
				System.out.println("Votre entrée ne correspond pas au champ demandé." );
			}
	    }
		
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
	
	
	
	
	
	
	
}
