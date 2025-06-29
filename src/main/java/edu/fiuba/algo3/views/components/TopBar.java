package edu.fiuba.algo3.views.components;

import javafx.scene.layout.HBox;

public class TopBar extends HBox {
    private GameButton menuButton;

    public TopBar() {
        super();
        setupTopBar();
    }
    
    private void setupTopBar() {
        setAlignment(javafx.geometry.Pos.TOP_LEFT);
        setStyle("-fx-background-color: #A0522D; -fx-border-color: #8B4513; -fx-border-width: 2px;");
        
        menuButton = GameButton.createMenuButton();
        menuButton.setPickOnBounds(false);
        menuButton.setCancelButton(true);
        menuButton.prefHeightProperty().bind(heightProperty().multiply(0.9));
        getChildren().add(menuButton);
    }

    public GameButton getMenuButton() {
        return this.menuButton;
    }
}
