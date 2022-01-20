package tpGUI.Control;


public abstract class ControllerState {

    Controller ctrl;

    protected int accueilClicked() {
        ctrl.setState(new AccueilClickedState());
        return 0;
    }
    protected int optionsClicked() {
        ctrl.accueilClicked();
        ctrl.setState(new OptionsClickedState());
        return 0;
    }
    protected int validerClicked() {
        ctrl.setState(new ValiderClickedState());
        return 0;
    }
    protected int ajouterClicked() {
        ctrl.setState(new AjouterClickedState());
        return 0;
    }
    protected int archiveClicked() {
        ctrl.setState(new ArchiveClickedState());
        return 0;
    }
    protected int envoyerMessageClicked() {
        ctrl.accueilClicked();
        ctrl.setState(new MessageClickedState());
        return 0;
    }
    protected int afficherMessagesClicked() {
        ctrl.accueilClicked();
        ctrl.setState(new MessageClickedState());
        return 0;
    }

    protected void setCtrl(Controller ctrl) {
        this.ctrl = ctrl;
    }

}
