package tpGUI.Noyau;

public abstract class Habitable extends Biens{

		protected int nbPieces ;
	    protected boolean meuble;

		public Habitable() {}
	    
	    public Habitable(String adresse , int wilaya , double superficie , Proprietaire coordonnees , double prix , boolean negociable ,TypeTrans trans , String descriptif , String date , String photoURL ,int nbPieces , boolean meuble )
	    {
	      super(adresse,wilaya,superficie,coordonnees,prix,negociable,trans,descriptif,date,photoURL);
	      this.nbPieces=nbPieces;
	      this.meuble=meuble;
	    }
	    

		public String visualiser ()
	    {
	       String s=super.visualiser();
	       s=s+"\n11* Le nombre de pieces : "+nbPieces+"";
	       if (meuble==true) s=s+"\n12* Le bien est meublé. ";
	       else s=s+"\n12* Le bien n'est pas meublé.";
	       return s;
	    }
		
		public String generateTitle(int i) {
			switch (i) {
			case 9:
				return "Nombre de pieces:";
			case 10:
				return "Meuble";
			default:
				return super.generateTitle(i);
			}
		}
	    
		 
}

