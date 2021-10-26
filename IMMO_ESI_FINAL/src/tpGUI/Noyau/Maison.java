package tpGUI.Noyau;


public class Maison extends Habitable{
	
	protected int nbEtages;
	protected boolean garage;
	protected boolean piscine;
	protected boolean jardin;
	protected double superficiehabitable;
	
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
	
	
}
