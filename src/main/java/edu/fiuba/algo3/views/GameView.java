package edu.fiuba.algo3.views;

import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.components.*;
import edu.fiuba.algo3.views.components.cardcomponent.UIDeck;
import edu.fiuba.algo3.views.components.cardcomponent.UIDiscardPile;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import edu.fiuba.algo3.views.components.PassTurnButton;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameView extends StackPane {
    // Layout constants
    private static final double LEFT_COLUMN_WIDTH_RATIO = 0.2;
    private static final double CENTER_COLUMN_WIDTH_RATIO = 0.50;
    private static final double RIGHT_COLUMN_WIDTH_RATIO = 0.3;

    private final UIDeck playerUIDeck, opponentUIDeck;
    private final UIDiscardPile playerUIDiscardPile, opponentUIDiscardPile;
    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;
    private final PassTurnButton passButton;
    
    private Scene scene;

    public GameView(Board board) {

        UIHand UIHandList = new UIHand(board.getCurrentPlayerHand());
        UIRow opponentCloseCombat = new UIRow(board.getOpponentCloseCombat());
        UIRow opponentRanged = new UIRow(board.getOpponentRanged());
        UIRow opponentSiege = new UIRow(board.getOpponentSiege());
        UIRow playerCloseCombat = new UIRow(board.getCurrentPlayerCloseCombat());
        UIRow playerRanged = new UIRow(board.getCurrentPlayerRanged());
        UIRow playerSiege = new UIRow(board.getCurrentPlayerSiege());

        UISpecialZone UISpecialZoneList = new UISpecialZone(board.getSpecialZone());
        playerUIDeck = new UIDeck(board.getCurrentPlayerDeck());
        opponentUIDeck = new UIDeck(board.getOpponentDeck());
        playerUIDiscardPile = new UIDiscardPile(board.getCurrentPlayerDiscardPile());
        opponentUIDiscardPile = new UIDiscardPile(board.getOpponentDiscardPile());
        passButton = new PassTurnButton("Pass", board.getGame());
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

        leftColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(LEFT_COLUMN_WIDTH_RATIO));
        centerColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(CENTER_COLUMN_WIDTH_RATIO));
        rightColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(RIGHT_COLUMN_WIDTH_RATIO));
        gameBoardLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);

        VBox.setVgrow(leftColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(rightColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(gameBoardLayout, javafx.scene.layout.Priority.ALWAYS);

        gameBoardLayout.prefHeightProperty().bind(heightProperty());

        getChildren().add(gameBoardLayout);

        StackPane.setAlignment(passButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(passButton, new javafx.geometry.Insets(0, 300, 150, 0));

        getChildren().add(passButton);

        scene = new Scene(this, windowWidth, windowHeight);

        setupSceneSizeListeners();

        return scene;
    }

    public void showExitConfirmation() {
        ExitConfirmationDialog.show(this);
    }
}
