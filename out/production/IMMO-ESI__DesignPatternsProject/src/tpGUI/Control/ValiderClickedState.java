package tpGUI.Control;

public class ValiderClickedState extends ControllerState{
    @Override
    protected int accueilClicked() {
        ctrl.setState(new AccueilClickedState());
        return 0;
    }

    @Override
    protected int optionsClicked() {
        ctrl.setState(new OptionsClickedState());
        return 0;
    }

    @Override
    protected int validerClicked() {
        return 1;
    }

    @Override
    protected int ajouterClicked() {
        ctrl.setState(new AjouterClickedState());
        return 0;
    }

    @Override
    protected int archiveClicked() {
        ctrl.setState(new ArchiveClickedState());
        return 0;
    }
}
