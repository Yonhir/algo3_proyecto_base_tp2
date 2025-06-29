/*
package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.vistas.GameSetupView;
import edu.fiuba.algo3.vistas.ViewManager;
import edu.fiuba.algo3.vistas.components.ConfirmationOverlay;
import edu.fiuba.algo3.vistas.components.InfoOverlay;

public class GameSetupController {
    private final ViewManager viewManager;
    private final GameSetupView view;
    private final Hand redPlayerHand;
    private final Hand bluePlayerHand;
    private final Deck redPlayerDeck;
    private final Deck bluePlayerDeck;
    private final DiscardPile redPlayerDiscardPile;
    private final DiscardPile bluePlayerDiscardPile;
    
    private int redDiscardCount = 0;
    private int blueDiscardCount = 0;
    private final int MAX_DISCARDS = 2;
    
    public GameSetupController(ViewManager viewManager, GameSetupView view,
                             Hand redPlayerHand, Hand bluePlayerHand,
                             Deck redPlayerDeck, Deck bluePlayerDeck,
                             DiscardPile redPlayerDiscardPile, DiscardPile bluePlayerDiscardPile) {
        this.viewManager = viewManager;
        this.view = view;
        this.redPlayerHand = redPlayerHand;
        this.bluePlayerHand = bluePlayerHand;
        this.redPlayerDeck = redPlayerDeck;
        this.bluePlayerDeck = bluePlayerDeck;
        this.redPlayerDiscardPile = redPlayerDiscardPile;
        this.bluePlayerDiscardPile = bluePlayerDiscardPile;
        
        // Set up event handlers
        setupEventHandlers();
    }
    
    private void setupEventHandlers() {
        // Set up phase transition handlers
        view.setOnNextPhaseRequested(v -> handleNextPhase());
        view.setOnCardClickRequested(this::handleCardClick);
        view.setOnConfirmHandRequested(this::handleConfirmHand);
    }
    
    private void handleNextPhase() {
        view.nextPhase();
    }
    
    private void handleCardClick(int cardIndex) {
        // Get current player name based on phase
        String playerName = getCurrentPlayerName();
        int currentDiscards = getDiscardCount(playerName);
        
        if (currentDiscards >= MAX_DISCARDS) {
            showInfoOverlay("Maximum Discards Reached", 
                "You have already discarded " + MAX_DISCARDS + " cards. You can only confirm your hand now.");
            return;
        }
        
        showDiscardConfirmation(cardIndex, playerName);
    }
    
    private String getCurrentPlayerName() {
        // This is a simplified approach - in a real implementation you'd track the current phase
        // For now, we'll use the discard counts to determine which player
        if (redDiscardCount < blueDiscardCount) {
            return "Red";
        } else {
            return "Blue";
        }
    }
    
    private void showDiscardConfirmation(int cardIndex, String playerName) {
        String title = "Discard Card";
        String message = "Are you sure you want to discard this card and draw a new one?";
        
        ConfirmationOverlay.show(view, title, message, 
            (Void v) -> {
                // Accept action - discard card
                handleDiscardCard(cardIndex, playerName);
            },
            (Void v) -> {
                // Cancel action - do nothing
            }
        );
    }
    
    private void handleDiscardCard(int cardIndex, String playerName) {
        // Increment discard count
        if ("Red".equals(playerName)) {
            redDiscardCount++;
            view.setRedDiscardCount(redDiscardCount);
        } else {
            blueDiscardCount++;
            view.setBlueDiscardCount(blueDiscardCount);
        }
        
        // Refresh the hand selection view
        view.refreshCurrentPhase();
        
        showInfoOverlay("Card Discarded", "Card discarded successfully. You can discard " + 
            (MAX_DISCARDS - getDiscardCount(playerName)) + " more cards.");
    }
    
    private void handleConfirmHand(String playerName) {
        String title = "Confirm Hand";
        String message = "Are you sure you want to confirm your hand? This action cannot be undone.";
        
        ConfirmationOverlay.show(view, title, message, 
            (Void v) -> {
                // Accept action - confirm hand
                handleNextPhase();
            },
            (Void v) -> {
                // Cancel action - do nothing
            }
        );
    }
    
    private int getDiscardCount(String playerName) {
        return "Red".equals(playerName) ? redDiscardCount : blueDiscardCount;
    }
    
    private void showInfoOverlay(String title, String message) {
        InfoOverlay.show(view, title, message);
    }
    
    public int getRedDiscardCount() {
        return redDiscardCount;
    }
    
    public int getBlueDiscardCount() {
        return blueDiscardCount;
    }
    
    public int getMaxDiscards() {
        return MAX_DISCARDS;
    }
    
    public Hand getRedPlayerHand() {
        return redPlayerHand;
    }
    
    public Hand getBluePlayerHand() {
        return bluePlayerHand;
    }
}

 */