package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.views.components.cardcomponent.UIDeck;
import edu.fiuba.algo3.views.components.cardcomponent.UIDiscardPile;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class RightColumn extends VBox {
    private static final double SPACER_HEIGHT_RATIO = 0.4;
    
    private final UIDeck playerUIDeck, opponentUIDeck;
    private final UIDiscardPile playerUIDiscardPile, opponentUIDiscardPile;
    
    public RightColumn(UIDeck playerUIDeck, UIDeck opponentUIDeck,
                       UIDiscardPile playerUIDiscardPile, UIDiscardPile opponentUIDiscardPile,
                       PassTurnButton passTurnButton) {
        super();
        this.playerUIDeck = playerUIDeck;
        this.opponentUIDeck = opponentUIDeck;
        this.playerUIDiscardPile = playerUIDiscardPile;
        this.opponentUIDiscardPile = opponentUIDiscardPile;
        setupRightColumn(passTurnButton);
    }
    
    private void setupRightColumn(PassTurnButton passTurnButton) {
        setAlignment(javafx.geometry.Pos.TOP_CENTER);
        setStyle("-fx-background-color: #BC8F8F; -fx-border-color: #8B4513; -fx-border-width: 3px;");
        
        HBox opponentCardsContainer = new HBox();
        opponentCardsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        opponentCardsContainer.setStyle("-fx-background-color: #D2B48C; -fx-border-color: #A0522D; -fx-border-width: 1px;");
        opponentCardsContainer.getChildren().addAll(opponentUIDeck, opponentUIDiscardPile);
        
        HBox playerCardsContainer = new HBox();
        playerCardsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        playerCardsContainer.setStyle("-fx-background-color: #D2B48C; -fx-border-color: #A0522D; -fx-border-width: 1px;");
        playerCardsContainer.getChildren().addAll(playerUIDeck, playerUIDiscardPile);
        
        javafx.scene.layout.Region opponentSpacer = new javafx.scene.layout.Region();
        opponentSpacer.prefHeightProperty().bind(heightProperty().multiply(SPACER_HEIGHT_RATIO));
        
        javafx.scene.layout.Region playerSpacer = new javafx.scene.layout.Region();
        playerSpacer.prefHeightProperty().bind(heightProperty().multiply(SPACER_HEIGHT_RATIO));
        
        VBox.setVgrow(opponentCardsContainer, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(playerCardsContainer, javafx.scene.layout.Priority.ALWAYS);
        getChildren().addAll(opponentCardsContainer, opponentSpacer, passTurnButton, playerSpacer, playerCardsContainer);
    }
} 