package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.components.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class GameView extends StackPane {
    private final Hand handList;
    private final Row opponentCloseCombat, opponentRanged, opponentSiege;
    private final Row playerCloseCombat, playerRanged, playerSiege;
    private final SpecialZone specialZoneList;
    private final Deck playerDeck, opponentDeck;
    private final DiscardPile playerDiscardPile, opponentDiscardPile;
    private final TopBar topBar;
    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;
    
    private Scene scene;

    public GameView() {
        handList = new Hand();
        opponentCloseCombat = new Row();
        opponentRanged = new Row();
        opponentSiege = new Row();
        playerCloseCombat = new Row();
        playerRanged = new Row();
        playerSiege = new Row();
        specialZoneList = new SpecialZone();
        playerDeck = new Deck();
        opponentDeck = new Deck();
        playerDiscardPile = new DiscardPile();
        opponentDiscardPile = new DiscardPile();
        
        topBar = new TopBar();
        leftColumn = new LeftColumn(specialZoneList);
        centerColumn = new CenterColumn(opponentCloseCombat, opponentRanged, opponentSiege,
                                      playerCloseCombat, playerRanged, playerSiege, handList);
        rightColumn = new RightColumn(playerDeck, opponentDeck, playerDiscardPile, opponentDiscardPile);
    }

    private void setupSceneSizeListeners() {
        playerDeck.setupSceneSizeListener(scene);
        playerDiscardPile.setupSceneSizeListener(scene);
        opponentDeck.setupSceneSizeListener(scene);
        opponentDiscardPile.setupSceneSizeListener(scene);
    }

    public Scene createScene() {
        double windowWidth = 1920;
        double windowHeight = 1080;

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

        VBox mainContent = new VBox();
        mainContent.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        mainContent.setStyle("-fx-background-color: #654321; -fx-border-color: #3E2723; -fx-border-width: 5px;"); // Brown background
        topBar.prefHeightProperty().bind(mainContent.heightProperty().multiply(0.05));
        gameBoardLayout.prefHeightProperty().bind(mainContent.heightProperty().multiply(0.95));
        mainContent.getChildren().addAll(topBar, gameBoardLayout);
        
        getChildren().add(mainContent);

        scene = new Scene(this, windowWidth, windowHeight);

        setupSceneSizeListeners();

        return scene;
    }

    public void showExitConfirmation() {
        GameMenuOverlay.hide(this);
        ExitConfirmationDialog.show(this);
    }

    public void setOnMenuRequested(EventHandler<ActionEvent> handler) {
        topBar.getMenuButton().setOnAction(handler);
    }
}
