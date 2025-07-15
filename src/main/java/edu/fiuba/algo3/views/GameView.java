package edu.fiuba.algo3.views;

import edu.fiuba.algo3.controllers.RowHandler;
import edu.fiuba.algo3.controllers.SpecialZoneHandler;
import edu.fiuba.algo3.models.sections.Board;
import edu.fiuba.algo3.views.components.*;
import edu.fiuba.algo3.views.components.cardcomponent.UIDeck;
import edu.fiuba.algo3.views.components.cardcomponent.UIDiscardPile;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import edu.fiuba.algo3.views.components.PassTurnButton;
import edu.fiuba.algo3.views.components.PlayerNameScreen;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GameView extends StackPane {
    // Layout constants
    private static final double LEFT_COLUMN_WIDTH_RATIO = 0.2;
    private static final double CENTER_COLUMN_WIDTH_RATIO = 0.50;
    private static final double RIGHT_COLUMN_WIDTH_RATIO = 0.3;

    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;

    private PlayerNameScreen playerNameScreen;

    public GameView(Board board) {
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
        
        CardInfoView cardViewer = new CardInfoView();

        leftColumn = new LeftColumn(UISpecialZoneList, board.getCurrentPlayer(), board.getOpponentPlayer());
        centerColumn = new CenterColumn(opponentCloseCombat, opponentRanged, opponentSiege,
                playerCloseCombat, playerRanged, playerSiege, UIHandList);
        rightColumn = new RightColumn(playerUIDeck, opponentUIDeck, playerUIDiscardPile, opponentUIDiscardPile, passButton, cardViewer);

        // Create list of all rows for the controller
        List<UIRow> allRows = List.of(
            opponentCloseCombat, opponentRanged, opponentSiege,
            playerCloseCombat, playerRanged, playerSiege
        );
        
        // Set up UIHand with CardInfoView
        UIHandList.setCardInfoView(cardViewer);
        
        // Set up CardInfoView with rows and special zone for highlighting
        cardViewer.setRows(allRows);
        cardViewer.setSpecialZone(UISpecialZoneList);
        
        // Create RowHandler instances for each row and set up event handlers
        setupRowHandlers(allRows, UIHandList, board.getRound());
        
        // Create SpecialZoneHandler and set up event handler
        setupSpecialZoneHandler(UISpecialZoneList, UIHandList, board.getRound());
        
        String currentPlayerName = board.getCurrentPlayer().getName();
        playerNameScreen = new PlayerNameScreen(currentPlayerName);

        // Initialize the layout
        initializeLayout();
    }

    private void setupRowHandlers(List<UIRow> rows, UIHand hand, edu.fiuba.algo3.models.turnManagement.Round currentRound) {
        for (UIRow row : rows) {
            RowHandler rowHandler = new RowHandler(row, hand, currentRound);
            row.setOnMouseClicked(rowHandler);
        }
    }
    
    private void setupSpecialZoneHandler(UISpecialZone specialZone, UIHand hand, edu.fiuba.algo3.models.turnManagement.Round currentRound) {
        SpecialZoneHandler specialZoneHandler = new SpecialZoneHandler(specialZone, hand, currentRound);
        specialZone.setOnMouseClicked(specialZoneHandler);
    }

    private void showPlayerNameScreen() {
        getChildren().add(playerNameScreen);
        StackPane.setAlignment(playerNameScreen, Pos.CENTER);
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

        showPlayerNameScreen();
    }

    public void showExitConfirmation() {
        ExitConfirmationDialog.show(this);
    }
}
