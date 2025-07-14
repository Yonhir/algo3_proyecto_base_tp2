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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class GameView extends StackPane {
    // Layout constants
    private static final double LEFT_COLUMN_WIDTH_RATIO = 0.2;
    private static final double CENTER_COLUMN_WIDTH_RATIO = 0.50;
    private static final double RIGHT_COLUMN_WIDTH_RATIO = 0.3;

    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;

    public GameView(Board board) {
        setStyle("-fx-background-color: #8B4513;");

        UIHand UIHandList = new UIHand(board.getCurrentPlayerHand());
        UIRow opponentCloseCombat = new UIRow(board.getOpponentCloseCombat());
        UIRow opponentRanged = new UIRow(board.getOpponentRanged());
        UIRow opponentSiege = new UIRow(board.getOpponentSiege());
        UIRow playerCloseCombat = new UIRow(board.getCurrentPlayerCloseCombat());
        UIRow playerRanged = new UIRow(board.getCurrentPlayerRanged());
        UIRow playerSiege = new UIRow(board.getCurrentPlayerSiege());

        UISpecialZone UISpecialZoneList = new UISpecialZone(board.getSpecialZone());
        UIDeck playerUIDeck = new UIDeck(board.getCurrentPlayerDeck());
        UIDeck opponentUIDeck = new UIDeck(board.getOpponentDeck());
        UIDiscardPile playerUIDiscardPile = new UIDiscardPile(board.getCurrentPlayerDiscardPile());
        UIDiscardPile opponentUIDiscardPile = new UIDiscardPile(board.getOpponentDiscardPile());
        PassTurnButton passButton = new PassTurnButton(board.getGame());
        leftColumn = new LeftColumn(UISpecialZoneList);
        centerColumn = new CenterColumn(opponentCloseCombat, opponentRanged, opponentSiege,
                playerCloseCombat, playerRanged, playerSiege, UIHandList);
        rightColumn = new RightColumn(playerUIDeck, opponentUIDeck, playerUIDiscardPile, opponentUIDiscardPile, passButton);

        // Initialize the layout
        initializeLayout();
    }

    private void initializeLayout() {
        HBox gameBoardLayout = new HBox();
        gameBoardLayout.setAlignment(Pos.CENTER);
        gameBoardLayout.setStyle("-fx-background-color: #8B4513; -fx-border-color: #654321; -fx-border-width: 4px;");

        leftColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(LEFT_COLUMN_WIDTH_RATIO));
        centerColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(CENTER_COLUMN_WIDTH_RATIO));
        rightColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(RIGHT_COLUMN_WIDTH_RATIO));
        gameBoardLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);

        VBox.setVgrow(leftColumn, Priority.ALWAYS);
        VBox.setVgrow(rightColumn, Priority.ALWAYS);
        VBox.setVgrow(gameBoardLayout, Priority.ALWAYS);

        gameBoardLayout.prefHeightProperty().bind(heightProperty());

        getChildren().add(gameBoardLayout);
    }

    public void showExitConfirmation() {
        ExitConfirmationDialog.show(this);
    }
}
