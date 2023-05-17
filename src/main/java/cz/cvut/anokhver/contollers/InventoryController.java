package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.PlayerConfigurations;
import cz.cvut.anokhver.items.*;
import cz.cvut.anokhver.menu.Inventory;
import javafx.scene.input.KeyCode;

public class InventoryController extends AContoller {

    private static Item[] backPack;
    private Collar yourCollar = null;
    private Hat yourHat = null;
    private Bonus yourBonus = null;

    public InventoryController() {
        // Initialize the inventory

        Inventory temp_view = new Inventory();

        backPack = new Item[PlayerConfigurations.getBackPackSpace()]; // Set the size of the backpack
        if (PlayerConfigurations.getItem("Collar"))
        {
            addItem(new Collar("Collar", 0));
        }
        if (PlayerConfigurations.getItem("Hat"))
        {
            addItem(new Hat("Hat", 0));
        }
        if (PlayerConfigurations.getItem("Bonus"))
        {
            addItem(new Bonus("Bonus", 0));

        }
        for(int i = 0; i < PlayerConfigurations.getMilk(); i++)
        {
            addItem(new Milk("Milk", 0));
        }

        temp_view.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT) {
                handleArrowKeyPress(keyCode);
            } else if (keyCode == KeyCode.ENTER) {
                handleEnterKeyPress();
            }
        });

        setView(temp_view);
    }

    private void handleEnterKeyPress() {
        // Handle the slot click event
        GameLauncher.log.info("Clicked slot: " + (((Inventory) getView()).getSelectedSlotIndex() + 1));

        // Perform any desired actions with the clicked slot
        Item clickedItem = backPack[((Inventory) getView()).getSelectedSlotIndex()];
        if (clickedItem != null) {
           GameLauncher.log.info("Hero used:" + clickedItem.getName());
           clickedItem.useItem(GameLogic.getPlayer());
           ((Inventory) getView()).updateInventoryView(backPack);
        } else {
            // Slot is empty
            System.out.println("Slot is empty");
        }
    }

    private void handleArrowKeyPress(KeyCode keyCode) {
        int selectedSlotIndex =((Inventory) getView()).getSelectedSlotIndex();
        // Handle arrow key presses for navigating through the inventory
        switch (keyCode) {
            case UP:
                if (selectedSlotIndex >= 3) {
                    selectedSlotIndex -= 3;
                }
                break;
            case DOWN:
                if (selectedSlotIndex < backPack.length - 3) {
                    selectedSlotIndex += 3;
                }
                break;
            case LEFT:
                if (selectedSlotIndex % 3 != 0) {
                    selectedSlotIndex--;
                }
                break;
            case RIGHT:
                if ((selectedSlotIndex + 1) % 3 != 0 && selectedSlotIndex < backPack.length - 1) {
                    selectedSlotIndex++;
                }
                break;
        }
        ((Inventory) getView()).setSelectedSlotIndex(selectedSlotIndex);
        // Update the inventory view to reflect the selected slot
        ((Inventory) getView()).updateInventoryView(backPack);
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
    public void update(double delta) {

    }

    public Collar getYourCollar() {
        return yourCollar;
    }

    public void setYourCollar(Collar yourCollar) {
        this.yourCollar = yourCollar;
    }

    public Hat getYourHat() {
        return yourHat;
    }

    public void setYourHat(Hat yourHat) {
        this.yourHat = yourHat;
    }

    public Bonus getYourBonus() {
        return yourBonus;
    }

    public void setYourBonus(Bonus yourBonus) {
        this.yourBonus = yourBonus;
    }
    public static Item[] getBackPack() {
        return backPack;
    }

    public static void setBackPack(Item[] backPack) {
        InventoryController.backPack = backPack;
    }
}
