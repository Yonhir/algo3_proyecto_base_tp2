package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.Deck;

import java.util.ArrayList;
import java.util.List;

/**
 * A view-specific class to manage player profile data without modifying the model Player class.
 * This class handles player name and deck management for the UI.
 */
public class PlayerProfileManager {
    private String playerName;
    private List<Deck> playerDecks;
    private int activeDeckIndex;

    public PlayerProfileManager() {
        this.playerName = "";
        this.playerDecks = new ArrayList<>();
        this.activeDeckIndex = -1; // No active deck initially
    }

    public PlayerProfileManager(String playerName) {
        this.playerName = playerName;
        this.playerDecks = new ArrayList<>();
        this.activeDeckIndex = -1; // No active deck initially
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void addDeck(Deck deck) {
        playerDecks.add(deck);
        // If this is the first deck, make it active
        if (activeDeckIndex == -1 && playerDecks.size() == 1) {
            activeDeckIndex = 0;
        }
    }

    public void removeDeck(int index) {
        if (index >= 0 && index < playerDecks.size()) {
            playerDecks.remove(index);
            
            // Adjust active deck index if necessary
            if (playerDecks.isEmpty()) {
                activeDeckIndex = -1;
            } else if (activeDeckIndex == index) {
                activeDeckIndex = 0; // Default to first deck
            } else if (activeDeckIndex > index) {
                activeDeckIndex--; // Adjust index for removed deck
            }
        }
    }

    public List<Deck> getDecks() {
        return playerDecks;
    }

    public Deck getDeck(int index) {
        if (index >= 0 && index < playerDecks.size()) {
            return playerDecks.get(index);
        }
        return null;
    }

    public Deck getActiveDeck() {
        if (activeDeckIndex >= 0 && activeDeckIndex < playerDecks.size()) {
            return playerDecks.get(activeDeckIndex);
        }
        return null;
    }

    public int getActiveDeckIndex() {
        return activeDeckIndex;
    }

    public void setActiveDeck(int index) {
        if (index >= 0 && index < playerDecks.size()) {
            activeDeckIndex = index;
        }
    }

    public int getDeckCount() {
        return playerDecks.size();
    }
    
    public boolean hasPlayerProfile() {
        return !playerName.isEmpty();
    }
} 