package tpGUI.Noyau;


public class NonHabitable extends Biens {
	
	protected String statutJuridique;
	protected int nbFa�ades;
	
	public NonHabitable(String adresse , int wilaya , double superficie , Proprietaire coordonnees , double prix , boolean negociable ,TypeTrans trans , String descriptif , String date , String photoURL ,String statutJurdique , int nbFa�ades ){
 	 
		super(adresse,wilaya,superficie,coordonnees,prix,negociable,trans,descriptif,date,photoURL);
		this.statutJuridique=statutJurdique;
		this.nbFa�ades=nbFa�ades;
		
	}
	
	public void afficher() {
		System.out.print("Un terrain: ");
		super.afficher();
		System.out.println();
	}
	
	public String visualiser() {
		String s= super.visualiser();
		
		s=s+"\n11* Statut Juridique : "+statutJuridique+".\n12* Nbr de fa�ades : "+nbFa�ades+".";
		return s;
	}
	
	public double calculerPrix() {
		double prixbien;
		prixbien=super.calculerPrix();
		if(nbFa�ades>1) prixbien += 0.005*nbFa�ades*prix;
		
		return prixbien;
	}
	public Object recupererChamps(int i)
	{
		Object valeur ;
		if(i==8) valeur="Terrain";
		else
		{ 
			if(i==9) valeur=null;
			else
			{
				if(i==10) valeur = statutJuridique;
				else if(i==11) valeur = nbFa�ades;
				else valeur =super.recupererChamps(i);
			}
		}
		return valeur;
		
	}
	
	public String generateTitle(int i) {
    	switch(i) {
    	case 10:
    		return "Statut Juridique:\n";
    	case 11:
    		return "Nombre de facades:";
    	default:
    		return super.generateTitle(i);
    	}
    }



	
	
}
