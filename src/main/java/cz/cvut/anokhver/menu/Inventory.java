package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.items.Bonus;
import cz.cvut.anokhver.items.Collar;
import cz.cvut.anokhver.items.Hat;
import cz.cvut.anokhver.items.Item;

public class Inventory extends AMenu {
    private Item[] backPack;
    private Collar yourCollar;
    private Hat yourHat;
    private Bonus yourBonus;

    public boolean addItem(Item item){
        return false;
    }


    public boolean removeItem(Item item){
        return false;
    }

    public boolean haveItem(Item item){
        return false;
    }

    private boolean isFull()
    {
        return false;
    }


    @Override
    public void init() {

    }
}
