package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.additional.Configuration;
import cz.cvut.anokhver.additional.PlayerConfigutations;
import cz.cvut.anokhver.contollers.InventoryController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Inventory extends AMenu{

    public Inventory(){

        createInventoryGridPane(PlayerConfigutations.getBackPackSpace());
        this.setAlignment(Pos.CENTER);

        // Create a StackPane and add the inventoryGridPane as its child
        scene = new Scene(this, Configuration.getWindowWidth(), Configuration.getWindowHeight());
        scene.setFill(Color.BLACK);

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
        for (int i = 0; i < backpackSpace; i++) {
            int row = i / 3;
            int col = i % 3;

            Label slotLabel = new Label("Slot " + (i + 1));
            slotLabel.setStyle("-fx-border-color: black;");
            slotLabel.setPrefSize(100, 100);
            gridPane.add(slotLabel, col, row);

            // Add mouse click event handler to each slot label
            int slotIndex = i;
            slotLabel.setOnMouseClicked(event -> InventoryController.handleSlotClick(slotIndex));
        }

        // Add the grid pane to the VBox
        this.getChildren().add(gridPane);
    }


    @Override
    public void init() {

    }
}
