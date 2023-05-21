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

    private Boolean isRegularInv = true;
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
     * @param hat - if equipped
     * @param collar -  if equipped
     * @param bonus - if equipped
     * @param count_milk -
     * @param space - space in inventory for items
     * @param hasInvBonus - if just in inventory
     * @param hasInvHat - if just in inventory
     * @param hasInvCollar - if just in inventory
     */
    public InventoryController(Boolean hat, Boolean collar, Boolean bonus, Integer count_milk,
                               Integer space, Boolean hasInvHat, Boolean hasInvCollar, Boolean hasInvBonus) {
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
    }

    /*===========================
    *View setting
    ===========================*/

    public void setViewStart() {
        Inventory temp_view = new Inventory(backPackSpace);

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
                    case Q:
                        handleChangeInventory();
                        break;
                    default:
                        break;
                }
        });

        setView(temp_view);
    }

    private void handleChangeInventory(){
        isRegularInv = !isRegularInv;
        if(isRegularInv)
        {
            ((Inventory) getView()).setSelectedSlotIndex(0);
            ((Inventory) getView()).setSelectedSpecialSlotIndex(-1);
        }
        else
        {
            ((Inventory) getView()).setSelectedSlotIndex(-1);
            ((Inventory) getView()).setSelectedSpecialSlotIndex(0);
        }
        getView().update_menu();

    }

    private void handleEnterKeyPress() {
        Item clickedItem = null;
        // Perform use item with the clicked slot
        if(isRegularInv) {
            GameLauncher.log.log(Level.INFO, "Clicked slot: {0}{1}", new Object[]{((Inventory) getView()).getSelectedSlotIndex(), 1});

            clickedItem = backPack[((Inventory) getView()).getSelectedSlotIndex()];

            if (clickedItem != null) {
                GameLauncher.log.log(Level.INFO, "Hero used:{0}", clickedItem.getName());
                clickedItem.useItem(GameLogic.getPlayer());
                getView().update_menu();
            } else {
                // Slot is empty
                GameLauncher.log.info("Slot is empty");
            }
        }
        else {
            GameLauncher.log.log(Level.INFO, "Clicked slot equipment: {0}{1}", new Object[]{((Inventory) getView()).getSelectedSpecialSlotIndex(), 1});
            // Remove the equipped item from the slot
            switch (((Inventory) getView()).getSelectedSpecialSlotIndex()){
                case 0:
                    clickedItem = yourCollar;
                    setYourCollar(null);
                    break;
                case 1:
                    clickedItem = yourHat;
                    setYourHat(null);
                    break;
                case 2:
                    clickedItem = yourBonus;
                    setYourBonus(null);
                    break;
                default:
                    break;
            }
            if (clickedItem != null) {
                GameLauncher.log.log(Level.INFO, "Hero used:{0}", clickedItem.getName());
                clickedItem.UnUseItem(GameLogic.getPlayer());
                addItem(clickedItem);
                getView().update_menu();
            } else {
                // Slot is empty
                GameLauncher.log.info("Slot is empty");
            }
        }


        getView().update_menu();
    }

    private void handleArrowKeyPress(KeyCode keyCode) {
        int selectedSlotIndex;
        if(isRegularInv) {
            selectedSlotIndex = ((Inventory) getView()).getSelectedSlotIndex();
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

        }
        else {
            selectedSlotIndex = ((Inventory) getView()).getSelectedSpecialSlotIndex();
            switch (keyCode) {
                case UP:
                    if (selectedSlotIndex > 0) {
                        selectedSlotIndex -= 1;
                    }
                    break;
                case DOWN:
                    if (selectedSlotIndex < 2) {
                        selectedSlotIndex += 1;
                    }
                    break;
            }
            ((Inventory) getView()).setSelectedSpecialSlotIndex(selectedSlotIndex);
        }
        // Update the inventory view to reflect the selected slot
        getView().update_menu();
    }

    /*===========================
    *Item manipilation
    ===========================*/
    public void addItem(Item item) {
        // Check if the inventory is full
        if (isFull() || item == null) {
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

    public Item[] getBackPack() {
        return backPack;
    }

    public static void setBackPack(Item[] backPack) {
        InventoryController.backPack = backPack;
    }
    @Override
    public void update(double delta) {

    }
}
