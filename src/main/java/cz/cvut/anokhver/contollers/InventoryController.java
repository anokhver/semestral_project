package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.additional.PlayerConfigurations;
import cz.cvut.anokhver.items.*;
import cz.cvut.anokhver.menu.Inventory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class InventoryController extends AContoller {
    private static Item[] backPack;
    private Collar yourCollar = null;
    private Hat yourHat = null;
    private Bonus yourBonus = null;

    private Inventory view;

    public InventoryController() {
        // Initialize the inventory
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
        view = new Inventory();

        view.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.UP || keyCode == KeyCode.DOWN || keyCode == KeyCode.LEFT || keyCode == KeyCode.RIGHT) {
                handleArrowKeyPress(keyCode);
            } else if (keyCode == KeyCode.ENTER) {
                handleEnterKeyPress();
            }
        });
        view.updateInventoryView(backPack);
    }

    private void handleEnterKeyPress() {
        // Handle the slot click event
        GameLauncher.log.info("Clicked slot: " + (view.getSelectedSlotIndex() + 1));

        // Perform any desired actions with the clicked slot
        Item clickedItem = backPack[view.getSelectedSlotIndex()];
        if (clickedItem != null) {
           System.out.println(clickedItem.getName());
        } else {
            // Slot is empty
            System.out.println("Slot is empty");
        }
    }

    private void handleArrowKeyPress(KeyCode keyCode) {
        int selectedSlotIndex = view.getSelectedSlotIndex();
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
        view.setSelectedSlotIndex(selectedSlotIndex);
        // Update the inventory view to reflect the selected slot
        view.updateInventoryView(backPack);
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
