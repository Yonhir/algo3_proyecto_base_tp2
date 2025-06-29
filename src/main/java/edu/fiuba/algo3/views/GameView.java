package edu.fiuba.algo3.views;

import edu.fiuba.algo3.views.components.*;
import edu.fiuba.algo3.views.components.cardcomponent.UIDeck;
import edu.fiuba.algo3.views.components.cardcomponent.UIDiscardPile;
import edu.fiuba.algo3.views.components.cardlist.UIHand;
import edu.fiuba.algo3.views.components.cardlist.UIRow;
import edu.fiuba.algo3.views.components.cardlist.UISpecialZone;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    private final TopBar topBar;
    private final LeftColumn leftColumn;
    private final CenterColumn centerColumn;
    private final RightColumn rightColumn;
    
    private Scene scene;

    public GameView() {
        UIHandList = new UIHand();
        opponentCloseCombat = new UIRow();
        opponentRanged = new UIRow();
        opponentSiege = new UIRow();
        playerCloseCombat = new UIRow();
        playerRanged = new UIRow();
        playerSiege = new UIRow();
        UISpecialZoneList = new UISpecialZone();
        playerUIDeck = new UIDeck();
        opponentUIDeck = new UIDeck();
        playerUIDiscardPile = new UIDiscardPile();
        opponentUIDiscardPile = new UIDiscardPile();
        
        topBar = new TopBar();
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
