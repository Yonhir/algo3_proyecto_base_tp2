package edu.fiuba.algo3.models.json.cards.units.modifiers;

import edu.fiuba.algo3.models.cards.units.modifiers.*;
import edu.fiuba.algo3.models.cardcollections.Deck;
import edu.fiuba.algo3.models.cardcollections.Hand;
import edu.fiuba.algo3.models.cardcollections.DiscardPile;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ModifierJsonConverter {

    public List<Modifier> convertFromJsonArray(JSONArray modifiersArray, 
                                             Deck playerDeck, Hand playerHand, DiscardPile playerDiscardPile) {
        List<Modifier> modifiers = new ArrayList<>();
        
        for (Object modifierObj : modifiersArray) {
            String modifierName = (String) modifierObj;
            Modifier modifier = createModifier(modifierName, playerDeck, playerHand, playerDiscardPile);
            modifiers.add(modifier);
        }
        
        return modifiers;
    }

    private Modifier createModifier(String modifierName, 
                                   Deck playerDeck, Hand playerHand, DiscardPile playerDiscardPile) {
        switch (modifierName) {
            case "Agil":
                return new Agile();
            case "Legendaria":
                return new Hero();
            case "Medico":
                return new Medic(playerDiscardPile);
            case "Carta Unida":
                return new TightBond();
            case "Espia":
                return new Spy(playerDeck, playerHand);
            case "Morale Boost":
            case "Morale boost":
            case "Impulso de moral":
                return new MoraleBoostModifier();
            case "Quemar":
                return new ScorchModifier();
            default:
                throw new IllegalArgumentException("Invalid modifier: " + modifierName);
        }
    }
}
