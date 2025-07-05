package edu.fiuba.algo3.views;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.sections.rows.CloseCombat;
import edu.fiuba.algo3.models.sections.rows.Ranged;
import edu.fiuba.algo3.models.sections.rows.Siege;
import edu.fiuba.algo3.models.sections.SpecialZone;
import edu.fiuba.algo3.views.components.*;
import edu.fiuba.algo3.views.components.cardcomponent.UIDeck;
import edu.fiuba.algo3.views.components.cardcomponent.UIDiscardPile;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameView extends StackPane {
    private final UIHand UIHandList;
    private final UIRow opponentCloseCombat, opponentRanged, opponentSiege;
    private final UIRow playerCloseCombat, playerRanged, playerSiege;
    private final UISpecialZone UISpecialZoneList;
    private final UIDeck playerUIDeck, opponentUIDeck;
    private final UIDiscardPile playerUIDiscardPile, opponentUIDiscardPile;
    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;
    
    private Scene scene;

    public GameView(Hand currentPlayerHand, 
                   Deck player1Deck, Deck player2Deck,
                   DiscardPile player1DiscardPile, DiscardPile player2DiscardPile,
                   CloseCombat player1CloseCombat, Ranged player1Ranged, Siege player1Siege,
                   CloseCombat player2CloseCombat, Ranged player2Ranged, Siege player2Siege,
                   SpecialZone specialZone) {
        
        UIHandList = new UIHand(currentPlayerHand);
        opponentCloseCombat = new UIRow(player2CloseCombat);
        opponentRanged = new UIRow(player2Ranged);
        opponentSiege = new UIRow(player2Siege);
        playerCloseCombat = new UIRow(player1CloseCombat);
        playerRanged = new UIRow(player1Ranged);
        playerSiege = new UIRow(player1Siege);
        
        UISpecialZoneList = new UISpecialZone(specialZone);
        playerUIDeck = new UIDeck(player1Deck);
        opponentUIDeck = new UIDeck(player2Deck);
        playerUIDiscardPile = new UIDiscardPile(player1DiscardPile);
        opponentUIDiscardPile = new UIDiscardPile(player2DiscardPile);
        
        leftColumn = new LeftColumn(UISpecialZoneList);
        centerColumn = new CenterColumn(opponentCloseCombat, opponentRanged, opponentSiege,
                                      playerCloseCombat, playerRanged, playerSiege, UIHandList);
        rightColumn = new RightColumn(playerUIDeck, opponentUIDeck, playerUIDiscardPile, opponentUIDiscardPile);
    }

    private void setupSceneSizeListeners() {
        playerUIDeck.setupSceneSizeListener(scene);
        playerUIDiscardPile.setupSceneSizeListener(scene);
        opponentUIDeck.setupSceneSizeListener(scene);
        opponentUIDiscardPile.setupSceneSizeListener(scene);
    }

    public Scene createScene() {
        // Get screen dimensions automatically
        javafx.stage.Screen screen = javafx.stage.Screen.getPrimary();
        javafx.geometry.Rectangle2D bounds = screen.getVisualBounds();
        double windowWidth = bounds.getWidth();
        double windowHeight = bounds.getHeight();

        HBox gameBoardLayout = new HBox();
        gameBoardLayout.setAlignment(javafx.geometry.Pos.CENTER);
        gameBoardLayout.setStyle("-fx-background-color: #8B4513; -fx-border-color: #654321; -fx-border-width: 4px;");

        leftColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.2));
        centerColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.50));
        rightColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.3));
        gameBoardLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);

        VBox.setVgrow(leftColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(rightColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(gameBoardLayout, javafx.scene.layout.Priority.ALWAYS);

        // Game board now takes full height without top bar
        gameBoardLayout.prefHeightProperty().bind(heightProperty());
        
        getChildren().add(gameBoardLayout);

        scene = new Scene(this, windowWidth, windowHeight);

        setupSceneSizeListeners();

        return scene;
    }

    public void showExitConfirmation() {
        GameMenuOverlay.hide(this);
        ExitConfirmationDialog.show(this);
    }
}
