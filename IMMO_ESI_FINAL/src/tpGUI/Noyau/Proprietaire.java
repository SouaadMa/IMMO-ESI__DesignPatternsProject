package tpGUI.Noyau;


import java.util.Set;
import java.io.Serializable;
import java.util.*;



public class Proprietaire implements Serializable {
	
	  protected String  nom, prenom, adresseMail, adresse, numTelephone;
	  protected Set<Biens> possesions=new TreeSet<Biens>(); 
	       
	       
	  public Proprietaire(String nom , String prenom , String adresseMail , String adresse , String numTelephone)
	  {
			this.nom=nom;
			this.prenom=prenom;
			this.adresseMail=adresseMail;
			this.adresse=adresse;
			this.numTelephone=numTelephone;
  
	  }
	  
	  
	  public void afficherBiens()
	  {
		
		  Iterator<Biens> it=possesions.iterator(); Biens sauv;
		  while(it.hasNext())
		  {
			  sauv = it.next();
			  if(!sauv.archive() && sauv.existe()) sauv.afficher();
		  }    
	  }
	       
	       
	  public void afficher()
	  
	  {
		  
	      System.out.print("le nom du proprietaire : "+nom+"  le prenom: "+prenom+"  son adresse e-mail: "+adresseMail+"  son numero de telephone: "+numTelephone+" ");

	      System.out.println();
	           
      }
	     
	  public boolean equals(Object proprietaire)
	  {
	     if (this.getNomPrenom().equalsIgnoreCase(((Proprietaire)proprietaire).getNomPrenom()) ) return true ;
	     else return false ;    	   
	  }
	       
	       
	  public void ajouterBiens( Biens bien )
	  {
	 	  possesions.add(bien);
      }
	  
	  public String getNomPrenom() {
		  return (nom+" "+prenom);
	  }
	       
	  public int getNbProprietairesExistants()
	  {
		  return possesions.size();
	  }
	 
     public String getNom() {
    	 return nom;
     }
     public String getPrenom() {
    	 return prenom;
     }
     public String getTel() {
    	 return numTelephone;
     }
     public String getAdresse() {
    	 return adresse;
     }
     public String getAdresseMail() {
    	 return adresseMail;
     }
     
     public Set<Biens> getPossesions() {
    	 return possesions;
     }

	 
}

