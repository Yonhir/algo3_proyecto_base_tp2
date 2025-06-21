package edu.fiuba.algo3.modelo.json.cardcollections;

import edu.fiuba.algo3.modelo.cardcollections.Deck;
import edu.fiuba.algo3.modelo.cards.Card;
import edu.fiuba.algo3.modelo.cards.units.Unit;
import edu.fiuba.algo3.modelo.cards.specials.Special;
import edu.fiuba.algo3.modelo.json.cards.units.UnitJsonConverter;
import edu.fiuba.algo3.modelo.json.cards.specials.SpecialJsonConverter;
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
    
    public Deck convertFromJsonObject(JSONObject playerDeck) {
        Deck deck = new Deck();
        List<Card> allCards = new ArrayList<>();
        
        JSONArray unitsArray = (JSONArray) playerDeck.get("unidades");
        List<Unit> unitCards = unitConverter.convertFromJsonArray(unitsArray);
        allCards.addAll(unitCards);
        
        JSONArray specialsArray = (JSONArray) playerDeck.get("especiales");
        List<Special> specialCards = specialConverter.convertFromJsonArray(specialsArray);
        allCards.addAll(specialCards);
        
        deck.insertCards(allCards);
        
        return deck;
    }
} 