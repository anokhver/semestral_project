package cz.cvut.anokhver.menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class InGameMenu extends AMenu {

    protected Button continueButton;
    protected Button exitButton;

    public InGameMenu(){

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(20, 20, 20, 20));

        Label titleLabel = new Label("Night Walk");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        this.getChildren().add(titleLabel);

        continueButton = new Button("Continue");
        this.getChildren().add(continueButton);


        exitButton = new Button("Exit");
        this.getChildren().add(exitButton);

    }


    @Override
    public void init() {
        new InGameMenu();
    }
}
