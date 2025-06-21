package edu.fiuba.algo3.modelo.json;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
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
    
    @Override
    public List<Deck> loadFromResource(String resourcePath) {
        JSONObject jsonGame = null;
        List<Deck> decks = null;

        try {
            jsonGame = parseJsonObjectFromResource(resourcePath);
        } catch (IOException | ParseException | ClassCastException | NullPointerException e) {
            throw new GwentFileInvalid("Error reading or parsing file");
        }

        try {
            decks = convertFromJsonObject(jsonGame);
        } catch (RuntimeException e) {
            throw new GwentFileInvalid("Error converting data");
        }

        return decks;
    }
    
    private List<Deck> convertFromJsonObject(JSONObject jsonGame) {
        List<Deck> decks = new ArrayList<>();
        
        JSONObject playerOneDeck = (JSONObject) jsonGame.get("mazo_jugador_uno");
        Deck deckOne = deckConverter.convertFromJsonObject(playerOneDeck);
        decks.add(deckOne);
        
        JSONObject playerTwoDeck = (JSONObject) jsonGame.get("mazo_jugador_dos");
        Deck deckTwo = deckConverter.convertFromJsonObject(playerTwoDeck);
        decks.add(deckTwo);
        
        return decks;
    }
}
