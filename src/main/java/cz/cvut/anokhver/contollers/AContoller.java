package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.menu.AMenu;

public abstract class AContoller {

    private AMenu view;

    public AMenu getView() {
        return view;
    }
    public abstract void update(double delta);
    public void setView(AMenu view) {
        this.view = view;
    }
}
