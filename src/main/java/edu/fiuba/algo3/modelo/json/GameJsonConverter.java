package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameJsonConverter {
    
    private final UnitJsonConverter unitConverter;
    private final SpecialJsonConverter specialConverter;
    
    public GameJsonConverter() {
        this.unitConverter = new UnitJsonConverter();
        this.specialConverter = new SpecialJsonConverter();
    }
    
    public List<Deck> convertFromJsonObject(JSONObject jsonGame) {
        List<Deck> decks = new ArrayList<>();
        
        // Convert player one deck
        JSONObject playerOneDeck = (JSONObject) jsonGame.get("mazo_jugador_uno");
        Deck deckOne = convertPlayerDeck(playerOneDeck);
        decks.add(deckOne);
        
        // Convert player two deck
        JSONObject playerTwoDeck = (JSONObject) jsonGame.get("mazo_jugador_dos");
        Deck deckTwo = convertPlayerDeck(playerTwoDeck);
        decks.add(deckTwo);
        
        return decks;
    }
    
    private Deck convertPlayerDeck(JSONObject playerDeck) {
        Deck deck = new Deck();
        List<Card> allCards = new ArrayList<>();
        
        // Convert units
        JSONArray unitsArray = (JSONArray) playerDeck.get("unidades");
        List<Unit> units = unitConverter.convertFromJsonArray(unitsArray);
        allCards.addAll(units);
        
        // Convert specials
        JSONArray specialsArray = (JSONArray) playerDeck.get("especiales");
        List<Special> specials = specialConverter.convertFromJsonArray(specialsArray);
        allCards.addAll(specials);
        
        // Add all cards to the deck
        deck.insertCards(allCards);
        
        return deck;
    }
}
