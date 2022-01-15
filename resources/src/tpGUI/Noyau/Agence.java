package tpGUI.Noyau;

import java.util.*;
import java.io.Serializable;
import java.text.*;


public class Agence implements Serializable
{
	private static Agence instance = null;

	private Set<Biens> biens=new TreeSet<Biens>();
	private List<Proprietaire> proprietaires=new ArrayList<Proprietaire>();
	private int nbProprietaires=0;


	private Set<Biens> biensArchives=new TreeSet<Biens>();
	private Set<Biens> biensAValider = new TreeSet<Biens>();
	private int nbBiensAValider=0;

	private Agence() {}

	public static Agence getInstance() {
		if(instance==null) instance = new Agence();
		return instance;
	}

	public static void setInstance(Agence inst) {
		instance = inst;
	}

	public void ajouterProprietaire(Proprietaire P) {
		proprietaires.add(P);
		nbProprietaires ++;
	}


	public void insereBien(Biens b) {
		biensAValider.add(b);
	}


	public void valideBien(Biens b) {
    	/*Suppression de la liste des biens à valider*/
		getBiensAValider().remove(b);
		getBiens().add(b); b.coordonnees.ajouterBiens(b);
		if(existeProprietaire(b.coordonnees)<0) proprietaires.add(b.coordonnees);
	}


	public Biens getBien(int i) {
		Biens sauv=null; boolean found=false; int pos=1;
		Iterator<Biens> it=getBiens().iterator();
		if(i<=getBiens().size()) {
			while(it.hasNext() && !found)
			{
				sauv = it.next();
				if(!sauv.archive && !sauv.suppr) {
					if(pos==i) found = true;
					else pos++;
				}
			}
		}
		return sauv;
	}


	public void archiverBiens( Biens bien )
	{
		bien.archiver();
		getBiens().remove(bien);
		biensArchives.add(bien);
	}

	public Proprietaire getProprietaire (String nomprenom) {
		Proprietaire p=null; boolean found=false; int i=0;
		while(!found && i<nbProprietaires) {

			if(proprietaires.get(i).getNomPrenom().equalsIgnoreCase(nomprenom)) {
				found = true; p=proprietaires.get(i);
			}
			else {
				i++;
			}
		}

		return p;
	}

	public void afficherArchives() {
		Iterator<Biens> it=biensArchives.iterator(); int i=1; Biens b;
		while(it.hasNext())
		{
			b = it.next();
			if(b.existe()) {
				System.out.print(i+". ");
				b.afficher();i++;
				System.out.println();
			}
		}
	}


	public int getNbBiens() {
		return getBiens().size();
	}



	public void supprimerProprietaire(Proprietaire p)
	{
		proprietaires.remove(p);

	}


	public int existeProprietaire(Proprietaire proprietaire)
	{
		return proprietaires.indexOf(proprietaire);

	}


	public void supprimerBiens(Biens bien)
	{
		if(bien!=null) {
			bien.supprimer();
			bien.coordonnees.getPossesions().remove(bien);
			biens.remove(bien);
			biensArchives.remove(bien);
			biensAValider.remove(bien);
			int i=-1;
			if(bien.coordonnees.getNbProprietairesExistants()==0)
			{
				i=existeProprietaire(bien.coordonnees);
				if(i>=0) supprimerProprietaire(bien.coordonnees);
			}
		}
	}

	public void desarchiverBiens(Biens bien) {

			bien.desarchiver();
			biens.add(bien);
			biensArchives.remove(bien);

	}

	public Set<Biens> getBiens() {
		return biens;
	}

	public Set<Biens> getArchives() {
		return biensArchives;
	}

	public Biens getBienArchive(int i) {
		Biens sauv=null; boolean found=false; int pos=1;
		Iterator<Biens> it=getArchives().iterator();
		if(i<=getArchives().size()) {
			while(it.hasNext() && !found)
			{
				sauv = it.next();

				if(pos==i) found = true;
				else pos++;

			}

		}
		return sauv;
	}

	public Set<Biens> getBiensAValider() {
		return biensAValider;
	}

	public Biens getBienAValider(int i) {
		Biens sauv=null; boolean found=false; int pos=1;
		Iterator<Biens> it=getBiensAValider().iterator();
		if(i<=getBiensAValider().size()) {
			while(it.hasNext() && !found)
			{
				sauv = it.next();

				if(pos==i) found = true;
				else pos++;

			}

		}
		return sauv;

	}

	public List<Proprietaire> getProprietaires() {
		return proprietaires;
	}

}

