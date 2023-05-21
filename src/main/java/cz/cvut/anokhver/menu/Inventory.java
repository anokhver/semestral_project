package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import static javafx.scene.paint.Color.*;

/**
 * Inventory view
 * Displays items using images
 */public class Inventory extends AMenu {
    private StackPane[] slotPanes;
    private StackPane[] slotPanesSpecial;
    private int selectedSlotIndex = 0;

    private int selectedSpecialSlotIndex = -1;

    /**
     * Creating standard inventory view
     */
    public Inventory(int backpackSpace) {

        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(BLACK, null, null)));

        createInventoryGridPane(backpackSpace);

        // Create a StackPane and add the inventoryGridPane as its child
        this.scene = new Scene(this, Configuration.getWindowWidth(), Configuration.getWindowHeight());

        // Request focus on the scene to ensure key presses are detected
        this.scene.getRoot().requestFocus();
    }

    /*===========================
     *Visually settings
     ===========================*/

    private void createInventoryGridPane(int backpackSpace) {
        // Clear the current children of the VBox
        this.getChildren().clear();

        // Create the grid pane for the inventory
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create and add slots to the grid pane
        slotPanes = new StackPane[backpackSpace];
        for (int i = 0; i < backpackSpace; i++) {
            int row = i / 3;
            int col = i % 3;

            StackPane slotPane = createSlotPane();
            gridPane.add(slotPane, col, row);

            slotPanes[i] = slotPane; // Add the slot pane to the slotPanes array
        }

        //special slots
        slotPanesSpecial = new StackPane[3];
        for (int i = 0; i < 3; i++) {
            int col = backpackSpace / 3;

            StackPane slotPane = createSlotPane();
            gridPane.add(slotPane, col, i);
            GridPane.setMargin(slotPane, new Insets(0, 0, 0, 50));

            slotPanesSpecial[i] = slotPane; // Add the slot pane to the slotPanes array
        }

        // Add the grid pane to the VBox
        this.setSpacing(10);
        this.getChildren().addAll(createInstructionsText(), gridPane, createPlayerStatsView());
    }

    private Text createInstructionsText() {
        Text instructionsText = new Text("CLOSE INVENTORY: E || USE ITEM: ENTER || CHANGE TO THE EQUIPMENT PART & BACK: Q");
        instructionsText.setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 25));
        instructionsText.setTextAlignment(TextAlignment.CENTER);
        instructionsText.setFill(Color.WHITE);
        return instructionsText;
    }

    private StackPane createSlotPane() {
        StackPane slotPane = new StackPane();
        slotPane.setPrefSize(100, 100);
        slotPane.setBackground(new Background(new BackgroundFill(ANTIQUEWHITE, null, null)));

        return slotPane;
    }

    private VBox createPlayerStatsView() {
        VBox playerStatsBox = new VBox();
        playerStatsBox.setSpacing(10);
        playerStatsBox.setPadding(new Insets(10));

        Text titleText = new Text("Player Stats");
        titleText.setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 25));
        titleText.setTextAlignment(TextAlignment.RIGHT);
        titleText.setFill(Color.WHITE);

        Text damageText = new Text("Damage: ");
        damageText.setFill(Color.WHITE);

        Text speedDamageText = new Text("Speed Damage: ");
        speedDamageText.setFill(Color.WHITE);

        Text damageRadiusText = new Text("Damage Radius: ");
        damageRadiusText.setFill(Color.WHITE);

        Text walkSpeedText = new Text("Speed walk: ");
        walkSpeedText.setFill(Color.WHITE);


        if (GameLogic.getPlayer() != null) {
            damageText.setText(damageText.getText() + GameLogic.getPlayer().getDamage());
            speedDamageText.setText(speedDamageText.getText() + GameLogic.getPlayer().getSpeed_damage());
            damageRadiusText.setText(damageRadiusText.getText() + GameLogic.getPlayer().getDamage_radius());
            walkSpeedText.setText(walkSpeedText.getText() + GameLogic.getPlayer().getWalk_speed());
        }

        playerStatsBox.getChildren().addAll(titleText, damageText, speedDamageText, damageRadiusText, walkSpeedText);

        return playerStatsBox;
    }

    /**
     * Updating inventory view based on the backpack
     * (adding textures on the right places, highlighting chosen slots)
     *
     * @param backpack player's backpack
     */
    public void updateInventoryView(Item[] backpack) {
        // Update the inventory view to reflect the selected slot
        for (int i = 0; i < backpack.length; i++) {
            StackPane slotPane = slotPanes[i];
            Item item = backpack[i];

            updateSlotStyle(slotPane, i == selectedSlotIndex);

            slotPane.getChildren().clear();

            if (item != null) {
                ImageView imageView = createItemImageView(item);
                slotPane.getChildren().add(imageView);
            }
        }

        // Update the additional slots (collar, hat, and bonus)
        updateAdditionalSlot(0, GameLogic.getPlayer().getInventory().getYourCollar());
        updateAdditionalSlot(1, GameLogic.getPlayer().getInventory().getYourHat());
        updateAdditionalSlot(2, GameLogic.getPlayer().getInventory().getYourBonus());

        this.getChildren().set(2, createPlayerStatsView()); // Assuming the player stats VBox is at index 2
    }

    private ImageView createItemImageView(Item item) {
        ImageView imageView = new ImageView(item.getTexture());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        return imageView;
    }

    private void updateAdditionalSlot(int index, Item item) {
        StackPane slotPane = slotPanesSpecial[index];
        slotPane.getChildren().clear();


        updateSlotStyle(slotPane, index == selectedSpecialSlotIndex);

        if (item != null) {
            ImageView imageView = createItemImageView(item);
            slotPane.getChildren().add(imageView);
        }
    }


    private void updateSlotStyle(StackPane slotPane, boolean isSelected) {
        Color borderColor = isSelected ? BLACK : ANTIQUEWHITE;
        Color backgroundColor = isSelected ? ROSYBROWN : ANTIQUEWHITE;

        BorderStroke borderStroke = new BorderStroke(borderColor, BorderStrokeStyle.DASHED, null, new BorderWidths(3));
        Border border = new Border(borderStroke);
        slotPane.setBorder(border);
        slotPane.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
    }

    /*===================
    /Getters & Setters
    =====================*/

    public int getSelectedSlotIndex() {
        return this.selectedSlotIndex;
    }

    public void setSelectedSlotIndex(int selectedSlotIndex) {
        this.selectedSlotIndex = selectedSlotIndex;
    }

    @Override
    public void update_menu() {
        this.updateInventoryView(GameLogic.getPlayer().getInventory().getBackPack());
    }

    public int getSelectedSpecialSlotIndex() {
        return selectedSpecialSlotIndex;
    }

    public void setSelectedSpecialSlotIndex(int selectedSpecialSlotIndex) {
        this.selectedSpecialSlotIndex = selectedSpecialSlotIndex;
    }
}
