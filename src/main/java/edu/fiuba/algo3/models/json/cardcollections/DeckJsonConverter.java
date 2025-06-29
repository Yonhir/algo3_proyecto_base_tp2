package edu.fiuba.algo3.models.json.cardcollections;

import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import edu.fiuba.algo3.models.cards.Card;
import edu.fiuba.algo3.models.cards.units.Unit;
import edu.fiuba.algo3.models.cards.specials.Special;
import edu.fiuba.algo3.models.json.cards.units.UnitJsonConverter;
import edu.fiuba.algo3.models.json.cards.specials.SpecialJsonConverter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeckJsonConverter {
    
    private final UnitJsonConverter unitConverter;
    private final SpecialJsonConverter specialConverter;
    
    public DeckJsonConverter() {
        this.unitConverter = new UnitJsonConverter();
        this.specialConverter = new SpecialJsonConverter();
    }
    
    public Deck convertFromJsonObject(JSONObject jsonDeck, Deck playerDeck,
                                    Hand playerHand, DiscardPile playerDiscardPile) {
        // Use passed deck if not null, create new one if null
        Deck targetDeck = (playerDeck != null) ? playerDeck : new Deck();
        List<Card> allCards = new ArrayList<>();
        
        JSONArray unitsArray = (JSONArray) jsonDeck.get("unidades");
        List<Unit> unitCards = unitConverter.convertFromJsonArray(unitsArray, targetDeck, playerHand, playerDiscardPile);
        allCards.addAll(unitCards);
        
        JSONArray specialsArray = (JSONArray) jsonDeck.get("especiales");
        List<Special> specialCards = specialConverter.convertFromJsonArray(specialsArray);
        allCards.addAll(specialCards);
        
        targetDeck.insertCards(allCards);
        
        return targetDeck;
    }
} 