package tpGUI.UI;

import java.text.DecimalFormat;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tpGUI.Noyau.*;

public interface CreationMessage {

	default Text creerMessage(Biens model, int i) {
		
		String nonh = "NonHabitable";
		Text etiquette ;
	
		switch(i) {
		case 0:
			etiquette = new Text( model.getClass().getSimpleName() );  
			if(model.getClass().getSimpleName().equals(nonh)) {
				etiquette = new Text ("Terrain");
			}
			break;
		case 1:
			etiquette = new Text( (String) Wilaya.getNom(model.getWilaya()) + " " + (String) model.recupererChamps(1) );
			break;
		case 2:
			etiquette = new Text( (String) Wilaya.getNom(model.getWilaya()) ); 
			break;
		case 3:
			etiquette = new Text( "Superficie: "+ fixDoubleDigits((Double) model.recupererChamps(3))+" m²" );
			
			break;
		case 4:
			etiquette = new Text( "Proprietaire: "+ ((Proprietaire) model.recupererChamps(4)).getNomPrenom() ); 
			break;
		case 5:
			etiquette = new Text( "Prix: "+ fixDoubleDigits((Double) model.getPrix()) +" DA" );
			break;
		case 55:
			
			if(model.recupererChamps(6).equals("ECHANGE")) 
				etiquette = new Text( "Prix final (Echange dans la même wilaya):\n"+ fixDoubleDigits((Double) model.calculerPrix()) +" DA"
						+"\n(Echange dans une autre wilaya): "+fixDoubleDigits((Double)( model.calculerPrix() + 0.0025*model.getPrix()))+" DA");
			else 
				etiquette = new Text( "Prix final: "+ fixDoubleDigits((Double) model.calculerPrix()) +" DA" );
				
			break;
		default:
			
			if(model.recupererChamps(i).getClass().getSimpleName().equals("Boolean")) {
				etiquette = new Text(model.generateTitle(i));
			}
			else {
			try {
			etiquette = new Text(model.generateTitle(i)+" "+(String) model.recupererChamps(i) ); 
			}
			catch(Exception e) {
				
					etiquette = new Text( model.generateTitle(i) + " "+(model.recupererChamps(i)).toString() ); 
				}
			}
			
			break;
		}
		
		
		 etiquette.setFont(Font. font ("Berlin Sans FB", 18));     
		 
		 
		 etiquette.setStyle("white-space: normal;");
		 return etiquette; 

	}
	
	default String fixDoubleDigits(double value) 
    {
        DecimalFormat formatter;

        if(value - (int)value > 0.0)
            formatter = new DecimalFormat("0.00"); 
        else
            formatter = new DecimalFormat("0");

        return formatter.format(value);
    }
	
	
	
	
	
}
