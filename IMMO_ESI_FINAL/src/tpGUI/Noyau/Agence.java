package tpGUI.Noyau;

import java.util.*;
import java.io.Serializable;
import java.text.*;


public class Agence implements Serializable
{
	
	protected Set<Biens> biens=new TreeSet<Biens>();
	protected List<Proprietaire> proprietaires=new ArrayList<Proprietaire>(); 
	protected int nbProprietaires=0;
	protected String[] criteres={ "adresse","wilaya souhaitee","superficie" , " coordonnees d'un proprietaire","prix" ,"type de transaction"  ,"date", "le type du bien" , "nombre maximal des pièces" };
	protected static int nbCriteres=9;
	protected int[] numCriteres={1,2,3,4,5,6,7,8,9};
	protected int nbNumCriteres=6;
	protected Set<Biens> biensArchives=new TreeSet<Biens>();
	
	protected Map<String, String> admins = new TreeMap<String, String>();
	
	
	protected Set<Biens> biensAValider = new TreeSet<Biens>();
	protected int nbBiensAValider=0;
	
	
	public Agence() {
		admins.put("20042000", "Yasmine");
		admins.put("01052001", "Saadia");
		admins.put("15062017", "Annabelle");
		
	}
	
	public boolean existeAdmin(String key, String val) {
		if(admins.containsKey(key)) {
			if(((String)admins.get(key)).equals(val)) return true;
			else return false;
		}
		else return false;
	}
	
    

	public void consulterBiens(int n)
	{
		Iterator<Biens> it=getBiens().iterator(); int i=1; Biens b;
		while(it.hasNext())
		{
			b = it.next();
			if(b.existe() && !b.archive()) {
				System.out.print(i+". ");
				b.afficher();i++;
				if(n==1) System.out.println(" Prix final: "+b.calculerPrix());

				System.out.println();
			}
		}
	}

	public void consulterProp()
	{
		for(int i=0; i<nbProprietaires; i++) {
			System.out.println("Propriétaire "+i+" ");
			proprietaires.get(i).afficher();
		}
	}

	public void afficherMessages() {
		Iterator<Biens> it=getBiens().iterator(); int i=1; Biens b;
		while(it.hasNext())
		{
			b = it.next();
			if(b.existe() && !b.archive()) {
				System.out.print(i+". ");
				if(b.nbMsgs>0)
					b.afficherMessages();
				else System.out.println("Ce bien n'a pas de messages.");

				System.out.println();
				i++;

			}
		}
	}

	public Set<Biens> remplirEnsembleBiens(int i , Set<Biens> ensembleBiens, Object valeur1 , Object valeur2) throws Exception
	{

		Biens bien;
		
		//System.out.println(ensembleBiens);

		Iterator<Biens> it=ensembleBiens.iterator();
		
		Set<Biens> temp=new TreeSet<Biens>();
		
		
		while(it.hasNext())
		{
			bien=it.next();
			
			Object valeur=bien.recupererChamps(i);
			
			
			switch(i)
			{

				case 1:
					if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
					break;
				case 2:
					int numWilaya=Wilaya.getNumWilaya((String) valeur1);
					if(((Integer)numWilaya).equals((int)valeur)) temp.add(bien);
					break;
				case 3:
					double superficieMin=Double.parseDouble((String)valeur1);
					double superficieMax=Double.parseDouble((String)valeur2);

					if(((double)valeur)>=(superficieMin) && ((double)valeur)<=(superficieMax)) temp.add(bien);
					break;
				case 4:
					if(((String)valeur1).equalsIgnoreCase(((Proprietaire)valeur).nom)&& ((String)valeur2).equalsIgnoreCase(((Proprietaire)valeur).prenom)) temp.add(bien);
					break;
				case 5:
					double prixMin=Double.parseDouble((String)valeur1);
					double prixMax=Double.parseDouble((String)valeur2);
					if(((double)valeur)>=prixMin && ((double)valeur)<=prixMax )temp.add(bien);
					break;
				case 6:
					if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
					break;
				case 7:
					if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
					break;
				case 8:
					if(((String)valeur1).equalsIgnoreCase((String)valeur)) temp.add(bien);
					break;
				case 9:
					String typeBien=((String)bien.recupererChamps(8));
					if(!(typeBien.equalsIgnoreCase("Terrain")))
					{int nbPieces=Integer.parseInt((String) valeur1);
						if((nbPieces)>=(int)valeur) temp.add(bien);}
					break;
			}


		}
		return temp;


	}


	public   Set<Biens>  filtrer(int i, Set<Biens> ensembleBiens , Object valeur1 ,Object  valeur2) throws Exception
	{

		
		i=numCriteres[i-1];
		
		return remplirEnsembleBiens(i,ensembleBiens,valeur1,valeur2);

	}


	public void ajouterCritere()
	{
		int i; Scanner scanner=new Scanner(System.in);

		System.out.println("voici tous les criteres disponibles: ");
		for(i=0;i<nbCriteres;i++)
		{
			System.out.println("  -"+(i+1)+criteres[i]);
		}

		System.out.println("choisissez le critere a ajouter au systeme en repondant par son numero: ");
		i=scanner.nextInt();

		if(Arrays.binarySearch(numCriteres, i)<0)
		{
			numCriteres[nbNumCriteres]=i; nbNumCriteres++;

		}


	}



	public void ajouterProprietaire(Proprietaire P) {
		proprietaires.add(P);
		nbProprietaires ++;
	}


	public Biens ajouterBiens()
	{
		Biens bien=null;
		Scanner sc = new Scanner(System.in);
		boolean Cohérence=false;
		boolean neg=false, gar=false, pis=false, jar=false; int type=0;
		int m=0, n=0, wi=0, d=0, nb1=0, nb2=0, nb3=0;  double sup=0, pr=0, suph=0; String nom="", st="", des="", adr=""; Xplexe x= Xplexe.SIMPLEXE;
		TypeTrans tr=TypeTrans.ECHANGE; Proprietaire p=null;
		Date date = new Date();
		String format = "dd/MM/yyyy";
		SimpleDateFormat f = new SimpleDateFormat(format);


		while(Cohérence==false)  {
			try {

				sc=new Scanner(System.in);
				switch(m) {
					case 0: System.out.println("Type du bien :\n*Tappez <1> pour Maison.\n*Tappez <2> pour Appartement.\n*Tappez <3> pour Terrain.");
						type = sc.nextInt();
						if(type<=3 && type>=1) m=1;
						else throw new WrongChoiceException();
					case 1: System.out.print("ADRESSE : ");
						adr = sc.next(); m=2;
					case 2: System.out.print("SUPERFICIE :");
						sup = sc.nextInt();
						if(sup<0) throw new Exception();
						else m=3;
					case 3: System.out.print("Nature de la transaction  :\n*Tappez <1> pour location.\n*Tappez <2> pour vente.\n*Tappez <3> pour échange.");
						int nature = sc.nextInt(); if(nature<1 || nature>3) throw new WrongChoiceException();
					else m=4;
						if(nature==1) { tr=TypeTrans.LOCATION ;}
						if(nature==2) { tr=TypeTrans.VENTE ;}
						if(nature==3) { tr=TypeTrans.ECHANGE ; }
					case 4: System.out.print("Prix :");
						pr = sc.nextInt();
						if(pr<0) throw new Exception();
						else m=5;
					case 5: System.out.print("Saisissez <true> si le prix est négociable <false> sinon  :");
						neg = sc.nextBoolean(); m=6;
					case 6:	System.out.println("Saisissez le nom du propriétaire.");
						nom = sc.next();
						System.out.println("Saisissez le prénom du propriétaire.");
						String prenom = sc.next();
						String esp=" ";
						String nomprenom = nom.concat(esp.concat(prenom));
						p = this.getProprietaire(nomprenom);
						if(p==null) throw new ElementNonExistantException(); else m=7;
					case 7: System.out.print("Donner une description : ");
						des = sc.next(); m=8;
					case 8: System.out.print("Wilaya :");
						String w=sc.next();
						wi = Wilaya.getNumWilaya(w);
						if(wi==-1) throw new ElementNonExistantException(); else m=9;
					case 9: if(type==1) {/*Maison*/
						switch (n) {
							case 0:
								System.out.print("NbEtages : ");
								nb1 = sc.nextInt();
								if(nb1<0) throw new Exception();
								else n=1;
							case 1:
								System.out.print("NbPieces : ");
								nb2 = sc.nextInt();
								if(nb2<0) throw new Exception();
								else n=2;
							case 2:
								System.out.print("Garage : (true/false) ");
								gar = sc.nextBoolean();
								n=3;
							case 3:
								System.out.print("Piscine : (true/false) ");
								pis = sc.nextBoolean(); n=4;
							case 4:
								System.out.print("Jardin : (true/false) ");
								jar = sc.nextBoolean(); n=5;
							case 5:
								System.out.print("Superficie Habitable : ");
								suph = sc.nextDouble();
								if(suph<=0) throw new Exception();
								if(suph>sup) throw new SuperficieHabitableTresGrandeException();
								else n=6;
							case 6:
								bien = new Maison(adr, wi, sup, p, pr, neg, tr, des, f.format(date), "", nb2, false, nb1, gar, pis, jar, suph);
								System.out.println("Votre Maison a été créé avec succés.");
						}
					}
					else {
						if(type==2) { /*Appartement*/
							switch(n) {
								case 0:
									System.out.print("Etage : ");
									nb1 = sc.nextInt(); n=1;
								case 1:
									System.out.print("Type : 1 pour simplexe et 2 pour duplexe ");
									nb2 = sc.nextInt();
									if(nb2==1 || nb2==2) {n=2; if(nb2==1) x = Xplexe.SIMPLEXE; else x = Xplexe.DUPLEXE; }
									else throw new WrongChoiceException();
								case 2:
									System.out.print("NbPieces : ");
									nb3 = sc.nextInt(); n=3;
								case 3:
									System.out.print("Ascenseur : (true/false) ");
									gar = sc.nextBoolean(); n=4;
								case 4:
									bien = new Appartement(adr, wi, sup, p, pr, neg, tr, des, f.format(date), "", nb3, false, nb1, x, gar);
									System.out.println("Votre appartement a été créé avec succés. ");
							}
						}
						else { /*NonHabitable*/
							switch(n) {
								case 0:
									System.out.print("Statut Juridique : ");
									st = sc.next(); n=1;
								case 1:
									System.out.print("Nb Façades : ");
									nb2 = sc.nextInt(); n=2;
								case 2:
									bien = new NonHabitable(adr, wi, sup, p, pr, neg, tr, des, f.format(date), "", st, nb2);
									System.out.println("Votre terrain a été créé avec succés. ");
							}
						}
					}
						/**E**/
						Cohérence = true;
				}
			}
			catch(SuperficieHabitableTresGrandeException e) {
				System.out.println("La superficie habitable ne doit pas dépasser la superficie totale.");
			}
			catch(WrongChoiceException e) {
				System.out.println("Vous avez entré un mauvais choix.");
			}
			catch(ElementNonExistantException e) {
				System.out.println("L'élement que vous avez choisi n'existe pas.");
			}
			catch(Exception e )
			{
				System.out.println("Votre entrée a généré un problème.");
			}
		}
		//inserBien(bien);
		return bien;
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


	public void modifierBiens(Biens bien)
	{
		Scanner scanner=new Scanner(System.in);
		bien.visualiser();
		System.out.println("Donner le numero du champs que vous voulez modifier: ");
		int i=scanner.nextInt();
		bien.modifier(i, this);

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

