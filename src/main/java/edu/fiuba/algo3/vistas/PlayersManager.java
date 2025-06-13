package edu.fiuba.algo3.vistas;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import edu.fiuba.algo3.modelo.Deck;
import edu.fiuba.algo3.modelo.Card;
import edu.fiuba.algo3.modelo.Unit;
import edu.fiuba.algo3.modelo.Special;
import edu.fiuba.algo3.modelo.Agil;
import edu.fiuba.algo3.modelo.TightBond;
import edu.fiuba.algo3.modelo.BitingFrost;
import edu.fiuba.algo3.modelo.TorrentialRain;
import edu.fiuba.algo3.modelo.ImpenetrableFog;
import edu.fiuba.algo3.modelo.ClearWeather;
import edu.fiuba.algo3.modelo.Modifier;

/**
 * Manages multiple player profiles for the application.
 */
public class PlayersManager {
    private List<PlayerProfileManager> players;
    private int activePlayerIndex;

    public PlayersManager() {
        this.players = new ArrayList<>();
        this.activePlayerIndex = -1; // No active player initially
    }

    /**
     * Adds a new player profile.
     * 
     * @param player The player profile to add
     */
    public void addPlayer(PlayerProfileManager player) {
        players.add(player);
        // If this is the first player, make them active
        if (activePlayerIndex == -1 && players.size() == 1) {
            activePlayerIndex = 0;
        }
    }

    /**
     * Removes a player profile.
     * 
     * @param index The index of the player to remove
     */
    public void removePlayer(int index) {
        if (index >= 0 && index < players.size()) {
            players.remove(index);
            
            // Adjust active player index if necessary
            if (players.isEmpty()) {
                activePlayerIndex = -1;
            } else if (activePlayerIndex == index) {
                activePlayerIndex = 0; // Default to first player
            } else if (activePlayerIndex > index) {
                activePlayerIndex--; // Adjust index for removed player
            }
        }
    }

    /**
     * Gets the player profile at the specified index.
     * 
     * @param index The index of the player to get
     * @return The player profile, or null if index is invalid
     */
    public PlayerProfileManager getPlayer(int index) {
        if (index >= 0 && index < players.size()) {
            return players.get(index);
        }
        return null;
    }

    /**
     * Gets the active player profile.
     * 
     * @return The active player profile, or null if no active player
     */
    public PlayerProfileManager getActivePlayer() {
        if (activePlayerIndex >= 0 && activePlayerIndex < players.size()) {
            return players.get(activePlayerIndex);
        }
        return null;
    }

    /**
     * Sets the active player.
     * 
     * @param index The index of the player to set as active
     */
    public void setActivePlayer(int index) {
        if (index >= 0 && index < players.size()) {
            activePlayerIndex = index;
        }
    }

    /**
     * Gets the index of the active player.
     * 
     * @return The index of the active player, or -1 if no active player
     */
    public int getActivePlayerIndex() {
        return activePlayerIndex;
    }

    /**
     * Gets the number of players.
     * 
     * @return The number of players
     */
    public int getPlayerCount() {
        return players.size();
    }

    /**
     * Gets all player profiles.
     * 
     * @return The list of player profiles
     */
    public List<PlayerProfileManager> getPlayers() {
        return players;
    }

    /**
     * Checks if a player with the given name already exists.
     * 
     * @param name The name to check
     * @return True if a player with the name exists, false otherwise
     */
    public boolean playerExists(String name) {
        for (PlayerProfileManager player : players) {
            if (player.getPlayerName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Exports all player profiles to a JSON file.
     */
    @SuppressWarnings("unchecked")
    public void exportPlayers(String filePath) throws IOException {
        JSONArray playersArray = new JSONArray();
        for (PlayerProfileManager player : players) {
            JSONObject playerObj = new JSONObject();
            playerObj.put("playerName", player.getPlayerName());
            JSONArray decksArray = new JSONArray();
            for (Deck deck : player.getDecks()) {
                JSONObject deckObj = new JSONObject();
                // Export units
                JSONArray unitsArray = new JSONArray();
                for (Card card : deck.getCards()) {
                    if (card instanceof Unit) {
                        Unit unit = (Unit) card;
                        JSONObject cardObject = new JSONObject();
                        cardObject.put("name", unit.getName());
                        cardObject.put("description", unit.getDescription());
                        cardObject.put("points", unit.getBasePoints());
                        cardObject.put("closeCombat", unit.canBeInCloseCombat());
                        cardObject.put("ranged", unit.canBeInRanged());
                        cardObject.put("siege", unit.canBeInSiege());
                        JSONArray modifiersArray = new JSONArray();
                        if (unit.haveModifier(new Agil())) modifiersArray.add("Agil");
                        if (unit.haveModifier(new TightBond())) modifiersArray.add("TightBond");
                        cardObject.put("modifiers", modifiersArray);
                        unitsArray.add(cardObject);
                    }
                }
                deckObj.put("units", unitsArray);
                // Export specials
                JSONArray specialsArray = new JSONArray();
                for (Card card : deck.getCards()) {
                    if (card instanceof Special) {
                        Special special = (Special) card;
                        JSONObject specialObj = new JSONObject();
                        specialObj.put("name", special.getName());
                        specialObj.put("description", special.getDescription());
                        specialObj.put("type", special.getClass().getSimpleName());
                        specialsArray.add(specialObj);
                    }
                }
                deckObj.put("specials", specialsArray);
                decksArray.add(deckObj);
            }
            playerObj.put("decks", decksArray);
            playerObj.put("activeDeckIndex", player.getActiveDeckIndex());
            playersArray.add(playerObj);
        }
        try (FileWriter file = new FileWriter(filePath)) {
            file.write(playersArray.toJSONString());
            file.flush();
        }
    }

    /**
     * Imports player profiles from a JSON file.
     */
    public void importPlayers(String filePath) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        players.clear();
        try (FileReader reader = new FileReader(filePath)) {
            JSONArray playersArray = (JSONArray) parser.parse(reader);
            for (Object obj : playersArray) {
                JSONObject playerObj = (JSONObject) obj;
                String playerName = (String) playerObj.get("playerName");
                PlayerProfileManager player = new PlayerProfileManager(playerName);
                JSONArray decksArray = (JSONArray) playerObj.get("decks");
                for (Object deckObjRaw : decksArray) {
                    JSONObject deckObj = (JSONObject) deckObjRaw;
                    // Use DeckImporter logic inline
                    List<Card> cards = new ArrayList<>();
                    // Import units
                    JSONArray unitsArray = (JSONArray) deckObj.get("units");
                    if (unitsArray != null) {
                        for (Object unitObj : unitsArray) {
                            JSONObject unitJson = (JSONObject) unitObj;
                            String name = (String) unitJson.get("name");
                            String description = (String) unitJson.get("description");
                            int points = ((Long) unitJson.get("points")).intValue();
                            boolean closeCombat = (Boolean) unitJson.get("closeCombat");
                            boolean ranged = (Boolean) unitJson.get("ranged");
                            boolean siege = (Boolean) unitJson.get("siege");
                            List<Modifier> modifiers = new ArrayList<>();
                            JSONArray modifiersArray = (JSONArray) unitJson.get("modifiers");
                            if (modifiersArray != null) {
                                for (Object modObj : modifiersArray) {
                                    String modifierName = (String) modObj;
                                    if ("Agil".equals(modifierName)) modifiers.add(new Agil());
                                    else if ("TightBond".equals(modifierName)) modifiers.add(new TightBond());
                                }
                            }
                            Unit unit = new Unit(name, description, points, closeCombat, ranged, siege, modifiers);
                            cards.add(unit);
                        }
                    }
                    // Import specials
                    JSONArray specialsArray = (JSONArray) deckObj.get("specials");
                    if (specialsArray != null) {
                        for (Object specialObj : specialsArray) {
                            JSONObject specialJson = (JSONObject) specialObj;
                            String name = (String) specialJson.get("name");
                            String description = (String) specialJson.get("description");
                            String type = (String) specialJson.get("type");
                            Card specialCard = null;
                            switch (type) {
                                case "BitingFrost":
                                    specialCard = new BitingFrost(name, description);
                                    break;
                                case "TorrentialRain":
                                    specialCard = new TorrentialRain(name, description);
                                    break;
                                case "ImpenetrableFog":
                                    specialCard = new ImpenetrableFog(name, description);
                                    break;
                                case "ClearWeather":
                                    specialCard = new ClearWeather(name, description);
                                    break;
                            }
                            if (specialCard != null) cards.add(specialCard);
                        }
                    }
                    Deck deck = new Deck(cards);
                    player.addDeck(deck);
                }
                int activeDeckIndex = ((Long) playerObj.get("activeDeckIndex")).intValue();
                player.setActiveDeck(activeDeckIndex);
                players.add(player);
            }
        }
    }
} 