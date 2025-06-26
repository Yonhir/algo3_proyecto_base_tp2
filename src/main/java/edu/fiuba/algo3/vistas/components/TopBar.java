package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.HBox;

public class TopBar extends HBox {
    private GameButton menuButton;

    public TopBar() {
        super();
        setupTopBar();
    }
    
    private void setupTopBar() {
        setAlignment(javafx.geometry.Pos.TOP_LEFT);
        setStyle("-fx-background-color: #FF6B6B; -fx-border-color: #FF0000; -fx-border-width: 2px;"); // Red background
        
        // Create menu button at the top with dynamic size
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
