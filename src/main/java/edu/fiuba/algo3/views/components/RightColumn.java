package edu.fiuba.algo3.views.components;

import edu.fiuba.algo3.views.components.cardcomponent.UIDeck;
import edu.fiuba.algo3.views.components.cardcomponent.UIDiscardPile;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class RightColumn extends VBox {
    private static final double SPACER_HEIGHT_RATIO = 0.8;
    
    private final UIDeck playerUIDeck, opponentUIDeck;
    private final UIDiscardPile playerUIDiscardPile, opponentUIDiscardPile;
    private CardInfoView cardViewer;

    public RightColumn(UIDeck playerUIDeck, UIDeck opponentUIDeck,
                       UIDiscardPile playerUIDiscardPile, UIDiscardPile opponentUIDiscardPile) {
        super();
        this.playerUIDeck = playerUIDeck;
        this.opponentUIDeck = opponentUIDeck;
        this.playerUIDiscardPile = playerUIDiscardPile;
        this.opponentUIDiscardPile = opponentUIDiscardPile;
        setupRightColumn();
    }
    
    private void setupRightColumn() {
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

        cardViewer = new CardInfoView();
        cardViewer.prefHeightProperty().bind(heightProperty().multiply(SPACER_HEIGHT_RATIO));
        
        VBox.setVgrow(opponentCardsContainer, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(playerCardsContainer, javafx.scene.layout.Priority.ALWAYS);
        getChildren().addAll(opponentCardsContainer, cardViewer, playerCardsContainer);
    }

    public CardInfoView getCardViewer() { return cardViewer;}
} 