package cz.cvut.anokhver.contollers;

import cz.cvut.anokhver.GameLauncher;
import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.PlayerConfigurations;
import cz.cvut.anokhver.items.*;
import cz.cvut.anokhver.menu.Inventory;
import javafx.scene.input.KeyCode;

import java.util.logging.Level;

public final class InventoryController extends AContoller {

    private static Item[] backPack;
    private Integer backPackSpace;
    private Collar yourCollar = null;
    private Hat yourHat = null;
    private Bonus yourBonus = null;

   /*===========================
   *Setting basic
   ===========================*/

    /**
     * Create standard inventory controller
     * add items from player configuration
     * set view
     */

    public InventoryController() {
        // Initialize the inventory


        backPackSpace = PlayerConfigurations.getBackPackSpace();
        backPack = new Item[backPackSpace]; // Set the size of the backpack
        if (PlayerConfigurations.getItem("Collar")) {
            addItem(new Collar("Collar"));
        }
        if (PlayerConfigurations.getItem("Hat")) {
            addItem(new Hat("Hat"));
        }
        if (PlayerConfigurations.getItem("Bonus")) {
            addItem(new Bonus("Bonus"));

        }
        for (int i = 0; i < PlayerConfigurations.getMilk(); i++) {
            addItem(new Milk("Milk"));
        }

        setViewStart();
    }

    /**
     * Create inventory controller from parameters
     * usually used to load from saves
     *
     * @param hat
     * @param collar
     * @param bonus
     * @param count_milk
     * @param space
     * @param hasInvBonus
     * @param hasInvHat
     * @param hasInvCollar
     */
    public InventoryController(Boolean hat, Boolean collar, Boolean bonus, Integer count_milk,
                               Integer space, Boolean hasInvBonus, Boolean hasInvHat, Boolean hasInvCollar) {
        // Initialize the inventory
        backPackSpace = space;
        backPack = new Item[backPackSpace]; // Set the size of the backpack
        if (hasInvCollar) {
            addItem(new Collar("Collar"));
        }
        if (hasInvHat) {
            addItem(new Hat("Hat"));
        }
        if (hasInvBonus) {
            addItem(new Bonus("Bonus"));

        }

        if (collar) {
            yourCollar = new Collar("Collar");
        }
        if (hat) {
            yourHat = new Hat("Hat");
        }
        if (bonus) {
            yourBonus = new Bonus("Bonus");

        }

        for (int i = 0; i < count_milk; i++) {
            addItem(new Milk("Milk"));
        }

        setViewStart();
    }

    /*===========================
    *View setting
    ===========================*/

    public static Item[] getBackPack() {
        return backPack;
    }

    public static void setBackPack(Item[] backPack) {
        InventoryController.backPack = backPack;
    }

    private void setViewStart() {
        Inventory temp_view = new Inventory();

        temp_view.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (null != keyCode) //if arrows pressed
                switch (keyCode) {
                    case UP:
                    case DOWN:
                    case LEFT:
                    case RIGHT:
                        handleArrowKeyPress(keyCode);
                        break;
                    case ENTER:
                        handleEnterKeyPress();
                        break;
                    case E:
                        GameLogic.renewGame();
                        break;
                    default:
                        break;
                }
        });

        setView(temp_view);
    }

    private void handleEnterKeyPress() {
        GameLauncher.log.log(Level.INFO, "Clicked slot: {0}{1}", new Object[]{((Inventory) getView()).getSelectedSlotIndex(), 1});

        // Perform use item with the clicked slot
        Item clickedItem = backPack[((Inventory) getView()).getSelectedSlotIndex()];
        if (clickedItem != null) {
            GameLauncher.log.log(Level.INFO, "Hero used:{0}", clickedItem.getName());
            clickedItem.useItem(GameLogic.getPlayer());
            getView().update_menu();
        } else {
            // Slot is empty
            GameLauncher.log.info("Slot is empty");
        }
    }

    private void handleArrowKeyPress(KeyCode keyCode) {
        int selectedSlotIndex = ((Inventory) getView()).getSelectedSlotIndex();
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
        getView().update_menu();
    }

    /*===========================
    *Item manipilation
    ===========================*/
    public void addItem(Item item) {
        // Check if the inventory is full
        if (isFull()) {
            return;
        }

        // Find an empty slot in the backpack
        for (int i = 0; i < backPack.length; i++) {
            if (backPack[i] == null) {
                backPack[i] = item;
                return;
            }
        }

    }

    public void removeItem(Item item) {
        // Check if the item is in the backpack
        for (int i = 0; i < backPack.length; i++) {
            if (backPack[i] == item) {
                backPack[i] = null;
                return;
            }
        }

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

    /*===========================
    *Getter & Setter
    ===========================*/
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

    public Integer getBackPackSpace() {
        return backPackSpace;
    }

    public void setBackPackSpace(Integer backPackSpace) {
        this.backPackSpace = backPackSpace;
    }

    @Override
    public void update(double delta) {

    }
}
