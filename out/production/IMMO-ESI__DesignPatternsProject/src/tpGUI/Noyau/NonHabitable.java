package tpGUI.Noyau;


import javafx.scene.text.Text;
import tpGUI.UI.CreationMessage;

import java.util.ArrayList;

public class NonHabitable extends Biens {
	
	protected String statutJuridique;
	protected int nbFacades;
	
	public NonHabitable(String adresse , int wilaya , double superficie , Proprietaire coordonnees , double prix , boolean negociable ,
						TypeTrans trans , String descriptif , String date , String photoURL ,String statutJurdique , int nbFacades ){
 	 
		super(adresse,wilaya,superficie,coordonnees,prix,negociable,trans,descriptif,date,photoURL);
		this.statutJuridique=statutJurdique;
		this.nbFacades=nbFacades;
		
	}
	
	public void afficher() {
		System.out.print("Un terrain: ");
		super.afficher();
		System.out.println();
	}
	
	public String visualiser() {
		String s= super.visualiser();
		
		s=s+"\n11* Statut Juridique : "+statutJuridique+".\n12* Nbr de façades : "+nbFacades+".";
		return s;
	}

	public ArrayList<Text> visualiserInfos() {

		ArrayList<Text> infos = super.visualiserInfos();



		return infos;
	}

	public ArrayList<Text> visualiserInfosDetails() {

		ArrayList<Text> infos = super.visualiserInfosDetails();

		infos.add(CreationMessage.creerMessage(this, 11));
		infos.add(CreationMessage.creerMessage(this, 10));

		return infos;
	}
	
	public double calculerPrix() {
		double prixbien;
		prixbien=super.calculerPrix();
		if(nbFacades>1) prixbien += 0.005*nbFacades*prix;
		
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
				else if(i==11) valeur = nbFacades;
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
    		return "Nombre de façades:";
    	default:
    		return super.generateTitle(i);
    	}
    }



	
	
}
