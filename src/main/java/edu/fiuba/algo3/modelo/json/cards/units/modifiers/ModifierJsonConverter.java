package edu.fiuba.algo3.modelo.json.cards.units.modifiers;

import edu.fiuba.algo3.modelo.cards.units.modifiers.*;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ModifierJsonConverter {

    public List<Modifier> convertFromJsonArray(JSONArray modifiersArray) {
        List<Modifier> modifiers = new ArrayList<>();
        
        for (Object modifierObj : modifiersArray) {
            String modifierName = (String) modifierObj;
            Modifier modifier = createModifier(modifierName);
            modifiers.add(modifier);
        }
        
        return modifiers;
    }

    private Modifier createModifier(String modifierName) {
        switch (modifierName) {
            case "Agil":
                return new Agile();
            case "Legendaria":
                return new Hero();
            case "Medico":
                return new Medic();
            case "Carta Unida":
                return new TightBond();
            case "Espia":
                return new Spy(null, null, null);
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
