package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.components.*;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class GameView extends StackPane {
    private final ViewManager viewManager;

    // Reference to all specialized components for responsive scaling
    private final Hand handList;
    private final Row opponentCloseCombat, opponentRanged, opponentSiege;
    private final Row playerCloseCombat, playerRanged, playerSiege;
    private final SpecialZone specialZoneList;
    
    private Scene scene;

    public GameView(ViewManager viewManager) {
        this.viewManager = viewManager;
        
        // Initialize all components without elements in constructor
        handList = new Hand();
        opponentCloseCombat = new Row( true);
        opponentRanged = new Row(true);
        opponentSiege = new Row(true);
        playerCloseCombat = new Row(false);
        playerRanged = new Row(false);
        playerSiege = new Row(false);
        specialZoneList = new SpecialZone();
    }

    private void populateCardLists() {
        // Generate cards for Hand
        UnitCard handArcherCard = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard handKnightCard = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        SpecialCard handWeatherCard = new SpecialCard("Biting Frost", "Reduces all melee units to 1 power");
        UnitCard handCatapultCard = new UnitCard("Canon", "Siege unit with 8 attack power", 9);
        
        // Generate cards for Opponent Row 1 (Knight cards)
        UnitCard opp1Knight1 = new UnitCard("Knight1", "Close combat unit with 3 attack power", 3);
        UnitCard opp1Knight2 = new UnitCard("Knight2", "Close combat unit with 3 attack power", 3);
        UnitCard opp1Knight3 = new UnitCard("Knight3", "Close combat unit with 3 attack power", 3);
        UnitCard opp1Knight4 = new UnitCard("Knight4", "Close combat unit with 3 attack power", 3);
        
        // Generate cards for Opponent Row 2 (Archer cards)
        UnitCard opp2Archer1 = new UnitCard("Archer1", "Ranged unit with 5 attack power", 5);
        UnitCard opp2Archer2 = new UnitCard("Archer2", "Ranged unit with 5 attack power", 5);
        UnitCard opp2Archer3 = new UnitCard("Archer3", "Ranged unit with 5 attack power", 5);
        UnitCard opp2Archer4 = new UnitCard("Archer4", "Ranged unit with 5 attack power", 5);
        
        // Generate cards for Opponent Row 3 (Catapult cards)
        UnitCard opp3Catapult1 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        UnitCard opp3Catapult2 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        UnitCard opp3Catapult3 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        UnitCard opp3Catapult4 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        
        // Generate cards for Player Row 1 (Knight cards)
        UnitCard player1Knight1 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        UnitCard player1Knight2 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        UnitCard player1Knight3 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        UnitCard player1Knight4 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        
        // Generate cards for Player Row 2 (Archer cards)
        UnitCard player2Archer1 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard player2Archer2 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard player2Archer3 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard player2Archer4 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        
        // Generate cards for Player Row 3 (Catapult cards)
        UnitCard player3Catapult1 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        UnitCard player3Catapult2 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        UnitCard player3Catapult3 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        UnitCard player3Catapult4 = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        
        // Generate cards for Special Zone
        SpecialCard specialWeatherCard = new SpecialCard("Biting Frost", "Reduces all melee units to 1 power");
        SpecialCard specialMoraleCard = new SpecialCard("Morale Boost", "Increases adjacent units by 1");
        SpecialCard specialClearWeatherCard = new SpecialCard("Clear Weather", "Removes all weather effects");
        
        // Populate all card lists
        handList.addCards(handArcherCard, handKnightCard, handWeatherCard, handCatapultCard);
        
        // opponentCloseCombat.addCards(opp1Knight1, opp1Knight2, opp1Knight3, opp1Knight4);
        opponentRanged.addCards(opp2Archer1, opp2Archer2, opp2Archer3, opp2Archer4);
        opponentSiege.addCards(opp3Catapult1, opp3Catapult2, opp3Catapult3, opp3Catapult4);
        
        playerCloseCombat.addCards(player1Knight1, player1Knight2, player1Knight3, player1Knight4);
        playerRanged.addCards(player2Archer1, player2Archer2, player2Archer3, player2Archer4);
        playerSiege.addCards(player3Catapult1, player3Catapult2, player3Catapult3, player3Catapult4);
        
        specialZoneList.addCards(specialWeatherCard, specialMoraleCard, specialClearWeatherCard);
        
        // Store all cards for scene size listener setup
        List<Card> allCards = List.of(handArcherCard, handKnightCard, handWeatherCard, handCatapultCard,
                opp1Knight1, opp1Knight2, opp1Knight3, opp1Knight4,
                opp2Archer1, opp2Archer2, opp2Archer3, opp2Archer4,
                opp3Catapult1, opp3Catapult2, opp3Catapult3, opp3Catapult4,
                player1Knight1, player1Knight2, player1Knight3, player1Knight4,
                player2Archer1, player2Archer2, player2Archer3, player2Archer4,
                player3Catapult1, player3Catapult2, player3Catapult3, player3Catapult4,
                specialWeatherCard, specialMoraleCard, specialClearWeatherCard);

        for (Card card : allCards){
            card.setupSceneSizeListener(scene);
        }
    }

    public Scene createScene() {
        double windowWidth = 1920;
        double windowHeight = 1080;

        // Create menu button at the top with dynamic size
        GameButton menuButton = GameButton.createMenuButton();
        menuButton.setPickOnBounds(false);
        menuButton.setCancelButton(true);
        menuButton.setOnAction(e -> showMenuOverlay());

        // Create top bar container
        HBox topBar = new HBox();
        topBar.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        topBar.setStyle("-fx-background-color: #FF6B6B; -fx-border-color: #FF0000; -fx-border-width: 2px;"); // Red background
        menuButton.prefHeightProperty().bind(topBar.heightProperty().multiply(0.9));
        topBar.getChildren().add(menuButton);
        
        // Create the spacing region between rows
        javafx.scene.layout.Region opponentRowSpacer1 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region opponentRowSpacer2 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region playerRowSpacer1 = new javafx.scene.layout.Region();
        javafx.scene.layout.Region playerRowSpacer2 = new javafx.scene.layout.Region();
        
        // Create a special zone container with dynamic width
        VBox specialZoneContainer = new VBox(); // Reduced spacing
        specialZoneContainer.setAlignment(javafx.geometry.Pos.CENTER);
        specialZoneContainer.setStyle("-fx-background-color: #4ECDC4; -fx-border-color: #00FFFF; -fx-border-width: 2px;"); // Cyan background
        specialZoneContainer.getChildren().add(specialZoneList);

        // Create opponent rows container
        VBox opponentRowsContainer = new VBox(); // Reduced spacing
        opponentRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        opponentRowsContainer.setStyle("-fx-background-color: #45B7D1; -fx-border-color: #0080FF; -fx-border-width: 2px;"); // Blue background
        opponentRowSpacer1.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.03));
        opponentRowSpacer2.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.03));
        
        // Bind individual row heights to container height
        opponentSiege.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.31));
        opponentRanged.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.31));
        opponentCloseCombat.prefHeightProperty().bind(opponentRowsContainer.heightProperty().multiply(0.31));
        
        opponentRowsContainer.getChildren().addAll(
                opponentSiege,  // Siege row (top)
                opponentRowSpacer1,
                opponentRanged,  // Ranged row (middle)
                opponentRowSpacer2,
                opponentCloseCombat   // Close Combat row (bottom)
        );

        // Create player rows container
        VBox playerRowsContainer = new VBox(); // Reduced spacing
        playerRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        playerRowsContainer.setStyle("-fx-background-color: #96CEB4; -fx-border-color: #00FF00; -fx-border-width: 2px;"); // Green background
        playerRowSpacer1.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.03));
        playerRowSpacer2.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.03));
        
        // Bind individual row heights to container height
        playerCloseCombat.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.31));
        playerRanged.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.31));
        playerSiege.prefHeightProperty().bind(playerRowsContainer.heightProperty().multiply(0.31));
        
        playerRowsContainer.getChildren().addAll(
                playerCloseCombat,   // Close Combat row (top)
                playerRowSpacer1,
                playerRanged,   // Ranged row (middle)
                playerRowSpacer2,
                playerSiege    // Siege row (bottom)
        );
        
        // Create hand container
        VBox handContainer = new VBox(); // Reduced spacing
        handContainer.setAlignment(javafx.geometry.Pos.CENTER);
        handContainer.setStyle("-fx-background-color: #FFEAA7; -fx-border-color: #FFFF00; -fx-border-width: 2px;"); // Yellow background
        handContainer.getChildren().add(handList);
        
        // Left column: Special Zone only (menu button moved to top) with dynamic width
        VBox leftColumn = new VBox();
        leftColumn.setAlignment(javafx.geometry.Pos.CENTER);
        leftColumn.setStyle("-fx-background-color: #DDA0DD; -fx-border-color: #800080; -fx-border-width: 3px;"); // Purple background
        leftColumn.getChildren().add(specialZoneContainer);
        
        // Center column: Game board (opponent rows, player rows, hand)
        VBox centerColumn = new VBox();
        centerColumn.setAlignment(javafx.geometry.Pos.CENTER);
        centerColumn.setStyle("-fx-background-color: #F7DC6F; -fx-border-color: #FFA500; -fx-border-width: 3px;"); // Orange background
        opponentRowsContainer.prefHeightProperty().bind(centerColumn.heightProperty().multiply(0.4));
        playerRowsContainer.prefHeightProperty().bind(centerColumn.heightProperty().multiply(0.4));
        handContainer.prefHeightProperty().bind(centerColumn.heightProperty().multiply(0.13));
        
        // Create spacing regions
        javafx.scene.layout.Region rowSpacer = new javafx.scene.layout.Region();
        rowSpacer.prefHeightProperty().bind(centerColumn.heightProperty().multiply(0.02)); // Small spacer between opponent and player rows
        javafx.scene.layout.Region handSpacer = new javafx.scene.layout.Region();
        handSpacer.prefHeightProperty().bind(centerColumn.heightProperty().multiply(0.05)); // Larger spacer between player rows and hand
        
        centerColumn.getChildren().addAll(
            opponentRowsContainer,
            rowSpacer,  // Small spacer between opponent and player rows
            playerRowsContainer,
            handSpacer,  // Larger spacer between player rows and hand
            handContainer
        );
        
        // Right column: Future deck/discard pile area (empty for now) with dynamic width
        VBox rightColumn = new VBox();
        rightColumn.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        rightColumn.setStyle("-fx-background-color: #BB8FCE; -fx-border-color: #8E44AD; -fx-border-width: 3px;"); // Light purple background
        // Add placeholder for future deck/discard pile components
        GameLabel placeholderLabel = new GameLabel("Deck/Discard\nArea");
        // placeholderLabel.setStyle("-fx-font-size: " + (paddingSize * 7) + "px; -fx-text-fill: #666; -fx-text-alignment: center;");
        rightColumn.getChildren().add(placeholderLabel);
        
        // Main horizontal layout containing all three columns
        HBox gameBoardLayout = new HBox();
        gameBoardLayout.setAlignment(javafx.geometry.Pos.CENTER);
        gameBoardLayout.setStyle("-fx-background-color: #85C1E9; -fx-border-color: #2980B9; -fx-border-width: 4px;"); // Light blue background
        leftColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.2));
        centerColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.50));
        rightColumn.prefWidthProperty().bind(gameBoardLayout.widthProperty().multiply(0.3));
        gameBoardLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);

        // Set the left and right columns to take all vertical space
        VBox.setVgrow(leftColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(rightColumn, javafx.scene.layout.Priority.ALWAYS);

        // Set gameBoardLayout to take all vertical space
        VBox.setVgrow(gameBoardLayout, javafx.scene.layout.Priority.ALWAYS);

        // Create the main content container with topBar at top and gameBoardLayout at bottom
        VBox mainContent = new VBox();
        mainContent.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        mainContent.setStyle("-fx-background-color: #FF69B4; -fx-border-color: #FF1493; -fx-border-width: 5px;"); // Hot pink background
        topBar.prefHeightProperty().bind(mainContent.heightProperty().multiply(0.05));
        gameBoardLayout.prefHeightProperty().bind(mainContent.heightProperty().multiply(0.95));
        mainContent.getChildren().addAll(topBar, gameBoardLayout);
        
        // Add the main content to this StackPane
        getChildren().add(mainContent);

        // Create the scene with the calculated dimensions
        scene = new Scene(this, windowWidth, windowHeight);

        // Populate all card lists with sample cards
        populateCardLists();

        return scene;
    }

    private void backToMenu() {
        viewManager.showMainMenu();
    }
    
    private void toggleFullScreen() {
        viewManager.toggleFullScreen();
    }

    private void showMenuOverlay() {
        GameMenuOverlay.show(this, this::backToMenu, this::toggleFullScreen, this::showExitConfirmation);
    }

    public void showExitConfirmation() {
        GameMenuOverlay.hide(this);
        ExitConfirmationDialog.show(this);
    }
}
