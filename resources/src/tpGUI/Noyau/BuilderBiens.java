package tpGUI.Noyau;

public abstract class BuilderBiens {
    
    Proprietaire proprietaire = null;
    Biens nouveauproduit = null;

    public abstract void instantiateProduct() ;

    public void buildProprietaire(String nom , String prenom , String adresseMail , String adresse , String numTelephone) {
        proprietaire = Agence.getInstance().getProprietaire(nom+" "+prenom);

        if(proprietaire==null) {
            proprietaire = new Proprietaire(nom, prenom, adresseMail, adresse, numTelephone);
        }
    }

    public void setAddress(String adr) {
        nouveauproduit.adresse = adr;
    }
    
    public abstract void setWilaya(int wil) ;

    public abstract void setSuperficie(double sup) ;

    public void setProprietaire() {
        nouveauproduit.setCoordonnees(proprietaire);
    }
    
    public abstract void setPrix(double prix, boolean negociable) ;

    public abstract void setTypeTrans(TypeTrans type) ;

    public abstract void setDescription(String desc) ;

    public abstract void setDate(String date) ;

    public abstract void setPhotoURL(String url) ;

    public int saveNouveauBien() {
        return Agence.getInstance().insereBien(nouveauproduit);
    }

    public Biens getNouveauBien() {
        return nouveauproduit;
    }

}
