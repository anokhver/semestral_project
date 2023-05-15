package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.additional.PlayerConfigutations;
import cz.cvut.anokhver.items.*;
import cz.cvut.anokhver.menu.Inventory;
import javafx.scene.layout.VBox;

public class InventoryController extends AContoller {
    private static Item[] backPack;
    private Collar yourCollar = null;
    private Hat yourHat = null;
    private Bonus yourBonus = null;

    private VBox view;
    public InventoryController() {
        // Initialize the inventory
        backPack = new Item[PlayerConfigutations.getBackPackSpace()]; // Set the size of the backpack
        if (PlayerConfigutations.getItem("Collar"))
        {
            yourCollar = new Collar("Collar", 0);
        }
        if (PlayerConfigutations.getItem("Hat"))
        {
            yourHat = new Hat("Hat", 0);
        }
        if (PlayerConfigutations.getItem("Bonus"))
        {
            yourBonus = new Bonus("Bonus", 0);
        }
        for(int i = 0; i < PlayerConfigutations.getMilk(); i++)
        {
            addItem(new Milk("Milk", 0));
        }
        view = new Inventory();
    }

    public static void handleSlotClick(int slotIndex) {
        // Handle the slot click event
        GameLauncher.log.info("Clicked slot: " + (slotIndex + 1));

        // Perform any desired actions with the clicked slot
        Item clickedItem = backPack[slotIndex];
        if (clickedItem != null) {
            if(clickedItem.getName() == "Milk")
            {
                System.out.println("IT is milk lol");
            }

        } else {
            // Slot is empty
            System.out.println("Slot is empty");
        }
    }

    /*===========================
    *Item manipilation
    ===========================*/
    public boolean addItem(Item item) {
        // Check if the inventory is full
        if (isFull()) {
            return false;
        }

        // Find an empty slot in the backpack
        for (int i = 0; i < backPack.length; i++) {
            if (backPack[i] == null) {
                backPack[i] = item;
                return true;
            }
        }

        return false; // If no empty slot is found
    }

    public boolean removeItem(Item item) {
        // Check if the item is in the backpack
        for (int i = 0; i < backPack.length; i++) {
            if (backPack[i] == item) {
                backPack[i] = null;
                return true;
            }
        }

        return false; // If the item is not found in the backpack
    }

    public boolean haveItem(Item item) {
        // Check if the item is in the backpack
        for (Item value : backPack) {
            if (value == item) {
                return true;
            }
        }

        return false; // If the item is not found in the backpack
    }

    private boolean isFull() {
        // Check if the backpack is full
        for (Item item : backPack) {
            if (item == null) {
                return false; // If there is an empty slot
            }
        }

        return true; // If all slots are filled
    }

    @Override
    public void init() {

    }

    @Override
    public VBox getView() {
        return view;
    }
    @Override
    public void update(double delta) {

    }
}
