package tpGUI.Control;

import tpGUI.Noyau.Biens;
import tpGUI.Noyau.Proprietaire;
import tpGUI.Noyau.TypeTrans;

public interface BuilderBiens {
    
    Proprietaire proprietaire = null;
    Biens biens = null;

    public void setAddress(String adr) ;
    
    public void setWilaya(int wil) ;

    public void setSuperficie(double sup) ;

    public default void setProprietaire(Proprietaire p) {
        biens.setCoordonnees(p);
    }
    
    public void setPrix(double prix, boolean negociable) ;

    public void setTypeTrans(TypeTrans type) ;

    public void setDescription(String desc) ;

    public void setDate() ;

    public void setPhotoURL(String url) ;
    
    
}
