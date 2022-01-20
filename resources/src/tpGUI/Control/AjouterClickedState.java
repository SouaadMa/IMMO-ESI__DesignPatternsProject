package tpGUI.Control;

public class AjouterClickedState extends ControllerState{

    @Override
    protected int ajouterClicked() {
        ctrl.setState(new AjouterClickedState());
        return 0;
    }

}
