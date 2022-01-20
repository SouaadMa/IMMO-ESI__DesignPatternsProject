package tpGUI.Noyau;

import java.util.*;
import java.io.Serializable;
import java.text.*;


public class Agence implements Serializable
{
	private static Agence instance = null;
	private static int id=0;

	private Map<Integer, Biens> biens=new TreeMap<>();
	private List<Proprietaire> proprietaires=new ArrayList<Proprietaire>();
	private int nbProprietaires=0;


	private Map<Integer, Biens> biensArchives=new TreeMap<>();
	private Map<Integer, Biens> biensAValider = new TreeMap<>();
	private int nbBiensAValider=0;

	private Agence() {}

	public static Agence getInstance() {
		if(instance==null) {
			instance = new Agence();
		}
		return instance;
	}

	public static void setInstance(Agence inst) {
		instance = inst;
	}

	public void ajouterProprietaire(Proprietaire P) {
		proprietaires.add(P);
		nbProprietaires ++;
	}


	public int insereBien(Biens b) {
		b.setId(Agence.id);
		System.out.println( " inserer " + b.getId() );
		biensAValider.put(Agence.id, b);
		Agence.id ++;
		return b.getId();
	}


	public void valideBien(Biens b) {
    	/*Suppression de la liste des biens à valider*/
		getBiensAValider().remove(b.getId());
		getBiens().put(b.getId(), b); b.coordonnees.ajouterBiens(b);
		System.out.println( " valider " + b.getId() );
		if(existeProprietaire(b.coordonnees)<0) proprietaires.add(b.coordonnees);
	}


	public Biens getBien(int i) {
		Biens b = getBiens().get(i);
		if(b==null) return null;
		if(!b.archive && !b.suppr) return b;
		return null;
	}


	public void archiverBiens( int bien )
	{
		getBiens().get(bien).archiver();
		getBiens().remove(bien);
		biensArchives.put(bien, getBien(bien));
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
			biens.put(id, bien);
			biensArchives.remove(bien);

	}

	public Map<Integer, Biens> getBiens() {
		return biens;
	}

	public Map<Integer, Biens> getArchives() {
		return biensArchives;
	}

	public Biens getBienArchive(int i) {
		return getArchives().get(i);
	}

	public Map<Integer, Biens> getBiensAValider() {
		return biensAValider;
	}

	public Biens getBienAValider(int i) {
		return getBiensAValider().get(i);
	}

	public List<Proprietaire> getProprietaires() {
		return proprietaires;
	}

}

