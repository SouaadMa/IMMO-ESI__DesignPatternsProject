package tpGUI.Noyau;



public class Wilaya {
	private static double[] tabPrix= {100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0,100000.0, 30000.0, 60000.0, 50000.0 };
	private static String[] tabNoms= {"Adrar", "Chlef", "Aghouat", "Ombeouaghi", "Batna", "Bejaia","Biskera","Bechar","Blida","Bouira","Temenraset","Tebessa","Telemcen","Tiaret","Tizi Ouzou", "Alger","Djelfa","Jijel","Setif","Saida","Sekikda", "Sidi Bel Abas","Annaba", "Galema", "Constantine", "Media", "Mostaghanem", "Mesila", "Maascar", "Ouregla", "Oran", "ElBayadh", "Ilizi", "BorjBouariridj", "Boumerdes", "Taref", "Tindouf","Tissemssilt", "Ouadi", "Khenchela", "Sougahrass", "Tipaza", "Mila", "Ain Defla", "Neama", "Ain Temouchenet", "Gharedaia", "Relizane"};
	
	public static String getNom(int i) {
		
		return tabNoms[i-1];
	}
	
	public static double getPrix(int i) {
		return tabPrix[i-1];
	}
	
	public static int getNumWilaya(String nom) {
		int i=0; boolean found=false;
		while(!found && i<48) {
			if(tabNoms[i].equalsIgnoreCase(nom)) found=true;
			else i++;
		}
		if(found) return (i+1);
		else return -1;
		
	}
}
