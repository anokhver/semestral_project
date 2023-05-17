package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.GameLogic;
import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.PlayerConfigurations;
import cz.cvut.anokhver.contollers.InventoryController;
import cz.cvut.anokhver.items.Item;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class Inventory extends AMenu {
    private GridPane gridPane;
    private Region[] slotRegions;
    private int selectedSlotIndex = 0;

    public Inventory() {
        createInventoryGridPane(PlayerConfigurations.getBackPackSpace());
        this.setAlignment(Pos.CENTER);

        // Create a StackPane and add the inventoryGridPane as its child
        scene = new Scene(this, Configuration.getWindowWidth(), Configuration.getWindowHeight());
        scene.setFill(Color.BLACK);

        // Request focus on the scene to ensure key presses are detected
        scene.getRoot().requestFocus();
    }

    /*===========================
     *Visually settings
     ===========================*/
    private void createInventoryGridPane(int backpackSpace) {
        // Clear the current children of the VBox
        this.getChildren().clear();

        // Create the grid pane for the inventory
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create and add slots to the grid pane
        slotRegions = new Region[backpackSpace + 3];
        for (int i = 0; i < backpackSpace; i++) {
            int row = i / 3;
            int col = i % 3;

            Region slotRegion = new Region();
            slotRegion.setStyle("-fx-background-color: white;");
            slotRegion.setPrefSize(100, 100);
            gridPane.add(slotRegion, col, row);

            slotRegions[i] = slotRegion; // Add the slot region to the slotRegions array
        }

        for (int i = 0; i < 3; i++) {
            int row = i;
            int col = backpackSpace/3;

            Region slotRegion = new Region();
            slotRegion.setStyle("-fx-background-color: white;");
            slotRegion.setPrefSize(100, 100);
            gridPane.add(slotRegion, col, row);

            slotRegions[backpackSpace + i] = slotRegion; // Add the slot region to the slotRegions array
        }



        Text instructionsText = new Text("CLOSE INVENTORY: E  USE ITEM: ENTER");
        instructionsText.setFill(Color.RED);
        instructionsText.setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 25));
        instructionsText.setTextAlignment(TextAlignment.CENTER);
        // Add the grid pane to the VBox
        this.setSpacing(10);

        this.getChildren().addAll(instructionsText, gridPane, createPlayerStatsView());
    }

    private VBox createPlayerStatsView() {
        VBox playerStatsBox = new VBox();
        playerStatsBox.setSpacing(10);
        playerStatsBox.setPadding(new Insets(10));

        Text titleText = new Text("Player Stats");
        titleText.setFill(Color.RED);
        titleText.setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 25));
        titleText.setTextAlignment(TextAlignment.RIGHT);

        Text damageText = new Text("Damage: " + GameLogic.getPlayer().getDamage());
        Text speedDamageText = new Text("Speed Damage: " + GameLogic.getPlayer().getSpeed_damage());
        Text damageRadiusText = new Text("Damage Radius: " + GameLogic.getPlayer().getDamage_radius());
        Text walkSpeedText = new Text("Speed walk" + GameLogic.getPlayer().getWalk_speed());

        playerStatsBox.getChildren().addAll(titleText, damageText, speedDamageText, damageRadiusText, walkSpeedText);

        return playerStatsBox;
    }

    public void updateInventoryView(Item[] backPack) {
        // Update the inventory view to reflect the selected slot
        for (int i = 0; i < backPack.length; i++) {
            Region slotRegion = slotRegions[i];
            Item item = backPack[i];

            if (i == selectedSlotIndex) {
                slotRegion.setStyle("-fx-border-color: white; -fx-background-color: #ea6969;");
            } else {
                slotRegion.setStyle("-fx-border-color: black; -fx-background-color: white;");
            }

            if (item != null) {
                String imageUrl = item.getTexture().impl_getUrl();
                String imageStyle = "-fx-background-image: url('" + imageUrl + "'); -fx-background-size: cover;";
                slotRegion.setStyle(slotRegion.getStyle() + imageStyle);
            }

            // Display collar, hat, and bonus in the additional slots
            if (GameLogic.getPlayer().getInventory().getYourCollar() != null) {
                Region slotRegionAdditional = slotRegions[backPack.length];
                String imageUrl = GameLogic.getPlayer().getInventory().getYourCollar().getTexture().impl_getUrl();
                String imageStyle = "-fx-background-image: url('" + imageUrl + "'); -fx-background-size: cover;";
                slotRegionAdditional.setStyle("-fx-border-color: black; -fx-background-color: white;" + imageStyle);
            }

            if (GameLogic.getPlayer().getInventory().getYourHat() != null) {
                Region slotRegionAdditional = slotRegions[backPack.length + 1];
                String imageUrl = GameLogic.getPlayer().getInventory().getYourHat() .getTexture().impl_getUrl();
                String imageStyle = "-fx-background-image: url('" + imageUrl + "'); -fx-background-size: cover;";
                slotRegionAdditional.setStyle("-fx-border-color: black; -fx-background-color: white;" + imageStyle);
            }

            if (GameLogic.getPlayer().getInventory().getYourBonus()  != null) {
                Region slotRegionAdditional = slotRegions[backPack.length + 2];
                String imageUrl = GameLogic.getPlayer().getInventory().getYourBonus().getTexture().impl_getUrl();
                String imageStyle = "-fx-background-image: url('" + imageUrl + "'); -fx-background-size: cover;";
                slotRegionAdditional.setStyle("-fx-border-color: black; -fx-background-color: white;" + imageStyle);
            }
        }

        this.getChildren().set(2,  createPlayerStatsView()); // Assuming the player stats VBox is at index 2

    }


    public void setSelectedSlotIndex(int selectedSlotIndex) {
        this.selectedSlotIndex = selectedSlotIndex;
    }
    public int getSelectedSlotIndex (){
        return  this.selectedSlotIndex;
    }

    @Override
    public void init() {

    }

    @Override
    public void update_menu() {
        this.updateInventoryView(InventoryController.getBackPack());
    }

}
