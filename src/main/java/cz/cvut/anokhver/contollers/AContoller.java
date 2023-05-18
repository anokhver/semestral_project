package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.menu.AMenu;

/**
 * Controller of some view as menu or levels
 *
 * @author Veronika
 */
public abstract class AContoller {

    private AMenu view;

    public AMenu getView() {
        return view;
    }

    public void setView(AMenu view) {
        this.view = view;
    }

    /**
     * Update state of contollers if needed
     *
     * @param delta animation par
     */
    public abstract void update(double delta);
}
