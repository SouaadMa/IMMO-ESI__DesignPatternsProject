package tpGUI.Control;

public class FacadeController {

    private static FacadeController instance = null;

    private FacadeController() {}

    public static FacadeController getInstance() {
        if(instance==null) instance = new FacadeController();
        return instance;
    }



}
