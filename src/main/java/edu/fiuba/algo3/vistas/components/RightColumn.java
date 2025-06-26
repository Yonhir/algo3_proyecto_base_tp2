package edu.fiuba.algo3.vistas.components;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class RightColumn extends VBox {
    
    private final Deck playerDeck, opponentDeck;
    private final DiscardPile playerDiscardPile, opponentDiscardPile;
    
    public RightColumn(Deck playerDeck, Deck opponentDeck, 
                      DiscardPile playerDiscardPile, DiscardPile opponentDiscardPile) {
        super();
        this.playerDeck = playerDeck;
        this.opponentDeck = opponentDeck;
        this.playerDiscardPile = playerDiscardPile;
        this.opponentDiscardPile = opponentDiscardPile;
        setupRightColumn();
    }
    
    private void setupRightColumn() {
        setAlignment(javafx.geometry.Pos.TOP_CENTER);
        setStyle("-fx-background-color: #BB8FCE; -fx-border-color: #8E44AD; -fx-border-width: 3px;"); // Light purple background
        
        // Create horizontal container for opponent deck and discard pile (top)
        HBox opponentCardsContainer = new HBox(20); // 20px spacing between deck and discard
        opponentCardsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        opponentCardsContainer.setStyle("-fx-background-color: #E8E8E8; -fx-border-color: #CCCCCC; -fx-border-width: 1px;");
        opponentCardsContainer.getChildren().addAll(opponentDeck, opponentDiscardPile);
        
        // Create horizontal container for player deck and discard pile (bottom)
        HBox playerCardsContainer = new HBox(20); // 20px spacing between deck and discard
        playerCardsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        playerCardsContainer.setStyle("-fx-background-color: #E8E8E8; -fx-border-color: #CCCCCC; -fx-border-width: 1px;");
        playerCardsContainer.getChildren().addAll(playerDeck, playerDiscardPile);
        
        // Create spacing regions
        javafx.scene.layout.Region opponentSpacer = new javafx.scene.layout.Region();
        opponentSpacer.prefHeightProperty().bind(heightProperty().multiply(0.4)); // 40% of column height
        
        javafx.scene.layout.Region playerSpacer = new javafx.scene.layout.Region();
        playerSpacer.prefHeightProperty().bind(heightProperty().multiply(0.4)); // 40% of column height
        
        // Add both containers to the right column with spacing
        VBox.setVgrow(opponentCardsContainer, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(playerCardsContainer, javafx.scene.layout.Priority.ALWAYS);
        getChildren().addAll(opponentCardsContainer, opponentSpacer, playerSpacer, playerCardsContainer);
    }
} 