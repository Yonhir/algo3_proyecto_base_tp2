package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.vistas.components.GameButton;
import edu.fiuba.algo3.vistas.components.GameLabel;
import edu.fiuba.algo3.vistas.components.GameMenuOverlay;
import edu.fiuba.algo3.vistas.components.UnitCard;
import edu.fiuba.algo3.vistas.components.SpecialCard;
import edu.fiuba.algo3.vistas.components.ExitConfirmationDialog;
import edu.fiuba.algo3.vistas.components.Hand;
import edu.fiuba.algo3.vistas.components.SpecialZone;
import edu.fiuba.algo3.vistas.components.Row;
import edu.fiuba.algo3.vistas.components.CardList;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Game View - Contains the actual game interface
 */
public class GameView {
    private final ViewManager viewManager;
    private StackPane rootPane;
    private Scene scene;
    
    // Reference to all specialized components for responsive scaling
    private final Hand handList;
    private final Row opponentRow1, opponentRow2, opponentRow3;
    private final Row playerRow1, playerRow2, playerRow3;
    private final SpecialZone specialZoneList;
    
    // 16:9 aspect ratio constants
    private static final double ASPECT_RATIO = 16.0 / 9.0; // 16:9 aspect ratio
    private static final double MIN_SCALE_FACTOR = 0.3;

    public GameView(ViewManager viewManager) {
        this.viewManager = viewManager;
        
        // Initialize all components without elements in constructor
        handList = new Hand(8, 15);
        opponentRow1 = new Row("Close Combat", true, 6, 10);
        opponentRow2 = new Row("Ranged", true, 6, 10);
        opponentRow3 = new Row("Siege", true, 6, 10);
        playerRow1 = new Row("Close Combat", false, 6, 10);
        playerRow2 = new Row("Ranged", false, 6, 10);
        playerRow3 = new Row("Siege", false, 6, 10);
        specialZoneList = new SpecialZone(10, 15);
    }

    /**
     * Generate and populate all card lists with sample cards for demonstration
     */
    private void populateCardLists() {
        // Generate cards for Hand
        UnitCard handArcherCard = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard handKnightCard = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        SpecialCard handWeatherCard = new SpecialCard("Biting Frost", "Reduces all melee units to 1 power");
        UnitCard handCatapultCard = new UnitCard("Catapult", "Siege unit with 8 attack power", 8);
        
        // Generate cards for Opponent Row 1 (Knight cards)
        UnitCard opp1Knight1 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        UnitCard opp1Knight2 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        UnitCard opp1Knight3 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        UnitCard opp1Knight4 = new UnitCard("Knight", "Close combat unit with 3 attack power", 3);
        
        // Generate cards for Opponent Row 2 (Archer cards)
        UnitCard opp2Archer1 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard opp2Archer2 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard opp2Archer3 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        UnitCard opp2Archer4 = new UnitCard("Archer", "Ranged unit with 5 attack power", 5);
        
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
        handList.addCardsSafely(handArcherCard, handKnightCard, handWeatherCard, handCatapultCard);
        
        opponentRow1.addUnitCardsSafely(opp1Knight1, opp1Knight2, opp1Knight3, opp1Knight4);
        opponentRow2.addUnitCardsSafely(opp2Archer1, opp2Archer2, opp2Archer3, opp2Archer4);
        opponentRow3.addUnitCardsSafely(opp3Catapult1, opp3Catapult2, opp3Catapult3, opp3Catapult4);
        
        playerRow1.addUnitCardsSafely(player1Knight1, player1Knight2, player1Knight3, player1Knight4);
        playerRow2.addUnitCardsSafely(player2Archer1, player2Archer2, player2Archer3, player2Archer4);
        playerRow3.addUnitCardsSafely(player3Catapult1, player3Catapult2, player3Catapult3, player3Catapult4);
        
        specialZoneList.addSpecialCardsSafely(specialWeatherCard, specialMoraleCard, specialClearWeatherCard);
    }

    public Scene createScene() {
        double windowWidth = viewManager.getWindowWidth();
        double windowHeight = viewManager.getWindowHeight();
        
        if (windowWidth < 1280 || windowHeight < 720) {
            windowWidth = 1280;
            windowHeight = 720;
        } else {
            // Calculate the maximum resolution that can fit within the usable screen area
            // while maintaining the ASPECT_RATIO (16:9)
            double maxWidthForHeight = windowHeight * ASPECT_RATIO;
            double maxHeightForWidth = windowWidth / ASPECT_RATIO;
            
            if (maxWidthForHeight <= windowWidth) {
                // Height is the limiting factor, use full height
                windowWidth = maxWidthForHeight;
            } else {
                // Width is the limiting factor, use full width
                windowHeight = maxHeightForWidth;
            }
        }

        System.out.println("createScene: Final window dimensions: " + windowWidth + "x" + windowHeight);

        // Calculate dynamic sizes based on window dimensions
        double menuButtonSize = windowWidth * 0.025; // 2.5% of window width
        double leftColumnWidth = windowWidth * 0.3; // 30% of window width
        double centerColumnWidth = windowWidth * 0.5; // 50% of window width
        double rightColumnWidth = windowWidth * 0.2; // 20% of window width
        double paddingSize = windowWidth * 0.02; // 2% of window width

        // Create menu button at the top with dynamic size
        GameButton menuButton = new GameButton("☰", menuButtonSize, menuButtonSize);
        menuButton.setStyle("-fx-font-size: " + (menuButtonSize * 0.3) + "px; -fx-font-weight: bold; -fx-background-color: #34495e; -fx-text-fill: white; -fx-border-radius: 8px; -fx-background-radius: 8px; -fx-padding: 0;");
        menuButton.setOnAction(e -> showMenuOverlay());

        // Create top bar container
        HBox topBar = new HBox();
        topBar.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        topBar.getChildren().add(menuButton);
        
        // Populate all card lists with sample cards
        populateCardLists();
        
        // Create a special zone container with dynamic width
        VBox specialZoneContainer = new VBox(); // Reduced spacing
        specialZoneContainer.setAlignment(javafx.geometry.Pos.CENTER);
        specialZoneContainer.getChildren().add(specialZoneList);

        // Create opponent rows container
        VBox opponentRowsContainer = new VBox(); // Reduced spacing
        opponentRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        opponentRowsContainer.getChildren().addAll(
            opponentRow3,  // Siege row (top)
            opponentRow2,  // Ranged row (middle)
            opponentRow1   // Close Combat row (bottom)
        );

        // Create player rows container
        VBox playerRowsContainer = new VBox(); // Reduced spacing
        playerRowsContainer.setAlignment(javafx.geometry.Pos.CENTER);
        playerRowsContainer.getChildren().addAll(
            playerRow1,   // Close Combat row (top)
            playerRow2,   // Ranged row (middle)
            playerRow3    // Siege row (bottom)
        );
        
        // Create hand container
        VBox handContainer = new VBox(); // Reduced spacing
        handContainer.setAlignment(javafx.geometry.Pos.CENTER);
        handContainer.getChildren().add(handList);
        
        // Left column: Special Zone only (menu button moved to top) with dynamic width
        VBox leftColumn = new VBox();
        leftColumn.setAlignment(javafx.geometry.Pos.CENTER);
        leftColumn.setPrefWidth(leftColumnWidth);
        leftColumn.setMaxWidth(leftColumnWidth);
        leftColumn.getChildren().add(specialZoneContainer);
        
        // Center column: Game board (opponent rows, player rows, hand)
        VBox centerColumn = new VBox();
        centerColumn.setAlignment(javafx.geometry.Pos.CENTER);
        centerColumn.setPrefWidth(centerColumnWidth);
        centerColumn.setMaxWidth(centerColumnWidth);
        centerColumn.getChildren().addAll(
            opponentRowsContainer,
            playerRowsContainer,
            handContainer
        );
        
        // Right column: Future deck/discard pile area (empty for now) with dynamic width
        VBox rightColumn = new VBox();
        rightColumn.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        rightColumn.setPrefWidth(rightColumnWidth);
        rightColumn.setMaxWidth(rightColumnWidth);
        // Add placeholder for future deck/discard pile components
        GameLabel placeholderLabel = new GameLabel("Deck/Discard\nArea");
        placeholderLabel.setStyle("-fx-font-size: " + (paddingSize * 7) + "px; -fx-text-fill: #666; -fx-text-alignment: center;");
        rightColumn.getChildren().add(placeholderLabel);
        
        // Main horizontal layout containing all three columns
        HBox gameBoardLayout = new HBox();
        gameBoardLayout.setAlignment(javafx.geometry.Pos.CENTER);
        gameBoardLayout.getChildren().addAll(leftColumn, centerColumn, rightColumn);
        
        // Set the center column to grow and take a remaining space
        HBox.setHgrow(centerColumn, javafx.scene.layout.Priority.ALWAYS);
        
        // Set the left and right columns to take all vertical space
        VBox.setVgrow(leftColumn, javafx.scene.layout.Priority.ALWAYS);
        VBox.setVgrow(rightColumn, javafx.scene.layout.Priority.ALWAYS);
        
        // Create the main content container with topBar at top and gameBoardLayout at bottom
        VBox mainContent = new VBox();
        mainContent.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        mainContent.setPadding(new javafx.geometry.Insets(paddingSize * 20, paddingSize * 5, paddingSize * 5, paddingSize * 5)); // Dynamic padding based on window size
        mainContent.getChildren().addAll(topBar, gameBoardLayout);
        
        // Create the scene with the calculated dimensions
        rootPane = new StackPane();
        rootPane.getChildren().add(mainContent);
        scene = new Scene(rootPane, windowWidth, windowHeight);
        
        // Add window resize listener to maintain aspect ratio and scale components
        setupResponsiveLayout();

        return scene;
    }
    
    /**
     * Setup responsive layout that maintains aspect ratio and scales components
     */
    private void setupResponsiveLayout() {
        // Listen to scene width and height changes
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() > 0) {
                adjustLayoutForNewSize(newValue.doubleValue(), scene.getHeight());
            }
        });
        
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.doubleValue() > 0) {
                adjustLayoutForNewSize(scene.getWidth(), newValue.doubleValue());
            }
        });
    }
    
    /**
     * Adjust layout components based on new window size while maintaining the aspect ratio
     */
    private void adjustLayoutForNewSize(double newWidth, double newHeight) {
        // Get the actual screen dimensions to calculate proper scaling
        double screenWidth = viewManager.getWindowWidth();
        double screenHeight = viewManager.getWindowHeight();
        
        System.out.println("Screen dimensions: " + screenWidth + "x" + screenHeight);
        System.out.println("Window dimensions: " + newWidth + "x" + newHeight);
        
        // Calculate the maximum resolution that can fit within the current window
        // while maintaining the ASPECT_RATIO (16:9)
        double maxWidthForHeight = newHeight * ASPECT_RATIO;
        double maxHeightForWidth = newWidth / ASPECT_RATIO;
        
        double targetWidth, targetHeight;
        
        if (maxWidthForHeight <= newWidth) {
            // Height is the limiting factor, use full height
            targetWidth = maxWidthForHeight;
            targetHeight = newHeight;
        } else {
            // Width is the limiting factor, use full width
            targetWidth = newWidth;
            targetHeight = maxHeightForWidth;
        }
        
        // Calculate the scale factor based on the actual window dimensions vs. base resolution (1280x720)
        // This ensures we use the full available space for scaling
        double scaleFactor = newWidth / 1280.0;
        
        // Ensure minimum and maximum scale factors
        scaleFactor = Math.max(scaleFactor, MIN_SCALE_FACTOR);
        scaleFactor = Math.min(scaleFactor, 2.0); // Cap at 2x to prevent excessive scaling
        
        System.out.println("Target resolution: " + targetWidth + "x" + targetHeight + 
                          " (maintaining " + ASPECT_RATIO + " aspect ratio)");
        System.out.println("Window resized to: " + newWidth + "x" + newHeight + 
                          ", Scale factor: " + scaleFactor + 
                          " (X: " + scaleFactor + ", Y: " + scaleFactor + ")");
        
        // Scale all specialized components
        scaleCardList(handList, scaleFactor);
        scaleCardList(opponentRow1, scaleFactor);
        scaleCardList(opponentRow2, scaleFactor);
        scaleCardList(opponentRow3, scaleFactor);
        scaleCardList(playerRow1, scaleFactor);
        scaleCardList(playerRow2, scaleFactor);
        scaleCardList(playerRow3, scaleFactor);
        scaleCardList(specialZoneList, scaleFactor);
        
        // Recalculate positioning for all components
        recalculateAllComponentPositions();
    }
    
    /**
     * Scale a component and its cards
     */
    private void scaleCardList(CardList cardList, double scaleFactor) {
        /* // Scale the component itself
        cardList.setScaleX(scaleFactor);
        cardList.setScaleY(scaleFactor);

        double cardScaleFactor = scaleFactor * 0.8;
        
        // Scale each card in the list using the new scaleCard method
        for (int i = 0; i < cardList.getCardCount(); i++) {
            var card = cardList.getCard(i);
            if (card != null) {
                // Use the new scaleCard method with a single conversion factor
                card.scaleCard(cardScaleFactor);
            }
        } */
    }
    
    /**
     * Recalculate positioning for all components
     */
    private void recalculateAllComponentPositions() {
        handList.recalculatePositioning();
        opponentRow1.recalculatePositioning();
        opponentRow2.recalculatePositioning();
        opponentRow3.recalculatePositioning();
        playerRow1.recalculatePositioning();
        playerRow2.recalculatePositioning();
        playerRow3.recalculatePositioning();
        specialZoneList.recalculatePositioning();
    }

    private void backToMenu() {
        viewManager.showMainMenu();
    }
    
    private void toggleFullScreen() {
        viewManager.toggleFullScreen();
    }

    private void showMenuOverlay() {
        GameMenuOverlay.show(rootPane, this::backToMenu, this::toggleFullScreen, this::showExitConfirmation);
    }

    public void showExitConfirmation() {
        System.out.println("GameView.showExitConfirmation() called");
        // Hide the menu overlay first if it's open
        GameMenuOverlay.hide(rootPane);
        System.out.println("Menu overlay hidden, showing exit dialog");
        // Then show the exit confirmation dialog
        ExitConfirmationDialog.show(rootPane);
        System.out.println("Exit dialog shown");
    }
}
