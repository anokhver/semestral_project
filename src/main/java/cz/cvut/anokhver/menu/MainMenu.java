package cz.cvut.anokhver.menu;

import cz.cvut.anokhver.additional.Configuration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu extends AMenu {

    protected Button startButton;
    protected Button loadButton;
    protected Button exitButton;


    public MainMenu(){
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20, 20, 20, 20));

        Label titleLabel = new Label("Night Walk");
        titleLabel.setFont(Font.font("Impact", FontWeight.BOLD, 100));
        this.getChildren().add(titleLabel);

        startButton = new Button("New Game");
        startButton.setFont(Font.font("Impact", FontWeight.BOLD, 36));
        this.getChildren().add(startButton);

        loadButton = new Button("Load Game");
        loadButton.setFont(Font.font("Impact", FontWeight.BOLD, 36));

        this.getChildren().add(loadButton);

        exitButton = new Button("Exit");
        exitButton.setFont(Font.font("Impact", FontWeight.BOLD, 36));

        this.getChildren().add(exitButton);

        scene = new Scene(this, Configuration.getWindowWidth(), Configuration.getWindowHeight());


    }

    public Button getExitButton() {
        return exitButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getLoadButton() {
        return loadButton;
    }

    @Override
    public void init() {
        new MainMenu();
    }

    @Override
    public void update_menu() {

    }
}
