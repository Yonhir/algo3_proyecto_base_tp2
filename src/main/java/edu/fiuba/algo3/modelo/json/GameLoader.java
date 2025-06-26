package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cardcollections.Hand;
import edu.fiuba.algo3.modelo.cardcollections.DiscardPile;
import edu.fiuba.algo3.modelo.errors.GwentFileInvalid;
import edu.fiuba.algo3.modelo.json.cardcollections.DeckJsonConverter;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameLoader extends AbstractJsonLoader {
    
    private final DeckJsonConverter deckConverter;
    
    public GameLoader() {
        this.deckConverter = new DeckJsonConverter();
    }
    
    public List<Deck> loadFromResource(String resourcePath, 
                                     Deck player1Deck, Hand player1Hand, DiscardPile player1DiscardPile,
                                     Deck player2Deck, Hand player2Hand, DiscardPile player2DiscardPile) {
        JSONObject jsonGame = null;
        List<Deck> decks = null;

        try {
            jsonGame = parseJsonObjectFromResource(resourcePath);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            throw new GwentFileInvalid("Error reading or parsing file");
        }

        try {
            decks = convertFromJsonObject(jsonGame, 
                                        player1Deck, player1Hand, player1DiscardPile,
                                        player2Deck, player2Hand, player2DiscardPile);
        } catch (RuntimeException e) {
            throw new GwentFileInvalid("Error converting data");
        }

        return decks;
    }

    private List<Deck> convertFromJsonObject(JSONObject jsonGame, 
                                           Deck player1Deck, Hand player1Hand, DiscardPile player1DiscardPile,
                                           Deck player2Deck, Hand player2Hand, DiscardPile player2DiscardPile) {
        List<Deck> decks = new ArrayList<>();
        
        JSONObject playerOneDeck = (JSONObject) jsonGame.get("mazo_jugador_uno");
        Deck deckOne = deckConverter.convertFromJsonObject(playerOneDeck, player1Deck, player1Hand, player1DiscardPile);
        decks.add(deckOne);
        
        JSONObject playerTwoDeck = (JSONObject) jsonGame.get("mazo_jugador_dos");
        Deck deckTwo = deckConverter.convertFromJsonObject(playerTwoDeck, player2Deck, player2Hand, player2DiscardPile);
        decks.add(deckTwo);
        
        return decks;
    }
}
