package edu.fiuba.algo3.modelo;

import java.util.ArrayList;
import java.util.List;

public class PlayerProfile {
    private String name;
    private List<Deck> decks;
    private int activeDeckIndex;

    public PlayerProfile(String name) {
        this.name = name;
        this.decks = new ArrayList<>();
        this.activeDeckIndex = -1; // No active deck initially
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDeck(Deck deck) {
        decks.add(deck);
        // If this is the first deck, make it active
        if (activeDeckIndex == -1 && decks.size() == 1) {
            activeDeckIndex = 0;
        }
    }

    public void removeDeck(int index) {
        if (index >= 0 && index < decks.size()) {
            decks.remove(index);
            
            // Adjust active deck index if necessary
            if (decks.isEmpty()) {
                activeDeckIndex = -1;
            } else if (activeDeckIndex == index) {
                activeDeckIndex = 0; // Default to first deck
            } else if (activeDeckIndex > index) {
                activeDeckIndex--; // Adjust index for removed deck
            }
        }
    }

    public List<Deck> getDecks() {
        return decks;
    }

    public Deck getDeck(int index) {
        if (index >= 0 && index < decks.size()) {
            return decks.get(index);
        }
        return null;
    }

    public Deck getActiveDeck() {
        if (activeDeckIndex >= 0 && activeDeckIndex < decks.size()) {
            return decks.get(activeDeckIndex);
        }
        return null;
    }

    public int getActiveDeckIndex() {
        return activeDeckIndex;
    }

    public void setActiveDeck(int index) {
        if (index >= 0 && index < decks.size()) {
            activeDeckIndex = index;
        }
    }

    public int getDeckCount() {
        return decks.size();
    }
} 