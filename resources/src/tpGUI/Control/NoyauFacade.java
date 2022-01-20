package tpGUI.Control;

import javafx.scene.text.Text;
import tpGUI.Noyau.Agence;
import tpGUI.Noyau.Biens;

import java.util.ArrayList;

public class NoyauFacade {

    private static NoyauFacade instance = null;

    private NoyauFacade() {}

    public static NoyauFacade getInstance() {
        if(instance==null) instance = new NoyauFacade();
        return instance;
    }

    public boolean existeBiensArchives() {
        return !Agence.getInstance().getArchives().isEmpty();
    }
    public boolean existeBiens() {
        return !Agence.getInstance().getBiens().isEmpty();
    }
    public boolean existeBiensAValider() {
        return !Agence.getInstance().getBiensAValider().isEmpty();
    }

    public Biens getBienById(int id) {
        return Agence.getInstance().getBien(id);
    }

    public Object recupererChamps(int idBiens, int ichamps) {
        return getBienById(idBiens).recupererChamps(ichamps);
    }

    public String visualiserBien(int idBiens) {
        return getBienById(idBiens).visualiser();
    }

    public ArrayList<Text> visualiserInfos(int idBiens) {
        return getBienById(idBiens).visualiserInfos();
    }

    public ArrayList<Text> visualiserInfosDetails(int idBiens) {
        return getBienById(idBiens).visualiserInfosDetails();
    }

    public ArrayList<String> getMessagesBien(int idBiens) {return getBienById(idBiens).getMessages();}

    public void archivageBien(int idBiens) { Agence.getInstance().archiverBiens(idBiens); }
    public int insertionBien(Biens idBiens) { return Agence.getInstance().insereBien(idBiens); }
    public void validationBien(Biens idBiens) { Agence.getInstance().valideBien(idBiens); }
    public void suppressionBien(int idBiens) { Agence.getInstance().supprimerBiens(Agence.getInstance().getBien(idBiens)); }
    public void desarchivageBien(int idBiens) { Agence.getInstance().archiverBiens(idBiens); }
}
