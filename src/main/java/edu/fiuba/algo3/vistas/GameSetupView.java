package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.vistas.components.*;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import java.util.function.Consumer;

public class GameSetupView extends StackPane {
    
    public enum SetupPhase {
        RED_TURN_ANNOUNCEMENT,
        RED_HAND_SELECTION,
        BLUE_TURN_ANNOUNCEMENT,
        BLUE_HAND_SELECTION
    }
    
    private final Hand redPlayerHand;
    private final Hand bluePlayerHand;
    private final ViewManager viewManager;
    
    private SetupPhase currentPhase = SetupPhase.RED_TURN_ANNOUNCEMENT;
    private int redDiscardCount = 0;
    private int blueDiscardCount = 0;
    private final int MAX_DISCARDS = 2;
    
    private VBox currentContent;
    private Scene scene;
    
    // Event handlers for controller
    private String currentPlayerName;
    
    public GameSetupView(Hand redPlayerHand, Hand bluePlayerHand, ViewManager viewManager) {
        this.redPlayerHand = redPlayerHand;
        this.bluePlayerHand = bluePlayerHand;
        this.viewManager = viewManager;
        
        showCurrentPhase();
    }
    
    public void setOnNextPhaseRequested(Consumer<Void> handler) {
        this.onNextPhaseRequested = handler;
    }
    
    public void setOnCardClickRequested(Consumer<Integer> handler) {
        this.onCardClickRequested = handler;
    }
    
    public void setOnConfirmHandRequested(Consumer<String> handler) {
        this.onConfirmHandRequested = handler;
    }
    
    private void showCurrentPhase() {
        getChildren().clear();
        
        switch (currentPhase) {
            case RED_TURN_ANNOUNCEMENT:
                showTurnAnnouncement("Player Red Turn");
                break;
            case RED_HAND_SELECTION:
                showHandSelection(redPlayerHand, "Red");
                break;
            case BLUE_TURN_ANNOUNCEMENT:
                showTurnAnnouncement("Player Blue Turn");
                break;
            case BLUE_HAND_SELECTION:
                showHandSelection(bluePlayerHand, "Blue");
                break;
        }
    }
    
    public void refreshCurrentPhase() {
        showCurrentPhase();
    }
    
    private void showTurnAnnouncement(String message) {
        currentContent = new VBox(30);
        currentContent.setAlignment(javafx.geometry.Pos.CENTER);
        currentContent.setStyle("-fx-background-color: black;");
        
        GameLabel titleLabel = new GameLabel(message, 
            "-fx-font-size: 48px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        GameButton nextButton = new GameButton("Next Phase", 200, 50);
        nextButton.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        nextButton.setOnAction(e -> {
            if (onNextPhaseRequested != null) {
                onNextPhaseRequested.accept(null);
            }
        });
        
        currentContent.getChildren().addAll(titleLabel, nextButton);
        getChildren().add(currentContent);
    }
    
    private void showHandSelection(Hand playerHand, String playerName) {
        currentContent = new VBox(20);
        currentContent.setAlignment(javafx.geometry.Pos.CENTER);
        currentContent.setStyle("-fx-background-color: #2C3E50;");
        
        GameLabel titleLabel = new GameLabel(playerName + " Player - Select Your Hand", 
            "-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");
        
        // Create hand component (UI component, not model)
        edu.fiuba.algo3.vistas.components.Hand handList = new edu.fiuba.algo3.vistas.components.Hand(playerHand);
        
        // Create button container
        HBox buttonContainer = new HBox();
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);
        
        GameButton confirmButton = new GameButton("Confirm Hand", 150, 40);
        confirmButton.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #27AE60; -fx-text-fill: white;");
        confirmButton.setOnAction(e -> {
            if (onConfirmHandRequested != null) {
                onConfirmHandRequested.accept(playerName);
            }
        });
        
        GameLabel discardLabel = new GameLabel("Discards: " + getDiscardCount(playerName) + "/" + MAX_DISCARDS, 
            "-fx-font-size: 14px; -fx-text-fill: #ECF0F1;");
        
        buttonContainer.getChildren().addAll(confirmButton, discardLabel);
        
        currentContent.getChildren().addAll(titleLabel, handList, buttonContainer);
        getChildren().add(currentContent);
    }
    
    public void nextPhase() {
        switch (currentPhase) {
            case RED_TURN_ANNOUNCEMENT:
                currentPhase = SetupPhase.RED_HAND_SELECTION;
                break;
            case RED_HAND_SELECTION:
                currentPhase = SetupPhase.BLUE_TURN_ANNOUNCEMENT;
                break;
            case BLUE_TURN_ANNOUNCEMENT:
                currentPhase = SetupPhase.BLUE_HAND_SELECTION;
                break;
            case BLUE_HAND_SELECTION:
                // Game setup complete, show main GameView
                viewManager.showGameView();
                return;
        }
        
        showCurrentPhase();
    }
    
    private int getDiscardCount(String playerName) {
        return "Red".equals(playerName) ? redDiscardCount : blueDiscardCount;
    }
    
    public void setRedDiscardCount(int count) {
        this.redDiscardCount = count;
    }
    
    public void setBlueDiscardCount(int count) {
        this.blueDiscardCount = count;
    }
    
    public Scene createScene() {
        double windowWidth = 1920;
        double windowHeight = 1080;
        
        scene = new Scene(this, windowWidth, windowHeight);
        return scene;
    }
} 