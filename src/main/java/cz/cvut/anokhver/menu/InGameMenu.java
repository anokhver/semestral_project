package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.additional.Configuration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * In game menu view
 *
 * @author Veronika
 */
public class InGameMenu extends AMenu {

    protected Button continueButton;
    protected Button exitButton;
    protected Button saveButton;
    protected Button exitMainMenu;

    /**
     * Creating standard in game menu view with 4 buttons
     */
    public InGameMenu() {

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20, 20, 20, 20));

        Label titleLabel = new Label("Menu");
        titleLabel.setFont(Font.font("Impact", FontWeight.BOLD, 100));
        this.getChildren().add(titleLabel);

        continueButton = new Button("Continue Game");
        continueButton.setFont(Font.font("Impact", FontWeight.BOLD, 36));
        this.getChildren().add(continueButton);

        saveButton = new Button("Save Game");
        saveButton.setFont(Font.font("Impact", FontWeight.BOLD, 36));
        this.getChildren().add(saveButton);

        exitMainMenu = new Button("Exit to main menu");
        exitMainMenu.setFont(Font.font("Impact", FontWeight.BOLD, 36));
        this.getChildren().add(exitMainMenu);

        exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Impact", FontWeight.BOLD, 36));
        this.getChildren().add(exitButton);

        scene = new Scene(this, Configuration.getWindowWidth(), Configuration.getWindowHeight());
    }


    /*===================
    /Getters & Setters
    =====================*/
    public Button getContinueButton() {
        return continueButton;
    }

    public void setContinueButton(Button continueButton) {
        this.continueButton = continueButton;
    }

    public Button getExitButton() {
        return exitButton;
    }

    public void setExitButton(Button exitButton) {
        this.exitButton = exitButton;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Button getExitMainMenu() {
        return exitMainMenu;
    }

    public void setExitMainMenu(Button exitMainMenu) {
        this.exitMainMenu = exitMainMenu;
    }

    @Override
    public void update_menu() {

    }
}
