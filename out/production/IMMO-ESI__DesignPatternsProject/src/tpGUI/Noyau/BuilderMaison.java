package tpGUI.Noyau;

public class BuilderMaison extends BuilderBiens {

    @Override
    public void instantiateProduct() {
        nouveauproduit = new Maison();
    }

    @Override
    public void setWilaya(int wil) {
        nouveauproduit.wilaya = wil;
    }

    @Override
    public void setSuperficie(double sup) {
        nouveauproduit.superficie = sup;
    }

    @Override
    public void setPrix(double prix, boolean negociable) {
        nouveauproduit.prix = prix;
        nouveauproduit.negociable = negociable;
    }

    @Override
    public void setTypeTrans(TypeTrans type) {
        nouveauproduit.trans = type;
    }

    @Override
    public void setDescription(String desc) {
        nouveauproduit.descriptif = desc;
    }

    @Override
    public void setDate(String date) {
        nouveauproduit.date = date;
    }

    @Override
    public void setPhotoURL(String url) {
        nouveauproduit.photoURL = url;
    }

    public void setMeuble(boolean m) {
        ((Maison)nouveauproduit).meuble = m;
    }

    public void setNbPiecesNbEtages(int nbPieces, int nbEtages) {
        ((Maison)nouveauproduit).nbPieces = nbPieces;
        ((Maison)nouveauproduit).setNbEtages(nbEtages);
    }

    public void setGaragePiscineJardin(boolean g, boolean p, boolean j) {
        ((Maison)nouveauproduit).setGaragePiscineJardin(g, p, j);
    }

    public void setSuperfHabitable(Double superfHabitable) {
        ((Maison)nouveauproduit).setSuperficiehabitable(superfHabitable);
    }

}
