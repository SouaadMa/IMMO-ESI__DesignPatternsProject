package tpGUI.Noyau;

import java.util.Scanner;
import java.util.Date;
import java.text.*;



public class ImmoESI {
	

	public static Agence begin() {
		
		Agence ImmoESI = new Agence();
		
		Proprietaire Prop1 = new Proprietaire ("Benelhadj" , "Saadia" , "@esi.dz" , "Cité 05 Juillet" , "05" ) ;
		ImmoESI.ajouterProprietaire(Prop1);
		Proprietaire Prop2 = new Proprietaire ("Hamlaoui" , "Yasmine" , "@esi.dz" , "1er Novembre" , "06") ;
		ImmoESI.ajouterProprietaire(Prop2);
		Proprietaire Prop3 = new Proprietaire ("Mecheri" , "Hadia" , "@esi.dz" , "Ma lebyed" , "05" ) ;
		ImmoESI.ajouterProprietaire(Prop3);
		Proprietaire Prop4 = new Proprietaire ("Rouabhia" , "Anfel" , "@esi.dz" , "Tafchoun" , "07") ;
		ImmoESI.ajouterProprietaire(Prop4);
		
		Biens Bien1 = new Appartement ("adr 1", 2, 120, Prop2, 4000000,
			false, TypeTrans.VENTE, "descrip 1", "03/09/2020", "URL1", 
			4, false, 1, Xplexe.DUPLEXE, false);
		ImmoESI.valideBien(Bien1);
		
		
		Biens Bien2 = new Maison ("adr 2", 3, 200, Prop1, 10000000,
			false, TypeTrans.VENTE, "descrip 2", "02/09/2020", "URL2", 
			2, false, 1, false, false, true, 120);
		ImmoESI.valideBien(Bien2);
		
		Biens Bien3 = new NonHabitable("adr 3", 1, 500, Prop1, 20000000,
			false, TypeTrans.VENTE, "descrip 3", "01/09/2020", "URL 3",
			"statut juridique 3", 3);
		ImmoESI.valideBien(Bien3);
		
		Biens Bien4 = new Appartement ("adr 4", 3, 100, Prop2, 40000,
			false, TypeTrans.LOCATION, "descrip 4", "01/05/2020", "URL 4", 
			3, false, 1, Xplexe.DUPLEXE, false);
		ImmoESI.valideBien(Bien4);
		
		Biens Bien5 = new Maison ("adr 5", 2, 160, Prop3, 150000, /*Nbpieces=3*/
			false, TypeTrans.LOCATION, "descrip 5", "01/04/2020", "URL 5",
			3, true, 1, false, true, false, 120);
		ImmoESI.valideBien(Bien5);
		
		Biens Bien6 = new Appartement ("adr 6", 3, 50, Prop2, 600000,
			false, TypeTrans.LOCATION, "descrip 6", "02/03/2020", "URL 6", 
			1, true, 6, Xplexe.SIMPLEXE, false);
		ImmoESI.valideBien(Bien6);
		
		Biens Bien7 = new NonHabitable ("adr 7", 1, 650, Prop1, 18000000,
			false, TypeTrans.ECHANGE, "descrip 7", "13/02/2020", "URL 7",
			"Statut Juridique 7", 1);
		ImmoESI.valideBien(Bien7);
		
		Biens Bien8 = new Maison ("adr 8", 2, 200, Prop2, 14000000,
			true, TypeTrans.ECHANGE, "descrip 8", "12/12/2019", "URL 8",
			3, true, 1, true, false, false, 120);
		
		ImmoESI.valideBien(Bien8);
		
		
		
		/*
		
		System.out.println("Si vous voulez vous connecter en tant qu'administrateur, entrez vos informations.");
		
		Scanner sc = new Scanner(System.in);

		int choix=0;
		System.out.println("Nom d'utilisateur ");
		String nomu=sc.next();
		System.out.println("Mot de passe  ");
		String mdp=sc.next();
		String msg="";
		
		if(ImmoESI.existeAdmin(nomu, mdp)>=0) {
			while(ImmoESI.existeAdmin(nomu,mdp)>=0) {
				try {
				sc = new Scanner(System.in);
				System.out.println("\n\n****Opérations pour admin: ");
				System.out.println("1. Afficher la liste des biens (+Détails).");
				System.out.println("2. Ajouter un bien.");
				System.out.println("3. Supprimer un bien.");
				System.out.println("4. Archiver un bien.");
				System.out.println("5. Rechercher par critère.");
				System.out.println("6. Afficher les prix finaux.");
				System.out.println("7. Modifier un bien.");
				System.out.println("8. Afficher les messages envoyés à l'agence.");
				System.out.println("9. Afficher la liste des biens archivés. ");
				System.out.println("10. Afficher les biens d'un propriétaire.");
				System.out.println("11. Sortir.");
				choix = sc.nextInt();
				
				switch (choix) {
				case 1: System.out.println("\n*****\n");
						System.out.println("1. Afficher la liste des biens.");
						ImmoESI.consulterBiens(0);
						System.out.println("\nPour afficher les détails d'un bien, entrer son numéro!");
						int n = sc.nextInt(); if(n>ImmoESI.getNbBiens() || n<=0) throw new Exception();
						Biens b=ImmoESI.getBien(n);
						
						if(b!=null) {
							b.visualiser();
						}
						break;
				case 2:	System.out.println("\n*****\n");
						System.out.println("2. Ajouter un nouveau bien.");
						Biens nouveau=ImmoESI.ajouterBiens();
						if(nouveau!=null) nouveau.visualiser();
						break;
				case 3: System.out.println("\n*****\n");
						System.out.println("3. Supprimer un bien.");
						ImmoESI.consulterBiens(0);
						System.out.println("\nPour supprimer un certain bien, entrer son numéro !");
						n = sc.nextInt(); if(n>ImmoESI.getNbBiens() || n<=0) throw new Exception();
						b = ImmoESI.getBien(n);
						if(b!=null) ImmoESI.supprimerBiens(b); 
						break;
				case 4: System.out.println("\n*****\n");
						System.out.println("4. Archiver un bien.");
						ImmoESI.consulterBiens(0);
						System.out.println("\nPour archiver un certain bien, entrer son numéro !");
						n = sc.nextInt(); if(n>ImmoESI.getNbBiens() || n<=0) throw new Exception();
						b = ImmoESI.getBien(n);
						if(b!=null) ImmoESI.archiverBiens(b); 
						break;
				case 5: System.out.println("\n*****\n");
						System.out.println("5. Rechercher par critère.");
						ImmoESI.filtrer();
						break;
				case 6: System.out.println("\n*****\n");
						System.out.println("6. Afficher les prix finaux.");
						ImmoESI.consulterBiens(1);
						break;
				case 7: System.out.println("\n*****\n");
						System.out.println("7. Modifier un bien.");
						ImmoESI.consulterBiens(0);
						System.out.println("\nPour modifier un certain bien, entrer son numéro !");
						n = sc.nextInt(); if(n>ImmoESI.getNbBiens() || n<=0) throw new Exception();
						b = ImmoESI.getBien(n);
						if(b!=null) ImmoESI.modifierBiens(b);
						System.out.println("***Le bien a été modifié! \n Son prix final : "+b.calculerPrix());
						
						break;
				case 8: System.out.println("\n*****\n");
						System.out.println("8. Afficher les messages des biens.");
						ImmoESI.afficherMessages();
						break;
						
				case 9: System.out.println("\n*****\n");
						System.out.println("9. Afficher les biens archivés. ");
						ImmoESI.afficherArchives();
						
						break;
				case 10: System.out.println("\n*****\n");
						System.out.println("10. Afficher les biens d'un propriétaire. ");
						ImmoESI.consulterProp();
						System.out.println("\n Si vous voulez afficher les biens d'un certain propriétaire: ");
						System.out.println("Saisissez le nom du propriétaire.");
			    		String nom = sc.next();
			    		System.out.println("Saisissez le prénom du propriétaire.");
			    		String prenom = sc.next();
			    		String esp=" ";
			    		String nomprenom = nom.concat(esp.concat(prenom));
			    		Proprietaire p = ImmoESI.getProprietaire(nomprenom);
			    		p.afficherBiens();
						
						break;
				case 11: 	System.out.println("\n*****\n");
							System.out.println("A bientot !");
							mdp="";
							break;
				}
				}
				catch (Exception e) {
					//e.printStackTrace();
					System.out.println("Mauvais Choix.");
				}
				
				
			}
		}
		else {
			while(choix!=5) {
				System.out.println("Ces informations ne correspondent pas à un administrateur.");
				System.out.println("\n\n******Les opérations disponibles pour un visiteur ordinaire: ");
				System.out.println("1. Afficher la liste des biens (+Détails).");
				System.out.println("2. Rechercher par critère.");
				System.out.println("3. Afficher les prix finaux.");
				System.out.println("4. Envoyer un message à l'agence.");
				System.out.println("5. Sortir.");
				try {
					//sc = new Scanner(System.in);
					choix = sc.nextInt();
					switch (choix) {
					case 1: System.out.println("\n*****\n");
							System.out.println("1. Afficher la liste des biens.");
							ImmoESI.consulterBiens(0);
							System.out.println("\nPour afficher les détails d'un bien, entrer son numéro!");
							int n = sc.nextInt(); if(n>ImmoESI.getNbBiens() || n<=0) throw new Exception();
							Biens b=ImmoESI.getBien(n);
							
							if(b!=null) {
								b.visualiser();
							}
							break;
					
					case 3: System.out.println("\n*****\n");
							System.out.println("3. Afficher les prix finaux.");
							ImmoESI.consulterBiens(1); 
							break;
					case 4: System.out.println("\n*****\n");
							System.out.println("4. Envoyer un message à l'agence.");
							ImmoESI.consulterBiens(0);
							System.out.println("\nPour envoyer un message, entrer le numéro du bien concerné !");
							n = sc.nextInt(); if(n>ImmoESI.getNbBiens() || n<=0) throw new Exception();
							b = ImmoESI.getBien(n);
							System.out.println("Entrez votre message: \n");
							while(msg.equals("")) msg=sc.nextLine();
							if(b!=null) b.ajouterMessage(msg);
							ImmoESI.afficherMessages();
							break;
					case 2: System.out.println("\n*****\n");
							System.out.println("2. Rechercher par critère.");
							ImmoESI.filtrer();
							break;
					case 5: System.out.println("\n*****\n");
							System.out.println("5. Sortir.");
							break;
					}
				}
				catch(Exception e) {
					System.out.println("Mauvais Choix.");
				}
			}
		}
		
		//sc.close();
		
		
		
		
		/*
		System.out.println(Bien1.calculerPrix());
		System.out.println(Bien2.calculerPrix());
		System.out.println(Bien3.calculerPrix());
		System.out.println(Bien4.calculerPrix());
		System.out.println(Bien5.calculerPrix());
		System.out.println(Bien6.calculerPrix());
		System.out.println(Bien7.calculerPrix());
		System.out.println(Bien8.calculerPrix());
		*/
		
		return ImmoESI;
	}

}
